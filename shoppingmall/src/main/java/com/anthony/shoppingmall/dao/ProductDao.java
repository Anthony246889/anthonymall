package com.anthony.shoppingmall.dao;


import com.anthony.shoppingmall.constant.ProductCategory;
import com.anthony.shoppingmall.dto.ProductQueryParams;
import com.anthony.shoppingmall.dto.ProductRequest;
import com.anthony.shoppingmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProduct(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
