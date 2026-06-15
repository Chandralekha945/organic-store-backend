package organic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import organic.model.Cart;
import organic.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    public List<Cart> getCartItems() {
        return repository.findAll();
    }

    public Cart save(Cart cart) {
        return repository.save(cart);
    }

}