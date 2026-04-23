package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report implementation for course data.
 * Provides course-specific content for each step of the report workflow
 * defined in {@link Report}.
 *
 * @author Michael Cobbins
 */
public class CourseReport extends Report {

    /** The name of the course. */
    private String courseName;

    /** The number of students enrolled in the course. */
    private int enrollment;

    /**
     * Loads course data for the report.
     * Sets courseName and enrollment to the values used in output.
     */
    @Override
    protected void loadData() {
        courseName = "CSCI 363";
        enrollment = 45;
    }

    /**
     * Prints the course report header.
     */
    @Override
    protected void formatHeader() {
        System.out.println("Course Report");
    }

    /**
     * Prints the course name and enrollment count.
     */
    @Override
    protected void formatBody() {
        System.out.println("Course: " + courseName);
        System.out.println("Enrollment: " + enrollment);
    }

    /**
     * Prints the course report footer.
     */
    @Override
    protected void formatFooter() {
        System.out.println("End of Course Report");
    }
}
