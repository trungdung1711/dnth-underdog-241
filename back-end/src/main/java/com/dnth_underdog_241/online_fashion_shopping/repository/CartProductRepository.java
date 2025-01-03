package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.model.CartProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long>
{
    Page<CartProduct> findByCartId(Long cartId, Pageable pageable);


    @Query("select cp " +
            "from CartProduct cp " +
            "where cp.cart.customer.id = :customerId")
    Page<CartProduct> findByCustomerId(Long customerId, Pageable pageable);


    boolean existsByVariantProductIdAndCartId(Long variantProductId, Long cartId);


    @Query("select cp " +
            "from CartProduct cp " +
            "where cp.cart.customer.id = :customerId " +
            "and cp.variantProduct.id = :variantProductId")
    CartProduct findByCustomerIdAndVariantProductId(Long customerId, Long variantProductId);
}
