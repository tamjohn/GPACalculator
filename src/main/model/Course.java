package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Represents a course having a name and a grade (in percentage)
 */
public class Course implements Writable {

    public static final int gradeAndGpaScaleListSize = 11;

    private String name;
    private double grade;
    private double resultFourPointThreeGPA;
    private double resultFourPointZeroGPA;
    private ArrayList<Double> gradesCeiling;
    private ArrayList<Double> gradesFloor;
    private ArrayList<Double> gpaFourPointZero;
    private ArrayList<Double> gpaFourPointThree;

    /*
     * REQUIRES: course name has a non-zero length
     *           grade is [0, 100]
     * EFFECTS: name of course is set to courseTitle; grade of course is set to courseGrade
     */
    public Course(String courseTitle, double courseGrade) {
        this.name = courseTitle;
        this.grade = courseGrade;
    }

    // REQUIRES: Percentage grade [0, 100]
    // MODIFIES: this
    // EFFECTS: convert grade to 4.0 GPA scale
    public double calculateFourPointZeroGPA() {
        gradesCeiling = new ArrayList<>(Arrays.asList(100.0, 94.0, 89.0, 84.0, 79.0,
                74.0, 69.0, 64.0, 59.0, 54.0, 49.0));
        gradesFloor = new ArrayList<>(Arrays.asList(95.0, 90.0, 85.0, 80.0, 75.0, 70.0, 65.0,
                60.0, 55.0, 50.0, 0.0));
        gpaFourPointZero = new ArrayList<>(Arrays.asList(4.0, 3.9, 3.7, 3.3, 3.0, 2.7, 2.3, 2.0, 1.7, 1.0, 0.0));
        for (int i = 0; i < gradeAndGpaScaleListSize; i++) {
            if (getGrade() >= gradesFloor.get(i) && getGrade() <= gradesCeiling.get(i)) {
                resultFourPointZeroGPA = gpaFourPointZero.get(i);
            }
        }
        return resultFourPointZeroGPA;
    }

    // REQUIRES: Percentage grade [0, 100]
    // MODIFIES: this
    // EFFECTS: convert grade to 4.33 GPA scale
    public double calculateFourPointThreeGPA() {
        gradesCeiling = new ArrayList<>(Arrays.asList(100.0, 94.0, 89.0, 84.0, 79.0,
                74.0, 69.0, 64.0, 59.0, 54.0, 49.0));
        gradesFloor = new ArrayList<>(Arrays.asList(95.0, 90.0, 85.0, 80.0, 75.0, 70.0, 65.0,
                60.0, 55.0, 50.0, 0.0));
        gpaFourPointThree = new ArrayList<>(Arrays.asList(4.33, 4.0, 3.67, 3.33, 3.0, 2.67, 2.33, 2.0,
                1.67, 1.0, 0.0));
        for (int i = 0; i < gradeAndGpaScaleListSize; i++) {
            if (getGrade() >= gradesFloor.get(i) && getGrade() <= gradesCeiling.get(i)) {
                resultFourPointThreeGPA = gpaFourPointThree.get(i);
            }
        }
        return resultFourPointThreeGPA;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Course Name", name);
        json.put("Percentage Grade", grade);
        return json;
    }

    // EFFECTS: prints out course name with its grade in % in this format: "CPSC121 80.0%"
    public String printCourseLayout() {
        return getName() + " " + getGrade() + "%";
    }

    // getters
    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

}
