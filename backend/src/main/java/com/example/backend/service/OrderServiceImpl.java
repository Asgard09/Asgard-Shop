package com.example.backend.service;

import com.example.backend.exception.OrderException;
import com.example.backend.model.Address;
import com.example.backend.model.Order;
import com.example.backend.model.User;
import com.example.backend.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{


    private CartRepository cartRepository;
    private CartService cartService;
    private ProductService productService;
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(User user, Address shippingAddress) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {

    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return List.of();
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);

        orderRepository.deleteById(orderId);
    }
}
