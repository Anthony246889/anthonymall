package com.anthony.shoppingmall.service.impl;



import com.anthony.shoppingmall.dao.ProductDao;
import com.anthony.shoppingmall.dto.ProductQueryParams;
import com.anthony.shoppingmall.dto.ProductRequest;
import com.anthony.shoppingmall.model.Product;
import com.anthony.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        return productDao.countProduct(productQueryParams);
    }

    @Override
    public List<Product> getProduct(ProductQueryParams productQueryParams) {
        return productDao.getProduct(productQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }


    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }


    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId,productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);

    }
}
