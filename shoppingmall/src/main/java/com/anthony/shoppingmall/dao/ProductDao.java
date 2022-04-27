package com.anthony.shoppingmall.dao;


import com.anthony.shoppingmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
