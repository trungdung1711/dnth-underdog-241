package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.model.ImportTransaction;
import com.dnth_underdog_241.online_fashion_shopping.model.VariantProduct;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.SupplierEnum;
import com.dnth_underdog_241.online_fashion_shopping.repository.ImportTransactionRepository;
import com.dnth_underdog_241.online_fashion_shopping.util.ImportCostCalculatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@Transactional
@RequiredArgsConstructor
public class ImportTransactionService
{
    private final ImportTransactionRepository importTransactionRepository;


    public void createImportTransaction(Double unitCost, Long quantity, VariantProduct variantProduct)
    {
        ImportTransaction importTransaction = ImportTransaction
                .builder()
                .date(LocalDate.now())
                .status("SUCCESSFUL")
                .supplierEnum(SupplierEnum.getRandomSupplier())
                .shippingCost(ImportCostCalculatorUtil.generateRandomShippingCost())
                .unitCost(unitCost)
                .tax(ImportCostCalculatorUtil.generateRandomImportTaxRate())
                .quantity(quantity)
                .totalCost(ImportCostCalculatorUtil.calculateTotalCost(unitCost, quantity))
                .variantProduct(variantProduct)
                .build();

        importTransactionRepository.save(importTransaction);
    }
}