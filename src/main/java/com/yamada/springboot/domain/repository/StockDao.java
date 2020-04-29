package com.yamada.springboot.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.yamada.springboot.domain.model.Stock;

public interface StockDao {

	//	全件の在庫を取得
	public List<Stock> selectMany() throws DataAccessException;

	//	保管場所ごとに在庫を取得
	public Stock eachPlace(String place) throws DataAccessException;

	//	保管場所ごとに在庫を取得
	public Stock selectOne(int id) throws DataAccessException;

	//	在庫を一軒挿入
	public int insertOne(Stock stock) throws DataAccessException;

	//	在庫を更新
	public int updateOne(Stock stock) throws DataAccessException;

	//	在庫を削除
	public int deleteOne(int id) throws DataAccessException;

}
