package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * tests for the integerset class.
 */
public class IntegerSetTest {

  /**
   * tests clear, length, and isempty together.
   */
  @Test
  public void testClearLengthAndIsEmpty() {
    IntegerSet set = new IntegerSet();
    assertTrue(set.isEmpty());
    assertEquals(0, set.length());

    set.add(1);
    set.add(2);
    assertFalse(set.isEmpty());
    assertEquals(2, set.length());

    set.clear();
    assertTrue(set.isEmpty());
    assertEquals(0, set.length());
  }

  /**
   * tests add and that duplicates are not inserted.
   */
  @Test
  public void testAddNoDuplicates() {
    IntegerSet set = new IntegerSet();
    set.add(1);
    set.add(2);
    set.add(2);
    set.add(3);

    assertEquals(3, set.length());
    assertTrue(set.contains(1));
    assertTrue(set.contains(2));
    assertTrue(set.contains(3));
  }

  /**
   * tests remove behavior when item is present and when it is not.
   */
  @Test
  public void testRemove() {
    IntegerSet set = new IntegerSet();
    set.add(1);
    set.add(2);
    set.add(3);

    set.remove(2);
    assertFalse(set.contains(2));
    assertEquals(2, set.length());

    set.remove(5);
    assertEquals(2, set.length());
    assertTrue(set.contains(1));
    assertTrue(set.contains(3));
  }

  /**
   * tests contains for present and absent values.
   */
  @Test
  public void testContains() {
    IntegerSet set = new IntegerSet();
    set.add(10);
    set.add(-3);

    assertTrue(set.contains(10));
    assertTrue(set.contains(-3));
    assertFalse(set.contains(0));
  }

  /**
   * tests largest on normal sets and that it throws on empty.
   */
  @Test
  public void testLargest() {
    IntegerSet set = new IntegerSet();
    set.add(4);
    set.add(10);
    set.add(-2);
    set.add(7);

    assertEquals(10, set.largest());

    set.clear();
    assertThrows(IllegalStateException.class, set::largest);
  }

  /**
   * tests smallest on normal sets and that it throws on empty.
   */
  @Test
  public void testSmallest() {
    IntegerSet set = new IntegerSet();
    set.add(4);
    set.add(10);
    set.add(-2);
    set.add(7);

    assertEquals(-2, set.smallest());

    set.clear();
    assertThrows(IllegalStateException.class, set::smallest);
  }

  /**
   * tests will equals for equal and unequal sets, including order independence.
   */
  @Test
  public void testEquals() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1);
    set1.add(2);
    set1.add(3);

    IntegerSet set2 = new IntegerSet();
    set2.add(3);
    set2.add(2);
    set2.add(1);

    IntegerSet set3 = new IntegerSet();
    set3.add(1);
    set3.add(2);

    assertTrue(set1.equals(set2));
    assertTrue(set2.equals(set1));
    assertFalse(set1.equals(set3));
    assertFalse(set1.equals(null));
    assertFalse(set1.equals("not a set"));
    assertTrue(set1.equals(set1));
  }

  /**
   * tests union behavior, including overlap and that other is unchanged.
   */
  @Test
  public void testUnion() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1);
    set1.add(2);

    IntegerSet set2 = new IntegerSet();
    set2.add(2);
    set2.add(3);

    set1.union(set2);

    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertEquals(3, set1.length());

    assertTrue(set2.contains(2));
    assertTrue(set2.contains(3));
    assertEquals(2, set2.length());

    IntegerSet set3 = new IntegerSet();
    set3.add(5);
    set3.add(6);
    set3.union(set3);
    assertTrue(set3.contains(5));
    assertTrue(set3.contains(6));
    assertEquals(2, set3.length());
  }

  /**
   * tests intersection behavior for overlapping and disjoint sets.
   */
  @Test
  public void testIntersect() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1);
    set1.add(2);
    set1.add(3);

    IntegerSet set2 = new IntegerSet();
    set2.add(2);
    set2.add(3);
    set2.add(4);

    set1.intersect(set2);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertFalse(set1.contains(1));
    assertFalse(set1.contains(4));

    IntegerSet set3 = new IntegerSet();
    set3.add(10);
    set3.add(20);

    IntegerSet set4 = new IntegerSet();
    set4.add(30);
    set4.add(40);

    set3.intersect(set4);
    assertTrue(set3.isEmpty());
  }

  /**
   * tests difference behavior (this \ other).
   */
  @Test
  public void testDiff() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1);
    set1.add(2);
    set1.add(3);

    IntegerSet set2 = new IntegerSet();
    set2.add(2);
    set2.add(4);

    set1.diff(set2);

    assertEquals(2, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(3));
    assertFalse(set1.contains(2));
    assertFalse(set1.contains(4));

    IntegerSet set3 = new IntegerSet();
    set3.add(5);
    set3.add(6);
    set3.diff(set3);
    assertTrue(set3.isEmpty());
  }

  /**
   * tests complement behavior (other \ this).
   */
  @Test
  public void testComplement() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1);
    set1.add(2);

    IntegerSet set2 = new IntegerSet();
    set2.add(2);
    set2.add(3);
    set2.add(4);

    set1.complement(set2);

    assertEquals(2, set1.length());
    assertTrue(set1.contains(3));
    assertTrue(set1.contains(4));
    assertFalse(set1.contains(1));
    assertFalse(set1.contains(2));

    IntegerSet set3 = new IntegerSet();
    set3.add(10);
    set3.add(20);

    IntegerSet set4 = new IntegerSet();
    set4.add(10);
    set4.add(20);

    set3.complement(set4);
    assertTrue(set3.isEmpty());
  }

  /**
   * tests tostring form for empty and non-empty sets.
   */
  @Test
  public void testToString() {
    IntegerSet empty = new IntegerSet();
    assertEquals("[]", empty.toString());

    IntegerSet set = new IntegerSet();
    set.add(1);
    set.add(2);
    set.add(3);

    String s = set.toString();
    assertTrue(s.startsWith("["));
    assertTrue(s.endsWith("]"));
    assertTrue(s.contains("1"));
    assertTrue(s.contains("2"));
    assertTrue(s.contains("3"));
  }
}