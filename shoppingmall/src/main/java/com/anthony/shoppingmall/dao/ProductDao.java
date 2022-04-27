package com.anthony.shoppingmall.dao;


import com.anthony.shoppingmall.dto.ProductRequest;
import com.anthony.shoppingmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
