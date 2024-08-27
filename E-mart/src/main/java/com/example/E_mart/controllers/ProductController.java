package com.example.E_mart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_mart.entities.Product;
import com.example.E_mart.repositories.ProductRepository;
import com.example.E_mart.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService prSrv;

    @Autowired
    ProductRepository prRepo;

    @PostMapping("/add")
    public String addProduct(@RequestBody Product pr){
        prSrv.addProduct(pr);
        return "Product added successfully";
    }

    @PostMapping("/update")
    public String updateProduct(@RequestBody Product pr){
        prSrv.addProduct(pr);
        return "Product updated successfully";
    }

    @GetMapping("/readAll")
    public List<Product> readProduct(){
        return prSrv.readAll();
    }

    @PostMapping("/delete/{id}")
    public String removeProduct(@PathVariable int id){
        prSrv.removeProduct(id);
        return "Product removed successfully";
    }

    @PostMapping("/search/{keyword}")
    public List<Product> searchProduct(@PathVariable String keyword){
        return prSrv.searchProducts(keyword);
    }

    @GetMapping("/sort")
    public List<Product> sortProduct(@RequestParam(defaultValue="price") String attribute, @RequestParam(defaultValue="asc") String order){
      
           return prSrv.sortProducts(attribute, order);
    }

    @PostMapping("/payment/{id}")
    public String makePayment(@PathVariable int id){
       return  prSrv.payment(id);
        
    }




    
}
