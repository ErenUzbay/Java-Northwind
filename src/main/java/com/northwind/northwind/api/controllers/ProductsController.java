package com.northwind.northwind.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.northwind.northwind.business.abstracts.ProductService;
import com.northwind.northwind.core.utilities.results.DataResult;
import com.northwind.northwind.core.utilities.results.ErrorDataResult;
import com.northwind.northwind.core.utilities.results.Result;
import com.northwind.northwind.entities.concretes.Product;
import com.northwind.northwind.entities.dtos.ProductWithCategoryDto;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
@RequestMapping("/api/products/")
@CrossOrigin
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("findall")
    public DataResult<List<Product>> findAll() {
        return productService.findAll();
    }

    @GetMapping("findProductName")
    public DataResult<Product> findProductName(@RequestParam String productName) {
        return productService.findByProductName(productName);
    }

    @GetMapping("findById")
    public DataResult<Product> findById(@RequestParam Integer productId) {
        return productService.findById(productId);
    }

    @GetMapping("findByProductNameAndCategoryId")
    public DataResult<Product> findByProductNameAndCategoryId(@RequestParam("productName") String productName,
            @RequestParam("categoryId") int categoryId) {
        return productService.findByProductNameAndCategoryId(productName, categoryId);
    }

    @GetMapping("findByProductNameOrCategoryId")
    public DataResult<List<Product>> findByProductNameOrCategoryId(@RequestParam String productName,
            @RequestParam int categoryId) {
        return productService.findByProductNameOrCategoryId(productName, categoryId);
    }

    @GetMapping("findByCategoryIdIn")
    public DataResult<List<Product>> findByCategoryIdIn(@RequestParam List<Integer> categories) {
        return productService.findByCategoryIdIn(categories);
    }

    @GetMapping("findByProductNameContains")
    public DataResult<List<Product>> findByProductNameContains(@RequestParam String productName) {
        return productService.findByProductNameContains(productName);
    }

    @GetMapping("findByProductNameStartsWith")
    public DataResult<List<Product>> findByProductNameStartsWith(@RequestParam String productName) {
        return productService.findByProductNameStartsWith(productName);
    }

    @GetMapping("findByNameAndCategory")
    public DataResult<List<Product>> findByNameAndCategory(@RequestParam String productName,
            @RequestParam int categoryId) {
        return productService.findByNameAndCategory(productName, categoryId);
    }

    @GetMapping("findAllByPage")
    public DataResult<List<Product>> findAll(int pageNo, int pageSize) {
        return productService.findAll(pageNo, pageSize);
    }

    @GetMapping("findAllSortedASC")
    public DataResult<List<Product>> findAllSorted() {
        return productService.findAllSorted();
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

    @GetMapping("findProductWithCategoryDetails")
    public DataResult<List<ProductWithCategoryDto>> findProductWithCategoryDetails() {
        return this.productService.findProductWithCategoryDetails();
    }
    
    
    @PostMapping("add")
    public ResponseEntity<?> Add(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.add(product));
    }

    @PostMapping("delete")
    public Result Delete(@RequestParam Integer productId) {
        return productService.delete(productId);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationExcepiton(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Doğrulama Hatası");
        return errors;
    }

}
