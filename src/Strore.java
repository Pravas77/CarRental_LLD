import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Strore {

    private int id;
    private List<Vehicle> vehicles;

    public Strore(int id, List<Vehicle> vehicles) {
        this.id = id;
        this.vehicles = vehicles;
    }

    public int getId() {
        return id;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Vehicle> searchVehicles(LocalDate startDate, LocalDate endDate, VehicleType vehicleType) {

        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.searchVehicle(startDate, endDate, vehicleType)) availableVehicles.add(vehicle);
        }

        return availableVehicles;
    }
}
