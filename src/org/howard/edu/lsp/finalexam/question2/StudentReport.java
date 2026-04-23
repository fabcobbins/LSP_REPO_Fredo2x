package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report implementation for student data.
 * Provides student-specific content for each step of the report workflow
 * defined in {@link Report}.
 *
 * @author Michael Cobbins
 */
public class StudentReport extends Report {

    /** The student's full name. */
    private String studentName;

    /** The student's GPA on a 4.0 scale. */
    private double gpa;

    /**
     * Loads student data for the report.
     * Sets studentName and gpa to the values used in output.
     */
    @Override
    protected void loadData() {
        studentName = "John Doe";
        gpa = 3.8;
    }

    /**
     * Prints the student report header.
     */
    @Override
    protected void formatHeader() {
        System.out.println("Student Report");
    }

    /**
     * Prints the student's name and GPA.
     */
    @Override
    protected void formatBody() {
        System.out.println("Student Name: " + studentName);
        System.out.println("GPA: " + gpa);
    }

    /**
     * Prints the student report footer.
     */
    @Override
    protected void formatFooter() {
        System.out.println("End of Student Report");
    }
}
