package com.example.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart{
    private String cartId;  // ��ٱ��� ID
    private Map<String,CartItem> cartItems; // ��ٱ��� �׸�
    private int grandTotal;  // �Ѿ�

    public Cart() {  
        cartItems = new HashMap<String, CartItem>();
        grandTotal = 0;
    }  

    public Cart(String cartId) {  
        this();
        this.cartId = cartId;
    }  

    public String getCartId() {  
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getGrandTotal() {
        return grandTotal;
    }  

    public void updateGrandTotal() { 
        grandTotal= 0;
        for(CartItem item : cartItems.values()){
            grandTotal = grandTotal + item.getTotalPrice();
        }
    }  

    public void addCartItem(CartItem item) { // 새로만든 cartItem
        String bookId = item.getBook().getBookId();  

        if(cartItems.containsKey(bookId)) {  // cart에 이미 있더라 >> 수량 더하기 >> 추가하더라
            CartItem cartItem = cartItems.get(bookId);  
            cartItem.setQuantity(cartItem.getQuantity()+item.getQuantity());  
            cartItems.put(bookId, cartItem);  
        } else { // 없으면, cartItems 목록에 추가
            cartItems.put(bookId, item);  
        }
        updateGrandTotal();  
    }
    
    public void removeCartItem(CartItem item) {
        String bookId = item.getBook().getBookId();
        cartItems.remove(bookId);  
        updateGrandTotal();  
    }
    
    @Override  
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cart other = (Cart) obj;
        if (cartId == null) {
            if (other.cartId != null)
                return false;
        } else if (!cartId.equals(other.cartId))
            return false;
        return true;
    }  
}