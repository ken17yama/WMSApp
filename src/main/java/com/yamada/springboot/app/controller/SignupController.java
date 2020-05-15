package com.yamada.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.yamada.springboot.domain.model.SignupForm;
import com.yamada.springboot.domain.model.User;
import com.yamada.springboot.domain.service.UserService;

@Controller
public class SignupController {

	@Autowired
	private UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form, Model model) {
		return "login/signup";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return getSignup(form, model);
		}
		System.out.println(form);

		User user = new User();
		user.setUserName(form.getUserName());
		user.setMail(form.getMail());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		user.setBirthday(form.getBirthday());
		user.setRole("ROLE_GENERAL");

		boolean result = userService.insertOne(user);
		if(result == true) {
			System.out.println("inset成功");
		}else {
			System.out.println("inset失敗");
		}

		return "redirect:/login";
	}

	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		model.addAttribute("error", "内部サーバーエラー（DB） : ExceptionHandler");
		model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました。");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		model.addAttribute("error", "内部サーバーエラー : ExceptionHandler");
		model.addAttribute("message", "SignupControllerでExceptionが発生しました。");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

}
