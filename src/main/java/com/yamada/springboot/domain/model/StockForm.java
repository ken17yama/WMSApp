package com.yamada.springboot.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StockForm {

	//	品名
	@NotBlank
	private String stockName;

	//	在庫数
	@NotBlank
	private double quantity;

	//	単位
	@NotBlank
	private String unit;

}
