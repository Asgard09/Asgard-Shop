package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id")
    private String orderId;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Column(name = "deliveryDate")
    private LocalDateTime deliveryDate;

    @OneToOne
    private Address shippingAddress;

    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails();

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "totalDiscoutedPrice")
    private Integer totalDiscoutedPrice;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "orderStatus")
    private String orderStatus;

    @Column(name = "totalItem")
    private int totalItem;

    @Column(name = "createdAt")
    private LocalDateTime createAt;




}
