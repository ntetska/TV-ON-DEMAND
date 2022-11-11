import java.util.Date;

public class Payment {
    private int paymentID;
    private double amount;
    private Date paymentDate;
    private int rentalID;
    private int customerID;

    public Payment(int paymentID, double price, Date paymentDate, int rentalID, int customerID) {
        this.paymentID = paymentID;
        this.amount = price;
        this.paymentDate = paymentDate;
        this.rentalID = rentalID;
        this.customerID = customerID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public int getRentalID() {
        return rentalID;
    }

    public int getCustomerID() {
        return customerID;
    }
}
