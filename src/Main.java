import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniversityManagementSystem ums = new UniversityManagementSystem();

        // Inject some initial data into the contact management system
        ums.dataInject();

        Scanner scanner = new Scanner(System.in);

        // Colorize console output
        String CYAN = "\u001B[36m";
        String RESET = "\u001B[0m";


        int choice = 0;

        while (choice != 6) {
            System.out.println("1. Student");
            System.out.println("2. Teacher");
            System.out.println("3. Course");
            System.out.println("4. Display average grade per student");
            System.out.println("5. Display average grade per course");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            // Print saved data
//            System.out.println("\n\n");
//            System.out.println(ums.loadData("Students.txt"));
//            System.out.println(ums.loadData("Teachers.txt"));
//            System.out.println(ums.loadData("Courses.txt"));
//            System.out.println(ums.loadData("Grades.txt"));
//            System.out.println(ums.loadData("Tuitions.txt"));

            int secondChoice = 0;
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("\n1. Add student");
                        System.out.println("2. Edit student");
                        System.out.println("3. Delete student");
                        System.out.println("4. Find student by ID");
                        System.out.println("5. Enter grade for student");
                        System.out.println("6. Go back");
                        System.out.print("Choose an option: ");
                        try {
                            secondChoice = scanner.nextInt();
                            switch (secondChoice) {
                                case 1:
                                    ums.addStudent(scanner);
                                    break;
                                case 2:
                                    ums.editStudentById(scanner);
                                    break;
                                case 3:
                                    ums.deleteStudentById(scanner);
                                    break;
                                case 4:
                                    System.out.print("Enter student ID: ");
                                    Student studentFoundById = ums.findStudentById(scanner.next(), ums.loadData("Students.txt"));
                                    ums.displayStudent(studentFoundById);
                                    System.out.print("\n");
                                    break;
                                case 5:
                                    System.out.print("Enter student ID: ");
                                    String studentId = scanner.next();
                                    System.out.print("Enter course ID: ");
                                    String courseId = scanner.next();
                                    System.out.print("Enter grade: ");
                                    double grade = scanner.nextDouble();
                                    ums.addGradeToStudent(studentId, courseId, grade);
                                    break;
                                case 6:
                                    break;
                                default:
                                    System.out.println(CYAN + "Invalid choice. Please try again.\n" + RESET);
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(CYAN + "Invalid input. Please enter a number.\n" + RESET);
                            scanner.nextLine(); // Consume the invalid input to allow the user to enter again
                        }
                        break;
                    case 2:
                        System.out.println("\n1. Add teacher");
                        System.out.println("2. Edit teacher");
                        System.out.println("3. Delete teacher");
                        System.out.println("4. Find teacher by ID");
                        System.out.println("5. Go back");
                        System.out.print("Choose an option: ");
                        try {
                            secondChoice = scanner.nextInt();
                            switch (secondChoice) {
                                case 1:
                                    ums.addTeacher(scanner);
                                    break;
                                case 2:
                                    ums.editTeacherById(scanner);
                                    break;
                                case 3:
                                    ums.deleteTeacherById(scanner);
                                    break;
                                case 4:
                                    System.out.print("Enter teacher ID: ");
                                    Teacher teacherFoundById = ums.findTeacherById(scanner.next(), ums.loadData("Teachers.txt"));
                                    ums.displayTeacher(teacherFoundById);
                                    break;
                                case 5:
                                    break;
                                default:
                                    System.out.println(CYAN + "Invalid choice. Please try again.\n" + RESET);
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(CYAN + "Invalid input. Please enter a number.\n" + RESET);
                            scanner.nextLine(); // Consume the invalid input to allow the user to enter again
                        }
                        break;
                    case 3:
                        System.out.println("\n1. Add course");
                        System.out.println("2. Edit course");
                        System.out.println("3. Delete course");
                        System.out.println("4. Find course by ID");
                        System.out.println("5. Assign course to student");
                        System.out.println("6. Assign course to teacher");
                        System.out.println("7. Go back");
                        System.out.print("Choose an option: ");
                        try {
                            secondChoice = scanner.nextInt();
                            switch (secondChoice) {
                                case 1:
                                    ums.addCourse(scanner);
                                    break;
                                case 2:
                                    ums.editCourseById(scanner);
                                    break;
                                case 3:
                                    ums.deleteCourseById(scanner);
                                    break;
                                case 4:
                                    System.out.print("Enter course ID: ");
                                    Course courseFoundById = ums.findCourseById(scanner.next(), ums.loadData("Courses.txt"));
                                    ums.displayCourse(courseFoundById);
                                    break;
                                case 5:
                                    System.out.print("Enter student ID: ");
                                    String studentId = scanner.next();
                                    System.out.print("Enter course ID: ");
                                    String studentCourseId = scanner.next();
                                    ums.assignCourseToStudent(studentId, studentCourseId);
                                    break;
                                case 6:
                                    System.out.print("Enter teacher ID: ");
                                    String teacherId = scanner.next();
                                    System.out.print("Enter course ID: ");
                                    String teacherCourseId = scanner.next();
                                    ums.assignCourseToTeacher(teacherId, teacherCourseId);
                                    break;
                                case 7:
                                    System.out.println("\nMain menu.\n");
                                    break;
                                default:
                                    System.out.println(CYAN + "Invalid choice. Please try again.\n" + RESET);
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(CYAN + "Invalid input. Please enter a number.\n" + RESET);
                            scanner.nextLine(); // Consume the invalid input to allow the user to enter again
                        }
                        break;
                    case 4:
                        System.out.print("Enter student ID: ");
                        String studentId = scanner.next();
                        ums.displayAverageGradeForStudent(studentId, ums.loadDataAsArray("Grades.txt"));
                        break;
                    case 5:
                        System.out.print("Enter course ID: ");
                        String courseId = scanner.next();
                        ums.displayAverageGradeForCourse(courseId, ums.loadDataAsArray("Grades.txt"));
                        break;
                    case 6:
                        // Exit the program
                        System.out.println("Exiting the program.");
                        break;
                    default:
                        System.out.println(CYAN + "Invalid choice. Please try again.\n" + RESET);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(CYAN + "Invalid input. Please enter a number.\n" + RESET);
                scanner.nextLine(); // Consume the invalid input to allow the user to enter again
            }
        }
        scanner.close();
    }
}
