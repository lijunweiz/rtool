package com.lijw.decision.product;

import com.lijw.decision.core.product.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

    @Bean
    public Product aliJieBei() {
        return new AliJieBei();
    }

    @Bean
    public Product demoOtherProduct() {
        return new DemoOtherProduct();
    }

}
