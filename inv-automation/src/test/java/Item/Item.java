package Item;

public class Item {
    private String name;
    private String price_for_quantity;
    private String quantity_unit;
    private String price;

    public void setPrice(String price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice_for_quantity(String price_for_quantity) {
        this.price_for_quantity = price_for_quantity;
    }

    public void setQuantity_unit(String quantity_unit) {
        this.quantity_unit = quantity_unit;
    }

    public Item(String name, String price_for_quantity, String quantity_unit, String price){
        this.name = name;
        this.price_for_quantity=price_for_quantity;
        this.quantity_unit=quantity_unit;
        this.price= price;
    }

}
