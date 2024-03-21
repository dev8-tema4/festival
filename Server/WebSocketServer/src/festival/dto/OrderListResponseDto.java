package festival.dto;

public class OrderListResponseDto {
    private int orderItemId;
    private String name;
    private int price;
    private int count;

    public OrderListResponseDto() {
    }

    public OrderListResponseDto(int orderItemId, String name, int price, int count) {
        this.orderItemId = orderItemId;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public static OrderListResponseDto createOrderList(int orderItemId, String name, int price, int count) {
        return new OrderListResponseDto(orderItemId, name, price, count);
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
