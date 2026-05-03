package com.example.demo.controller;

import com.example.demo.models.Order;
import com.example.demo.models.OrderItem;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserOrders(@PathVariable Long userId) {
        List<Order> orders = orderRepository.findByUserIdOrderByOrderDateDesc(userId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (!userOpt.isPresent()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOpt.get();
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(0.0);
        order.setItems(new ArrayList<>());

        double totalAmount = 0;

        for (CartItemRequest itemReq : request.getItems()) {
            Optional<Product> prodOpt = productRepository.findById(itemReq.getProductId());
            if (prodOpt.isPresent()) {
                Product product = prodOpt.get();
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setQuantity(itemReq.getQuantity());
                orderItem.setPrice(product.getPrice());

                order.getItems().add(orderItem);
                totalAmount += product.getPrice() * itemReq.getQuantity();
            }
        }

        order.setTotalAmount(totalAmount);
        Order savedOrder = orderRepository.save(order);

        return ResponseEntity.ok(savedOrder);
    }

    @Data
    public static class CheckoutRequest {
        private Long userId;
        private List<CartItemRequest> items;
    }

    @Data
    public static class CartItemRequest {
        private Long productId;
        private Integer quantity;
    }
}
