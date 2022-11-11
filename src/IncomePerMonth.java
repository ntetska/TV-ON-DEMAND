import java.util.Date;

public class IncomePerMonth {
    private String year_and_month;
    private double income;
    private String type;

    public IncomePerMonth(String year_and_month, double income, String type) {
        this.year_and_month = year_and_month;
        this.income = income;
        this.type = type;
    }

    public String getYearAndMonth() {
        return year_and_month;
    }

    public double getIncome() {
        return income;
    }

    public String getType() {
        return type;
    }
}
