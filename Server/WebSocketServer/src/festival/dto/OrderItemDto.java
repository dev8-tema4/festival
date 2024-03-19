package festival.dto;

public class OrderItemDto {
    private int orderId;
    private int itemId;
    private int count;
    private int price;

    public OrderItemDto() {
    }

    public OrderItemDto(int orderId, int itemId, int count, int price) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.count = count;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
