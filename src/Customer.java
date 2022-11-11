import java.util.Date;

public class Customer {
    public int customer_id;
    String first_name;
    String last_name;
    String email;
    String address;
    String city;
    public boolean active;
    public Date create_date;
    public String record_type;

    public Customer(int customer_id,
                    String first_name,
                    String last_name,
                    String email,
                    String address,
                    String city,
                    boolean active,
                    Date create_date,
                    String record_type)
    {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.address = address;
        this.city = city;
        this.active = active;
        this.create_date = create_date;
        this.record_type = record_type;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public boolean isActive() {
        return active;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public String getRecord_type() {
        return record_type;
    }

    @Override
    public String toString() {
        return "ID:"+getCustomer_id() + "\n" +
                "First Name:" + getFirst_name() + "\n" +
                "Last Name:" + getLast_name() + "\n" +
                "Email:" + getEmail() + "\n" +
                "Address:" + getAddress() + "\n" +
                "City:" + getCity() + "\n" +
                "Active:" + isActive() + "\n" +
                "Create date:" + getCreate_date() +"\n" +
                "Record type:" + getRecord_type();
    }

    public String isActiveText(){
        return active ? "Active" : "Inactive";
    }
}
