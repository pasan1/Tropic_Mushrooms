package lk.sliit.TropicoMushrooms.entity;

public class OrderDetail implements SuperEntity{
    private int orderId;
    private int itemCode;
    private double qty;
    private String unit;
    private double unitPrice;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int itemCode, double qty, String unit, double unitPrice) {
        this.setOrderId(orderId);
        this.setItemCode(itemCode);
        this.setQty(qty);
        this.setUnit(unit);
        this.setUnitPrice(unitPrice);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId=" + orderId +
                ", itemCode=" + itemCode +
                ", qty=" + qty +
                ", unit='" + unit + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
