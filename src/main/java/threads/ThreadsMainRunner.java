package threads;

import static threads.util.WaitHelper.waitForThisMuchSeconds;

public class ThreadsMainRunner {
  public static volatile boolean keepRoutingTraffic;

  public static void main(String[] args) {
    Parking parking = new Parking(5);
    keepRoutingTraffic = true;
    new Thread(() -> TrafficControl.routeTrafficToTheParking(parking)).start();
    waitForThisMuchSeconds(30);
    System.out.println("--------------------Traffic halted--------------------");
    keepRoutingTraffic = false;
  }
}
