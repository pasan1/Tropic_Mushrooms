package lk.sliit.TropicoMushrooms.entity;

public class User implements SuperEntity {
    private int userId;
    private String name;
    private String address;
    private String mobile;
    private String nic;
    private String designation;
    private String userName;
    private String password;
    private int q1;
    private int q2;
    private int q3;
    private String a1;
    private String a2;
    private String a3;

    public User() {
    }

    public User(int userId, String name, String address, String mobile, String nic, String designation, String userName, String password, int q1, int q2, int q3, String a1, String a2, String a3) {
        this.setUserId(userId);
        this.setName(name);
        this.setAddress(address);
        this.setMobile(mobile);
        this.setNic(nic);
        this.setDesignation(designation);
        this.setUserName(userName);
        this.setPassword(password);
        this.setQ1(q1);
        this.setQ2(q2);
        this.setQ3(q3);
        this.setA1(a1);
        this.setA2(a2);
        this.setA3(a3);
    }

    public User(int userId, String name, String address, String mobile, String nic, String designation) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.nic = nic;
        this.designation = designation;
    }

    public User(int userId, int q1, int q2, int q3, String a1, String a2, String a3) {
        this.userId = userId;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }

    public User(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public User(int userId, String name, String address, String mobile, String nic, String designation, String userName) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.nic = nic;
        this.designation = designation;
        this.userName = userName;
    }

    public User(int userId, String password, int q1, int q2, int q3, String a1, String a2, String a3) {
        this.userId = userId;
        this.password = password;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public int getQ3() {
        return q3;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nic='" + nic + '\'' +
                ", designation='" + designation + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", q1=" + q1 +
                ", q2=" + q2 +
                ", q3=" + q3 +
                ", a1='" + a1 + '\'' +
                ", a2='" + a2 + '\'' +
                ", a3='" + a3 + '\'' +
                '}';
    }
}
