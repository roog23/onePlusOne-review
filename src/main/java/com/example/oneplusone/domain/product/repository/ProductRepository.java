package com.example.oneplusone.domain.product.repository;

import com.example.oneplusone.domain.product.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
        SELECT p FROM Product p
        WHERE (:search IS NULL OR p.name LIKE CONCAT('%', :search, '%'))
        AND (:type IS NULL OR p.type = :type)
        AND (:minPrice IS NULL OR p.price >= :minPrice)
        AND (:maxPrice IS NULL OR p.price <= :maxPrice)
    """)
    Page<Product> findByProduct(String search, String type, Integer minPrice, Integer maxPrice, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = :id")
    Optional<Product> findByIdWithExclusiveLock(Long id);
}