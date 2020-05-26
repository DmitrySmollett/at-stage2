package errorsandexceptions.university;

import java.util.List;
import java.util.Objects;

public class Group {
  private String name;
  private List<Student> bunchOfStudents;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Student> getBunchOfStudents() {
    return bunchOfStudents;
  }

  public void setBunchOfStudents(List<Student> bunchOfStudents) {
    this.bunchOfStudents = bunchOfStudents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Group group = (Group) o;
    return name.equals(group.name) && bunchOfStudents.equals(group.bunchOfStudents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, bunchOfStudents);
  }

  @Override
  public String toString() {
    StringBuilder students = new StringBuilder();
    bunchOfStudents.forEach(obj -> students.append(obj.toString()));
    return "\nGroup - " + name + ", Students:" + students;
  }
}
