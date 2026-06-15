package organic.model;

import jakarta.persistence.*;

@Entity
@Table(name="orders")

public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private Double total;

    private String status;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String items;       // e.g. "Sesame Oil x2, Forest Honey x1"
    private String paymentMethod;

    public Order() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName=customerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total=total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status=status;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getItems() { return items; }
    public void setItems(String items) { this.items = items; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

}