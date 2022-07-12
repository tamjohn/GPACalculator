package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the Course class.
 */
class CourseTest {
    private Course testCourse;
    private Course testCourse2;
    private Course testCourse3;
    private Course testCourse4;
    private Course testCourse5;
    private Course testCourse6;
    private Course testCourse7;
    private Course testCourse8;
    private Course testCourse9;
    private Course testCourse10;


    @BeforeEach
     void runBefore() {
        testCourse = new Course ("CPSC210", 61);
        testCourse2 = new Course ("CPSC110", 86);
        testCourse3 = new Course ("CPSC101", 96);
        testCourse4 = new Course ("CPSC102", 81);
        testCourse5 = new Course ("CPSC103", 71);
        testCourse6 = new Course ("CPSC104", 66);
        testCourse7 = new Course ("CPSC105", 56);
        testCourse8 = new Course ("CPSC106", 51);
        testCourse9 = new Course ("CPSC107", 49);
        testCourse10 = new Course ("CPSC108", 76);

    }

    @Test
    void testConstructor() {
        assertEquals("CPSC210", testCourse.getName());
        assertEquals(61, testCourse.getGrade());
    }

    @Test
    void testGetCourseLayout() {
        assertEquals("CPSC210 61.0" + "%", testCourse.printCourseLayout());
        assertEquals("CPSC110 86.0" + "%", testCourse2.printCourseLayout());
    }

    @Test
    void testFourPointZeroConversion() {
        assertEquals(2, testCourse.calculateFourPointZeroGPA());
        assertEquals(3.7, testCourse2.calculateFourPointZeroGPA());
        assertEquals(4, testCourse3.calculateFourPointZeroGPA());
        assertEquals(3.3, testCourse4.calculateFourPointZeroGPA());
        assertEquals(2.7, testCourse5.calculateFourPointZeroGPA());
        assertEquals(2.3, testCourse6.calculateFourPointZeroGPA());
        assertEquals(1.7, testCourse7.calculateFourPointZeroGPA());
        assertEquals(1, testCourse8.calculateFourPointZeroGPA());
        assertEquals(0, testCourse9.calculateFourPointZeroGPA());
        assertEquals(3, testCourse10.calculateFourPointZeroGPA());
    }

    @Test
    void testFourPointThreeConversion() {
        assertEquals(2, testCourse.calculateFourPointThreeGPA());
        assertEquals(3.67, testCourse2.calculateFourPointThreeGPA());
        assertEquals(4.33, testCourse3.calculateFourPointThreeGPA());
        assertEquals(3.33, testCourse4.calculateFourPointThreeGPA());
        assertEquals(2.67, testCourse5.calculateFourPointThreeGPA());
        assertEquals(2.33, testCourse6.calculateFourPointThreeGPA());
        assertEquals(1.67, testCourse7.calculateFourPointThreeGPA());
        assertEquals(1, testCourse8.calculateFourPointThreeGPA());
        assertEquals(0, testCourse9.calculateFourPointThreeGPA());
        assertEquals(3, testCourse10.calculateFourPointThreeGPA());
    }
}