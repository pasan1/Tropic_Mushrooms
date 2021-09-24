package lk.sliit.TropicoMushrooms.tm;

public class UserTM {
    private int userId;
    private String name;
    private String nic;
    private String mobileNumber;
    private String address;
    private String designation;
    private String userName;

    public UserTM() {
    }

    public UserTM(int userId, String name, String nic, String mobileNumber, String address, String designation, String userName) {
        this.userId = userId;
        this.name = name;
        this.nic = nic;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.designation = designation;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserTM{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", address='" + address + '\'' +
                ", designation='" + designation + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
