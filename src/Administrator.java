public class Administrator extends Employee{
    private boolean admin;
    public Administrator(int employeeID, String firstName, String lastName, String email, boolean admin) {
        super(employeeID, firstName, lastName, email);
        this.admin = admin;
    }

    public boolean getAdmin() {
        return admin;
    }
}
