package com.anthony.shoppingmall.service;


import com.anthony.shoppingmall.dto.ProductRequest;
import com.anthony.shoppingmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

//    用void是因為沒有返回值
    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
