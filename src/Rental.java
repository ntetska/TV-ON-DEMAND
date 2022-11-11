import java.util.Date;

public class Rental {
    private int rentalID;
    private Date rentalDate;
    private String title;
    private boolean isPaid;

    public Rental(int rentalID, Date rentalDate, String title, boolean isPaid) {
        this.rentalID = rentalID;
        this.rentalDate = rentalDate;
        this.title = title;
        this.isPaid = isPaid;
    }

    public int getRentalID() {
        return rentalID;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public String getTitle(){
        return title;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String isPaidText(){
        return isPaid ? "COMPLETED" : "PENDING";
    }
}
