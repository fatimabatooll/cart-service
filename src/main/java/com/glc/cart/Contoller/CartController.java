package com.glc.cart.Contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glc.cart.Model.Cart;
import com.glc.cart.Repository.CartReository;

@RestController
@RequestMapping("/cart") //http://localhost:8080/cart
@CrossOrigin("*")
public class CartController {
    @Autowired
    private CartReository cartReository;

    @GetMapping("/getall") //http://localhost:8080/cart/getall
    public ResponseEntity<List<Cart>> getAllCart(){
       return ResponseEntity.ok().body(cartReository.findAll());
    }

    @PostMapping("/add") //http://localhost:8080/cart/add
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart){
        return ResponseEntity.ok().body(cartReository.save(cart));
    }
    
    @DeleteMapping("/getbyid/{id}") //http://localhost:8080/cart/getbyid/{id}
    public ResponseEntity<String> deleteAllCart(@PathVariable Long id){
        Optional<Cart> cartOptional = cartReository.findById(id);
        if(cartOptional.isPresent()){
            cartReository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteall") //http://localhost:8080/cart/deleteall
    public ResponseEntity<Void> deleteAllCart(){
        cartReository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PutMapping("updatebyid/{id}")  //http://localhost:8080/cart/deletebyid/{id}
    public ResponseEntity<Cart> updateCartItem(@PathVariable Long id, @RequestBody Cart updatedCart) {
        Optional<Cart> cartOptional = cartReository.findById(id);
        if (cartOptional.isPresent()) {
            Cart existingCart = cartOptional.get();
            existingCart.setImage(updatedCart.getImage());
            existingCart.setProductname(updatedCart.getProductname());
            existingCart.setQuantity(updatedCart.getQuantity());
            existingCart.setTotalPrice(updatedCart.getTotalPrice());
            existingCart.setPrice(updatedCart.getPrice());

            Cart updatedCartItem = cartReository.save(existingCart);
            return ResponseEntity.ok().body(updatedCartItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    
}
