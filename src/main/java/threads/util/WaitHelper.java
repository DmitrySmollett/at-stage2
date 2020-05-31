package threads.util;

import java.util.concurrent.TimeUnit;

public class WaitHelper {

  public static void waitForThisMuchSeconds(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
