package persistence;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * Unit tests for the JsonReader class.
 * Referenced code from JsonSerializationDemo.
 * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CourseList cl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected behavior
        }
    }

    @Test
    void testReaderEmptyCourseList() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyCourseList.json");
        try {
            CourseList cl = reader.read();
            assertEquals(0, cl.getCourseListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderCourseList() {
        JsonReader reader = new JsonReader("./data/testWriterCourseList.json");
        try {
            CourseList cl = reader.read();
            List<Course> courses = cl.getListOfCourses();
            assertEquals(2, courses.size());
            checkCourse("CPSC123", 66, courses.get(0));
            checkCourse("CPSC234", 78, courses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
