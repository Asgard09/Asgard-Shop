package com.example.backend.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.backend.exception.ProductException;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category, @RequestParam List<String> color,
                                                                      @RequestParam List<String> size, @RequestParam Integer minPrice,
                                                                      @RequestParam Integer maxPrice, @RequestParam Integer minDiscount,
                                                                      @RequestParam String sort, @RequestParam String stock,
                                                                      @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        Page<Product> res = productService.getAllProduct(category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);

        System.out.println("Complete products");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException{

        Product product = productService.findProductById(productId);

        return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
    }


}
