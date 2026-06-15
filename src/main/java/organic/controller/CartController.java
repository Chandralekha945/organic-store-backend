package organic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import organic.model.Cart;
import organic.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")

public class CartController {

    @Autowired
    private CartService service;

    @GetMapping
    public List<Cart> getCart() {
        return service.getCartItems();
    }

    @PostMapping
    public Cart addCart(
            @RequestBody Cart cart) {

        return service.save(cart);
    }

}