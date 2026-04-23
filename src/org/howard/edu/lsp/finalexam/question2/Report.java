package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class defining the Template Method pattern for report generation.
 * The fixed workflow — loadData, formatHeader, formatBody, formatFooter — is enforced
 * by the final {@link #generateReport()} method. Subclasses provide the concrete
 * implementations for each step.
 *
 * @author Michael Cobbins
 */
public abstract class Report {

    /**
     * Loads the data required for this report.
     * Subclasses must initialize all fields used in format methods here.
     */
    protected abstract void loadData();

    /**
     * Formats and prints the report header content.
     */
    protected abstract void formatHeader();

    /**
     * Formats and prints the report body content.
     */
    protected abstract void formatBody();

    /**
     * Formats and prints the report footer content.
     */
    protected abstract void formatFooter();

    /**
     * Template method that defines the fixed report generation workflow.
     * Calls each step in order: loadData → formatHeader → formatBody → formatFooter.
     * Declared final to prevent subclasses from altering the sequence.
     */
    public final void generateReport() {
        loadData();

        System.out.println("=== HEADER ===");
        formatHeader();
        System.out.println();

        System.out.println("=== BODY ===");
        formatBody();
        System.out.println();

        System.out.println("=== FOOTER ===");
        formatFooter();
        System.out.println();
    }
}
