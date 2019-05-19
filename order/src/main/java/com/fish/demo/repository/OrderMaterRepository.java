package com.fish.demo.repository;

import com.fish.demo.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mingkunyu on 2019-05-19
 */
public interface OrderMaterRepository extends JpaRepository<OrderMaster,String> {


}
