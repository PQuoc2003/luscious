package com.helios.commerce.services.imp;


import com.helios.commerce.model.Product;
import com.helios.commerce.model.Type;
import com.helios.commerce.repositories.ProductPagingRepository;
import com.helios.commerce.repositories.ProductRepository;
import com.helios.commerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {


    ProductPagingRepository productPagingRepository;
    ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductPagingRepository productPagingRepository, ProductRepository productRepository) {
        this.productPagingRepository = productPagingRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> findAllPaging(int offSet, int pageSize) {
        return productPagingRepository.findAll(PageRequest.of(offSet,pageSize));
    }

    @Override
    public Iterable<Product> findByType(Type type) {
        return productRepository.findByType(type);
    }

    @Override
    public Iterable<Product> findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Iterable<Product> searchProduct(String keyword) {
        return productRepository.search(keyword);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
