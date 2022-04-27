package com.anthony.shoppingmall.rawmapper;


import com.anthony.shoppingmall.constant.ProductCategory;
import com.anthony.shoppingmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product>{

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product=new Product();

        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));

        String categoryStr=resultSet.getString("category");
        ProductCategory category=ProductCategory.valueOf(categoryStr);
        product.setCategory(category);

//        熟練後可以用的方式寫法
//        product.setCategory(ProductCategory.valueOf(resultSet.getString("category")));

        product.setImageUrl(resultSet.getString("image_url"));
        product.setPrice(resultSet.getInt("price"));
        product.setStock(resultSet.getInt("stock"));
        product.setDescription(resultSet.getString("description"));
        product.setCreatedDate(resultSet.getTimestamp("created_date"));
        product.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return product;
    }
}
