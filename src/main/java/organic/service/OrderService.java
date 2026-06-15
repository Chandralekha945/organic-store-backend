package organic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import organic.model.Order;
import organic.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> getOrders() {
        return repository.findAll();
    }

    public Order getOrderById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Order placeOrder(Order order) {
        return repository.save(order);
    }

    public Order updateStatus(Long id, String status) {
        Order order = repository.findById(id).orElse(null);
        if (order == null) return null;
        order.setStatus(status);
        return repository.save(order);
    }
}