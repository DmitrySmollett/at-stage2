package threads;

import static threads.ThreadsMainRunner.keepRoutingTraffic;
import static threads.util.WaitHelper.waitForThisMuchSeconds;

import java.util.Random;

public class TrafficControl {
  private static final Random rand = new Random();

  public static void routeTrafficToTheParking(Parking parking) {
    while (keepRoutingTraffic) {
      waitForThisMuchSeconds(rand.nextInt(2) + 1);
      new ParkingPlaceContestant(createRandomCar(), parking).start();
    }
  }

  public static Car createRandomCar() {
    Car car = new Car();
    String[] carModelTemplate = {
      "AMC Hornet",
      "Audi A3",
      "BMW 3 Series",
      "Ford Escort",
      "Honda S2000",
      "Mercedes-Benz S-Class",
      "Nissan Micra",
      "Volkswagen Golf",
      "Volvo 200 series"
    };
    String[] carColorTemplate = {
      "Green", "Red", "White", "Black", "Grey", "Pink", "Blue", "Brown", "Lime"
    };
    car.setModel(carModelTemplate[rand.nextInt(carColorTemplate.length)]);
    car.setColor(carColorTemplate[rand.nextInt(carColorTemplate.length)]);
    car.setNumber(rand.nextInt(1000));
    car.setPatienceInSeconds((long) rand.nextInt(20) + 10);
    return car;
  }
}
