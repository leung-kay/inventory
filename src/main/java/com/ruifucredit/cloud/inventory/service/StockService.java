package com.ruifucredit.cloud.inventory.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruifucredit.cloud.inventory.repository.db.StockRepository;
import com.ruifucredit.cloud.inventory.support.dto.Stock;
import com.ruifucredit.cloud.kit.po.POKit;

import lombok.NonNull;

@Service
public class StockService implements IStockService {

	@Autowired
	private StockRepository stockRepo;

	@Override
	public Stock queryOne(Long id) {
		
		com.ruifucredit.cloud.inventory.pojo.po.Stock result = stockRepo.findOne(id);
		
		if(result!=null) return result.stock();
		
		return new Stock();
	}

	@Override
	public Stock queryBySubGoodsId(Long id) {
		
		com.ruifucredit.cloud.inventory.pojo.po.Stock result = stockRepo.findBySubGoodsId(id);
		
		if(result!=null) return result.stock();
		
		return new Stock();
	}

	@Override
	public List<Stock> queryByGoodsId(Long id) {

		List<com.ruifucredit.cloud.inventory.pojo.po.Stock> stocks = stockRepo.findByGoodsId(id);

		List<Stock> result = new ArrayList<>(stocks.size());

		for (com.ruifucredit.cloud.inventory.pojo.po.Stock stock : stocks) {
			result.add(stock.stock());
		}

		return result;
	}

	@Override
	public Stock modify(@NonNull Stock param, @NonNull Long id) {

		com.ruifucredit.cloud.inventory.pojo.po.Stock p = new com.ruifucredit.cloud.inventory.pojo.po.Stock(param);
		
		com.ruifucredit.cloud.inventory.pojo.po.Stock q = stockRepo.findOne(id);
		
		com.ruifucredit.cloud.inventory.pojo.po.Stock stock = POKit.join(p, q);
		
		stock.setStockId(id).setCreateTime(q.getCreateTime()).setUpdateTime(new Date());
		
		Stock result = stockRepo.save(stock).stock();

		return result;

	}

	@Override
	public List<Stock> create(@NonNull List<Stock> params) {

		List<com.ruifucredit.cloud.inventory.pojo.po.Stock> stocks = new ArrayList<>(params.size());
		
		Date now = new Date();
		
		for (Stock param : params) {
			com.ruifucredit.cloud.inventory.pojo.po.Stock stock = new com.ruifucredit.cloud.inventory.pojo.po.Stock(
					param);
			stock.setStockId(null).setCreateTime(now).setUpdateTime(now).setStockStatus(Stock.YES_STATUS);
			stocks.add(stock);
		}

		stocks = stockRepo.save(stocks);
		
		List<Stock> result = new ArrayList<>(stocks.size());
		
		for(com.ruifucredit.cloud.inventory.pojo.po.Stock stock : stocks) {
			result.add(stock.stock());
		}
		
		return result;

	}

	@Override
	public Stock create(@NonNull Stock stock) {
		
		List<Stock> results = create(Collections.singletonList(stock));
		
		if(results.size()==1) results.get(0);
		
		return null;
	}
	
	@Override
	public void remove(@NonNull Long id) {

		stockRepo.delete(id);

	}

}
