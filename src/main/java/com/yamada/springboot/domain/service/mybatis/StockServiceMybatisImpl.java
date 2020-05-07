package com.yamada.springboot.domain.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yamada.springboot.domain.model.Stock;
import com.yamada.springboot.domain.repository.mybatis.StockMapper;
import com.yamada.springboot.domain.service.StockService;

@Transactional
@Service("StockServiceMybatisImpl")
public class StockServiceMybatisImpl implements StockService {
	
	@Autowired
	StockMapper stockMapper;

	@Override
	public List<Stock> selectMany() {
		return stockMapper.selectMany();
	}

	@Override
	public List<Stock> eachPlace(Integer place) {
		return stockMapper.eachPlace(place);
	}

	@Override
	public Stock selectOne(Integer stockId) {
		return stockMapper.selectOne(stockId);
	}

	@Override
	public boolean insertOne(Stock stock) {
		return stockMapper.insertOne(stock);
	}

	@Override
	public boolean updateOne(Stock stock) {
		return stockMapper.updateOne(stock);
	}

	@Override
	public boolean deleteOne(Integer stockId) {
		return stockMapper.deleteOne(stockId);
	}

}
