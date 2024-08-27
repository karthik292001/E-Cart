package com.example.E_mart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_mart.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

    List<Product> findAllByName(String name);

    List<Product> findAllByCategory(String category);

    List<Product> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String keyword, String keyword2, String keyword3);

}
