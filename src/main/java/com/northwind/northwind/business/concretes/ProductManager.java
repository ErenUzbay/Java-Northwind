package com.northwind.northwind.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.northwind.northwind.business.abstracts.ProductService;
import com.northwind.northwind.core.utilities.results.DataResult;
import com.northwind.northwind.core.utilities.results.Result;
import com.northwind.northwind.core.utilities.results.SuccessDataResult;
import com.northwind.northwind.core.utilities.results.SuccessResult;
import com.northwind.northwind.dataAccess.abstracts.ProductDao;
import com.northwind.northwind.entities.concretes.Product;
import com.northwind.northwind.entities.dtos.ProductWithCategoryDto;

@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public DataResult<List<Product>> getAll() {
        return new SuccessDataResult<List<Product>>(productDao.findAll(), "Data listelendi");
    }

    @Override
    public Result add(Product product) {
        this.productDao.save(product);
        return new SuccessResult("Data eklendi");
    }

    @Override
    public Result delete(Integer productId) {
        this.productDao.deleteById(productId);
        return new SuccessResult("Data silindi");
    }

    @Override
    public DataResult<Product> findByProductName(String productName) {
        return new SuccessDataResult<Product>(this.productDao.findByProductName(productName),
                "Data listelendi.");
    }

    @Override
    public DataResult<Product> findById(Integer productId) {
        Optional<Product> product = this.productDao.findById(productId);
        return new SuccessDataResult<Product>(product.get(), "Data listelendi");
    }

    @Override
    public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
        return new SuccessDataResult<Product>(
                this.productDao.getByProductNameAndCategory_CategoryId(productName, categoryId),
                "Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>(
                this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId),
                "Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByCategory_CategoryIdIn(categories),
                "Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> getByProductNameContains(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameContains(productName),
                "Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameStartsWith(productName),
                "Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByNameAndCategory(productName, categoryId),
                "Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Product> products = this.productDao.findAll(pageable).getContent();
        return new SuccessDataResult<List<Product>>(products, "Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "productName");
        List<Product> products = this.productDao.findAll(sort);
        return new SuccessDataResult<List<Product>>(products, "Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> findByUnitPriceBetween(double lowerLimit, double upperLimit) {
        return new SuccessDataResult<List<Product>>(this.productDao.findByUnitPriceBetween(lowerLimit, upperLimit),
                "Data listelendi.");
    }

    @Override
    public DataResult<List<Product>> findByUnitsInStockLessThanEqual(short unitsInStock) {
        return new SuccessDataResult<List<Product>>(this.productDao.findByUnitsInStockLessThanEqual(unitsInStock),
                "Data listelendi.");
    }

    @Override
    public long count() {
        return this.productDao.count();
    }

    @Override
    public long countByProductName(String productName) {
        return this.productDao.countByProductName(productName);
    }

    @Override
    public long countByProductWithCategoryName(int categoryId) {
        return this.productDao.countByProductWithCategoryName(categoryId);
    }

    @Override
    public long countProductWithCategoryName(String categoryName) {
        return this.productDao.countProductWithCategoryName(categoryName);
    }

    @Override
    public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
        return new SuccessDataResult<List<ProductWithCategoryDto>>(this.productDao.getProductWithCategoryDetails(),
                "Data listelendi.");
    }
}
