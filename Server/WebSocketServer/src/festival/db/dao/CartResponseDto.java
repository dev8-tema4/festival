package festival.db.dao;

public class CartResponseDto {

    private String name;
    private int price;
    private int totalPrice;
    private int count;

    private CartResponseDto() {
    }

    public CartResponseDto(String name, int price, int totalPrice, int count) {
        this.name = name;
        this.price = price;
        this.totalPrice = totalPrice;
        this.count = count;
    }

    public static CartResponseDto createCartResponse(String name, int price, int totalPrice, int count) {
        return new CartResponseDto(name, price, totalPrice, count);
    }

    @Override
    public String toString() {
        return "CartResponseDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", count=" + count +
                '}';
    }
}
