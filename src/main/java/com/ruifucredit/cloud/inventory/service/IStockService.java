package com.ruifucredit.cloud.inventory.service;

import java.util.List;

import com.ruifucredit.cloud.inventory.support.dto.Stock;

public interface IStockService {

	Stock queryOne(Long id);

	Stock queryBySubGoodsId(Long id);

	List<Stock> queryByGoodsId(Long id);

	Stock modify(Stock stock, Long id);

	Stock create(Stock stock);

	void remove(Long id);

	List<Stock> create(List<Stock> params);

}
