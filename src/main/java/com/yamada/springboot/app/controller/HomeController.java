package com.yamada.springboot.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yamada.springboot.domain.model.User;
import com.yamada.springboot.domain.model.UserEditForm;
import com.yamada.springboot.domain.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String getHome(Model model) {
		model.addAttribute("contents", "login/home :: home_contents");

		return "redirect:/group";
	}

	@GetMapping("/mypage/delete")
	public String postUserDelete(@ModelAttribute UserEditForm form, Model model, Principal principal) {
		String mail = principal.getName();

		System.out.println("削除ボタンの処理");

		boolean result = userService.deleteOne(mail);
		if(result == true) {
			model.addAttribute("result", "削除成功");
		}else {
			model.addAttribute("result", "削除失敗");
		}

		return "redirect:/login";
	}
	
	@GetMapping("/admin/user")
	public String getAdmin(Model model) {
		model.addAttribute("contents", "login/admin :: admin_contents");

		List<User> userList = userService.selectMany();
		
		System.out.println(userList);

		model.addAttribute("userList", userList);

		int count = userService.count();
		model.addAttribute("userListCount", count);

		return "login/homeLayout";
	}
	
	@GetMapping("/admin/user/{mail}")
	public String getUserDetail(@ModelAttribute UserEditForm form, Model model, @PathVariable("mail") String mail) {
		model.addAttribute("contents", "login/userDetail :: userDetail_contents");
		if(mail != null) {
			User user = userService.selectOne(mail);
			
			form.setUserName(user.getUserName());
			form.setMail(user.getMail());
			form.setBirthday(user.getBirthday());

			model.addAttribute("signupForm", form);
		}

		return "login/homeLayout";
	}
	
	@GetMapping("/mypage")
	public String getMyPage(@ModelAttribute UserEditForm form, Model model, Principal principal) {
		model.addAttribute("contents", "login/myPage :: myPage_contents");
		
		String mail = principal.getName();
		System.out.println(mail);
		User user = userService.selectOne(mail);
		System.out.println(user);
		model.addAttribute("user", user);

		return "login/homeLayout";
	}
	
	@GetMapping("/mypage/edit")
	public String getMypageEdit(@ModelAttribute UserEditForm form, Principal principal, Model model) {
		String mail = principal.getName();
		System.out.println(mail);
		model.addAttribute("contents", "login/myPageEdit :: myPageEdit_contents");
		if(mail != null) {
			User user = userService.selectOne(mail);
			form.setUserName(user.getUserName());
			form.setMail(user.getMail());
			form.setBirthday(user.getBirthday());

			model.addAttribute("userEditForm", form);
		}

		return "login/homeLayout";
	}
	
	@PostMapping("/mypage/edit")
	public String postMypageEdit(@ModelAttribute @Validated UserEditForm form, BindingResult bindingResult, Principal principal, Model model) {
		if(bindingResult.hasErrors()) {
			return getMypageEdit(form, principal, model);
		}
		
		String mail = principal.getName();
		Integer userId = userService.selectOneId(mail);
		String password = userService.selectOnePassword(mail);
		
		User user = new User();
		user.setUserId(userId);
		user.setUserName(form.getUserName());
		user.setMail(form.getMail());
		if(form.getPassword() == "") {
			System.out.println("Nullやで！");
			user.setPassword(password);
		} else {
			System.out.println("Nullやないで！！");
			System.out.println(form.getPassword());
			user.setPassword(passwordEncoder.encode(form.getPassword()));
		}
		user.setBirthday(form.getBirthday());
		
		boolean result = userService.updateOne(user);
		if(result == true) {
			System.out.println("update成功");
		} else {
			System.out.println("update失敗");
		}
		
		return "redirect:/logout";
		
	}

}
