package com.fish.shop.service;

import com.fish.shop.entity.ProductCategory;

import java.util.List;

/**
 * Created by mingkunyu on 2019-05-19
 */
public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
