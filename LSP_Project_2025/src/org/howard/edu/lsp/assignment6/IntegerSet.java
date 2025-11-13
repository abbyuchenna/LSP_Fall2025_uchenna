package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * models a set of unique integers backed by an arraylist.
 */
public class IntegerSet {
  private List<Integer> set = new ArrayList<Integer>();

  /**
   * clears all elements from the set.
   */
  public void clear() {
    set.clear();
  }

  /**
   * returns the number of elements in the set.
   *
   * @return current number of elements
   */
  public int length() {
    return set.size();
  }

  /**
   * returns true if this set and the other object contain the same values
   * in any order. compares contents, not references.
   *
   * @param o the object to compare with
   * @return true if both sets contain the same integers, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IntegerSet)) {
      return false;
    }
    IntegerSet other = (IntegerSet) o;
    if (this.set.size() != other.set.size()) {
      return false;
    }
    return this.set.containsAll(other.set);
  }

  /**
   * returns true if the set contains the given value.
   *
   * @param value integer value to look for
   * @return true if present, false otherwise
   */
  public boolean contains(int value) {
    return set.contains(value);
  }

  /**
   * returns the largest value in the set.
   *
   * @return largest integer in the set
   * @throws IllegalStateException if the set is empty
   */
  public int largest() {
    if (set.isEmpty()) {
      throw new IllegalStateException("set is empty");
    }
    int max = set.get(0);
    for (int value : set) {
      if (value > max) {
        max = value;
      }
    }
    return max;
  }

  /**
   * returns the smallest value in the set.
   *
   * @return smallest integer in the set
   * @throws IllegalStateException if the set is empty
   */
  public int smallest() {
    if (set.isEmpty()) {
      throw new IllegalStateException("set is empty");
    }
    int min = set.get(0);
    for (int value : set) {
      if (value < min) {
        min = value;
      }
    }
    return min;
  }

  /**
   * adds an item to the set if it is not already present.
   *
   * @param item integer value to add
   */
  public void add(int item) {
    if (!set.contains(item)) {
      set.add(item);
    }
  }

  /**
   * removes an item from the set if it is present.
   *
   * @param item integer value to remove
   */
  public void remove(int item) {
    set.remove(Integer.valueOf(item));
  }

  /**
   * updates this set to be the union of this and another set.
   * result is all unique elements that appear in either set.
   *
   * @param other other set to union with
   */
  public void union(IntegerSet other) {
    if (other == null) {
      return;
    }
    if (this == other) {
      return;
    }
    for (int value : other.set) {
      if (!this.set.contains(value)) {
        this.set.add(value);
      }
    }
  }

  /**
   * updates this set to be the intersection of this and another set.
   *
   * @param other other set to intersect with
   */
  public void intersect(IntegerSet other) {
    if (other == null) {
      return;
    }
    set.retainAll(other.set);
  }

  /**
   * updates this set to be the difference of this and another set
   * (this \ other).
   *
   * @param other other set to subtract
   */
  public void diff(IntegerSet other) {
    if (other == null) {
      return;
    }
    set.removeAll(other.set);
  }

  /**
   * updates this set to be the complement relative to another set:
   * (other \ this).
   *
   * @param other reference set for the complement
   */
  public void complement(IntegerSet other) {
    if (other == null) {
      return;
    }
    List<Integer> result = new ArrayList<Integer>();
    for (int value : other.set) {
      if (!this.set.contains(value)) {
        result.add(value);
      }
    }
    set.clear();
    set.addAll(result);
  }

  /**
   * returns true if the set has no elements.
   *
   * @return true if empty, false otherwise
   */
  public boolean isEmpty() {
    return set.isEmpty();
  }

  /**
   * returns a string representation of the set in square brackets.
   *
   * @return string like [1, 2, 3] or [] for empty
   */
  @Override
  public String toString() {
    if (set.isEmpty()) {
      return "[]";
    }
    StringBuilder builder = new StringBuilder();
    builder.append("[");
    for (int i = 0; i < set.size(); i++) {
      builder.append(set.get(i));
      if (i < set.size() - 1) {
        builder.append(", ");
      }
    }
    builder.append("]");
    return builder.toString();
  }
}
