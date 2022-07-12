package ui;

import model.Course;
import model.CourseList;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * Java Swing GUI for GpaConverterApp
 * Referenced parts of code from:
 * 1) Java Tutorials: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
 * 2) AlarmSystem: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
 */
public class GUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/courseList.json";
    private static final String courseName = "Course Name: ";
    private static final String courseGrade = "Percentage Grade (%): ";

    private Course inputCourse;
    private CourseList listOfCourses;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JTextField courseNameField;
    private JTextField courseGradeField;

    private JTextArea listOfCoursesTextArea;

    private JScrollPane viewListOfCourses;

    private JLabel courseNameLabel;
    private JLabel courseGradeLabel;
    private JLabel headerLabel;
    private JLabel finalResultGPA;
    private JLabel calculatorPic;

    private JPanel mainMenu;
    private JPanel gradesConversionPage;

    private JButton addButton;
    private JButton calculateFourPointZeroButton;
    private JButton calculateFourPointThreeButton;
    private JButton loadCoursesButton;
    private JButton exitApplicationButton;
    private JButton enterCoursesButton;
    private JButton mainMenuButton;
    private JButton deleteCourseButton;
    private JButton saveCoursesButton;

    private JOptionPane saveCoursesPopUp;
    private JOptionPane unsuccessfulSaveCoursesPopUp;
    private JOptionPane loadCoursesPopUp;
    private JOptionPane unsuccessfulLoadCoursesPopUp;


    // EFFECTS: runs the Java Swing GUI with a new JFrame
    public GUI() {
        super("GPA Calculator");
        setBounds(100, 100, 950, 470);
        initializeComponents();
        initializeMainMenu();
        makeGradeConversionPage();
        mainMenu.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the main menu panel
    public void initializeMainMenu() {
        mainMenu = new JPanel();
        add(mainMenu);
        mainMenu.setBackground(Color.white);
        headerLabel = new JLabel("GPA Calculator");
        headerLabel.setFont(new Font("Verdana", Font.BOLD, 70));
        initializeMainMenuComponents();
        setMainMenuButtonActions();
        mainMenu.add(calculatorPic);
        mainMenu.add(headerLabel);
        mainMenu.add(Box.createVerticalStrut(260));
        mainMenu.add(enterCoursesButton);
        mainMenu.add(loadCoursesButton);
        mainMenu.add(exitApplicationButton);
        setVisible(true);
    }

    // EFFECTS: initialize components of main menu
    private void initializeMainMenuComponents() {
        calculatorPic = new JLabel();
        calculatorPic.setIcon(new ImageIcon("./data/calculator_logo.jpg"));
        //calculatorPic.setPreferredSize(new Dimension(400, 100));
        exitApplicationButton = new JButton("Exit App");
        exitApplicationButton.setPreferredSize(new Dimension(240,70));
        exitApplicationButton.setFont(new Font("Arial", Font.BOLD,30));
        enterCoursesButton = new JButton("Enter Courses");
        enterCoursesButton.setPreferredSize(new Dimension(240,70));
        enterCoursesButton.setFont(new Font("Arial", Font.BOLD, 30));
        loadCoursesButton = new JButton("Load Courses");
        loadCoursesButton.setFont(new Font("Arial", Font.BOLD, 30));
        loadCoursesButton.setPreferredSize(new Dimension(240,70));
    }

    // MODIFIES: this
    // EFFECTS: sets each button on main menu with their respective action
    private void setMainMenuButtonActions() {
        enterCoursesButton.setActionCommand("Enter Courses");
        enterCoursesButton.addActionListener(this);
        loadCoursesButton.setActionCommand("Load Courses");
        loadCoursesButton.addActionListener(this);
        exitApplicationButton.setActionCommand("Exit Application");
        exitApplicationButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: create the grade conversion page that converts percentage grades to 4.0 or 4.33 GPA
    public void makeGradeConversionPage() {
        initializeGradeConversionPageComponents();
        initializeGradeConversionPageButtons();
        setGradeConversionPageButtonsActions();
        gradesConversionPage = new JPanel();
        gradesConversionPage.add(viewListOfCourses);
        gradesConversionPage.add(courseNameLabel);
        gradesConversionPage.add(courseNameField);
        gradesConversionPage.add(courseGradeLabel);
        gradesConversionPage.add(courseGradeField);
        gradesConversionPage.add(addButton);
        gradesConversionPage.add(deleteCourseButton);
        gradesConversionPage.add(Box.createVerticalStrut(55));
        gradesConversionPage.add(calculateFourPointZeroButton);
        gradesConversionPage.add(calculateFourPointThreeButton);
        gradesConversionPage.add(saveCoursesButton);
        gradesConversionPage.add(mainMenuButton);
        gradesConversionPage.add(Box.createVerticalStrut(55));
        gradesConversionPage.add(finalResultGPA);
    }

    // EFFECTS: initialize components of grade conversion page
    private void initializeGradeConversionPageComponents() {
        courseNameField = new JTextField();
        courseNameField.setPreferredSize(new Dimension(140, 40));
        courseNameField.setFont(new Font("Arial", Font.PLAIN, 23));
        courseGradeField = new JTextField();
        courseGradeField.setPreferredSize(new Dimension(140, 40));
        courseGradeField.setFont(new Font("Arial", Font.PLAIN, 23));
        listOfCoursesTextArea = new JTextArea();
        viewListOfCourses = new JScrollPane(listOfCoursesTextArea);
        viewListOfCourses.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        listOfCoursesTextArea.setLineWrap(true);
        viewListOfCourses.setPreferredSize(new Dimension(910, 200));
        courseNameLabel = new JLabel(courseName);
        courseNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        courseGradeLabel = new JLabel(courseGrade);
        courseGradeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        finalResultGPA = new JLabel();
        finalResultGPA.setFont(new Font("Arial", Font.BOLD, 25));
        finalResultGPA.setForeground(Color.BLUE);
        finalResultGPA.setText("your final GPA will be displayed here");
    }

    // EFFECTS: initialize buttons on grade conversion page
    private void initializeGradeConversionPageButtons() {
        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(120,40));
        addButton.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteCourseButton = new JButton("Delete");
        deleteCourseButton.setPreferredSize(new Dimension(120, 40));
        deleteCourseButton.setFont(new Font("Arial", Font.PLAIN, 18));
        calculateFourPointZeroButton = new JButton("Calculate 4.0 GPA");
        calculateFourPointZeroButton.setPreferredSize(new Dimension(200, 45));
        calculateFourPointZeroButton.setFont(new Font("Arial", Font.BOLD, 18));
        calculateFourPointThreeButton = new JButton("Calculate 4.33 GPA");
        calculateFourPointThreeButton.setPreferredSize(new Dimension(200, 45));
        calculateFourPointThreeButton.setFont(new Font("Arial", Font.BOLD, 18));
        saveCoursesButton = new JButton("Save Courses");
        saveCoursesButton.setPreferredSize(new Dimension(200, 45));
        saveCoursesButton.setFont(new Font("Arial", Font.BOLD, 18));
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setPreferredSize(new Dimension(200, 45));
        mainMenuButton.setFont(new Font("Arial", Font.BOLD, 18));
    }

    // MODIFIES: this
    // EFFECTS: sets each button on grade conversion page with their respective action
    private void setGradeConversionPageButtonsActions() {
        mainMenuButton.setActionCommand("Main Menu");
        mainMenuButton.addActionListener(this);
        calculateFourPointZeroButton.setActionCommand("Calculate 4.0 GPA");
        calculateFourPointZeroButton.addActionListener(this);
        calculateFourPointThreeButton.setActionCommand("Calculate 4.33 GPA");
        calculateFourPointThreeButton.addActionListener(this);
        deleteCourseButton.setActionCommand("Delete");
        deleteCourseButton.addActionListener(this);
        saveCoursesButton.setActionCommand("Save Courses");
        saveCoursesButton.addActionListener(this);
        addButton.setActionCommand("Add");
        addButton.addActionListener(this);
    }

    // EFFECTS: adds grade conversion page to frame and sets other pages to false so not visible
    public void initializeGradeConversionPage() {
        add(gradesConversionPage);
        gradesConversionPage.setVisible(true);
        mainMenu.setVisible(false);
    }

    // EFFECTS: sets main manu page and sets other pages to false so not visible
    public void returnMainMenu() {
        mainMenu.setVisible(true);
        gradesConversionPage.setVisible(false);
    }

    // EFFECTS: initialize components for back-end of GPA calculator
    public void initializeComponents() {
        listOfCourses = new CourseList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: Adds a course name with associated percentage grade and displays it to the user
    public void addCourses() {
        String inputCourseName = courseNameField.getText();
        Double inputCoursePercentGrade = Double.parseDouble(courseGradeField.getText());
        inputCourse = new Course(inputCourseName, inputCoursePercentGrade);
        listOfCourses.addCourse(inputCourse);
        listOfCoursesTextArea.setText(listOfCourses.printListOfCourses());
        listOfCoursesTextArea.setFont(new Font("Arial", Font.BOLD, 20));
        courseNameField.setText("");
        courseGradeField.setText("");
    }

    // MODIFIES: this
    // EFFECTS: calculate the final 4.0 GPA
    public void calculateCourseListFourPointZeroGPA() {
        double finalResult = listOfCourses.calculateCourseListFourPointZeroGPA();
        finalResultGPA.setText("Your GPA on a 4.0 scale is: " + finalResult);
    }

    // MODIFIES: this
    // EFFECTS: Delete a course name with associated percentage grade and displays it to the user
    public void deleteCourse() {
        String inputCourseName = courseNameField.getText();
        Double inputCoursePercentGrade = Double.parseDouble(courseGradeField.getText());
        inputCourse = new Course(inputCourseName, inputCoursePercentGrade);
        listOfCourses.deleteCourseFromCourseList(inputCourseName, inputCoursePercentGrade);
        listOfCoursesTextArea.setText(listOfCourses.printListOfCourses());
        courseNameField.setText("");
        courseGradeField.setText("");
    }

    // MODIFIES: this
    // EFFECTS: calculate the final 4.0 GPA
    public void calculateCourseListFourPointThreeGPA() {
        double finalResult = listOfCourses.calculateCourseListFourPointThreeGPA();
        finalResultGPA.setText("Your GPA on a 4.33 scale is: " + finalResult);
    }

    // EFFECTS: saves the courseList to file
    public void saveCourses() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfCourses);
            jsonWriter.close();
            saveCoursesPopUp = new JOptionPane();
            saveCoursesPopUp.showMessageDialog(this, "Courses successfully saved to file");
        } catch (FileNotFoundException e) {
            unsuccessfulSaveCoursesPopUp = new JOptionPane();
            unsuccessfulSaveCoursesPopUp.showMessageDialog(this,
                    "Unable to save courses to file. Please double-check entries and try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: load courseList from file
    public void loadCourses() {
        try {
            listOfCourses = jsonReader.read();
            listOfCoursesTextArea.setText(listOfCourses.printListOfCourses());
            listOfCoursesTextArea.setFont(new Font("Arial", Font.BOLD, 14));
            loadCoursesPopUp = new JOptionPane();
            loadCoursesPopUp.showMessageDialog(this, "Courses successfully loaded from file");
        } catch (IOException e) {
            unsuccessfulLoadCoursesPopUp = new JOptionPane();
            unsuccessfulLoadCoursesPopUp.showMessageDialog(this,
                    "Unable to load courses from file. Please try again");
        }
    }

    public void exitApplication() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
        System.exit(EXIT_ON_CLOSE);
    }

    // EFFECTS: call respective methods upon each button command
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Exit Application")) {
            exitApplication();
        } else if (e.getActionCommand().equals("Enter Courses")) {
            initializeGradeConversionPage();
        } else if (e.getActionCommand().equals("Main Menu")) {
            returnMainMenu();
        } else if (e.getActionCommand().equals("Add")) {
            addCourses();
        } else if (e.getActionCommand().equals("Calculate 4.0 GPA")) {
            calculateCourseListFourPointZeroGPA();
        } else if (e.getActionCommand().equals("Delete")) {
            deleteCourse();
        } else if (e.getActionCommand().equals("Calculate 4.33 GPA")) {
            calculateCourseListFourPointThreeGPA();
        } else if (e.getActionCommand().equals("Save Courses")) {
            saveCourses();
        } else if (e.getActionCommand().equals("Load Courses")) {
            loadCourses();
        }
    }
}
