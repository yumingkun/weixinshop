package com.fish.server.repository;


import com.fish.server.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mingkunyu on 2019-05-19
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    List<OrderDetail>  findByOrderId(String orderId);
}
