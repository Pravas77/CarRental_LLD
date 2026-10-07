import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello");

        Vehicle vehicle1 = new Vehicle("EC-1", VehicleType.Economy, null);
        Vehicle vehicle2 = new Vehicle("LUX-1", VehicleType.Luxury, null);

        Store store1 = new Store(1,List.of(vehicle1,vehicle2));
        vehicle1.setStore(store1);
        vehicle2.setStore(store1);

        StoreManager storeManager = new StoreManager(
                Map.of(City.DELHI,List.of(store1))
        );

        BookingManager bookingManager = new BookingManager(new DailyCostComputationStrategy());

        User user1 = new User(1);
        User user2 = new User(2);

        CarRentalSystem carRentalSystem = new CarRentalSystem(storeManager,bookingManager,List.of(user1,user2));


        System.out.println(carRentalSystem.searchVehicles(City.DELHI,VehicleType.Economy));
        System.out.println(carRentalSystem.searchVehicles(City.MUMBAI,VehicleType.Economy));
        System.out.println(carRentalSystem.searchVehicles(City.DELHI,VehicleType.Luxury));

        Thread thread1 = new Thread(() -> {

            System.out.println(carRentalSystem.bookVehicle(
                    user1,
                    vehicle1,
                    LocalDate.of(2027,1,1),
                    LocalDate.of(2027,1,3),
                    new RazorparPaymentStrategy() ));

        });

        Thread thread2 = new Thread(() -> {

            System.out.println(carRentalSystem.bookVehicle(
                    user2,
                    vehicle1,
                    LocalDate.of(2027,1,2),
                    LocalDate.of(2027,1,4),
                    new RazorparPaymentStrategy() ));

        });

        thread1.start();
//        Thread.sleep(100);
        thread2.start();

    }
}