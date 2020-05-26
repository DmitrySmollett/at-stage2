package errorsandexceptions.university;

import java.util.Objects;

public class Subject {
  private CourseOfStudy courseOfStudy;
  private int grade;

  public CourseOfStudy getCourseOfStudy() {
    return courseOfStudy;
  }

  public void setCourseOfStudy(CourseOfStudy courseOfStudy) {
    this.courseOfStudy = courseOfStudy;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {

    this.grade = grade;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Subject subject = (Subject) o;
    return grade == subject.grade && courseOfStudy == subject.courseOfStudy;
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseOfStudy, grade);
  }

  @Override
  public String toString() {
    return " " + courseOfStudy + " = " + grade + ',';
  }
}
