package threads;

import static threads.ThreadsMainRunner.keepRoutingTraffic;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CarTraffic {
  private static Random rand = new Random();

  public static void routeTrafficToTheParking(BlockingQueue<Car> parking) {
    while (keepRoutingTraffic) {
      try {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(3000));
        new Thread(
                () -> {
                  Car car = createRandomCar();
                  System.out.println(car.toString() + " looking for a vacant place");
                  boolean parkingPlaceIsFound = false;
                  try {
                    parkingPlaceIsFound =
                        parking.offer(car, car.getPatienceInSeconds(), TimeUnit.SECONDS);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  if (parkingPlaceIsFound) {
                    try {
                      System.out.println(car.toString() + " has found a vacant place"
                          + " (" + parking.remainingCapacity() + " left)");
                      TimeUnit.SECONDS.sleep(20);
                      parking.remove(car);
                      System.out.println(car.toString() + " just headed home");
                    } catch (InterruptedException e) {
                      e.printStackTrace();
                    }
                  } else {
                    System.out.println(car.toString() + " got tired of waiting and headed home");
                  }
                })
            .start();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
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
