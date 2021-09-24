package lk.sliit.TropicoMushrooms.dto;

public class OrderDetailDTO {
    private int orderId;
    private int itemCode;
    private double qty;
    private String unit;
    private double unitPrice;

    private String itemName;
    private Double total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderId, int itemCode, double qty, String unit, double unitPrice) {
        this.setOrderId(orderId);
        this.setItemCode(itemCode);
        this.setQty(qty);
        this.setUnit(unit);
        this.setUnitPrice(unitPrice);
    }

    public OrderDetailDTO(int itemCode, String itemName, double unitPrice, double qty, String unit, Double total) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.unit = unit;
        this.total = total;
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
        return "OrderDetailDTO{" +
                "orderId=" + getOrderId() +
                ", itemCode=" + getItemCode() +
                ", qty=" + getQty() +
                ", unit='" + getUnit() + '\'' +
                ", unitPrice=" + getUnitPrice() +
                '}';
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
