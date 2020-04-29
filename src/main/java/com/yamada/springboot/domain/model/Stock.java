package com.yamada.springboot.domain.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Stock {

	//	ID
	private Integer stockId;
	//	品名
	private String stockName;
	//	在庫数
	private double quantity;
	//	単位
	private String unit;
	//	保管場所
	private String place;
	//	登録日
	private Timestamp createdAt;
	//	更新日
	private Timestamp updatedAt;

}
