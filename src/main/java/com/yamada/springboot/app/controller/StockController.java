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
import com.yamada.springboot.domain.model.Stock;
import com.yamada.springboot.domain.model.StockForm;
import com.yamada.springboot.domain.model.User;
import com.yamada.springboot.domain.service.GroupService;
import com.yamada.springboot.domain.service.StockService;
import com.yamada.springboot.domain.service.UserService;


@Controller
public class StockController {

	@Autowired
	StockService stockService;

	@Autowired
	GroupService groupService;

	@Autowired
	UserService userService;

	@GetMapping("/admin/group/{place:.+}/stock")
	public String getAdminStockMany(Model model, @PathVariable("place") Integer place) {
		model.addAttribute("contents", "login/stockList :: stockList_contents");

		Group group = groupService.selectOne(place);
		model.addAttribute("group", group);

		List<Stock> stockList = stockService.eachPlace(place);

		model.addAttribute("stockList", stockList);

		return "login/homeLayout";
	}

	@GetMapping("/group/{place:.+}/stock")
	public String getStockMany(Model model, @PathVariable("place") Integer place, Principal principal) {
		//	ユーザーの情報をusersテーブルから取り出し、対象のplaceがあるかを確認
		String mail = principal.getName();

		User user = userService.selectOne(mail);
		List<String> myPlaceStrList = new ArrayList<>(Arrays.asList(user.getMyPlace().split(",")));	
		List<Integer> myPlaceIntList = myPlaceStrList.stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());

		//	myPlaceの配列内から「place」に該当するIDがあるか確認
		if(myPlaceIntList.contains(place)) {
			System.out.println("OK");
		} else {
			System.out.println("NG");
			return "redirect:/";
		}

		model.addAttribute("contents", "login/stockList :: stockList_contents");

		Group group = groupService.selectOne(place);
		model.addAttribute("group", group);

		List<Stock> stockList = stockService.eachPlace(place);
		model.addAttribute("stockList", stockList);

