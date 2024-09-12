package com.example.backend.service;

import com.example.backend.exception.OrderException;
import com.example.backend.model.Address;
import com.example.backend.model.Order;
import com.example.backend.model.User;


import java.util.List;

public interface OrderService {

    public Order createOrder(User user, Address shippingAddress);

    public Order findOrderById(Long orderId) throws OrderException;

    public List<Order> usersOrderHistory(Long userId);

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order  cancelOrder(Long orderId) throws OrderException;

    public List<Order> getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;
}
