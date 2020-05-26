package errorsandexceptions.university;

import java.util.List;
import java.util.Objects;

public class Faculty {
  private String name;
  private List<Group> bunchOfGroups;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Group> getBunchOfGroups() {
    return bunchOfGroups;
  }

  public void setBunchOfGroups(List<Group> bunchOfGroups) {
    this.bunchOfGroups = bunchOfGroups;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Faculty faculty = (Faculty) o;
    return name.equals(faculty.name) && bunchOfGroups.equals(faculty.bunchOfGroups);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, bunchOfGroups);
  }

  @Override
  public String toString() {
    StringBuilder groups = new StringBuilder();
    bunchOfGroups.forEach(obj -> groups.append(obj.toString()));
    return "\nFaculty - " + name + ", Groups:" + groups;
  }
}
