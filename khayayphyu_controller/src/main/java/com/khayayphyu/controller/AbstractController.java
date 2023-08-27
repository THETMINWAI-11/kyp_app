package com.khayayphyu.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.khayayphyu.utils.Constants;

import jakarta.servlet.http.HttpServletResponse;


@Component
public abstract class AbstractController {

	@Autowired
	private Environment environment;

	protected void createPage(String pageName, Model model) {
		model.addAttribute("pageName", pageName);
		model.addAttribute("title", "Shwe Me logistic");
	}

//	public LoginUserDto getActiveLoginUser() {
//		String loginId = (String) httpSession.getAttribute(LoginController.sessionKey);
//		Optional<LoginUserDto> result = loginUserService.getLoginUserByName(loginId);
//		return result.isPresent() ? result.get() : null;
//	}

//	protected boolean isEditable(TruckOrderDto truckOrderDto) {
//		return truckOrderDto.getCreatedUserDto().getUsername().equals(httpSession.getAttribute(LoginController.sessionKey));
//	}

//	protected void setupOrderSearchPage(Model model, List<TruckOrderDto> dataSupplier) {
//		Function<TruckOrderDto, Boolean> test = this::isEditable;
//		model.addAttribute("authorize", test);
//		model.addAttribute("orderList", dataSupplier);
//		model.addAttribute("truckGroupList", truckGroupService.getAll());
//		model.addAttribute("searchRequest", new TruckOrderService.SearchRequest());
//	}

	protected void errorDialog(Model model, String message) {
		model.addAttribute("frmMessage", String.format("error|%s", message));
	}

	protected void successDialog(Model model, String message) {
		model.addAttribute(Constants.FORM_MSG_KEY, Constants.MSG_PREFIX_SUCCESS + message);
	}
	
//	protected void writeWorkbook(Workbook workbook, String name, HttpServletResponse response) {
//		renameHttpResponseName(name, response);
//		writeToHttpResponse(workbook, response);
//	}

	public void renameHttpResponseName(String name, HttpServletResponse response) {
		String str = String.format("attachment; filename=\"%s\"", name);
		response.setHeader("Content-Disposition", str);
	}

	// to bypass aop check :)
//	@ResponseBody
//	public void writeToHttpResponse(Workbook workbook, HttpServletResponse response) {
//		try {
//			workbook.write(response.getOutputStream());
//			workbook.close();
//		} catch (Exception e) {
//			logger.error("Can't write Excel to Response ", e);
//		}
//	}

//	public String getServerBasePath() {
//		return servletContext.getRealPath("/");
//	}

//	protected String getLocalName() {
//		return getHttpServletRequest().getContextPath();
//	}

//	private HttpServletRequest getHttpServletRequest() {
//		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//	}

	public String getMessage(String key) {
		if (StringUtils.isBlank(key))
			return "";
		String message = environment.getProperty(key);
		return StringUtils.isBlank(message) ? key : message;
	}
	
//	protected Workbook createWorkbook(String sheetName, Consumer<Sheet> apply) {
//		Workbook workbook = new XSSFWorkbook();
//		Sheet sheet = workbook.createSheet(sheetName);
//		apply.accept(sheet);
//		return workbook;
//	}
	
}
