package com.khayayphyu.controller.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khayayphyu.dao.user.UserDao;
import com.khayayphyu.dto.security.MenuDto;
import com.khayayphyu.entity.user.User;
import com.khayayphyu.service.security.MenuService;
import com.khayayphyu.utils.CommonUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Configuration
@Aspect
public class Interceptor {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserDao userDao;

	private static List<MenuDto> allMenuList;

	@Around(" (within(com.sm.logistic.controller.*) || within(com.sm.logistic.controller.dashboard.*)) && !(within (com.sm.logistic.controller.test.*))")
	public Object requestInterceptor(ProceedingJoinPoint pjp) throws Throwable {
		String loginId = (String) httpSession.getAttribute("loginId");
		if (loginId == null)
			return "redirect:/login";

		HttpServletRequest request = retrieveHttpServletFromLocalThread();

		return isNeedToRedirect(request) ? continueWithRedirect(pjp, getRedirectPath(request))
				: continueNormalProcedure(pjp);
	}

	private HttpServletRequest retrieveHttpServletFromLocalThread() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	private String getRedirectPath(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getParameter("redirect");
	}

	private boolean isNeedToRedirect(HttpServletRequest httpServletRequest) {
		String redirectPath = httpServletRequest.getParameter("redirect");
		return StringUtils.isNotBlank(redirectPath);
	}

	private Object continueNormalProcedure(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		ResponseBody responseBody = signature.getMethod().getAnnotation(ResponseBody.class);
		if (responseBody != null)
			return pjp.proceed();

		InitBinder initBinder = signature.getMethod().getAnnotation(InitBinder.class);
		if (initBinder != null)
			return pjp.proceed();

		Object[] arguments = pjp.getArgs();
		for (Object argument : arguments) {
			if (argument instanceof Model) {
				Model model = (Model) argument;
				model.addAttribute("menuList", objectMapper.writeValueAsString(getMenuList()));
				model.addAttribute("server_name", getLocalName());
				return pjp.proceed();
			}
		}

		throw new IllegalAccessError("Model need to add to parameter. >> " + signature.getName());
	}

	private Object continueWithRedirect(ProceedingJoinPoint pjp, String redirectPath) throws Throwable {
		continueNormalProcedure(pjp);
		return "redirect:" + redirectPath;
	}

	private List<MenuDto> getMenuList() {

		if (CollectionUtils.isEmpty(allMenuList)) {
			allMenuList = menuService.getAll();
		}

		List<MenuDto> parentMenuList = getParentMenu(allMenuList);
		List<MenuDto> menuList = addChildMenu(getAllowedMenu(), parentMenuList);
		return applyMenuSuffix(menuList);
	}

	private List<MenuDto> getParentMenu(List<MenuDto> allMenuList) {
		return allMenuList.stream().filter(menu -> menu.getIsParent() == 1).collect(Collectors.toList());
	}

	private List<MenuDto> getAllowedMenu() {
		User activeLoginuser = getActiveLoginUser();
		if (activeLoginuser.isAdministrator())
			return allMenuList;

		return CommonUtils.mapToList(userProfile.getMenuList(), MenuDto::create);
	}

	private List<MenuDto> addChildMenu(List<MenuDto> menuList, List<MenuDto> parentMenu) {
		Map<Long, MenuDto> map = parentMenu.stream()
				.collect(Collectors.toMap(menu -> menu.getId(), Function.identity()));
		List<MenuDto> allowedMenuList = new ArrayList<>();
		menuList.forEach(menu -> {
			if (menu.isParent()) {
				allowedMenuList.add(menu);
				return;
			}
			menu.setParentMenuId(map.get(menu.getParentMenuId()).getId());
			allowedMenuList.add(menu);
		});
		return allowedMenuList;
	}

	private String getLocalName() {
		return getHttpServletRequest().getContextPath();
	}

	private HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	private User getActiveLoginUser() {
		String loginId = (String) httpSession.getAttribute(User.sessionKey);
		Optional<User> result = userDao.getByUserName(loginId);
		return result.isPresent() ? result.get() : null;
	}

	private List<MenuDto> applyMenuSuffix(List<MenuDto> menuList) {
		return menuList.stream().map(menu -> {
			if (menu.getPermission().equals("advance-expense-expense-setup")) {
				menu = MenuDto.create(menu.toEntity());
				menu.setName(menu.getName() + " <span class=badge>"
						+ deliveryOrderService.countDeliveryOrderForExpense() + "</span>");
				return menu;
			}
			return menu;
		}).collect(Collectors.toList());
	}
}
