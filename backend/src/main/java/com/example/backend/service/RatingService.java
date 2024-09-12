package com.example.backend.service;

import com.example.backend.exception.ProductException;
import com.example.backend.model.Rating;
import com.example.backend.model.User;
import com.example.backend.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {

    public Rating createRating(RatingRequest req, User user) throws ProductException;
    public List<Rating> getProductsRating(Long productId);
}
