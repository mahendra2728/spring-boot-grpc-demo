package dto;

public class OrderDto {

    private int orderId;
    private int userId;
    private String productName;
    private String description;
    double totalPrice;

    public OrderDto(){}

    public OrderDto(int orderId, int userId, String productName, String description, double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.productName = productName;
        this.description = description;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
