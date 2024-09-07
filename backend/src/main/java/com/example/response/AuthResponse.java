package com.example.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthResponse {
    private String jwt;
    private String message;
}
