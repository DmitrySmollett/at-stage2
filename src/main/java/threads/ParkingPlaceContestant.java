package threads;

import static threads.util.WaitHelper.waitForThisMuchSeconds;

public class ParkingPlaceContestant extends Thread {
  private final int PARKING_TIME = 25;
  private final Car car;
  private final Parking parking;

  public ParkingPlaceContestant(Car car, Parking parking) {
    this.car = car;
    this.parking = parking;
  }

  public void run() {
    System.out.println(car.toString() + " looking for a vacant place");
    if (carJustFoundAVacantPlace()) {
      System.out.println(car.toString() + " has found a vacant place(" + parking.getPlacesLeft() + " left)");
      waitForThisMuchSeconds(PARKING_TIME);
      System.out.println(car.toString() + " just headed home");
      parking.removeCarFromParking(car);
    } else {
      System.out.println(car.toString() + " got tired of waiting and headed home");
    }
  }

  private boolean carJustFoundAVacantPlace() {
    boolean placeIsFound = false;
    try {
      placeIsFound = parking.offerCar(car);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return placeIsFound;
  }
}
