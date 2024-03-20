package festival.dto;

public class ItemDto {
    private int item_id;
    private String name;
    private int price;
    private int stockQuantity;

    public ItemDto(int item_id, String name, int price, int stockQuantity) {
        this.item_id = item_id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
