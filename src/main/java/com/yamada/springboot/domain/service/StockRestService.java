package com.yamada.springboot.domain.service;

import java.util.List;

import com.yamada.springboot.domain.model.Stock;

public interface StockRestService {

	//	全件の在庫を取得
	public List<Stock> selectMany();

	//	保管場所ごとに在庫を取得
	public List<Stock> eachPlace(String place);

	//	IDから在庫を一件取得
	public Stock selectOne(Integer stockId);

	//	在庫を一件挿入
	public boolean insertOne(Stock stock);

	//	在庫を一件更新
	public boolean updateOne(Stock stock);

	//	在庫を一件削除
	public boolean deleteOne(Integer stockId);

}
