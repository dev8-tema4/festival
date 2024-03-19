package festival.db.dao;

public class OrderResponseDto {

    private String name;
    private int price;
    private int totalPrice;
    private int count;

    public OrderResponseDto(String name, int price, int totalPrice, int count) {
        this.name = name;
        this.price = price;
        this.totalPrice = totalPrice;
        this.count = count;
    }

}
