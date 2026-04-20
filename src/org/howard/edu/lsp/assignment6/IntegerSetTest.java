package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test suite for {@link IntegerSet}.
 *
 * <p>Each method is covered by at least one normal case and one edge case,
 * matching the required test coverage for Assignment 6.</p>
 *
 * @author Michael Cobbins
 */
public class IntegerSetTest {

    private IntegerSet setA;
    private IntegerSet setB;

    @BeforeEach
    public void setUp() {
        setA = new IntegerSet();
        setB = new IntegerSet();
    }

    // -------------------------------------------------------------------------
    // clear()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("clear() - normal: clears a populated set")
    public void testClearNormal() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.clear();
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("clear() - edge: clearing an already-empty set is a no-op")
    public void testClearEmpty() {
        setA.clear(); // should not throw
        assertEquals(0, setA.length());
    }

    // -------------------------------------------------------------------------
    // length()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("length() - normal: returns correct count after multiple adds")
    public void testLengthNormal() {
        setA.add(10);
        setA.add(20);
        setA.add(30);
        assertEquals(3, setA.length());
    }

    @Test
    @DisplayName("length() - edge: empty set has length 0")
    public void testLengthEmpty() {
        assertEquals(0, setA.length());
    }

    // -------------------------------------------------------------------------
    // equals()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("equals() - normal: identical elements in same order")
    public void testEqualsNormal() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(1);
        setB.add(2);
        setB.add(3);
        assertTrue(setA.equals(setB));
    }

    @Test
    @DisplayName("equals() - edge: same elements in different insertion order")
    public void testEqualsDifferentOrder() {
        setA.add(3);
        setA.add(1);
        setA.add(2);
        setB.add(1);
        setB.add(2);
        setB.add(3);
        assertTrue(setA.equals(setB));
    }

    @Test
    @DisplayName("equals() - edge: different elements returns false")
    public void testEqualsMismatch() {
        setA.add(1);
        setA.add(2);
        setB.add(1);
        setB.add(99);
        assertFalse(setA.equals(setB));
    }

    // -------------------------------------------------------------------------
    // contains()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("contains() - normal: value is present")
    public void testContainsPresent() {
        setA.add(5);
        assertTrue(setA.contains(5));
    }

    @Test
    @DisplayName("contains() - edge: value is not present")
    public void testContainsAbsent() {
        setA.add(5);
        assertFalse(setA.contains(99));
    }

    // -------------------------------------------------------------------------
    // largest()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("largest() - normal: returns max from multi-element set")
    public void testLargestNormal() {
        setA.add(3);
        setA.add(7);
        setA.add(1);
        assertEquals(7, setA.largest());
    }

    @Test
    @DisplayName("largest() - edge: single-element set")
    public void testLargestSingleElement() {
        setA.add(42);
        assertEquals(42, setA.largest());
    }

    @Test
    @DisplayName("largest() - edge: throws IntegerSetException on empty set")
    public void testLargestEmptyThrows() {
        assertThrows(IntegerSetException.class, () -> setA.largest());
    }

    // -------------------------------------------------------------------------
    // smallest()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("smallest() - normal: returns min from multi-element set")
    public void testSmallestNormal() {
        setA.add(10);
        setA.add(3);
        setA.add(7);
        assertEquals(3, setA.smallest());
    }

    @Test
    @DisplayName("smallest() - edge: single-element set")
    public void testSmallestSingleElement() {
        setA.add(-5);
        assertEquals(-5, setA.smallest());
    }

    @Test
    @DisplayName("smallest() - edge: throws IntegerSetException on empty set")
    public void testSmallestEmptyThrows() {
        assertThrows(IntegerSetException.class, () -> setA.smallest());
    }

    // -------------------------------------------------------------------------
    // add()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("add() - normal: adds a new element")
    public void testAddNormal() {
        setA.add(7);
        assertTrue(setA.contains(7));
        assertEquals(1, setA.length());
    }

    @Test
    @DisplayName("add() - edge: duplicate value is not added again")
    public void testAddDuplicate() {
        setA.add(7);
        setA.add(7);
        assertEquals(1, setA.length());
    }

    // -------------------------------------------------------------------------
    // remove()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("remove() - normal: removes an existing element")
    public void testRemoveNormal() {
        setA.add(4);
        setA.add(5);
        setA.remove(4);
        assertFalse(setA.contains(4));
        assertEquals(1, setA.length());
    }

    @Test
    @DisplayName("remove() - edge: removing a value not in the set is a no-op")
    public void testRemoveNotPresent() {
        setA.add(4);
        setA.remove(99); // should not throw or change size
        assertEquals(1, setA.length());
    }

    // -------------------------------------------------------------------------
    // union()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("union() - normal: combines two non-empty sets without duplicates")
    public void testUnionNormal() {
        setA.add(1);
        setA.add(2);
        setB.add(2);
        setB.add(3);
        IntegerSet result = setA.union(setB);
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertEquals(3, result.length());
    }

    @Test
    @DisplayName("union() - edge: union with empty set returns copy of this set")
    public void testUnionWithEmpty() {
        setA.add(1);
        setA.add(2);
        IntegerSet result = setA.union(setB);
        assertEquals(setA.length(), result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }

    // -------------------------------------------------------------------------
    // intersect()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("intersect() - normal: returns common elements")
    public void testIntersectNormal() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.intersect(setB);
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertEquals(2, result.length());
    }

    @Test
    @DisplayName("intersect() - edge: no common elements yields empty set")
    public void testIntersectNoOverlap() {
        setA.add(1);
        setA.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.intersect(setB);
        assertTrue(result.isEmpty());
    }

    // -------------------------------------------------------------------------
    // diff()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("diff() - normal: returns elements in this set not in other")
    public void testDiffNormal() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(2);
        IntegerSet result = setA.diff(setB);
        assertTrue(result.contains(1));
        assertTrue(result.contains(3));
        assertFalse(result.contains(2));
    }

    @Test
    @DisplayName("diff() - edge: diff of identical sets yields empty set")
    public void testDiffIdenticalSets() {
        setA.add(1);
        setA.add(2);
        setB.add(1);
        setB.add(2);
        IntegerSet result = setA.diff(setB);
        assertTrue(result.isEmpty());
    }

    // -------------------------------------------------------------------------
    // complement()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("complement() - normal: returns elements in other not in this")
    public void testComplementNormal() {
        setA.add(1);
        setA.add(2);
        setB.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.complement(setB);
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertFalse(result.contains(2));
    }

    @Test
    @DisplayName("complement() - edge: disjoint sets yields all of other set")
    public void testComplementDisjoint() {
        setA.add(1);
        setA.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.complement(setB);
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(2, result.length());
    }

    // -------------------------------------------------------------------------
    // isEmpty()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("isEmpty() - edge: newly created set is empty")
    public void testIsEmptyTrue() {
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("isEmpty() - normal: non-empty set returns false")
    public void testIsEmptyFalse() {
        setA.add(1);
        assertFalse(setA.isEmpty());
    }

    // -------------------------------------------------------------------------
    // toString()
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("toString() - normal: elements printed in sorted ascending order")
    public void testToStringNormal() {
        setA.add(3);
        setA.add(1);
        setA.add(2);
        assertEquals("[1, 2, 3]", setA.toString());
    }

    @Test
    @DisplayName("toString() - edge: empty set returns []")
    public void testToStringEmpty() {
        assertEquals("[]", setA.toString());
    }

    // -------------------------------------------------------------------------
    // Exception messages
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Exception message - largest() message contains useful text")
    public void testLargestExceptionMessage() {
        IntegerSetException ex = assertThrows(
            IntegerSetException.class,
            () -> setA.largest()
        );
        assertNotNull(ex.getMessage());
        assertFalse(ex.getMessage().isEmpty());
    }

    @Test
    @DisplayName("Exception message - smallest() message contains useful text")
    public void testSmallestExceptionMessage() {
        IntegerSetException ex = assertThrows(
            IntegerSetException.class,
            () -> setA.smallest()
        );
        assertNotNull(ex.getMessage());
        assertFalse(ex.getMessage().isEmpty());
    }
}
