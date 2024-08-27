package com.example.E_mart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_mart.entities.Cart;

public interface CartRepositoty extends JpaRepository<Cart, Integer>{
    
}
