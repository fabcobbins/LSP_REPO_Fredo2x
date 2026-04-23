package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test suite for {@link GradeCalculator}.
 * Covers normal cases, boundary values, and invalid input exceptions.
 *
 * @author Michael Cobbins
 */
public class GradeCalculatorTest {

    private GradeCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new GradeCalculator();
    }

    // -------------------------------------------------------------------------
    // average()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("average() - normal: returns correct arithmetic mean of three scores")
    public void testAverageNormal() {
        assertEquals(80.0, calculator.average(80, 90, 70), 0.001);
    }

    // -------------------------------------------------------------------------
    // letterGrade()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("letterGrade() - normal: average of 85 maps to letter grade B")
    public void testLetterGradeNormal() {
        assertEquals("B", calculator.letterGrade(85.0));
    }

    // -------------------------------------------------------------------------
    // isPassing()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("isPassing() - normal: average of 75 is a passing score")
    public void testIsPassingNormal() {
        assertTrue(calculator.isPassing(75.0));
    }

    // -------------------------------------------------------------------------
    // Boundary-value tests
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("boundary: average exactly 90.0 maps to letter grade A, not B")
    public void testBoundaryLetterGradeExactlyA() {
        assertEquals("A", calculator.letterGrade(90.0));
    }

    @Test
    @DisplayName("boundary: average exactly 60.0 is the minimum passing score")
    public void testBoundaryIsPassingAtThreshold() {
        assertTrue(calculator.isPassing(60.0));
        assertEquals("D", calculator.letterGrade(60.0));
    }

    // -------------------------------------------------------------------------
    // Exception tests
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("exception: score below 0 throws IllegalArgumentException")
    public void testExceptionScoreBelowZero() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.average(-1, 50, 50));
    }

    @Test
    @DisplayName("exception: score above 100 throws IllegalArgumentException")
    public void testExceptionScoreAboveOneHundred() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.average(101, 50, 50));
    }
}
