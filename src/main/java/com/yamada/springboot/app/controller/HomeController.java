package com.yamada.springboot.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.yamada.springboot.domain.model.SignupForm;
import com.yamada.springboot.domain.model.User;
import com.yamada.springboot.domain.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("contents", "login/home :: home_contents");

		return "login/homeLayout";
	}

//	@GetMapping("/userList")
//	public String getUserList(Model model) {
//		model.addAttribute("contents", "login/userList :: userList_contents");
//
//		List<User> userList = userService.selectMany();
//		
//		System.out.println(userList);
//
//		model.addAttribute("userList", userList);
//
//		int count = userService.count();
//		model.addAttribute("userListCount", count);
//
//		return "login/homeLayout";
//	}

//	@GetMapping("/userDetail/{id:.+}")
//	public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") Integer userId) {
//		System.out.println("userId = " + userId);
//		model.addAttribute("contents", "login/userDetail :: userDetail_contents");
//		if(userId != null) {
//			User user = userService.selectOne(userId);
//			
//			form.setUserName(user.getUserName());
//			form.setMail(user.getMail());
//			form.setBirthday(user.getBirthday());
//
//			model.addAttribute("signupForm", form);
//		}
//
//		return "login/homeLayout";
//	}

//	@PostMapping(value = "/userDetail", params = "update")
//	public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
//
//		System.out.println("更新ボタンの処理");
//
//		User user = new User();
//		user.setUserId(form.getUserId());
//		user.setPassword(form.getPassword());
//		user.setUserName(form.getUserName());
//		user.setBirthday(form.getBirthday());
//		user.setAge(form.getAge());
//		user.setMarriage(form.isMarriage());
//
//		try {
//
//			boolean result = userService.updateOne(user);
//			if(result == true) {
//				model.addAttribute("result", "更新成功");
//			}else {
//				model.addAttribute("result", "更新失敗");
//			}
//		}catch(DataAccessException e) {
//			model.addAttribute("result", "更新失敗（トランザクションテスト）");
//		}
//
//		return getUserList(model);
//	}

//	@PostMapping(value = "/userDetail", params = "delete")
//	public String postUserDetailDelete(@ModelAttribute SignupForm form, Model model) {
//		System.out.println("削除ボタンの処理");
//
//		boolean result = userService.deleteOne(form.getUserId());
//		if(result == true) {
//			model.addAttribute("result", "削除成功");
//		}else {
//			model.addAttribute("result", "削除失敗");
//		}
//
//		return getUserList(model);
//	}

//	@GetMapping("/logout")
//	public String postLogout() {
//		return "redirect:/login";
//	}

//	@GetMapping("/userList/csv")
//	public ResponseEntity<byte[]> getUserListCsv(Model model) {
//
//		userService.userCsvOut();
//
//		byte[] bytes = null;
//
//		try {
//			bytes = userService.getFile("sample.csv");
//
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//
//		HttpHeaders header = new HttpHeaders();
//		header.add("Content-Type", "text/csv; charset=UTF-8");
//		header.setContentDispositionFormData("filename", "sample.csv");
//
//		return new ResponseEntity<>(bytes, header, HttpStatus.OK);
//	}
	
	@GetMapping("/admin/users")
	public String getAdmin(Model model) {
		model.addAttribute("contents", "login/admin :: admin_contents");

		List<User> userList = userService.selectMany();
		
		System.out.println(userList);

		model.addAttribute("userList", userList);

		int count = userService.count();
		model.addAttribute("userListCount", count);

		return "login/homeLayout";
	}
	
	@GetMapping("/admin/users/{mail}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("mail") String mail) {
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
	
	@GetMapping("/myPage")
	public String getMyPage(@ModelAttribute SignupForm form, Model model, Principal principal) {
		String mail = principal.getName();
		System.out.println(mail);
		model.addAttribute("contents", "login/myPage :: myPage_contents");
		if(mail != null) {
			User user = userService.selectOne(mail);
			
			form.setUserName(user.getUserName());
			form.setMail(user.getMail());
			form.setBirthday(user.getBirthday());

			model.addAttribute("signupForm", form);
		}

		return "login/homeLayout";
	}

}
