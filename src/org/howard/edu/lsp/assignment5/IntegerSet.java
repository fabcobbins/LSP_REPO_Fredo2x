package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a mathematical set of integers with no duplicate elements.
 * Supports standard set operations: union, intersection, difference, and complement.
 *
 * <p>All mutating operations (add, remove, clear) modify the set in place.
 * All set algebra operations (union, intersect, diff, complement) return a new
 * IntegerSet and do not modify either operand.</p>
 *
 * @author Marcus Cobb
 * @version 1.0
 */
public class IntegerSet {

    /** Internal storage for set elements. */
    private ArrayList<Integer> set = new ArrayList<>();

    /** Constructs an empty IntegerSet. */
    public IntegerSet() {}

    /**
     * Removes all elements from this set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the cardinality of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if this set contains exactly the same elements as {@code b},
     * regardless of insertion order.
     *
     * @param b the IntegerSet to compare against
     * @return true if both sets are equal; false otherwise
     */
    public boolean equals(IntegerSet b) {
        if (this.length() != b.length()) {
            return false;
        }
        ArrayList<Integer> copy = new ArrayList<>(this.set);
        Collections.sort(copy);

        ArrayList<Integer> bCopy = new ArrayList<>(b.set);
        Collections.sort(bCopy);

        return copy.equals(bCopy);
    }

    /**
     * Returns true if this set contains the specified value.
     *
     * @param value the integer to search for
     * @return true if the value is present; false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in this set.
     *
     * @return the maximum integer value in the set
     * @throws IntegerSetException if the set is empty
     */
    public int largest() throws IntegerSetException {
        if (isEmpty()) {
            throw new IntegerSetException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in this set.
     *
     * @return the minimum integer value in the set
     * @throws IntegerSetException if the set is empty
     */
    public int smallest() throws IntegerSetException {
        if (isEmpty()) {
            throw new IntegerSetException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds an integer to this set. If the item is already present, it is not added again.
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes the specified integer from this set. If the item is not present, does nothing.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new IntegerSet containing all elements that appear in either this set or {@code intSetb}.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the union
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);
        for (int item : intSetb.set) {
            if (!result.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing only elements common to both this set and {@code intSetb}.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the intersection
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (intSetb.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing elements in this set but not in {@code intSetb}.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the set difference (this - intSetb)
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (!intSetb.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing elements in {@code intSetb} but not in this set.
     * This is equivalent to {@code intSetb.diff(this)}.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the complement (intSetb - this)
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : intSetb.set) {
            if (!this.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if the set is empty; false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of this set in ascending sorted order.
     * Format: {@code [1, 2, 3]} or {@code []} for an empty set.
     *
     * @return the string representation of this set
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        return sorted.toString();
    }
}
