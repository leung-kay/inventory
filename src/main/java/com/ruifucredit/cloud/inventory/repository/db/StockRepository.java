package com.ruifucredit.cloud.inventory.repository.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruifucredit.cloud.inventory.pojo.po.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	
	List<Stock> findByGoodsId(Long id);
	
	Stock findBySubGoodsId(Long id);
	
}
