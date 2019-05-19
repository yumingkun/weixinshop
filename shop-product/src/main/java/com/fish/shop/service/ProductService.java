package com.fish.shop.service;

import com.fish.shop.entity.ProductCategory;
import com.fish.shop.entity.ProductInfo;

import java.util.List;

/**
 * Created by mingkunyu on 2019-05-19
 */

public interface ProductService {

    List<ProductInfo> findUpAll();

}
