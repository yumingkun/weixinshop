package com.fish.demo.service.impl;

import com.fish.demo.client.ProductClient;
import com.fish.demo.dto.CartDTD;
import com.fish.demo.dto.OrderDTO;
import com.fish.demo.entity.OrderDetail;
import com.fish.demo.entity.OrderMaster;
import com.fish.demo.entity.ProductInfo;
import com.fish.demo.enums.OrderStatusEnum;
import com.fish.demo.enums.PayStatusEnum;
import com.fish.demo.repository.OrderDetailRepository;
import com.fish.demo.repository.OrderMaterRepository;
import com.fish.demo.service.OrderService;
import com.fish.demo.untils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mingkunyu on 2019-05-19
 */
@Service
public class OrderServiceImpl  implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMaterRepository orderMaterRepository;
    @Autowired
    private ProductClient productClient;


    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        /**
         * 1:参数校验
         * 2：查询商品信息（调用商品服务）
         * 3；计算总价
         * 4：扣库  (存调用商品服务）
         * 5：订单入库
         */
        String orderId=KeyUtil.genUniqueKey();

        //参数校验
        //查询商品信息（调用商品服务）
        List<String> productIdList=orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getDetailId)
                .collect(Collectors.toList());
        List<ProductInfo> productInfoList=productClient.listForOrder(productIdList);

        //计算总价
        BigDecimal orderAmout=new BigDecimal(0);
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())){
                    //单价*数
                    orderAmout=productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }

            
        }

        //扣库存 （调用商品服务）
        List<CartDTD> cartDTDList=orderDTO.getOrderDetailList().stream()
                .map(e-> new CartDTD(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTDList);



        //订单入库
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);//拷贝到orderMaster

        orderMaster.setOrderAmount(new BigDecimal(5));//用来对超过16位有效位的数进行精确的运算
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMaterRepository.save(orderMaster);

        return orderDTO;
    }
}
