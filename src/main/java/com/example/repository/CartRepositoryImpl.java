package com.example.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.example.domain.Cart;

@Repository
public class CartRepositoryImpl implements CartRepository{

    private Map<String, Cart> listOfCarts;

    public CartRepositoryImpl() {
        listOfCarts = new HashMap<String,Cart>();
    }

    public Cart create(Cart cart) {  
        if(listOfCarts.keySet().contains(cart.getCartId())) {
            throw new IllegalArgumentException(String.format("해당 장바구니는 없습니다. %s", cart.getCartId()));
        }

        listOfCarts.put(cart.getCartId(), cart);
        return cart;
    }  

    public Cart read(String cartId) { 
        return listOfCarts.get(cartId);
    } 
    
    public void update(String cartId, Cart cart) {
        if(!listOfCarts.keySet().contains(cartId)) {
            throw new IllegalArgumentException(String.format("해당 장바구니가 없습니다. %s",cartId));  // ��ٱ��� ID�� �������� ���� ��� ���� ó��
        }
        listOfCarts.put(cartId, cart); //이미 있는데 put >> 수정
    }
    public void delete(String cartId) {
        if(!listOfCarts.keySet().contains(cartId)) {
            throw new IllegalArgumentException(String.format("해당 장바구니가 없습니다. %s",cartId));  // ��ٱ��� ID�� �������� ������ ���� ó��
        }
        listOfCarts.remove(cartId);
    }
}