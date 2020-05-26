package errorsandexceptions;

import errorsandexceptions.university.CourseOfStudy;
import errorsandexceptions.university.DeansOffice;
import errorsandexceptions.university.Faculty;
import errorsandexceptions.university.Group;
import errorsandexceptions.university.Student;
import errorsandexceptions.university.University;
import errorsandexceptions.university.exceptions.FacultyHasNoGroupsException;
import errorsandexceptions.university.exceptions.GradeOutOfRangeException;
import errorsandexceptions.university.exceptions.GroupHasNoStudentsException;
import errorsandexceptions.university.exceptions.StudentHasNoSubjectsException;
import errorsandexceptions.university.exceptions.UniversityHasNoFaculties;

public class JavaErrorAndExceptionsRunner {
  public static void main(String[] args) {

    Student student = DeansOffice.createRandomStudent(3);
    System.out.println(student.toString());
    try {
      System.out.println(
          "Average grade for student is " + DeansOffice.calculateAverageStudentGrade(student));
    } catch (StudentHasNoSubjectsException | GradeOutOfRangeException e) {
      e.printStackTrace();
    }

    University university = DeansOffice.createRandomUniversity(5, 5, 5);
    System.out.println("\n" + university.toString());

    try {
      System.out.println(
          "Average grade across university is "
              + DeansOffice.calculateAverageSubjectGradeInUniversity(
                  CourseOfStudy.PHYSICS, university));
    } catch (UniversityHasNoFaculties universityHasNoFaculties) {
      universityHasNoFaculties.printStackTrace();
    }

    Faculty faculty = null;
    if (!university.getBunchOfFaculties().isEmpty()) {
      faculty = university.getBunchOfFaculties().get(0);
      try {
        System.out.println(
            "Average grade across faculty is "
                + DeansOffice.calculateAverageSubjectGradeInFaculty(
                    CourseOfStudy.PHYSICS, faculty));
      } catch (FacultyHasNoGroupsException e) {
        e.printStackTrace();
      }
    }

    Group group;
    if (faculty != null && !faculty.getBunchOfGroups().isEmpty()) {
      group = faculty.getBunchOfGroups().get(0);
      try {
        System.out.println(
            "Average grade across group is "
                + DeansOffice.calculateAverageSubjectGradeInGroup(CourseOfStudy.PHYSICS, group));
      } catch (GroupHasNoStudentsException e) {
        e.printStackTrace();
      }
    }
  }
}
