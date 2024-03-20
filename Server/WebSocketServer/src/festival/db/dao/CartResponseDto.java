package festival.db.dao;

public class CartResponseDto {

    private String name;
    private int price;
    private int totalPrice;
    private int count;

    public CartResponseDto(String name, int price, int totalPrice, int count) {
        this.name = name;
        this.price = price;
        this.totalPrice = totalPrice;
        this.count = count;
    }

}
