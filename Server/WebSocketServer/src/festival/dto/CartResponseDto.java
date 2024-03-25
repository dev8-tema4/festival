package festival.dto;

public class CartResponseDto {

    private int orderItemId;
    private String name;
    private int price;
    private int count;

    private CartResponseDto() {
    }

    public CartResponseDto(int orderItemId, String name, int price, int count) {
        this.orderItemId = orderItemId;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public static CartResponseDto createCartResponse(int orderItemId, String name, int price, int count) {
        return new CartResponseDto(orderItemId, name, price, count);
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

    @Override
    public String toString() {
        return "CartResponseDto{" +
                "orderItemId=" + orderItemId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
