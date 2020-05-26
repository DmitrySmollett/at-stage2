package threads;

import java.util.Objects;

public class Car {
  private String model;
  private String color;
  private int number;
  private long patienceInSeconds;

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public long getPatienceInSeconds() {
    return patienceInSeconds;
  }

  public void setPatienceInSeconds(long patienceInSeconds) {
    this.patienceInSeconds = patienceInSeconds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Car car = (Car) o;
    return number == car.number
        && patienceInSeconds == car.patienceInSeconds
        && model.equals(car.model)
        && color.equals(car.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, color, number, patienceInSeconds);
  }

  @Override
  public String toString() {
    return String.format("%-5s %-23s number %-3s ", color, model, number);
  }
}
