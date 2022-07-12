package persistence;

import model.Course;

import static org.junit.jupiter.api.Assertions.*;

/*
 * JsonTest helper that contains checkCourse method to help with tests
 * Referenced code from JsonSerializationDemo.
 * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */
public class JsonTest {
    protected void checkCourse(String name, double grade, Course course) {
        assertEquals(name, course.getName());
        assertEquals(grade, course.getGrade());
    }
}
