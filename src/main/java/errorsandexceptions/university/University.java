package errorsandexceptions.university;

import java.util.List;
import java.util.Objects;

public class University {
  private String name;
  private List<Faculty> bunchOfFaculties;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Faculty> getBunchOfFaculties() {
    return bunchOfFaculties;
  }

  public void setBunchOfFaculties(List<Faculty> bunchOfFaculties) {
    this.bunchOfFaculties = bunchOfFaculties;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    University that = (University) o;
    return name.equals(that.name) && bunchOfFaculties.equals(that.bunchOfFaculties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, bunchOfFaculties);
  }

  @Override
  public String toString() {
    StringBuilder faculties = new StringBuilder();
    bunchOfFaculties.forEach(obj -> faculties.append(obj.toString()));
    return "University - " + name + ", Faculties:" + faculties + "\n";
  }
}
