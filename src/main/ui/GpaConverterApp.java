package ui;

import model.Course;
import model.CourseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * GPA converter application
 * Referenced parts of code from:
 * 1) FitLifeGymKiosk: https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters/tree/master/FitLifeGymKiosk
 * 2) SimpleCalculator: https://github.students.cs.ubc.ca/CPSC210/B04-SimpleCalculatorStarterLecLab
 * 3) JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class GpaConverterApp {

    private static final String CALCULATE_COMMAND_FOURPOINTZERO = "cal4.0";
    private static final String CALCULATE_COMMAND_FOURPOINTTHREE = "cal4.33";
    private static final String VIEW_COMMAND = "edit";
    private static final String ADD_COMMAND = "add";
    private static final String QUIT_COMMAND = "quit";
    private static final String YES_COMMAND = "Y";
    private static final String NO_COMMAND = "N";
    private static final String SAVE_COMMAND = "save";
    private static final String LOAD_COMMAND = "load";
    private static final String JSON_STORE = "./data/courseList.json";

    private double resultGPA;
    private Course inputCourse;
    private Scanner input;
    private CourseList listOfCourses;
    private boolean runProgram;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the converter application
    public GpaConverterApp() {
        listOfCourses = new CourseList();
        input = new Scanner(System.in);
        runProgram = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGpaConversionProcess();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void runGpaConversionProcess() {
        while (runProgram) {
            displayMenu();
            String chooseOptionsOperation = input.nextLine();

            switch (chooseOptionsOperation) {
                case CALCULATE_COMMAND_FOURPOINTZERO:
                    calculateFourPointZeroGPA();
                    break;
                case CALCULATE_COMMAND_FOURPOINTTHREE:
                    calculateFourPointThreeGPA();
                    break;
                case ADD_COMMAND:
                    printAddInstructions();
                    input.nextLine();
                    break;
                case VIEW_COMMAND:
                    viewAndEditCourseList();
                    break;
                case SAVE_COMMAND:
                    saveCourseList();
                    break;
                case LOAD_COMMAND:
                    loadCourseList();
                    break;
                case QUIT_COMMAND:
                    runProgram = false;
                    System.out.println("\nYou have exited the application.");
                    break;
                default:
                    System.out.println("You have entered an invalid input. Please try again.");
                    break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a course name with associated percentage grade
    private void printAddInstructions() {
        System.out.println("Please enter a course name and number as one word (ie. cpsc110)");
        String courseNameInput = input.next();
        System.out.println("Please enter the percentage grade for " + courseNameInput);
        String percentageGradeInput = input.next();
        double percentInDouble = Double.parseDouble(percentageGradeInput);
        inputCourse = new Course(courseNameInput, percentInDouble);
        listOfCourses.addCourse(inputCourse);
    }

    // EFFECTS: displays instruction menu to user
    private void displayMenu() {
        System.out.println("\nEnter " + CALCULATE_COMMAND_FOURPOINTZERO + " to return GPA on a 4.0 scale");
        System.out.println("Enter " + CALCULATE_COMMAND_FOURPOINTTHREE + " to return GPA on a 4.33 scale");
        System.out.println("Enter " + ADD_COMMAND + " to input courses");
        System.out.println("Enter " + VIEW_COMMAND + " to view and/or delete inputted courses");
        System.out.println("Enter " + SAVE_COMMAND + " to save list of courses and grades");
        System.out.println("Enter " + LOAD_COMMAND + " to load previously saved list of courses and grades");
        System.out.println("Enter " + QUIT_COMMAND + " to exit the application");
    }

    // MODIFIES: this
    // EFFECTS: calculate the final 4.0 GPA and displays list of all the courses + grades inputted
    private void calculateFourPointZeroGPA() {
        resultGPA = listOfCourses.calculateCourseListFourPointZeroGPA();
        System.out.println("\nFrom the courses and grade percentages you inputted: ");
        for (Course c : listOfCourses.getListOfCourses()) {
            System.out.println(c.printCourseLayout());
        }
        System.out.println("Your GPA on a 4.0 scale is " + resultGPA);
        runProgram = false;
    }

    // MODIFIES: this
    // EFFECTS: calculate final 4.33 GPA and displays list of all the courses + grades inputted
    private void calculateFourPointThreeGPA() {
        resultGPA = listOfCourses.calculateCourseListFourPointThreeGPA();
        System.out.println("\nFrom the courses and grade percentages you inputted: ");
        for (Course c : listOfCourses.getListOfCourses()) {
            System.out.println(c.printCourseLayout());
        }
        System.out.println("Your GPA on a 4.33 scale is " + resultGPA);
        runProgram = false;
    }

    // MODIFIES: this
    // EFFECTS: Display and delete course(s) from the list of courses in the 4.0 GPA conversion
    private void viewAndEditCourseList() {
        for (Course c : listOfCourses.getListOfCourses()) {
            System.out.println(c.printCourseLayout());
        }
        System.out.println("Would you like to delete any of the inputted course ("
                + YES_COMMAND + " or " + NO_COMMAND + ")?");
        String deleteOperation = input.nextLine();
        if (deleteOperation.equals(NO_COMMAND)) {
            runGpaConversionProcess();
        } else if (deleteOperation.equals(YES_COMMAND)) {
            System.out.println("Please enter the name of the course you would like to delete");
            String deleteCourseName = input.nextLine();
            System.out.println("Please enter the grade of the corresponding course");
            String percentageGradeInput = input.next();
            double percentInDouble = Double.parseDouble(percentageGradeInput);
            listOfCourses.deleteCourseFromCourseList(deleteCourseName, percentInDouble);
            System.out.println("You have deleted the course " + deleteCourseName);
            runGpaConversionProcess();
        }
    }

    // EFFECTS: saves the courseList to file
    private void saveCourseList() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfCourses);
            jsonWriter.close();
            System.out.println("Saved list of courses to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: load courseList from file
    private void loadCourseList() {
        try {
            listOfCourses = jsonReader.read();
            System.out.println("Loaded list of courses from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}


