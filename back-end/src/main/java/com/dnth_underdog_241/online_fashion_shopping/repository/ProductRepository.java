package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    @Query("select p from Product p where p.category.id = :categoryId")
    Page<Product> findAllByCategoryId(Pageable pageable, @Param("categoryId") Long categoryId);


    @Query("select p from Product p where p.brand.id = :brandId")
    Page<Product> findAllByBrandId(Pageable pageable, @Param("brandId") Long brandId);


    @Query("select p from Product p")
    Page<Product> findAllProduct(Pageable pageable);


    Product findProductById(Long id);


    void removeProductById(Long productId);
}
