package lk.sliit.TropicoMushrooms.entity;

import java.sql.Date;

public class Orders implements SuperEntity{
    private int orderId;
    private Date date;
    private int customerId;
    private int userId;

    public Orders() {
    }

    public Orders(int orderId, Date date, int customerId, int userId) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setUserId(userId);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", customerId=" + customerId +
                ", userId=" + userId +
                '}';
    }
}
