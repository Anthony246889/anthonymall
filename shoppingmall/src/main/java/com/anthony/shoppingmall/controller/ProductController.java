package com.anthony.shoppingmall.controller;


import com.anthony.shoppingmall.constant.ProductCategory;
import com.anthony.shoppingmall.dto.ProductQueryParams;
import com.anthony.shoppingmall.dto.ProductRequest;
import com.anthony.shoppingmall.model.Product;
import com.anthony.shoppingmall.service.ProductService;
import com.anthony.shoppingmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;


@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


//    查詢所有商品
    @GetMapping( "/products")
//    public ResponseEntity<List<Product>> getProduct(
    public ResponseEntity<Page<Product>> getProduct(
//            分類查詢
            @RequestParam(required = false) ProductCategory category,
//            關鍵字查詢
            @RequestParam(required = false) String search,
//            排序Sorting(根據種類:價格,產生日期等.....)
            @RequestParam(defaultValue = "created_date") String orderBy,
//             排序Sorting(升冪降冪)
            @RequestParam(defaultValue = "desc") String sort,

//            分頁控制
//            限制幾筆
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
//            限制跳過幾筆
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
            ){

        ProductQueryParams productQueryParams=new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

//        取得product list
        List<Product> productList=productService.getProduct(productQueryParams);

//        取得 product 總數
        Integer total =productService.countProduct(productQueryParams);

//        分頁
        Page<Product> page=new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

//        return ResponseEntity.status(HttpStatus.OK).body(productList);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }


//    查詢單個商品
    @GetMapping( "/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){

        Product product=productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


//    新增產品
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId=productService.createProduct(productRequest);
        Product product= productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

//    更新產品
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){
//        檢查Product是否存在
        Product product =productService.getProductById(productId);

        if (product == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

//           修改商品數據
        productService.updateProduct(productId,productRequest);

        Product updateProduct=productService.getProductById(productId);

        return  ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

//     刪除產品
    @DeleteMapping("/products/{productId}")
    public  ResponseEntity<?> deleteProduct(@PathVariable Integer productId){

        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
