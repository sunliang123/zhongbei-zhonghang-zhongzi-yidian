package com.waben.stock.datalayer.activity.repository;

import com.waben.stock.datalayer.activity.entity.PublisherTeleCharge;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PublisherTeleChargeDao {

	PublisherTeleCharge savePublisherTeleCharge(PublisherTeleCharge a);

	Page<PublisherTeleCharge> getPublisherTeleChargeList(int pageno, int pagesize);

	PublisherTeleCharge getPublisherTeleCharge(long publisherTeleChargeId);

    List<PublisherTeleCharge> getPublisherTeleChargesByApId(long apId);
}
