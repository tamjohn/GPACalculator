package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

/*
 * Represents a list of Courses
 * Referenced DelftStack to find code to round doubles in Java
 * 1) DelftStack: https://www.delftstack.com/howto/java/how-to-round-a-double-to-two-decimal-places-in-java/
 * 2) AlarmSystem: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
 */
public class CourseList implements Writable {
    private ArrayList<Course> listOfCourses;

    // Constructs a List of Courses
    public CourseList() {
        listOfCourses = new ArrayList<>();
    }

    // REQUIRES: Course to be added does not already exist in list
    // MODIFIES: this
    // EFFECTS: adds a course to the CourseList
    public void addCourse(Course c) {
        EventLog.getInstance().logEvent(new Event("Added " + c.getName() + " to list of courses."));
        listOfCourses.add(c);
    }

    // REQUIRES: list is not empty
    // MODIFIES: this
    // EFFECTS: calculate the 4.0 GPA of all courses in the CourseList
    public double calculateCourseListFourPointZeroGPA() {
        double result = 0;
        for (Course c : listOfCourses) {
            result += c.calculateFourPointZeroGPA() / listOfCourses.size();
        }
        EventLog.getInstance().logEvent(new Event("Converted list of courses to 4.0 GPA."));
        return Math.round(result * 100.0) / 100.0;
    }

    // REQUIRES: list is not empty
    // MODIFIES: this
    // EFFECTS: calculate the 4.33 GPA of all courses in the CourseList
    public double calculateCourseListFourPointThreeGPA() {
        double result = 0;
        for (Course c: listOfCourses) {
            result += c.calculateFourPointThreeGPA() / listOfCourses.size();
        }
        EventLog.getInstance().logEvent(new Event("Converted list of courses to 4.33 GPA."));
        return Math.round(result * 100.0) / 100.0;
    }

    // REQUIRES: list is not empty
    // MODIFIES: this
    // EFFECTS: remove a course from the CourseList
    public void deleteCourseFromCourseList(String s, Double grade) {
        EventLog.getInstance().logEvent(new Event("Removed " + s + " from list of courses."));
        listOfCourses.removeIf(c -> c.getName().equals(s) && c.getGrade() == grade);
        // for (Course c : listOfCourses) {
           // if (c.getName().equals(s) && c.getGrade() == grade) {
             //   EventLog.getInstance().logEvent(new Event("Removed " + s + " from list of courses."));
             //   listOfCourses.remove(c);
            //}
       // }
    }

    public String printListOfCourses() {
        String list = "";
        for (Course c : listOfCourses) {
            list += c.printCourseLayout() + "     ";
        }
        return list;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courses", coursesToJson());
        return json;
    }

    // EFFECTS: returns courses in this courseList as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : listOfCourses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // getters
    public ArrayList<Course> getListOfCourses() {
        return listOfCourses;
    }

    public int getCourseListSize() {
        return listOfCourses.size();
    }


}

