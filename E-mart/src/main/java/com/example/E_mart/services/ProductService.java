package com.example.E_mart.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

import com.example.E_mart.entities.Cart;
import com.example.E_mart.entities.Product;
import com.example.E_mart.repositories.CartRepositoty;
import com.example.E_mart.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository prRepo;

    @Autowired
    CartRepositoty crRepo;

    public void addProduct(Product pr) {
        prRepo.save(pr);
    }

    public List<Product> readAll() {
        return prRepo.findAll();
    }

    public void removeProduct(int id) {
        List<Cart> carts=crRepo.findAll();
        for(Cart c:carts){
            if(c.getProduct().getId()==id){
                crRepo.deleteById(c.getId());
            }
        }
        prRepo.deleteById(id);
    }

    public List<Product> searchProduct(String category) {
        return prRepo.findAllByCategory(category);
    }
    public List<Product> searchProducts(String keyword) {
        return prRepo.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword, keyword);
    }



    public List<Product> sortProducts(String attribute, String order) {
  
        List<Product> allProducts = prRepo.findAll();
        Comparator<Product> comparator = getComparator(attribute);
        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        } else if (!"asc".equalsIgnoreCase(order)) {
            throw new IllegalArgumentException("Invalid sort order: " + order);
        }
 
        return allProducts.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
 
    private Comparator<Product> getComparator(String attribute) {
        switch (attribute.toLowerCase()) {
            case "price":
                return (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice());
            case "rating":
                return (p1, p2) -> Double.compare(p1.getRating(), p2.getRating());
            default:
                throw new IllegalArgumentException("Invalid sort attribute: " + attribute);
        }
    }

    public String payment(int id) {
        Product p=prRepo.findById(id).orElse(null);
        if(p.getQuantity()>0){
            p.setQuantity(p.getQuantity()-1);
            prRepo.save(p);
            return "Payment is done! Thank you for shopping with us..";
        }
        return "Oops! Product is out of Stock....";
        
    }
 
    
    

 
 
}
