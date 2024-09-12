package com.example.backend.service;

import com.example.backend.exception.ProductException;
import com.example.backend.model.Review;
import com.example.backend.model.User;
import com.example.backend.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest req, User user) throws ProductException;
    public List<Review> getAllReview(Long productId);


}
