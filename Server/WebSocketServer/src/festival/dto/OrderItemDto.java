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

    public int getItemId() {
        return itemId;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }
}
