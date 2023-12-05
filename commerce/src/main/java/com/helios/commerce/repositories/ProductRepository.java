package com.helios.commerce.repositories;

import com.helios.commerce.model.Product;
import com.helios.commerce.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Iterable<Product> findByType(Type type);

    Iterable<Product> findProductById(Long id);

    @Query("SELECT pd FROM Product pd where CONCAT(pd.name, pd.description, pd.price, pd.type) LIKE %?1%")
    Iterable<Product> search(String keyword);

    void deleteById(Long id);


}
