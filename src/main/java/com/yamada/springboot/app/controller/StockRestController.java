package com.yamada.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yamada.springboot.domain.model.Stock;
import com.yamada.springboot.domain.service.StockRestService;

@RestController
public class StockRestController {
	
	@Autowired
	@Qualifier("StockRestServiceMybatisImpl")
	StockRestService service;
	
	@GetMapping("/rest/stock")
	public List<Stock> getStockMany(){
		return service.selectMany();
	}
	
	@GetMapping("/rest/stock/place/{place:.+}")
	public List<Stock> getEachPlace(@PathVariable("place") String place) {
		return service.eachPlace(place);
	}
	
	@GetMapping("/rest/stock/id/{stockId:.+}")
	public Stock getStockOne(@PathVariable("stockId") Integer stockId) {
		return service.selectOne(stockId);
	}
	
	@PostMapping("/rest/stock")
	public String postStockOne(@RequestBody Stock stock) {
		boolean result = service.insertOne(stock);
		
		String str = "";
		if(result == true) {
			str = "{\"result\":\"ok\\\"}";
		}else {
			str = "{\"result\":\"error\\\"}";
		}

		return str;
	}
	
	@PutMapping("/rest/stock")
	public String putStockOne(@RequestBody Stock stock) {
		boolean result = service.updateOne(stock);
		
		String str = "";
		if(result == true) {
			str = "{\"result\":\"ok\\\"}";
		}else {
			str = "{\"result\":\"error\\\"}";
		}

		return str;
	}
	
	@DeleteMapping("/rest/stock/delete/{stockId:.+}")
	public String deleteStockOne(@PathVariable("stockId") Integer stockId) {
		boolean result = service.deleteOne(stockId);
		
		String str = "";
		if(result == true) {
			str = "{\"result\":\"ok\\\"}";
		}else {
			str = "{\"result\":\"error\\\"}";
		}

		return str;
	}
	
}
