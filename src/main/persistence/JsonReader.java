package persistence;

import model.Course;
import model.CourseList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/*
 * Represents a reader that reads CourseList from JSON data stored in file
 * Referenced code from JsonSerializationDemo.
 * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads CourseList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CourseList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCourseList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses courseList from JSON object and returns it
    private CourseList parseCourseList(JSONObject jsonObject) {
        JSONArray courses = jsonObject.getJSONArray("courses");
        CourseList cl = new CourseList();
        addCourses(cl, courses);
        return cl;
    }

    // MODIFIES: cl
    // EFFECTS: parses through courses from JSON object and adds it to courseList
    private void addCourses(CourseList cl, JSONArray courses) {
        for (Object course : courses) {
            JSONObject nextCourse = (JSONObject) course;
            addCourse(cl, nextCourse);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses course from JSON object and adds it to CourseList
    private void addCourse(CourseList cl, JSONObject jsonObject) {
        String name = jsonObject.getString("Course Name");
        double grade = jsonObject.getDouble("Percentage Grade");
        Course course = new Course(name, grade);
        cl.addCourse(course);
    }

}



