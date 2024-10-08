package com.example.backend.service;

import com.example.backend.exception.ProductException;
import com.example.backend.model.Product;
import com.example.backend.model.Rating;
import com.example.backend.model.User;
import com.example.backend.repository.RatingRepository;
import com.example.backend.request.RatingRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class RatingServiceImpl implements RatingService{

    private RatingRepository ratingRepository;
    private ProductService productService;


    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {

        Product product = productService.findProductById(req.getProductId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {

        return ratingRepository.getAllProductRating(productId);
    }
}
