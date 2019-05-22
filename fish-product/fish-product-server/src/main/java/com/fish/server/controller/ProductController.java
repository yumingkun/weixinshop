package com.fish.server.controller;

import com.fish.server.dtd.CartDTD;
import com.fish.server.entity.ProductCategory;
import com.fish.server.entity.ProductInfo;
import com.fish.server.service.CategoryService;
import com.fish.server.service.ProductService;

import com.fish.server.untils.ResultVOUtils;
import com.fish.server.vo.ProductInfoVO;
import com.fish.server.vo.ProductVO;
import com.fish.server.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品
 * Created by mingkunyu on 2019-05-19
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    /**
     * 1：查询所有在架的商品
     * 2：获取类目type列表
     * 3：查询类目
     * 4：构造数据
     */

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public ResultVo<ProductVO> list() {
        //1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2. 获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //3. 从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }


        return ResultVOUtils.success(productVOList);

    }


    /**
     * 获取商品列表（给订单服务用）
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {
        return productService.findList(productIdList);

    }


    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTD> cartDTDList) {
        productService.decreaseStock(cartDTDList);
    }
}
