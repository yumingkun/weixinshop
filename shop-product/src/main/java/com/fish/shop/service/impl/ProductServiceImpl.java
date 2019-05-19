package com.fish.shop.service.impl;

import com.fish.shop.entity.ProductCategory;
import com.fish.shop.entity.ProductInfo;
import com.fish.shop.enums.ProductStatus;
import com.fish.shop.repository.ProductInfoRepository;
import com.fish.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mingkunyu on 2019-05-19
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;


    @Override
    public List<ProductInfo> findUpAll() {

        return productInfoRepository.findByProductStatus(ProductStatus.UP.getCode()) ;
    }
}
