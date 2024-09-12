package com.example.backend.service;

import com.example.backend.exception.CartItemException;
import com.example.backend.exception.UserException;
import com.example.backend.model.Cart;
import com.example.backend.model.CartItem;
import com.example.backend.model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;


}
