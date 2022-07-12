package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Unit tests for the CourseList class.
 */
class CourseListTest {

    private CourseList testCourseList;
    private Course testCourse1;
    private Course testCourse2;

    @BeforeEach
    void runBefore() {
        testCourseList = new CourseList();
        testCourse1 = new Course("CPSC210", 75);
        testCourse2 = new Course ("CPSC221", 90);
    }

    @Test
    void addCourse() {
        assertEquals(0, testCourseList.getCourseListSize());
        testCourseList.addCourse(testCourse1);
        assertEquals(1, testCourseList.getCourseListSize());
        assertEquals(testCourse1, testCourseList.getListOfCourses().get(0));

        testCourseList.addCourse(testCourse2);
        assertEquals(2, testCourseList.getCourseListSize());
        assertEquals(testCourse2, testCourseList.getListOfCourses().get(1));
        assertEquals(testCourse1, testCourseList.getListOfCourses().get(0));
    }

    @Test
    void testPrintListOfCourses() {
        testCourseList.addCourse(testCourse1);
        testCourseList.addCourse(testCourse2);
        assertEquals("CPSC210 75.0%" + "   " + "CPSC221 90.0%" + "   ", testCourseList.printListOfCourses());
    }

    @Test
    void testConvertGradesListToFourPointZeroGPA() {
        testCourseList.addCourse(testCourse1);
        testCourseList.addCourse(testCourse2);
        assertEquals(3.45, testCourseList.calculateCourseListFourPointZeroGPA());
    }

    @Test
    void testConvertGradesListToFourPointThreeGPA() {
        testCourseList.addCourse(testCourse1);
        testCourseList.addCourse(testCourse2);
        assertEquals(3.5, testCourseList.calculateCourseListFourPointThreeGPA());
    }

    @Test
    void testRemoveCourseFromList() {
        testCourseList.addCourse(testCourse1);
        testCourseList.addCourse(testCourse2);
        testCourseList.deleteCourseFromCourseList("CPSC221", 90.0);
        assertEquals(1, testCourseList.getCourseListSize());
    }

    @Test
    void testUnableToRemoveFromList() {
        testCourseList.addCourse(testCourse1);
        testCourseList.addCourse(testCourse2);
        testCourseList.deleteCourseFromCourseList("CPSC210", 73.0);
        assertEquals(2, testCourseList.getCourseListSize());
    }
}
