package com.waben.stock.datalayer.activity.repository.impl;

import com.waben.stock.datalayer.activity.entity.PublisherTeleCharge;
import com.waben.stock.datalayer.activity.repository.PublisherTeleChargeDao;
import com.waben.stock.datalayer.activity.repository.PublisherTeleChargeDao;
import com.waben.stock.datalayer.activity.repository.jpa.PublisherTeleChargeRespository;
import com.waben.stock.datalayer.activity.repository.jpa.PublisherTeleChargeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherTeleChargeDaoImpl implements PublisherTeleChargeDao {
	
	@Autowired
	private PublisherTeleChargeRespository respository;


	@Override
	public PublisherTeleCharge savePublisherTeleCharge(PublisherTeleCharge ptc) {
		return respository.save(ptc);
	}

	@Override
	public Page<PublisherTeleCharge> getPublisherTeleChargeList(int pageno, int pagesize) {
		Pageable p = new PageRequest(pageno-1, pagesize);
		Page<PublisherTeleCharge> pt =  respository.findAll(p);
		return pt;
	}

	@Override
	public PublisherTeleCharge getPublisherTeleCharge(long PublisherTeleChargeId) {
		return respository.findOne(PublisherTeleChargeId);
	}

	@Override
	public List<PublisherTeleCharge> getPublisherTeleChargesByApId(long apId) {
		return respository.findPublisherTeleChargesByApId(apId);
	}
}
