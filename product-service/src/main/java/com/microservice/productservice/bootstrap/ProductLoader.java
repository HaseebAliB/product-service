package com.microservice.productservice.bootstrap;

import com.microservice.productservice.model.Product;
import com.microservice.productservice.model.ProductType;
import com.microservice.productservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Calendar;

@AllArgsConstructor
@Component
public class ProductLoader implements CommandLineRunner {

    private ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0)
        loadProducts();
    }

    private void loadProducts(){
        Product p = Product.builder().name("Tucson41").
                productType(ProductType.AA).
                price(new BigDecimal(500)).
                creationDate(OffsetDateTime.now()).
                build();
    productRepository.save(p);

    }
}
