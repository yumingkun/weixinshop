package com.fish.server.service.impl;

import com.fish.common.DecreaseStockInput;
import com.fish.common.ProductInfoOutput;
import com.fish.server.dtd.CartDTD;
import com.fish.server.entity.ProductInfo;
import com.fish.server.enums.ProductStatus;
import com.fish.server.enums.ResultEnum;
import com.fish.server.exception.ProductException;
import com.fish.server.repository.ProductInfoRepository;
import com.fish.server.service.ProductService;


import com.fish.server.untils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by mingkunyu on 2019-05-19
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public List<ProductInfo> findUpAll() {

        return productInfoRepository.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        List<ProductInfo> productInfoList=decreaseStockProcess(decreaseStockInputList);

        //发送mq消息
        List<ProductInfoOutput> productInfoOutputList=productInfoList.stream()
                .map(e->{
                    ProductInfoOutput output=new ProductInfoOutput();
                    BeanUtils.copyProperties(e,output);
                    return output;
                }).collect(Collectors.toList());

        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));

    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList=new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            //判断商品是否存在
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);//商品不存在
            }

            //判断库存是否足够
            ProductInfo productInfo1 = productInfoOptional.get();
            Integer result = productInfo1.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo1.setProductStock(result);
            productInfoRepository.save(productInfo1);


            productInfoList.add(productInfo1);
        }
        return  productInfoList;
    }
}
