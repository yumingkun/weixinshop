package com.fish.server.service.impl;



import com.fish.client.ProductClient;
import com.fish.common.DecreaseStockInput;
import com.fish.common.ProductInfoOutput;
import com.fish.server.dto.CartDTD;
import com.fish.server.dto.OrderDTO;
import com.fish.server.entity.OrderDetail;
import com.fish.server.entity.OrderMaster;
import com.fish.server.enums.OrderStatusEnum;
import com.fish.server.enums.PayStatusEnum;
import com.fish.server.enums.ResultEnum;
import com.fish.server.exception.OrderException;
import com.fish.server.repository.OrderDetailRepository;
import com.fish.server.repository.OrderMaterRepository;
import com.fish.server.service.OrderService;
import com.fish.server.untils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        /**
         * 1:参数校验
         * 2：查询商品信息（调用商品服务）
         * 3；计算总价
         * 4：扣库  (存调用商品服务）
         * 5：订单入库
         */
        String orderId= KeyUtil.genUniqueKey();

        //参数校验
        //查询商品信息（调用商品服务）
        List<String> productIdList=orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getDetailId)
                .collect(Collectors.toList());

        List<ProductInfoOutput> productInfoList=productClient.listForOrder(productIdList);

        //计算总价
        BigDecimal orderAmout=new BigDecimal(0);
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            for (ProductInfoOutput productInfo: productInfoList) {
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
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);


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

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        //1:先查询订单
        Optional<OrderMaster> orderMasterOptional=orderMaterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()){
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2：判断订单状态
        OrderMaster orderMaster=orderMasterOptional.get();
        if (OrderStatusEnum.NEW.getCode()!=orderMaster.getOrderStatus()){
            throw  new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //3：修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMaterRepository.save(orderMaster);

        //查询订单详情
        List<OrderDetail> orderDetailList =orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw  new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
