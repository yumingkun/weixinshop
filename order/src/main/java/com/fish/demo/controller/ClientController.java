package com.fish.demo.controller;

import com.fish.demo.client.ProductClient;
import com.fish.demo.dto.CartDTD;
import com.fish.demo.entity.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mingkunyu on 2019-05-19
 */
@Slf4j
@RestController
public class ClientController {


    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public  String getProductMsg(){

        String response=productClient.productMsg();

        return response;

    }
    @GetMapping("/getProductList")
    public String getProductList(){
       List<ProductInfo> productInfoList= productClient.listForOrder(Arrays.asList("157875196366160022"));
       return productInfoList.toString();
    }
    @GetMapping("/productDecreaseStock")
    public  String  productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDTD("164103465734242707",3)));
        return "ok";
    }
}
