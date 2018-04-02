package com.ruifucredit.cloud.inventory.pojo.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.experimental.Accessors;

/*
create table i_stock (
    stock_id integer primary key,
    goods_id integer,
    sub_goods_id integer,
    stock_num number(15,5),
    create_time timestamp,
    update_time timestamp,
    stock_status varchar2(4)
);
create sequence i_stock_seq;
 */
@Data
@Accessors(chain=true)
@Entity
@Table(name="i_stock")
public class Stock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stock_id_generator")
	@SequenceGenerator(name="stock_id_generator", sequenceName="i_stock_seq", allocationSize=1)
	@Column(name="stock_id")
	private Long stockId;
	@Column(name="goods_id")
	private Long goodsId;
	@Column(name="sub_goods_id")
	private Long subGoodsId;
	@Column(name="stock_num")
	private BigDecimal stockNumber;
	@Column(name="create_time")
	private Date createTime;
	@Column(name="update_time")
	private Date updateTime;
	@Column(name="stock_status")
	private String stockStatus;
	
	public Stock() {
		super();
	}
	
	public Stock(com.ruifucredit.cloud.inventory.support.dto.Stock stock) {
		this();
		BeanUtils.copyProperties(stock, this);
	}
	
	public com.ruifucredit.cloud.inventory.support.dto.Stock stock() {
		com.ruifucredit.cloud.inventory.support.dto.Stock stock = 
				new com.ruifucredit.cloud.inventory.support.dto.Stock();
		BeanUtils.copyProperties(this, stock);
		return stock;
	}
	
}
