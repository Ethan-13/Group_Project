package final_project;

public class Guest {

    private String name;
    private String address;
    private String phone;
    private int days;

    public Guest(String name, String address, String phone, int days) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public int getdays() {
        return days;
    }
}
