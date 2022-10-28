package com.northwind.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.northwind.northwind.entities.concretes.Product;
import com.northwind.northwind.entities.dtos.ProductWithCategoryDto;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    Product findByProductName(String productName);

    Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);

    List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);

    // select * ... where category in(1,2,3,4)
    List<Product> getByCategory_CategoryIdIn(List<Integer> categories);

    List<Product> getByProductNameContains(String productName);

    List<Product> getByProductNameStartsWith(String productName);

    // JPQL
    // Product => Entitiy olan.(productName,categoryId)=> Entity property
    @Query("From Product where productName=:productName and category.categoryId=:categoryId")
    List<Product> getByNameAndCategory(String productName, int categoryId);
    // => Yukarıdaki sorgu
    // Select * From Products where product_name=x and categoryId=y

    List<Product> findByUnitPriceBetween(double lowerLimit, double upperLimit);

    List<Product> findByUnitsInStockLessThanEqual(short unitsInStock);

    long countByProductName(String productName);

    @Query("Select Count(*) From Product Where category.categoryId=:categoryId")
    long countByProductWithCategoryName(int categoryId);

    @Query("Select Count(*) From Product Where category.categoryName=:categoryName")
    long countProductWithCategoryName(String categoryName);

    // JOIN işlemleri
    @Query("Select new com.northwind.northwind.entities.dtos.ProductWithCategoryDto"
            + "(p.id,p.productName,c.categoryName) "
            + "From Category c Inner Join c.products p")
    List<ProductWithCategoryDto> getProductWithCategoryDetails();
}
