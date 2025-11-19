package com.inventory.root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan("com.inventory")
@EnableJpaRepositories("com.inventory.repository")
@EntityScan("com.inventory.entity")
public class ProductInventoryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductInventoryApiApplication.class, args);
    }

}