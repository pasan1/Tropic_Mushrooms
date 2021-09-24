package lk.sliit.TropicoMushrooms.tm;

public class OrderTM {
    private String itemCode;
    private String itemName;
    private double price;
    private double qty;
    private String unit;
    private double total;

    public OrderTM() {
    }

    public OrderTM(String itemCode, String itemName, double price, double qty, String unit, double total) {
        this.setItemCode(itemCode);
        this.setItemName(itemName);
        this.setPrice(price);
        this.setQty(qty);
        this.setUnit(unit);
        this.setTotal(total);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", unit='" + unit + '\'' +
                ", total=" + total +
                '}';
    }
}
