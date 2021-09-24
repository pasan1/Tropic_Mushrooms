package lk.sliit.TropicoMushrooms.dto;

public class CustomerDTO {
    private int customerId;
    private String name;
    private String address;
    private String mobile;
    private String nic;

    public CustomerDTO() {
    }

    public CustomerDTO(int customerId, String name, String address, String mobile, String nic) {
        this.setCustomerId(customerId);
        this.setName(name);
        this.setAddress(address);
        this.setMobile(mobile);
        this.setNic(nic);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
