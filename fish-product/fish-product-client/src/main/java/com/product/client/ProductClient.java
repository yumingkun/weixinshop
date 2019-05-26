package com.product.client;


import com.fish.common.DecreaseStockInput;
import com.fish.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by mingkunyu on 2019-05-19
 */
@FeignClient(name = "product",fallback = ProductClientFallback.class)
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

}
