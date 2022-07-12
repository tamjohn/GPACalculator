package persistence;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * Unit tests for the JsonWriter class.
 * Referenced code from JsonSerializationDemo.
 * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */
public class JsonWriterTest extends JsonTest {

    private CourseList cl;
    private Course testCourse1;
    private Course testCourse2;

    @BeforeEach
    void setup() {
        cl = new CourseList();
        testCourse1 = new Course("CPSC123", 66);
        testCourse2 = new Course("CPSC234", 78);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected behavior
        }
    }

    @Test
    void testWriterEmptyCourseList() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCourseList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCourseList.json");
            cl = reader.read();
            assertEquals(0, cl.getCourseListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterCourseList() {
        try {
            cl.addCourse(testCourse1);
            cl.addCourse(testCourse2);
            JsonWriter writer = new JsonWriter("./data/testWriterCourseList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterCourseList.json");
            cl = reader.read();
            List<Course> courses = cl.getListOfCourses();
            assertEquals(2, courses.size());
            checkCourse("CPSC123", 66, courses.get(0));
            checkCourse("CPSC234", 78, courses.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
