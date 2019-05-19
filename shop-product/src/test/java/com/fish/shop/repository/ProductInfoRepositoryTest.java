package com.fish.shop.repository;

import com.fish.shop.ProductApplicationTests;
import com.fish.shop.entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by mingkunyu on 2019-05-19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public  void findByProductStatus(){
        List<ProductInfo> list=productInfoRepository.findByProductStatus(0);
    }

}