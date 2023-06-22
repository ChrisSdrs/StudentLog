import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniversityManagementSystem ums = new UniversityManagementSystem();

        // Inject some initial data into the contact management system
        ums.dataInject();

        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        while (choice != 12) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Course");
            System.out.println("4. Assign Course to Teacher");
            System.out.println("5. Assign Course to Student");
            System.out.println("6. Enter Grade for Student");
            System.out.println("7. Display Average Grade per Student");
            System.out.println("8. Display Average Grade per Course");
            System.out.println("9. Find student by ID");
            System.out.println("10. Find teacher by ID");
            System.out.println("11. Find course by ID");
            System.out.println("12. Exit");
            System.out.print("Choose an option: ");

            String data = ums.loadData("data.txt");

//            System.out.println(data);

            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        // Add Student
                        System.out.print("Student ID: ");
                        String studentId = scanner.next();
                        scanner.nextLine(); // Consume the newline character
                        System.out.print("Full name: ");
                        String studentName = scanner.nextLine();
                        System.out.print("E-mail: ");
                        String studentEmail = scanner.nextLine();
                        System.out.print("Phone Number: ");
                        String studentPhone = scanner.nextLine();
                        System.out.print("Semester: ");

                        int studentSemester = scanner.nextInt();

                        Student studentToAdd = new Student(studentId, studentName, studentEmail, studentPhone, studentSemester);
                        ums.addStudent(studentToAdd);
                        System.out.println("The student has been added successfully!");
                        break;
                    case 2:
                        // Add teacher
                        System.out.print("Teacher ID: ");
                        String teacherId = scanner.next();
                        scanner.nextLine();
                        System.out.print("Full name: ");
                        String teacherName = scanner.nextLine();
                        System.out.print("E-mail: ");
                        String teacherEmail = scanner.nextLine();
                        System.out.print("Phone Number: ");
                        String teacherPhone = scanner.nextLine();
                        System.out.print("Specialty: ");
                        String teacherSpecialty = scanner.nextLine();

                        Teacher teacher = new Teacher(teacherId, teacherName, teacherEmail, teacherPhone, teacherSpecialty);
                        ums.addTeacher(teacher);
                        System.out.println("The teacher has been added successfully!");
                        break;
                    case 3:
                        // Add Course
                        System.out.print("Course ID: ");
                        String courseId = scanner.next();
                        scanner.nextLine();
                        System.out.print("Title: ");
                        String courseTitle = scanner.nextLine();
                        System.out.print("Semester: ");
                        int courseSemester = scanner.nextInt();

                        Course course = new Course(courseId, courseTitle, courseSemester);
                        ums.addCourse(course);
                        System.out.println("The course has been added successfully.");

                        break;
                    case 4:
                        // Assign Course to Teacher
                        System.out.print("Teacher ID: ");
                        teacherId = scanner.next();
                        System.out.print("Course ID: ");
                        courseId = scanner.next();

                        teacher = ums.findTeacherById(teacherId);
                        course = ums.findCourseById(courseId);

                        if (teacher != null && course != null) {
                            ums.assignCourseToTeacher(teacher, course);
                            System.out.println("The course has been assigned to the teacher.");
                        } else {
                            System.out.println("Invalid teacher ID or course ID.");
                        }
                        break;
                    case 5:
                        // Ανάθεση μαθήματος σε φοιτητή
                        System.out.print("Student ID: ");
                        studentId = scanner.next();
                        System.out.print("Course ID: ");
                        courseId = scanner.next();

                        Student student = ums.findStudentById(studentId);
                        course = ums.findCourseById(courseId);

                        if (student != null && course != null) {
                            ums.assignCourseToStudent(student, course);
                            System.out.println("The course has been assigned to the student.");
                        } else {
                            System.out.println("Invalid student ID or course ID.");
                        }
                        break;
                    case 6:
                        // Καταχώρηση βαθμολογίας σε φοιτητή
                        System.out.print("Student ID: ");
                        studentId = scanner.next();
                        System.out.print("Course ID: ");
                        courseId = scanner.next();
                        System.out.print("Grade: ");
                        double grade = scanner.nextDouble();

                        student = ums.findStudentById(studentId);
                        course = ums.findCourseById(courseId);

                        if (student != null && course != null) {
                            ums.addGrade(student, course, grade);
                            System.out.println("The grade has been recorded successfully.");
                        } else {
                            System.out.println("Invalid student ID or course ID.");
                        }
                        break;
                    case 7:
                        // Display average grade per student
                        System.out.print("Student ID: ");
                        studentId = scanner.next();

                        student = ums.findStudentById(studentId);

                        if (student != null) {
                            double average = ums.calculateStudentAverage(student);
                            System.out.println("The average grade for the student is: " + average);
                        } else {
                            System.out.println("Invalid student ID.");
                        }
                        break;
                    case 8:
                        // Display average grade per course
                        System.out.print("Course ID: ");
                        courseId = scanner.next();

                        course = ums.findCourseById(courseId);

                        if (course != null) {
                            double average = ums.calculateCourseAverage(course);
                            System.out.println("The average grade for the course is: " + average);
                        } else {
                            System.out.println("Invalid course ID.");
                        }
                        break;
                    case 9:
                        System.out.print("Enter student ID: ");
                        Student studentFoundById = (Student) ums.findById(scanner.next(), "Student", ums.loadData("data.txt"));
                        ums.displayStudent(studentFoundById);
                        break;
                    case 10:
                        System.out.print("Enter teacher ID: ");
                        Teacher teacherFoundById = (Teacher) ums.findById(scanner.next(), "Teacher", ums.loadData("data.txt"));
                        ums.displayTeacher(teacherFoundById);
                        break;
                    case 12:
                        // Exit the program
                        System.out.println("Exiting the program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                scanner.nextLine(); // Consume the invalid input to allow the user to enter again
            }
        }
        scanner.close();
    }
}
