package com.fish.demo.repository;

import com.fish.demo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mingkunyu on 2019-05-19
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
}
