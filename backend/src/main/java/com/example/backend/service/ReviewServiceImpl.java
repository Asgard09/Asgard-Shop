package com.example.backend.service;

import com.example.backend.exception.ProductException;
import com.example.backend.model.Product;
import com.example.backend.model.Review;
import com.example.backend.model.User;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.ReviewRepository;
import com.example.backend.request.ReviewRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;
    private ProductService productService;


    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product= productService.findProductById(req.getProductId());

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());


        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
