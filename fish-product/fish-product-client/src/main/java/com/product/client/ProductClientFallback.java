package com.product.client;

import com.fish.common.DecreaseStockInput;
import com.fish.common.ProductInfoOutput;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mingkunyu on 2019-05-26
 */
@Component
public class ProductClientFallback  implements ProductClient {
    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIdList) {

        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

    }
}
