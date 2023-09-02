package com.khayayphyu.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khayayphyu.controller.AbstractController;
import com.khayayphyu.controller.util.RequiredPermission;
import com.khayayphyu.entity.user.User;
import com.khayayphyu.service.search.UserSearchRequest;
import com.khayayphyu.service.user.UserService;
import com.khayayphyu.utils.type.UserRole;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	@GetMapping("/setup")
	@RequiredPermission("user-setup")
	public String getUserSetup(@RequestParam(value = "id", defaultValue = "0", required = false) Long id, Model model) {
		if (id.longValue() > 0) {
			User userDto = userService.get(id);
			model.addAttribute("userDto", userDto);
			model.addAttribute("userRole", UserRole.values());
			createPage("User Edit", model);
		} else {
			User userDto = new User();
			model.addAttribute("userDto", userDto);
			model.addAttribute("userRole", UserRole.values());
		}
		model.addAttribute("userList", userService.getAll());
		return "user_setup";
	}

	@PostMapping("/setup")
	public String postUserSetup(@ModelAttribute("user") User userDto, Model model) {
		userService.save(userDto);
		return getUserSetup(userDto.getId(), model);
	}

	@GetMapping("/search")
	@RequiredPermission("product-search")
	public String getUserSearch(Model model, HttpServletRequest searchRequest) {
		createPage("UserSearch", model);
		model.addAttribute("searchRequest", new UserSearchRequest());
		model.addAttribute("userList", userService.getAll());
		return "user_search";
	}

	@PostMapping("/search")
	public String postUserSearch(@ModelAttribute("userDto") UserSearchRequest userSearchRequest, Model model) {
		model.addAttribute("searchRequest", userSearchRequest);
		if(!userSearchRequest.isValid()) {
			return "user_search";
		}
		model.addAttribute("userList", userService.search(userSearchRequest));
		return "user_search";
	}

}
