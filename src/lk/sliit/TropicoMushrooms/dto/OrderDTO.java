package lk.sliit.TropicoMushrooms.dto;

import java.util.Date;
import java.util.ArrayList;

public class OrderDTO {
    private int orderId;
    private Date date;
    private int customerId;
    private int userId;

    private ArrayList<OrderDetailDTO> orderDetails = new ArrayList<>();

    private String name;
    private String address;
    private String mobile;
    private String nic;

    private int itemCode;
    private String description;
    private double price;
    private double qtyOnHand;
    private String unit;

    private int returnId;
    private String reason;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, Date date, int customerId, int userId, ArrayList<OrderDetailDTO> orderDetails) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setUserId(userId);
        this.setOrderDetails(orderDetails);
    }

    public OrderDTO(int orderId, Date date, int customerId, int userId) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setUserId(userId);
    }

    public OrderDTO(int customerId, String name, String address, String mobile, String nic) {
        this.setCustomerId(customerId);
        this.setName(name);
        this.setAddress(address);
        this.setMobile(mobile);
        this.setNic(nic);
    }

    public OrderDTO(int itemCode, String description, double price, double qtyOnHand, String unit) {
        this.setItemCode(itemCode);
        this.setDescription(description);
        this.setPrice(price);
        this.setQtyOnHand(qtyOnHand);
        this.setUnit(unit);
    }

    public OrderDTO(int returnId, int orderId, int userId, String reason) {
        this.returnId = returnId;
        this.orderId = orderId;
        this.userId = userId;
        this.reason = reason;
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

    public ArrayList<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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
        return "OrderDTO{" +
                "orderId=" + getOrderId() +
                ", date=" + getDate() +
                ", customerId=" + getCustomerId() +
                ", userId=" + getUserId() +
                ", orderDetails=" + getOrderDetails() +
                ", name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", mobile='" + getMobile() + '\'' +
                ", nic='" + getNic() + '\'' +
                ", itemCode=" + getItemCode() +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                ", qtyOnHand=" + getQtyOnHand() +
                ", unit='" + getUnit() + '\'' +
                '}';
    }

    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
