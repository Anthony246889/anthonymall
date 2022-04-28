package com.anthony.shoppingmall.controller;


import com.anthony.shoppingmall.constant.ProductCategory;
import com.anthony.shoppingmall.dto.ProductRequest;
import com.anthony.shoppingmall.model.Product;
import com.anthony.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


//    查詢所有商品
    @GetMapping( "/products")
    public ResponseEntity<List<Product>> getProduct(
//            分類查詢
            @RequestParam(required = false) ProductCategory category,
//            關鍵字查詢
            @RequestParam(required = false) String search
            ){
        List<Product> productList=productService.getProduct(category,search);

        return ResponseEntity.status(HttpStatus.OK).body(productList);
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
