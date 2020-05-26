package errorsandexceptions.university.exceptions;

public class UniversityHasNoFaculties extends Exception {

  public UniversityHasNoFaculties() {}

  public UniversityHasNoFaculties(String message) {
    super(message);
  }

  public UniversityHasNoFaculties(String message, Throwable cause) {
    super(message, cause);
  }

  public UniversityHasNoFaculties(Throwable cause) {
    super(cause);
  }
}
