package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.domain.Book;
import com.example.domain.Cart;
import com.example.domain.CartItem;
import com.example.service.BookService;
import com.example.service.CartService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private BookService bookService;

    @GetMapping  
    public String requestCartId(HttpServletRequest request) {
        String sessionid = request.getSession(true).getId();
        return "redirect:/cart/"+ sessionid;
    }  

    @PostMapping  
    public @ResponseBody Cart create(@RequestBody Cart cart ) {
        return cartService.create(cart);
    }  

    @GetMapping( "/{cartId}") 
    public String requestCartList(@PathVariable(value = "cartId") String cartId, Model model) {
        Cart cart = cartService.read(cartId);
        model.addAttribute("cart",cart);
        return "cart";
    } 

    @PutMapping("/{cartId}") 
    public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId) {
        return cartService.read(cartId);
    } 
    
    @PutMapping("/add/{bookId}")  
    @ResponseStatus(value = HttpStatus.NO_CONTENT)  
    public void addCartByNewItem(@PathVariable("bookId") String bookId, HttpServletRequest request) {
        String sessionId = request.getSession(true).getId();  
        Cart cart = cartService.read(sessionId);  
        if(cart== null)
        	cart = cartService.create(new Cart(sessionId));
        
        // 사용자의 장바구니 정보 읽어오기 없으면 만들기
        // bookService.getBookById(bookId);
        
        Book book = bookService.getBookById(bookId);  
        if(book == null)
        	throw new IllegalArgumentException("도서를 찾을 수 없습니다.");
        // 장바구니에 담을 도서 못 찾은 경우        
        
        // 찾은 경우, 장바구니 리스트에 book item을 추가하겠습니다.
        // 본인 카트에 대해서만 반영 되고
        cart.addCartItem(new CartItem(book));       
        
        
        cartService.update(sessionId, cart);  
    } 
    
    @PutMapping("/remove/{bookId}")  
    public String removeCartByItem(@PathVariable("bookId") String bookId, HttpServletRequest request) {
        String sessionId = request.getSession(true).getId();  
        Cart cart = cartService.read(sessionId);  
        if(cart== null)
            cart = cartService.create(new Cart(sessionId));
            Book book = bookService.getBookById(bookId);  
        if(book == null)
            throw new IllegalArgumentException("도서를 찾을 수 없습니다.");
        cart.removeCartItem(new CartItem(book));  
        cartService.update(sessionId, cart);
        return "redirect:/cart/" + sessionId;
    }  
    
    @DeleteMapping("/{cartId}")  
    public String deleteCartList(@PathVariable(value = "cartId") String cartId) {
        cartService.delete(cartId);
        return "redirect:/cart";
    }  
}