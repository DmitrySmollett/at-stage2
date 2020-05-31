package threads.util;

import java.util.Random;
import threads.Car;

public class RandomTemplate {
  private static final Random rand = new Random();

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

  public static int createRandomNumberFromZeroTo (int maxRange) {
    return rand.nextInt(maxRange + 1);
  }

}
