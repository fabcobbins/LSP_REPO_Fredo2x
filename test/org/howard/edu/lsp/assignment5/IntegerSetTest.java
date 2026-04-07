package org.howard.edu.lsp.assignment5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test suite for IntegerSet.
 * Covers all required methods including normal cases, edge cases, and exception handling.
 */
public class IntegerSetTest {

    private IntegerSet set1;
    private IntegerSet set2;

    @BeforeEach
    void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }

    // -------------------------------------------------------------------------
    // clear()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test clear() removes all elements")
    void testClear() {
        set1.add(1);
        set1.add(2);
        set1.clear();
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("Test clear() on already-empty set does nothing")
    void testClearEmptySet() {
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    // -------------------------------------------------------------------------
    // length()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test length() of empty set is 0")
    void testLengthEmpty() {
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("Test length() after adding elements")
    void testLengthAfterAdds() {
        set1.add(10);
        set1.add(20);
        set1.add(30);
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("Test length() does not count duplicates")
    void testLengthNoDuplicates() {
        set1.add(5);
        set1.add(5);
        set1.add(5);
        assertEquals(1, set1.length());
    }

    // -------------------------------------------------------------------------
    // isEmpty()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test isEmpty() returns true for empty set")
    void testIsEmptyTrue() {
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test isEmpty() returns false after adding an element")
    void testIsEmptyFalse() {
        set1.add(1);
        assertFalse(set1.isEmpty());
    }

    @Test
    @DisplayName("Test isEmpty() returns true after clear")
    void testIsEmptyAfterClear() {
        set1.add(1);
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    // -------------------------------------------------------------------------
    // contains()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test contains() returns true for existing element")
    void testContainsTrue() {
        set1.add(42);
        assertTrue(set1.contains(42));
    }

    @Test
    @DisplayName("Test contains() returns false for missing element")
    void testContainsFalse() {
        set1.add(1);
        assertFalse(set1.contains(99));
    }

    @Test
    @DisplayName("Test contains() on empty set returns false")
    void testContainsEmptySet() {
        assertFalse(set1.contains(0));
    }

    @Test
    @DisplayName("Test contains() works with negative integers")
    void testContainsNegative() {
        set1.add(-5);
        assertTrue(set1.contains(-5));
        assertFalse(set1.contains(5));
    }

    // -------------------------------------------------------------------------
    // add()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test add() increases length")
    void testAddIncreasesLength() {
        set1.add(1);
        assertEquals(1, set1.length());
    }

    @Test
    @DisplayName("Test add() duplicate does not increase length")
    void testAddDuplicate() {
        set1.add(7);
        set1.add(7);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(7));
    }

    @Test
    @DisplayName("Test add() zero")
    void testAddZero() {
        set1.add(0);
        assertTrue(set1.contains(0));
    }

    @Test
    @DisplayName("Test add() negative integer")
    void testAddNegative() {
        set1.add(-10);
        assertTrue(set1.contains(-10));
        assertEquals(1, set1.length());
    }

    // -------------------------------------------------------------------------
    // remove()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test remove() deletes an existing element")
    void testRemoveExisting() {
        set1.add(3);
        set1.add(4);
        set1.remove(3);
        assertFalse(set1.contains(3));
        assertEquals(1, set1.length());
    }

    @Test
    @DisplayName("Test remove() on non-existent element does nothing")
    void testRemoveNonExistent() {
        set1.add(1);
        set1.remove(99);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(1));
    }

    @Test
    @DisplayName("Test remove() on empty set does nothing")
    void testRemoveFromEmptySet() {
        assertDoesNotThrow(() -> set1.remove(5));
        assertTrue(set1.isEmpty());
    }

    // -------------------------------------------------------------------------
    // largest()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test largest() returns max value")
    void testLargest() {
        set1.add(1);
        set1.add(50);
        set1.add(25);
        assertEquals(50, set1.largest());
    }

    @Test
    @DisplayName("Test largest() with single element")
    void testLargestSingleElement() {
        set1.add(7);
        assertEquals(7, set1.largest());
    }

    @Test
    @DisplayName("Test largest() throws IntegerSetException on empty set")
    void testLargestEmptySetThrows() {
        assertThrows(IntegerSetException.class, () -> set1.largest());
    }

    @Test
    @DisplayName("Test largest() with negative integers")
    void testLargestNegatives() {
        set1.add(-1);
        set1.add(-10);
        set1.add(-3);
        assertEquals(-1, set1.largest());
    }

    // -------------------------------------------------------------------------
    // smallest()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test smallest() returns min value")
    void testSmallest() {
        set1.add(10);
        set1.add(1);
        set1.add(5);
        assertEquals(1, set1.smallest());
    }

    @Test
    @DisplayName("Test smallest() with single element")
    void testSmallestSingleElement() {
        set1.add(42);
        assertEquals(42, set1.smallest());
    }

    @Test
    @DisplayName("Test smallest() throws IntegerSetException on empty set")
    void testSmallestEmptySetThrows() {
        assertThrows(IntegerSetException.class, () -> set1.smallest());
    }

    @Test
    @DisplayName("Test smallest() with negative integers")
    void testSmallestNegatives() {
        set1.add(-1);
        set1.add(-10);
        set1.add(-3);
        assertEquals(-10, set1.smallest());
    }

    // -------------------------------------------------------------------------
    // equals()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test equals() returns true for identical sets")
    void testEqualsIdentical() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(1); set2.add(2); set2.add(3);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals() returns true regardless of insertion order")
    void testEqualsOrderIndependent() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(3); set2.add(1); set2.add(2);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals() returns false for different sets")
    void testEqualsDifferent() {
        set1.add(1); set1.add(2);
        set2.add(1); set2.add(3);
        assertFalse(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals() returns false for different sizes")
    void testEqualsDifferentSize() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(1); set2.add(2);
        assertFalse(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals() both empty sets are equal")
    void testEqualsBothEmpty() {
        assertTrue(set1.equals(set2));
    }

    // -------------------------------------------------------------------------
    // union()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test union() combines elements from both sets without duplicates")
    void testUnion() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.union(set2);
        assertEquals("[1, 2, 3, 4]", result.toString());
    }

    @Test
    @DisplayName("Test union() with disjoint sets")
    void testUnionDisjoint() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.union(set2);
        assertEquals(4, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(4));
    }

    @Test
    @DisplayName("Test union() with one empty set returns the other set")
    void testUnionOneEmpty() {
        set1.add(1); set1.add(2);
        IntegerSet result = set1.union(set2);
        assertTrue(result.equals(set1));
    }

    @Test
    @DisplayName("Test union() of two empty sets returns empty set")
    void testUnionBothEmpty() {
        IntegerSet result = set1.union(set2);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test union() does not modify original sets")
    void testUnionDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        set1.union(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // -------------------------------------------------------------------------
    // intersect()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test intersect() returns common elements")
    void testIntersect() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertEquals("[2, 3]", result.toString());
    }

    @Test
    @DisplayName("Test intersect() with disjoint sets returns empty set")
    void testIntersectDisjoint() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test intersect() with one empty set returns empty set")
    void testIntersectOneEmpty() {
        set1.add(1); set1.add(2);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test intersect() with identical sets returns same set")
    void testIntersectIdentical() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(1); set2.add(2); set2.add(3);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.equals(set1));
    }

    @Test
    @DisplayName("Test intersect() does not modify original sets")
    void testIntersectDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.intersect(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // -------------------------------------------------------------------------
    // diff()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test diff() returns elements in set1 not in set2")
    void testDiff() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.diff(set2);
        assertEquals("[1]", result.toString());
    }

    @Test
    @DisplayName("Test diff() with disjoint sets returns original set")
    void testDiffDisjoint() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.diff(set2);
        assertTrue(result.equals(set1));
    }

    @Test
    @DisplayName("Test diff() when set2 contains all of set1 returns empty")
    void testDiffSubset() {
        set1.add(2); set1.add(3);
        set2.add(1); set2.add(2); set2.add(3);
        IntegerSet result = set1.diff(set2);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test diff() with empty set2 returns original set")
    void testDiffEmptySet2() {
        set1.add(1); set1.add(2);
        IntegerSet result = set1.diff(set2);
        assertTrue(result.equals(set1));
    }

    @Test
    @DisplayName("Test diff() does not modify original sets")
    void testDiffDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.diff(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // -------------------------------------------------------------------------
    // complement()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test complement() returns elements in set2 not in set1")
    void testComplement() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.complement(set2);
        assertEquals("[4]", result.toString());
    }

    @Test
    @DisplayName("Test complement() when sets are equal returns empty set")
    void testComplementEqualSets() {
        set1.add(1); set1.add(2);
        set2.add(1); set2.add(2);
        IntegerSet result = set1.complement(set2);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test complement() with empty set1 returns all of set2")
    void testComplementEmptySet1() {
        set2.add(1); set2.add(2); set2.add(3);
        IntegerSet result = set1.complement(set2);
        assertTrue(result.equals(set2));
    }

    @Test
    @DisplayName("Test complement() does not modify original sets")
    void testComplementDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.complement(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // -------------------------------------------------------------------------
    // toString()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Test toString() on empty set returns []")
    void testToStringEmpty() {
        assertEquals("[]", set1.toString());
    }

    @Test
    @DisplayName("Test toString() outputs elements in ascending order")
    void testToStringSorted() {
        set1.add(3); set1.add(1); set1.add(2);
        assertEquals("[1, 2, 3]", set1.toString());
    }

    @Test
    @DisplayName("Test toString() with single element")
    void testToStringSingleElement() {
        set1.add(5);
        assertEquals("[5]", set1.toString());
    }

    @Test
    @DisplayName("Test toString() with negative integers sorted correctly")
    void testToStringNegatives() {
        set1.add(0); set1.add(-3); set1.add(2);
        assertEquals("[-3, 0, 2]", set1.toString());
    }
}
