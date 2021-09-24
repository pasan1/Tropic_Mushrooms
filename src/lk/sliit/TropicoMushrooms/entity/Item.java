package lk.sliit.TropicoMushrooms.entity;

public class Item implements SuperEntity{
    private int itemCode;
    private String description;
    private double price;
    private double qtyOnHand;
    private String unit;

    public Item() {
    }

    public Item(int itemCode, String description, double price, double qtyOnHand, String unit) {
        this.setItemCode(itemCode);
        this.setDescription(description);
        this.setPrice(price);
        this.setQtyOnHand(qtyOnHand);
        this.setUnit(unit);
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(double qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode=" + itemCode +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", qtyOnHand=" + qtyOnHand +
                ", unit='" + unit + '\'' +
                '}';
    }
}
