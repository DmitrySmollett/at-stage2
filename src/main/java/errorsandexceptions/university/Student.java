package errorsandexceptions.university;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Student {
  private String firstName;
  private String lastName;
  private List<Subject> bunchOfSubjects;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Subject> getBunchOfSubjects() {
    return bunchOfSubjects;
  }

  public void setBunchOfSubjects(List<Subject> bunchOfSubjects) {
    this.bunchOfSubjects = bunchOfSubjects;
  }

  public Optional<Integer> findGrade(CourseOfStudy course) {
    Optional<Subject> subject;
    subject =
        this.getBunchOfSubjects().stream().filter(o -> o.getCourseOfStudy() == course).findFirst();
    return subject.map(Subject::getGrade);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return firstName.equals(student.firstName)
        && lastName.equals(student.lastName)
        && bunchOfSubjects.equals(student.bunchOfSubjects);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, bunchOfSubjects);
  }

  @Override
  public String toString() {
    StringBuilder subjects = new StringBuilder();
    bunchOfSubjects.forEach(obj -> subjects.append(obj.toString()));
    return "\nStudent " + firstName + ' ' + lastName + ", Subjects: " + subjects;
  }
}
