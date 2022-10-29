package com.northwind.northwind.business.abstracts;

import java.util.List;


import com.northwind.northwind.core.utilities.results.DataResult;
import com.northwind.northwind.core.utilities.results.Result;
import com.northwind.northwind.entities.concretes.Product;
import com.northwind.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductService {
    Result add(Product product);
    Result delete(Integer productId);
    DataResult<List<Product>> findAll();
    DataResult<List<Product>> findAll(int pageNo, int pageSize); // sayfalama
    DataResult<List<Product>> findAllSorted(); // sıralama
    DataResult<Product> findById(Integer productId);
    DataResult<Product> findByProductName(String productName);
    DataResult<Product> findByProductNameAndCategoryId(String productName, int categoryId);
    DataResult<List<Product>> findByProductNameOrCategoryId(String productName, int categoryId);
    DataResult<List<Product>> findByCategoryIdIn(List<Integer> categories);
    DataResult<List<Product>> findByProductNameContains(String productName);
    DataResult<List<Product>> findByProductNameStartsWith(String productName);
    DataResult<List<Product>> findByNameAndCategory(String productName, int categoryId);
    DataResult<List<Product>> findByUnitPriceBetween(double lowerLimit, double upperLimit);
    DataResult<List<Product>> findByUnitsInStockLessThanEqual(short unitsInStock);
    DataResult<List<ProductWithCategoryDto>> findProductWithCategoryDetails();
    long count();    
    long countByProductName(String productName);
    long countByProductWithCategoryName(int categoryId);
    long countProductWithCategoryName(String categoryName);
}
