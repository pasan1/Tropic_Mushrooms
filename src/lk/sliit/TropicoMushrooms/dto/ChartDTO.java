package lk.sliit.TropicoMushrooms.dto;

public class ChartDTO {

    private String date;
    private double amount;

    public ChartDTO() {
    }

    public ChartDTO(String date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
