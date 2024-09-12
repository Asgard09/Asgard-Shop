package com.example.backend.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {

    private Long productId;
    private double rating;
}
