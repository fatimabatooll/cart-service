package com.glc.cart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glc.cart.Model.Cart;

public interface CartReository extends JpaRepository<Cart, Long>{
    
}
