import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class StoreManager {
    private Map<City, List<Strore>> storeFactory;

    public StoreManager(Map<City, List<Strore>> storeFactory) {
        this.storeFactory = storeFactory;
    }

    public List<Strore> searchStores(City city){
        return storeFactory.getOrDefault(city,List.of());
    }

    public List<Vehicle> searchVehicles(LocalDate startDate, LocalDate endDate, VehicleType vehicleType,Strore strore) {
        return strore.searchVehicles(startDate, endDate, vehicleType);
    }
}
