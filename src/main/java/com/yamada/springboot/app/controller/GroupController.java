package com.yamada.springboot.app.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yamada.springboot.domain.model.Group;
import com.yamada.springboot.domain.model.GroupForm;
import com.yamada.springboot.domain.model.User;
import com.yamada.springboot.domain.service.GroupService;
import com.yamada.springboot.domain.service.UserService;

@Controller
public class GroupController {

	@Autowired
	GroupService groupService;

	@Autowired
	UserService userService;

	@GetMapping("/admin/group")
	public String getAdminGroupList(Model model) {
		model.addAttribute("contents", "login/groupList :: groupList_contents");

		List<Group> groupList = groupService.selectMany();

		System.out.println(groupList);

		model.addAttribute("groupList", groupList);

		return "login/homeLayout";
	}

	@GetMapping("/group")
	public String getGroupList(Model model, Principal principal) {
		model.addAttribute("contents", "login/groupList :: groupList_contents");

		//	ユーザーの情報をusersテーブルから取り出し、対象のplaceがあるかを確認
		String mail = principal.getName();
		User user = userService.selectOne(mail);
		if(user.getMyPlace() != null) {
			List<String> myPlaceStrList = new ArrayList<>(Arrays.asList(user.getMyPlace().split(",")));	
			List<Integer> myPlaceIntList = myPlaceStrList.stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
			List<Group> groupList = groupService.selectThat(myPlaceIntList);

			model.addAttribute("groupList", groupList);
		}

		return "login/homeLayout";
	}

	@GetMapping("/group/add")
	public String getGroupAdd(@ModelAttribute GroupForm form, Model model, Principal principal) {

		model.addAttribute("contents", "login/groupAdd :: groupAdd_contents");
		
		List<String> list = Arrays.asList(principal.getName());
		form.setGroupList(list);
		
		model.addAttribute("mail", principal.getName());

		return "login/homeLayout";

	}

	@PostMapping("/group/add")
	public String PostAddStock(@ModelAttribute GroupForm form, Model model, Principal principal) {

		System.out.println("追加ボタンの処理");

		Group group = new Group();
		group.setGroupName(form.getGroupName());

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		group.setCreatedAt(timestamp);
		group.setUpdatedAt(timestamp);

		try {
			Integer place = groupService.insertOneReturn(group);

			System.out.println("追加成功");

			List<String> mailList = form.getGroupList();
			

			for(String mail : mailList){
				User user = userService.selectOne(mail);
				
				System.out.println(user);
				
				List<String> myPlaceStrList = new ArrayList<>();
				List<Integer> myPlaceIntList = new ArrayList<>();

				if(user.getMyPlace() != null) {
					myPlaceStrList = Arrays.asList(user.getMyPlace().split(","));
					myPlaceIntList = myPlaceStrList.stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
				}
				
				myPlaceIntList.add(place);
				myPlaceStrList = myPlaceIntList.stream().map(e -> String.valueOf(e)).collect(Collectors.toList());

				String myPlaceStr = String.join(",", myPlaceStrList);

				boolean result = userService.editPlace(mail, myPlaceStr);
				if(result == true) {
					System.out.println("更新成功");
				}else {
					System.out.println("更新失敗");
				}
			}
		}catch(DataAccessException e) {
			System.out.println("追加失敗（トランザクションテスト）");
		}

		return "redirect:/group";
	}

	@GetMapping("/group/{place:.+}/stock/delete")
	public String getGroupList(Model model, @PathVariable("place") Integer place, Principal principal) {
		//	ユーザーの情報をusersテーブルから取り出し、対象のplaceがあるかを確認
		String mail = principal.getName();
		User user = userService.selectOne(mail);
		List<String> myPlaceStrList = new ArrayList<>(Arrays.asList(user.getMyPlace().split(",")));	
		List<Integer> myPlaceIntList = myPlaceStrList.stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
		//		myPlaceの配列内から「place」に該当するIDがあるか確認
		if(myPlaceIntList.contains(place)) {
			System.out.println("OK");
		} else {
			System.out.println("NG");
			return "redirect:/";
		}

		try {
			boolean result = groupService.deleteOne(place);
			if(result == true) {
				System.out.println("追加成功");
			}else {
				System.out.println("追加失敗");
			}
		}catch(DataAccessException e) {
			System.out.println("追加失敗（トランザクションテスト）");
		}


		return "redirect:/group";
	}

}
