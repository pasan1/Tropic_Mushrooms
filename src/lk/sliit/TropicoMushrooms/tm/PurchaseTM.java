package lk.sliit.TropicoMushrooms.tm;

import java.util.Date;

public class PurchaseTM {
    private int purchaseId;
    private int userId;
    private Date date;
    private String description;
    private double qty;
    private String unit;
    private double totalPrice;

    public PurchaseTM() {
    }

    public PurchaseTM(int purchaseId, int userId, Date date, String description, double qty, String unit, double totalPrice) {
        this.setPurchaseId(purchaseId);
        this.setUserId(userId);
        this.setDate(date);
        this.setDescription(description);
        this.setQty(qty);
        this.setUnit(unit);
        this.setTotalPrice(totalPrice);
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "PurchaseTM{" +
                "purchaseId=" + purchaseId +
                ", userId=" + userId +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", unit='" + unit + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
