public class Price {
    private int priceID;
    private String recordType;
    private double price;
    private boolean isSerie;

    public Price(int priceID, String recordType, double price, boolean isSerie) {
        this.priceID = priceID;
        this.recordType = recordType;
        this.price = price;
        this.isSerie = isSerie;
    }

    public int getPriceID() {
        return priceID;
    }

    public String getRecordType() {
        return recordType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSerie() {
        return isSerie;
    }
}
