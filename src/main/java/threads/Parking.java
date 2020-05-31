package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Parking {
    private final BlockingQueue<Car> parking;

  public Parking (int capacity) {
    parking = new LinkedBlockingQueue<>(capacity);
  }

  public boolean offerCar(Car car) throws InterruptedException {
    return parking.offer(car, car.getPatienceInSeconds(), TimeUnit.SECONDS);
  }

  public void removeCarFromParking(Car car) {
    parking.remove(car);
  }

  public int getPlacesLeft() {
    return parking.remainingCapacity();
  }
}
