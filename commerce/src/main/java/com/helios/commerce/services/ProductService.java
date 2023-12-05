package com.helios.commerce.services;

import com.helios.commerce.model.Product;
import com.helios.commerce.model.Type;
import org.springframework.stereotype.Service;


@Service
public interface ProductService {

    Iterable<Product> findAllPaging(int offSet, int pageSize);

    Iterable<Product> findByType(Type type);

    Iterable<Product> findProductById(Long id);

    Iterable<Product> searchProduct(String keyword);

    void addProduct(Product product);

    Iterable<Product> findAll();

    void deleteById(Long id);



}
