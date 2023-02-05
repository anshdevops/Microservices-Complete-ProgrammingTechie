package com.programmingtechie.inventoryservice.service;


import com.programmingtechie.inventoryservice.repo.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

@RequiredArgsConstructor
public class InventoryService {


    private final InventoryRepo  inventoryRepo;


    @Transactional(readOnly=true)
    public boolean isInStock(String skuCode){

       return inventoryRepo.findBySkuCode(skuCode).isPresent();


    }
}
