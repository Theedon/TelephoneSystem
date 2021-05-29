package sample;

public class Contacts {

    private int serialNumber;
    private int id;
    private String name;
    private String email;
    private String gender;
    private String phone;

    public Contacts(int serialNumber, int id, String name, String email, String gender, String phone) {
        this.serialNumber= serialNumber;
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }

    public int getSerialNumber(){
        return serialNumber;
    }

    public void setSerialNumber(){
        this.serialNumber= serialNumber;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
