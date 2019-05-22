package com.fish.server.service.impl;

import com.fish.server.dtd.CartDTD;
import com.fish.server.entity.ProductInfo;
import com.fish.server.enums.ProductStatus;
import com.fish.server.enums.ResultEnum;
import com.fish.server.exception.ProductException;
import com.fish.server.repository.ProductInfoRepository;
import com.fish.server.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by mingkunyu on 2019-05-19
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;


    @Override
    public List<ProductInfo> findUpAll() {

        return productInfoRepository.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<CartDTD> cartDTDList) {
        for (CartDTD cartDTD : cartDTDList) {
            //判断商品是否存在
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTD.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);//商品不存在
            }

            //判断库存是否足够
            ProductInfo productInfo1 = productInfoOptional.get();
            Integer result = productInfo1.getProductStock() - cartDTD.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
        }
    }
}
