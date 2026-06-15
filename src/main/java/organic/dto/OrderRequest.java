package organic.dto;

public class OrderRequest {

    private String customerName;

    private Double total;

    public OrderRequest() {}

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

}