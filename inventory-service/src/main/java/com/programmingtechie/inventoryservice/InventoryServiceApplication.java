package com.programmingtechie.inventoryservice;

import com.programmingtechie.inventoryservice.model.Inventory;
import com.programmingtechie.inventoryservice.repo.InventoryRepo;
import com.programmingtechie.inventoryservice.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}



	@Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepo){

		return args ->{
			Inventory inventory= new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);


			Inventory inventory1= new Inventory();
			inventory1.setSkuCode("iphone_13_red");
			inventory1.setQuantity(100);


			inventoryRepo.save(inventory);
			inventoryRepo.save(inventory1);

		};

	}
}


