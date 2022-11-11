import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FirstFiveRentals {
    private int id;
    private String title;
    private int rentals;

    public FirstFiveRentals(int id, String title, int rentals) {
        this.id = id;
        this.title = title;
        this.rentals = rentals;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getRentals() {
        return rentals;
    }
}
