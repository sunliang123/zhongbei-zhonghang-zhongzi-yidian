package com.waben.stock.datalayer.activity.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.activity.entity.Activity;


public interface ActivityRespository extends JpaRepository<Activity, Long>{

    Activity findActivitiesByLocation(String location);

    Activity findByActivityId(Long id);
}
