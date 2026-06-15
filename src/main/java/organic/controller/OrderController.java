package organic.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import organic.model.Order;
import organic.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService service;

    // GET all orders
    @GetMapping
    public List<Order> getOrders() {
        return service.getOrders();
    }

    // GET order by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        Order order = service.getOrderById(id);
        if (order == null) {
            Map<String, String> err = new HashMap<>();
            err.put("message", "Order not found");
            return ResponseEntity.status(404).body(err);
        }
        return ResponseEntity.ok(order);
    }

    // POST place new order
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        order.setStatus("PENDING");
        Order saved = service.placeOrder(order);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order placed successfully");
        response.put("order", saved);
        return ResponseEntity.ok(response);
    }

    // POST order from chat widget
    @PostMapping("/chat-order")
    public ResponseEntity<?> chatOrder(@RequestBody ChatOrderRequest req) {
        Order order = new Order();
        order.setCustomerName(req.getName());
        order.setTotal(req.getTotal());
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus("PENDING");
        }
        Order saved = service.placeOrder(order);
        Map<String, Object> res = new HashMap<>();
        res.put("orderId", saved.getId());
        res.put("message", "Order placed successfully");
        return ResponseEntity.ok(res);
    }

    // PATCH update order status
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        Order updated = service.updateStatus(id, body.get("status"));
        if (updated == null) {
            Map<String, String> err = new HashMap<>();
            err.put("message", "Order not found");
            return ResponseEntity.status(404).body(err);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Status updated");
        response.put("order", updated);
        return ResponseEntity.ok(response);
    }

    // ── Inner class for chat order request ────────────────────────────────────
    public static class ChatOrderRequest {

        private String name;
        private String phone;
        private String address;
        private Double total;

        public ChatOrderRequest() {}

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }

        public Double getTotal() { return total; }
        public void setTotal(Double total) { this.total = total; }
    }
}