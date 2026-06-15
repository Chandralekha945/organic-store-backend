package organic.model;

import jakarta.persistence.*;

@Entity
@Table(name="cart")

public class Cart {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Integer quantity;

    private Double price;

    public Cart() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName=productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity=quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price=price;
    }

}