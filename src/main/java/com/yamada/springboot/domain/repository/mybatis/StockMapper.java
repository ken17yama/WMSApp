package com.yamada.springboot.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yamada.springboot.domain.model.Stock;

@Mapper
public interface StockMapper {

	//	全件の在庫を取得
	public List<Stock> selectMany();

	//	保管場所ごとに在庫を取得
	public List<Stock> eachPlace(Integer place);

	//	保管場所ごとに在庫を取得
	public Stock selectOne(Integer stockId);

	//	在庫を一件挿入
	public boolean insertOne(Stock stock);

	//	在庫を一件更新
	public boolean updateOne(Stock stock);

	//	在庫を一件削除
	public boolean deleteOne(Integer stockId);

}
