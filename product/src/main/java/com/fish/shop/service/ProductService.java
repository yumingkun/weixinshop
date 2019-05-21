package com.fish.shop.service;

import com.fish.shop.entity.ProductInfo;

import java.util.List;

/**
 * Created by mingkunyu on 2019-05-19
 */

public interface ProductService {

    /**
     * 查询所有在架的商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

}
