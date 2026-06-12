import java.time.LocalDate;

public interface CostComputationStrategy {
    int compute(Vehicle vehicle, LocalDate startDate, LocalDate endDate);
}
