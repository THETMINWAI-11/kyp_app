package com.khayayphyu.controller.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.khayayphyu.entity.security.Menu;
import com.khayayphyu.service.security.MenuService;

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

	private static List<Menu> allMenuList;
	
	//login is disabled.
	@Around("within(com.khayayphyu.controller.*)")
	public Object requestInterceptor(ProceedingJoinPoint pjp) throws Throwable {
		Model model = null;
		Object[] arguments = pjp.getArgs();
		for (Object argument : arguments) {
			if (argument instanceof Model) {
				model = (Model) argument;
			}
		}
		
		Object obj = continueNormalProcedure(pjp);
		Method method =  ((MethodSignature) pjp.getSignature()).getMethod();
		ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
		if(responseBody != null)
			return responseBody;
		
		String page = (String) obj;
		if(model != null ) {
			model.addAttribute("page", page);
		}
		
		return "index";
	}


	@SuppressWarnings("unused")
	private HttpServletRequest retrieveHttpServletFromLocalThread() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	@SuppressWarnings("unused")
	private String getRedirectPath(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getParameter("redirect");
	}

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
	private Object continueWithRedirect(ProceedingJoinPoint pjp, String redirectPath) throws Throwable {
		continueNormalProcedure(pjp);
		return "redirect:" + redirectPath;
	}

	private List<Menu> getMenuList() {

		if (CollectionUtils.isEmpty(allMenuList)) {
			allMenuList = menuService.getAll();
		}
		List<Menu> parentMenuList = getParentMenu(allMenuList);
		List<Menu> menuList = addChildMenu(getAllowedMenu(), parentMenuList);
		return applyMenuSuffix(menuList);
	}

	private List<Menu> getParentMenu(List<Menu> allMenuList) {
		return allMenuList.stream().filter(menu -> menu.getIsParent() == 1).collect(Collectors.toList());
	}

	private List<Menu> getAllowedMenu() {
		return allMenuList;
	}

	private List<Menu> addChildMenu(List<Menu> menuList, List<Menu> parentMenu) {
		Map<Long, Menu> map = parentMenu.stream()
				.collect(Collectors.toMap(menu -> menu.getId(), Function.identity()));
		List<Menu> allowedMenuList = new ArrayList<>();
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

	private List<Menu> applyMenuSuffix(List<Menu> menuList) {
		return menuList;
	}
}
