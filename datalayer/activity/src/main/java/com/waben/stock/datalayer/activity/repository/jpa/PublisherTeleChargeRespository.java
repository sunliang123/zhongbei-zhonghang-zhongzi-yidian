package com.waben.stock.datalayer.activity.repository.jpa;

import com.waben.stock.datalayer.activity.entity.PublisherTeleCharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherTeleChargeRespository extends JpaRepository<PublisherTeleCharge, Long>{
    List<PublisherTeleCharge> findPublisherTeleChargesByApId(long apId);
}
