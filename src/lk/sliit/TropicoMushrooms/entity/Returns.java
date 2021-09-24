package lk.sliit.TropicoMushrooms.entity;

public class Returns implements SuperEntity {
    private int returnId;
    private int orderId;
    private int userId;
    private String reason;

    public Returns() {
    }

    public Returns(int returnId, int orderId, int userId, String reason) {
        this.setReturnId(returnId);
        this.setOrderId(orderId);
        this.setUserId(userId);
        this.setReason(reason);
    }

    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Returns{" +
                "returnId=" + returnId +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", reason='" + reason + '\'' +
                '}';
    }
}
