package organic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import organic.model.Cart;

public interface CartRepository
extends JpaRepository<Cart, Long>{

}