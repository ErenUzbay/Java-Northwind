package com.northwind.northwind.business.abstracts;

import java.util.List;


import com.northwind.northwind.core.utilities.results.DataResult;
import com.northwind.northwind.core.utilities.results.Result;
import com.northwind.northwind.entities.concretes.Product;
import com.northwind.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductService {
    Result add(Product product);
    Result delete(Integer productId);
    DataResult<List<Product>> getAll();
    DataResult<List<Product>> getAll(int pageNo, int pageSize); // sayfalama
    DataResult<List<Product>> getAllSorted(); // sıralama
    DataResult<Product> findById(Integer productId);
    DataResult<Product> findByProductName(String productName);
    DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId);
    DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId);
    DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories);
    DataResult<List<Product>> getByProductNameContains(String productName);
    DataResult<List<Product>> getByProductNameStartsWith(String productName);
    DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId);
    DataResult<List<Product>> findByUnitPriceBetween(double lowerLimit, double upperLimit);
    DataResult<List<Product>> findByUnitsInStockLessThanEqual(short unitsInStock);
    DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails();
    long count();    
    long countByProductName(String productName);
    long countByProductWithCategoryName(int categoryId);
    long countProductWithCategoryName(String categoryName);
}
