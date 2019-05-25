package com.fish.server.dto;

import com.fish.server.entity.OrderDetail;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

// 第一个是客户端从服务器端读取数据 vo
// 第二个是客户端将本身的数据传递给服务器端。dto

/**
 * Created by mingkunyu on 2019-05-19
 */
@Data
public class OrderDTO {

//    OrderMaster

    /** 订单id. */
    @Id
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;

//    OrderDetail
    private  List<OrderDetail>  orderDetailList;

}


