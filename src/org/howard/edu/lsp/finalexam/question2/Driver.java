package org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class demonstrating the Template Method pattern for report generation.
 * Uses a polymorphic {@code List<Report>} to process both StudentReport and
 * CourseReport through a single unified interface.
 *
 * @author Michael Cobbins
 */
public class Driver {

    /**
     * Entry point. Adds a StudentReport and a CourseReport to a shared list
     * and calls generateReport() on each, demonstrating polymorphism.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();
        reports.add(new StudentReport());
        reports.add(new CourseReport());

        for (Report report : reports) {
            report.generateReport();
        }
    }
}
