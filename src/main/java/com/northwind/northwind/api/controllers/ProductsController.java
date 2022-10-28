package com.northwind.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.northwind.northwind.business.abstracts.ProductService;
import com.northwind.northwind.core.utilities.results.DataResult;
import com.northwind.northwind.core.utilities.results.Result;
import com.northwind.northwind.entities.concretes.Product;
import com.northwind.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/products/")
@CrossOrigin
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("getall")
    public DataResult<List<Product>> getAll() {
        return productService.getAll();
    }

    @GetMapping("getProductName")
    public DataResult<Product> getProductName(@RequestParam String productName) {
        return productService.findByProductName(productName);
    }

    @GetMapping("getById")
    public DataResult<Product> getById(@RequestParam Integer productId) {
        return productService.findById(productId);
    }

    @GetMapping("getByProductNameAndCategoryId")
    public DataResult<Product> getByProductNameAndCategoryId(@RequestParam("productName") String productName,
            @RequestParam("categoryId") int categoryId) {
        return productService.getByProductNameAndCategoryId(productName, categoryId);
    }

    @GetMapping("getByProductNameOrCategoryId")
    public DataResult<List<Product>> getByProductNameOrCategoryId(@RequestParam String productName,
            @RequestParam int categoryId) {
        return productService.getByProductNameOrCategoryId(productName, categoryId);
    }

    @GetMapping("getByCategoryIdIn")
    public DataResult<List<Product>> getByCategoryIdIn(@RequestParam List<Integer> categories) {
        return productService.getByCategoryIdIn(categories);
    }

    @GetMapping("getByProductNameContains")
    public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName) {
        return productService.getByProductNameContains(productName);
    }

    @GetMapping("getByProductNameStartsWith")
    public DataResult<List<Product>> getByProductNameStartsWith(@RequestParam String productName) {
        return productService.getByProductNameStartsWith(productName);
    }

    @GetMapping("getByNameAndCategory")
    public DataResult<List<Product>> getByNameAndCategory(@RequestParam String productName,
            @RequestParam int categoryId) {
        return productService.getByNameAndCategory(productName, categoryId);
    }

    @GetMapping("getAllByPage")
    public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
        return productService.getAll(pageNo, pageSize);
    }

    @GetMapping("getAllSortedASC")
    public DataResult<List<Product>> getAllSorted() {
        return productService.getAllSorted();
    }

    @GetMapping("findByUnitPriceBetween")
    public DataResult<List<Product>> findByUnitPriceBetween(@RequestParam double lowerLimit,
            @RequestParam double upperLimit) {
        return this.productService.findByUnitPriceBetween(lowerLimit, upperLimit);
    }

    @GetMapping("findByUnitsInStockLessThanEqual")
    public DataResult<List<Product>> findByUnitsInStockLessThanEqual(@RequestParam short unitsInStock) {
        return this.productService.findByUnitsInStockLessThanEqual(unitsInStock);
    }

    @GetMapping("count")
    public long count() {
        return this.productService.count();
    }
    
    @GetMapping("countByProductName")
    public long countByProductName(@RequestParam String productName) {
        return this.productService.countByProductName(productName);
    }
    
    @GetMapping("countByProductWithCategoryName")
    public long countByProductWithCategoryName(@RequestParam int categoryId) {
        return this.productService.countByProductWithCategoryName(categoryId);
    }
   
    @GetMapping("countProductWithCategoryName")
    public long countProductWithCategoryName(@RequestParam String categoryName) {
        return this.productService.countProductWithCategoryName(categoryName);
    }    

    @GetMapping("getProductWithCategoryDetails")
    public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
        return this.productService.getProductWithCategoryDetails();
    }
    
    @PostMapping("add")
    public Result Add(@RequestBody Product product) {
        return productService.add(product);
    }

    @PostMapping("delete")
    public Result Delete(@RequestParam Integer productId) {
        return productService.delete(productId);
    }

}
