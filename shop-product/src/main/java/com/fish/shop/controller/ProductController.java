package com.fish.shop.controller;

import com.fish.shop.entity.ProductCategory;
import com.fish.shop.entity.ProductInfo;
import com.fish.shop.service.CategoryService;
import com.fish.shop.service.ProductService;
import com.fish.shop.vo.ProductInfoVO;
import com.fish.shop.vo.ProductVO;
import com.fish.shop.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultVo<ProductVO> list(){
        List<ProductInfo> productInfoList=productService.findUpAll();

        List<Integer> categoryTypeList=productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        List<ProductCategory> categoryList=categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList=new ArrayList<>();
        for (ProductCategory productCategory: categoryList) {
            ProductVO productVO=new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);//拷贝到productInfoVO
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }


        ResultVo resultVo=new ResultVo();
        resultVo.setData(productVOList);
        resultVo.setMsg("成功");//暂时写死
        return  resultVo;

    }

}