		return "login/homeLayout";
	}

	@GetMapping("/group/{place:.+}/stock//add")
	public String GetAddStock(@ModelAttribute StockForm form, Model model, @PathVariable("place") Integer place) {
		model.addAttribute("contents", "login/stockAdd :: stockAdd_contents");

		return "login/homeLayout";
	}

	@PostMapping("/group/{place:.+}/stock/add")
	public String PostAddStock(@ModelAttribute StockForm form, Model model, @PathVariable("place") Integer place, Principal principal) {
		// ユーザーの情報をusersテーブルから取り出し、対象のplaceがあるかを確認
		String mail = principal.getName();

		User user = userService.selectOne(mail);
		List<String> myPlaceStrList = new ArrayList<>(Arrays.asList(user.getMyPlace().split(",")));	
		List<Integer> myPlaceIntList = myPlaceStrList.stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());

		//	myPlaceの配列内から「place」に該当するIDがあるか確認
		if(myPlaceIntList.contains(place)) {
			System.out.println("OK");
		} else {
			System.out.println("NG");
			return "redirect:/";
		}

		Stock stock = new Stock();
		stock.setStockName(form.getStockName());
		stock.setQuantity(form.getQuantity());
		stock.setUnit(form.getUnit());
		stock.setPlace(place);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		stock.setCreatedAt(timestamp);
		stock.setUpdatedAt(timestamp);

		try {
			boolean result = stockService.insertOne(stock);
			if(result == true) {
				System.out.println("追加成功");
			}else {
				System.out.println("追加失敗");
			}
		}catch(DataAccessException e) {
			System.out.println("追加失敗（トランザクションテスト）");
		}

		return "redirect:/group/" + place + "/stock/";
	}

	@GetMapping("/group/{place:.+}/stock/edit/{stockId:.+}")
	public String getEditStock(@ModelAttribute StockForm form, Model model, @PathVariable("place") Integer place, @PathVariable("stockId") Integer stockId, Principal principal) {
		//	ユーザーの情報をusersテーブルから取り出し、対象のplaceがあるかを確認
		String mail = principal.getName();

		User user = userService.selectOne(mail);
		List<String> myPlaceStrList = new ArrayList<>(Arrays.asList(user.getMyPlace().split(",")));	
		List<Integer> myPlaceIntList = myPlaceStrList.stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());

		//	myPlaceの配列内から「place」に該当するIDがあるか確認
		if(myPlaceIntList.contains(place)) {
			System.out.println("OK");
		} else {
			System.out.println("NG");
			return "redirect:/";
		}

		model.addAttribute("contents", "login/stockEdit :: stockEdit_contents");

		if(place != null && stockId != null) {
			Stock stock = stockService.selectOne(stockId);

			form.setStockName(stock.getStockName());
			form.setQuantity(stock.getQuantity());
			form.setUnit(stock.getUnit());

			model.addAttribute("stockForm", form);
		}

		Group group = groupService.selectOne(place);
		model.addAttribute("group", group);

		return "login/homeLayout";
	}

	@PostMapping("/group/{place:.+}/stock/edit/{stockId:.+}")
	public String postEditStock(@ModelAttribute StockForm form, Model model, Principal principal, @PathVariable("place") Integer place, @PathVariable("stockId") Integer stockId) {
		//	ユーザーの情報をusersテーブルから取り出し、対象のplaceがあるかを確認
		String mail = principal.getName();

		User user = userService.selectOne(mail);
		List<String> myPlaceStrList = new ArrayList<>(Arrays.asList(user.getMyPlace().split(",")));	
		List<Integer> myPlaceIntList = myPlaceStrList.stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());

		//	myPlaceの配列内から「place」に該当するIDがあるか確認
		if(myPlaceIntList.contains(place)) {
			System.out.println("OK");
		} else {
			System.out.println("NG");
			return "redirect:/";
		}

		Stock stock = new Stock();
		stock.setStockId(stockId);
		stock.setStockName(form.getStockName());
		stock.setQuantity(form.getQuantity());
		stock.setUnit(form.getUnit());

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		stock.setUpdatedAt(timestamp);

		try {
			boolean result = stockService.updateOne(stock);
			if(result == true) {
				System.out.println("更新成功");
			}else {
				System.out.println("更新失敗");
			}
		}catch(DataAccessException e) {
			System.out.println("更新失敗（トランザクションテスト）");
		}
		
		return "redirect:/group/" + place + "/stock/";
	}

	@GetMapping("/group/{place:.+}/stock/delete/{stockId:.+}")
	public String getDeleteStock(Model model, @PathVariable("place") Integer place, @PathVariable("stockId") Integer stockId, Principal principal) {
		//	ユーザーの情報をusersテーブルから取り出し、対象のplaceがあるかを確認
		String mail = principal.getName();

		User user = userService.selectOne(mail);
		List<String> myPlaceStrList = new ArrayList<>(Arrays.asList(user.getMyPlace().split(",")));	
		List<Integer> myPlaceIntList = myPlaceStrList.stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());

		//	myPlaceの配列内から「place」に該当するIDがあるか確認
		if(myPlaceIntList.contains(place)) {
			System.out.println("OK");
		} else {
			System.out.println("NG");
			return "redirect:/";
		}

		model.addAttribute("contents", "login/stockDelete :: stockDelete_contents");

		Group group = groupService.selectOne(place);
		model.addAttribute("group", group);
		
		Stock stock = stockService.selectOne(stockId);
		model.addAttribute("stock", stock);

		return "login/homeLayout";
	}

	@PostMapping("/group/{place:.+}/stock/delete/{stockId:.+}")
	public String postDeleteStock(Model model, Principal principal, @PathVariable("place") Integer place, @PathVariable("stockId") Integer stockId) {
		//	ユーザーの情報をusersテーブルから取り出し、対象のplaceがあるかを確認
		String mail = principal.getName();

		User user = userService.selectOne(mail);
		List<String> myPlaceStrList = new ArrayList<>(Arrays.asList(user.getMyPlace().split(",")));	
		List<Integer> myPlaceIntList = myPlaceStrList.stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());

		//	myPlaceの配列内から「place」に該当するIDがあるか確認
		if(myPlaceIntList.contains(place)) {
			System.out.println("OK");
		} else {
			System.out.println("NG");
			return "redirect:/";
		}
		
		try {
			boolean result = stockService.deleteOne(stockId);
			if(result == true) {
				System.out.println("更新成功");
			}else {
				System.out.println("更新失敗");
			}
		}catch(DataAccessException e) {
			System.out.println("更新失敗（トランザクションテスト）");
		}

		return "redirect:/group/" + place + "/stock/";
	}

}
