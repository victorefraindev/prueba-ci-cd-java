package com.example.demo.config;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            Product p1 = Product.builder()
                    .name("PlayStation 5 Pro")
                    .description("Experience next-gen gaming with ultra-high speed SSD and ray tracing.")
                    .price(499.99)
                    .imageUrl("https://images.unsplash.com/photo-1606813907291-d86efa9b94db?auto=format&fit=crop&q=80&w=1000")
                    .category("Console")
                    .build();

            Product p2 = Product.builder()
                    .name("Xbox Series X")
                    .description("The most powerful Xbox ever, featuring 12 teraflops of graphic processing power.")
                    .price(499.99)
                    .imageUrl("https://images.unsplash.com/photo-1605901309584-818e25960b8f?auto=format&fit=crop&q=80&w=1000")
                    .category("Console")
                    .build();

            Product p3 = Product.builder()
                    .name("Nintendo Switch OLED")
                    .description("Play at home or on the go with a vibrant 7-inch OLED screen.")
                    .price(349.99)
                    .imageUrl("https://images.unsplash.com/photo-1617096200347-cb04ae810b1d?auto=format&fit=crop&q=80&w=1000")
                    .category("Console")
                    .build();

            Product p4 = Product.builder()
                    .name("DualSense Controller")
                    .description("Discover a deeper gaming experience with haptic feedback and adaptive triggers.")
                    .price(69.99)
                    .imageUrl("https://images.unsplash.com/photo-1606318801954-d46d46d3360a?auto=format&fit=crop&q=80&w=1000")
                    .category("Accessory")
                    .build();

            Product p5 = Product.builder()
                    .name("Xbox Wireless Controller")
                    .description("Experience the modernized design of the Xbox Wireless Controller.")
                    .price(59.99)
                    .imageUrl("https://images.unsplash.com/photo-1600080972464-8e5f35f63d08?auto=format&fit=crop&q=80&w=1000")
                    .category("Accessory")
                    .build();

            Product p6 = Product.builder()
                    .name("Pro Gaming Headset")
                    .description("Immersive 3D audio with noise-canceling microphone for competitive gaming.")
                    .price(129.99)
                    .imageUrl("https://images.unsplash.com/photo-1618366712010-f4ae9c647dcb?auto=format&fit=crop&q=80&w=1000")
                    .category("Accessory")
                    .build();

            productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
            System.out.println("Products seeded to database.");
        }
    }
}
