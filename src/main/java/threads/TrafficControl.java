package threads;

import static threads.ThreadsMainRunner.keepRoutingTraffic;
import static threads.util.RandomTemplate.createRandomCar;
import static threads.util.RandomTemplate.createRandomNumberFromZeroTo;
import static threads.util.WaitHelper.waitForThisMuchSeconds;

public class TrafficControl {

  public static void routeTrafficToTheParking(Parking parking) {
    while (keepRoutingTraffic) {
      waitForThisMuchSeconds(createRandomNumberFromZeroTo(1) + 1);
      new ParkingPlaceContestant(createRandomCar(), parking).start();
    }
  }
}
