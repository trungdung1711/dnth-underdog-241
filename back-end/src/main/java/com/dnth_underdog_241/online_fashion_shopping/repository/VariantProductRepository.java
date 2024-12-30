package com.dnth_underdog_241.online_fashion_shopping.repository;


import com.dnth_underdog_241.online_fashion_shopping.model.Colour;
import com.dnth_underdog_241.online_fashion_shopping.model.Size;
import com.dnth_underdog_241.online_fashion_shopping.model.VariantProduct;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.ColourEnum;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SizeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VariantProductRepository extends JpaRepository<VariantProduct, Long>
{
    boolean existsByProductId(Long productId);


    @Query("select v from VariantProduct v " +
            "where v.product.id = :productId ")
    List<VariantProduct> findByProductId(Long productId);
}