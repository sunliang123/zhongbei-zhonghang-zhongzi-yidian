package com.waben.stock.datalayer.activity.repository.jpa;

import com.waben.stock.datalayer.activity.entity.ActivityPublisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ActivityPublisherRespository extends JpaRepository<ActivityPublisher, Long>{

    List<ActivityPublisher> findActivityPublishersByApId(long apId);

    ActivityPublisher findActivityPublishersByPublisherId(long publisherId);
}
