package festival.dto;

public class OrderListResponseDto {
    private String name;
    private int price;
    private int totalPrice;
    private int count;

    public OrderListResponseDto() {
    }

    public OrderListResponseDto(String name, int price, int totalPrice, int count) {
        this.name = name;
        this.price = price;
        this.totalPrice = totalPrice;
        this.count = count;
    }
}
