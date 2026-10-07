public enum VehicleType {
    Economy(500), Luxury(1000);

    private int dailyPrice;

    private VehicleType(int dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public int getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(int dailyPrice) {
        this.dailyPrice = dailyPrice;
    }
}
