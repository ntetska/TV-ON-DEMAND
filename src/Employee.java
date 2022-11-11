public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String email;

    public Employee(int employeeID, String firstName, String lastName, String email) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
