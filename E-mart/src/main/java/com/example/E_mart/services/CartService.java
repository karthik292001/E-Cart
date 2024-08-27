package com.example.E_mart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_mart.entities.Cart;
import com.example.E_mart.entities.Product;
import com.example.E_mart.repositories.CartRepositoty;
import com.example.E_mart.repositories.ProductRepository;

@Service
public class CartService {

    @Autowired
    CartRepositoty crRepo;

    @Autowired
    ProductRepository prRepo;

    public String addCart(Cart cr) {

        boolean isExist=false;
        List<Cart> crt=crRepo.findAll();
        for(Cart c:crt){
            if(c.getProduct().getId()==cr.getProduct().getId()){
                isExist=true;
            }
        }
        if(!isExist){
            Product prod = prRepo.findById(cr.getProduct().getId()).orElse(null);
            if(cr.getQuantity()<=prod.getQuantity())
            {
                crRepo.save(cr);
                // prod.setQuantity(prod.getQuantity()-cr.getQuantity());
                // prRepo.save(prod);
                return "Cart added successfully";
             }
            return "Oops! product is out of stock..";
        }
        return "Product already added to cart";
        
    }

    public String increQuantity(int id) {
        Cart cr=crRepo.findById(id).orElse(null);

        if(cr.getProduct().getQuantity()>0){
            cr.setQuantity(cr.getQuantity()+1);
            crRepo.save(cr);
            // Product pr= cr.getProduct();
            // pr.setQuantity(pr.getQuantity()-1);
            // prRepo.save(pr);
        }else{
            return "Oops! product is out of stock..";
        }
        return "Quantity increased successfully";
    }

    public List<Cart> readAll() {
        return crRepo.findAll();
    }

    public void deleteCart(int id) {
        crRepo.deleteById(id);
    }

    public void payment() {
        List<Cart> cr=crRepo.findAll();
        for(Cart c:cr){
            Product p=prRepo.findById(c.getProduct().getId()).orElse(null);
            p.setQuantity(p.getQuantity()-c.getQuantity());
            prRepo.save(p);
        }

    }
    
}
