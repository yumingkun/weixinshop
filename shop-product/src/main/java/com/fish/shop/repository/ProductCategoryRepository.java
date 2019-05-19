package com.fish.shop.repository;

import com.fish.shop.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mingkunyu on 2019-05-19
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory>  findByCategoryTypeIn(List<Integer> categoryTypeList);
}
