package lk.sliit.TropicoMushrooms.entity;

import java.sql.Date;

public class CustomEntity {
    private Date date;
    private double amount;

    public CustomEntity() {
    }

    public CustomEntity(Date date, double amount) {
        this.setDate(date);
        this.setAmount(amount);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }
}
