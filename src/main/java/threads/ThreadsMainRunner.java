package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadsMainRunner {
  public static volatile boolean keepRoutingTraffic;

  public static void main(String[] args) {
    BlockingQueue<Car> parking = new LinkedBlockingQueue<>(7);
    keepRoutingTraffic = true;
    new Thread(() -> CarTraffic.routeTrafficToTheParking(parking)).start();
    try {
      TimeUnit.SECONDS.sleep(30);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("--------------------Traffic halted--------------------");
    keepRoutingTraffic = false;
  }
}
