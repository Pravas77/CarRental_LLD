public enum VehicleType {
    Economy(500,50),Luxury(1000,100),SEDEN(800,80),SUV(600,60);

    private int dailyPrice;
    private int hourlyPrice;

    private VehicleType(int dailyPrice, int hourlyPrice) {
        this.dailyPrice = dailyPrice;
        this.hourlyPrice = hourlyPrice;
    }

    public int getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(int dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public int getHourlyPrice() {
        return hourlyPrice;
    }

    public void setHourlyPrice(int hourlyPrice) {
        this.hourlyPrice = hourlyPrice;
    }
}
