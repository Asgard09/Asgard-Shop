package com.example.backend.controller;

import com.example.backend.exception.ProductException;
import com.example.backend.exception.UserException;
import com.example.backend.model.Cart;
import com.example.backend.model.User;
import com.example.backend.request.AddItemRequest;
import com.example.backend.response.ApiResponse;
import com.example.backend.service.CartService;
import com.example.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
//@Tag(name = "Cart Management", description = "find user cart, add item to cart")
public class CartController {

    private CartService cartService;
    private UserService userService;

    @GetMapping("/")
//    @Operation(description = "find cart by user id")
    public ResponseEntity<Cart>findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
//    @Operation(description = "add item to cart")
    public ResponseEntity<ApiResponse>addItemToCart(@RequestBody AddItemRequest req,
                                                    @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
        User user = userService.findUserProfileByJwt(jwt);

        cartService.addCartItem(user.getId(), req);
        ApiResponse res = new ApiResponse();
        res.setMessage("product deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res,HttpStatus.OK);
    }


}
