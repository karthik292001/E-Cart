package com.example.E_mart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_mart.entities.Cart;
import com.example.E_mart.entities.Product;
import com.example.E_mart.repositories.ProductRepository;
import com.example.E_mart.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartProduct {

    @Autowired
    CartService crSrv;

    @Autowired
    ProductRepository prRepo;

    @PostMapping("/add")
    public String addCart(@RequestBody Cart cr){
        Product p=prRepo.findById(cr.getProduct().getId()).orElse(null);
        cr.setProduct(p);
        crSrv.addCart(cr);
        List<Cart> carts=crSrv.readAll();
        int totalprice=0;
        for(Cart c:carts){
            totalprice +=c.getProduct().getPrice()*c.getQuantity();
        }
        return "Total Price:"+totalprice;
    }

    @GetMapping("/readAll")
    public List<Cart> readCart(){
        return crSrv.readAll();
    }

    @PostMapping("/increment/{id}")
    public String increQuantityByOne(@PathVariable int id){
        return crSrv.increQuantity(id);
    }

    @PostMapping("/delete/{id}")
    public String deleteCart(@PathVariable int id){
        crSrv.deleteCart(id);
        return "Cart deleted successfully";
    }

    @GetMapping("/cartPayment")
    public String cartItemsPayment(){
        crSrv.payment();
        return "Payment is successfull!";
    }
    
}
