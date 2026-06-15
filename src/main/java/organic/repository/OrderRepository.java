package organic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import organic.model.Order;

public interface OrderRepository
extends JpaRepository<Order, Long>{

	List<Order> findAll();

}