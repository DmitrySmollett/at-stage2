package errorsandexceptions.university;

import errorsandexceptions.university.exceptions.FacultyHasNoGroupsException;
import errorsandexceptions.university.exceptions.GradeOutOfRangeException;
import errorsandexceptions.university.exceptions.GroupHasNoStudentsException;
import errorsandexceptions.university.exceptions.StudentHasNoSubjectsException;
import errorsandexceptions.university.exceptions.UniversityHasNoFaculties;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class DeansOffice {

  public static double calculateAverageStudentGrade(Student student)
      throws StudentHasNoSubjectsException, GradeOutOfRangeException {
    double averageGrade = 0;
    List<Subject> bunchOfSubjects = student.getBunchOfSubjects();
    if (bunchOfSubjects.isEmpty()) {
      throw new StudentHasNoSubjectsException("Student has no subjects");
    }
    for (Subject subject : bunchOfSubjects) {
      if (subject.getGrade() < 0 || subject.getGrade() > 10) {
        throw new GradeOutOfRangeException("Grade supposed to be in the range from 0 to 10");
      }
      averageGrade += subject.getGrade();
    }
    averageGrade = averageGrade / bunchOfSubjects.size();
    return averageGrade;
  }

  public static double calculateAverageSubjectGradeInGroup(CourseOfStudy course, Group group)
      throws GroupHasNoStudentsException {
    double averageGrade = 0;
    int numberOfStudentsWithTheCourse = 0;
    List<Student> bunchOfStudents = group.getBunchOfStudents();
    if (bunchOfStudents.isEmpty()) {
      throw new GroupHasNoStudentsException("Group " + group.getName() + " has no students");
    }
    for (Student student : bunchOfStudents) {
      Optional<Integer> grade = student.findGrade(course);
      if (grade.isPresent() && grade.get() >= 0 && grade.get() <= 10) {
        averageGrade += grade.get();
        numberOfStudentsWithTheCourse++;
      }
    }
    if (numberOfStudentsWithTheCourse == 0) {
      return 0;
    }
    averageGrade = averageGrade / numberOfStudentsWithTheCourse;
    return averageGrade;
  }

  public static double calculateAverageSubjectGradeInFaculty(CourseOfStudy course, Faculty faculty)
      throws FacultyHasNoGroupsException {
    double averageGrade = 0;
    int numberOfStudentsWithTheCourse = 0;
    List<Group> bunchOfGroups = faculty.getBunchOfGroups();
    if (bunchOfGroups.isEmpty()) {
      throw new FacultyHasNoGroupsException("Faulty " + faculty.getName() + "has no groups");
    }
    for (Group group : bunchOfGroups) {
      for (Student student : group.getBunchOfStudents()) {
        Optional<Integer> grade = student.findGrade(course);
        if (grade.isPresent() && grade.get() >= 0 && grade.get() <= 10) {
          averageGrade += grade.get();
          numberOfStudentsWithTheCourse++;
        }
      }
    }
    if (numberOfStudentsWithTheCourse == 0) {
      return 0;
    }
    averageGrade = averageGrade / numberOfStudentsWithTheCourse;
    return averageGrade;
  }

  public static double calculateAverageSubjectGradeInUniversity(
      CourseOfStudy course, University university) throws UniversityHasNoFaculties {
    double averageGrade = 0;
    int numberOfStudentsWithTheCourse = 0;
    List<Faculty> bunchOfFaculties = university.getBunchOfFaculties();
    if (bunchOfFaculties.isEmpty()) {
      throw new UniversityHasNoFaculties("There is no faculties in " + university.getName());
    }
    for (Faculty faculty : bunchOfFaculties) {
      for (Group group : faculty.getBunchOfGroups()) {
        for (Student student : group.getBunchOfStudents()) {
          Optional<Integer> grade = student.findGrade(course);
          if (grade.isPresent() && grade.get() >= 0 && grade.get() <= 10) {
            averageGrade += grade.get();
            numberOfStudentsWithTheCourse++;
          }
        }
      }
    }
    if (numberOfStudentsWithTheCourse == 0) {
      return 0;
    }
    averageGrade = averageGrade / numberOfStudentsWithTheCourse;
    return averageGrade;
  }

  private static Random rand = new Random();

  public static Student createRandomStudent(int numberOfSubjects) {
    String[] firstNameTemplate = {"Harris", "Regan", "Natan", "Rhys", "Marley", "Blake", "Arthur"};
    String[] lastNameTemplate = {"Barker", "Rogers", "Brown", "Howard", "Kelly", "Allen", "Willis"};
    List<Subject> bunchOfSubjects = new ArrayList<>();
    Student student = new Student();
    student.setFirstName(firstNameTemplate[rand.nextInt(firstNameTemplate.length)]);
    student.setLastName(lastNameTemplate[rand.nextInt(lastNameTemplate.length)]);
    for (int i = 0; i < numberOfSubjects; i++) {
      Subject subject = new Subject();
      subject.setCourseOfStudy(CourseOfStudy.values()[rand.nextInt(8)]);
      subject.setGrade(rand.nextInt(13) - 1);
      bunchOfSubjects.add(subject);
    }
    student.setBunchOfSubjects(bunchOfSubjects);
    return student;
  }

  public static University createRandomUniversity(
      int maxFaculties, int maxGroups, int maxStudents) {
    String[] groupNameTemplate = {"A", "B", "C", "D", "E", "F", "Z"};
    String[] facultyNameTemplate = {
      "Faculty of Music",
      "Faculty of Natural Sciences",
      "Faculty of Philosophy",
      "Faculty of Law",
      "Faculty of Engineering",
      "Faculty of Economics",
      "Faculty of Arts"
    };
    String[] universityNameTemplate = {
      "Indira Gandhi National Open University",
      "Allama Iqbal Open University",
      "California Community Colleges System",
      "National University, Bangladesh",
      "Anadolu University",
      "Islamic Azad University",
      "Laureate Education, Inc."
    };
    University university = new University();
    List<Faculty> bunchOfFaculties = new ArrayList<>();
    for (int i = 0; i < rand.nextInt(maxFaculties); i++) {
      Faculty faculty = new Faculty();
      faculty.setName(facultyNameTemplate[rand.nextInt(facultyNameTemplate.length)]);
      List<Group> bunchOfGroups = new ArrayList<>();
      for (int j = 0; j < rand.nextInt(maxGroups); j++) {
        Group group = new Group();
        group.setName(groupNameTemplate[rand.nextInt(groupNameTemplate.length)]);
        List<Student> bunchOfStudents = new ArrayList<>();
        for (int k = 0; k < rand.nextInt(maxStudents); k++) {
          bunchOfStudents.add(DeansOffice.createRandomStudent(8));
        }
        group.setBunchOfStudents(bunchOfStudents);
        bunchOfGroups.add(group);
      }
      faculty.setBunchOfGroups(bunchOfGroups);
      bunchOfFaculties.add(faculty);
    }
    university.setName(universityNameTemplate[rand.nextInt(universityNameTemplate.length)]);
    university.setBunchOfFaculties(bunchOfFaculties);
    return university;
  }
}
