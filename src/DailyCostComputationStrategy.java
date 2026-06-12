import java.time.LocalDate;

public class DailyCostComputationStrategy implements CostComputationStrategy {
    @Override
    public int compute(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {

        int daysCount = (int) (endDate.toEpochDay() - startDate.toEpochDay() + 1);
        return vehicle.getVehicleType().getDailyPrice() * daysCount;

    }
}









