package com.fish.shop.service.impl;

import com.fish.shop.ProductApplicationTests;
import com.fish.shop.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mingkunyu on 2019-05-19
 */
@Component
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductServiceImpl productService;
    @Test
    public  void findUpAll() {
        List<ProductInfo> list=productService.findUpAll();
          Assert.assertTrue(list.size()>0);

    }
}