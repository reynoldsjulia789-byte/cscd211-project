package edu.ewu.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD test suite for GenericLinkedList.
 * 
 * Following Red-Green-Refactor:
 * 1. Write a failing test (RED)
 * 2. Write minimal code to pass (GREEN)
 * 3. Improve the code (REFACTOR)
 * 
 * Tests are organized by behavior using @Nested classes.
 */
@DisplayName("GenericLinkedList")
class GenericLinkedListTest
{

    private GenericLinkedList<String> stringList;
    private GenericLinkedList<Integer> intList;

    @BeforeEach
    void setUp()
    {
        stringList = new GenericLinkedList<>();
        intList = new GenericLinkedList<>();
    }

    @Nested
    @DisplayName("when new")
    class WhenNew
    {

        @Test
        @DisplayName("should be empty")
        void shouldBeEmpty() {
            assertTrue(stringList.isEmpty());
        }

        @Test
        @DisplayName("should have size 0")
        void shouldHaveSizeZero() {
            assertEquals(0, stringList.size());
        }

        @Test
        @DisplayName("get(0) should throw IndexOutOfBoundsException")
        void getShouldThrowOnEmptyList() {
            assertThrows(IndexOutOfBoundsException.class, () -> stringList.get(0));
        }
    }

    @Nested
    @DisplayName("after adding one element")
    class AfterAddingOne
    {

        @BeforeEach
        void addOne() {
            stringList.addFirst("first");
        }

        @Test
        @DisplayName("should not be empty")
        void shouldNotBeEmpty() {
            assertFalse(stringList.isEmpty());
        }

        @Test
        @DisplayName("should have size 1")
        void shouldHaveSizeOne() {
            assertEquals(1, stringList.size());
        }

        @Test
        @DisplayName("get(0) should return the element")
        void getShouldReturnElement() {
            assertEquals("first", stringList.get(0));
        }

        @Test
        @DisplayName("get(1) should throw IndexOutOfBoundsException")
        void getOutOfBoundsShouldThrow() {
            assertThrows(IndexOutOfBoundsException.class, () -> stringList.get(1));
        }

        @Test
        @DisplayName("get(-1) should throw IndexOutOfBoundsException")
        void getNegativeIndexShouldThrow() {
            assertThrows(IndexOutOfBoundsException.class, () -> stringList.get(-1));
        }
    }

    @Nested
    @DisplayName("after adding multiple elements")
    class AfterAddingMultiple
    {

        @BeforeEach
        void addMultiple()
        {
            stringList.addFirst("third");
            stringList.addFirst("second");
            stringList.addFirst("first");
        }

        /**
         * @author Julia Reynolds
         */
        @Test
        @DisplayName("first element should be 'first'")
        void addFirstMaintainsOrder()
        {
            assertEquals("[first --> second --> third]", stringList.toString());
        }

        @Test
        @DisplayName("should have correct size")
        void shouldHaveCorrectSize() {
            assertEquals(3, stringList.size());
        }

        @Test
        @DisplayName("elements should be in order")
        void elementsShouldBeInOrder() {
            assertEquals("first", stringList.get(0));
            assertEquals("second", stringList.get(1));
            assertEquals("third", stringList.get(2));
        }

        @Test
        @DisplayName("get last index should work")
        void getLastShouldWork() {
            assertEquals("third", stringList.get(2));
        }
    }

    @Nested
    @DisplayName("with Integer type")
    class WithIntegerType
    {

        @Test
        @DisplayName("should handle Integer autoboxing")
        void shouldHandleIntegers() {
            intList.addFirst(3);
            intList.addFirst(2);
            intList.addFirst(1);

            assertEquals(3, intList.size());
            assertEquals(1, intList.get(0));
            assertEquals(2, intList.get(1));
            assertEquals(3, intList.get(2));
        }
    }

    @Nested
    @DisplayName("null handling")
    class NullHandling
    {

        @Test
        @DisplayName("should allow adding null elements")
        void shouldAllowNull() {
            stringList.addFirst(null);
            assertEquals(1, stringList.size());
            assertNull(stringList.get(0));
        }

        @Test
        @DisplayName("should handle null among other elements")
        void shouldHandleNullAmongOthers() {
            stringList.addLast("first");
            stringList.addLast(null);
            stringList.addLast("third");

            assertEquals(3, stringList.size());
            assertEquals("first", stringList.get(0));
            assertNull(stringList.get(1));
            assertEquals("third", stringList.get(2));
        }
    }

    @Nested
    @DisplayName("addFirst operation")
    class AddFirstOperation
    {

        @Test
        @DisplayName("addFirst on empty list makes element the head")
        void addFirstOnEmpty() {
            stringList.addFirst("first");
            assertEquals(1, stringList.size());
            assertEquals("first", stringList.get(0));
        }

        @Test
        @DisplayName("addFirst prepends element to existing list")
        void addFirstPrepends() {
            stringList.addFirst("second");
            stringList.addLast("third");
            stringList.addFirst("first");

            assertEquals(3, stringList.size());
            assertEquals("first", stringList.get(0));
            assertEquals("second", stringList.get(1));
            assertEquals("third", stringList.get(2));
        }

        @Test
        @DisplayName("multiple addFirst operations maintain correct order")
        void multipleAddFirst() {
            stringList.addFirst("third");
            stringList.addFirst("second");
            stringList.addFirst("first");

            assertEquals("first", stringList.get(0));
            assertEquals("second", stringList.get(1));
            assertEquals("third", stringList.get(2));
        }
    }

    @Nested
    @DisplayName("remove operation")
    class RemoveOperation
    {

        @BeforeEach
        void setup()
        {
            stringList.addLast("first");
            stringList.addLast("second");
            stringList.addLast("third");
        }

        @Test
        @DisplayName("remove existing element returns true")
        void removeExistingReturnsTrue() {
            assertTrue(stringList.remove("second"));
        }

        @Test
        @DisplayName("remove existing element decreases size")
        void removeDecreasesSize() {
            stringList.remove("second");
            assertEquals(2, stringList.size());
        }

        @Test
        @DisplayName("remove middle element relinks correctly")
        void removeMiddleRelinks()
        {
            stringList.remove("second");
            assertEquals("first", stringList.get(0));
            assertEquals("third", stringList.get(1));
            assertEquals("[first --> third]", stringList.toString());
        }

        @Test
        @DisplayName("remove head element works")
        void removeHead()
        {
            stringList.remove("first");
            assertEquals(2, stringList.size());
            assertEquals("second", stringList.get(0));
        }

        @Test
        @DisplayName("remove last element works")
        void removeLast() {
            stringList.remove("third");
            assertEquals(2, stringList.size());
            assertEquals("second", stringList.get(1));
        }

        @Test
        @DisplayName("remove non-existent element returns false")
        void removeNonExistentReturnsFalse()
        {
            assertFalse(stringList.remove("nonexistent"));
            assertEquals(3, stringList.size());  // Size unchanged
        }

        @Test
        @DisplayName("remove from empty list returns false")
        void removeFromEmptyReturnsFalse() {
            GenericLinkedList<String> empty = new GenericLinkedList<>();
            assertFalse(empty.remove("anything"));
        }

        @Test
        @DisplayName("remove only element makes list empty")
        void removeOnlyElement() {
            GenericLinkedList<String> single = new GenericLinkedList<>();
            single.addFirst("only");
            single.remove("only");
            assertTrue(single.isEmpty());
        }
    }

    @Nested
    @DisplayName("contains operation")
    class ContainsOperation
    {

        @BeforeEach
        void setup()
        {
            stringList.addLast("first");
            stringList.addLast("second");
            stringList.addLast("third");
        }

        @Test
        @DisplayName("contains returns true for existing element")
        void containsExisting() {
            assertTrue(stringList.contains("second"));
        }

        @Test
        @DisplayName("contains returns false for non-existent element")
        void containsNonExistent() {
            assertFalse(stringList.contains("fourth"));
        }

        @Test
        @DisplayName("contains returns false on empty list")
        void containsOnEmpty() {
            GenericLinkedList<String> empty = new GenericLinkedList<>();
            assertFalse(empty.contains("anything"));
        }

        @Test
        @DisplayName("contains handles null correctly when null is present")
        void containsNull() {
            stringList.addLast(null);
            assertTrue(stringList.contains(null));
        }

        @Test
        @DisplayName("contains handles null correctly when null is not present")
        void containsNullNotPresent() {
            assertFalse(stringList.contains(null));
        }
    }

//    @Nested
//    @DisplayName("clear operation")
//    class ClearOperation {
//
//        @Test
//        @DisplayName("clear makes list empty")
//        void clearMakesEmpty() {
//            stringList.addLast("first");
//            stringList.addLast("second");
//            stringList.clear();
//            assertTrue(stringList.isEmpty());
//            assertEquals(0, stringList.size());
//        }
//
//        @Test
//        @DisplayName("clear on empty list is safe")
//        void clearOnEmpty() {
//            stringList.clear();
//            assertTrue(stringList.isEmpty());
//        }
//
//        @Test
//        @DisplayName("list is usable after clear")
//        void usableAfterClear() {
//            stringList.addLast("first");
//            stringList.clear();
//            stringList.addLast("new");
//            assertEquals(1, stringList.size());
//            assertEquals("new", stringList.get(0));
//        }
//    }

    @Nested
    @DisplayName("Iterable interface")
    class IterableTests
    {

        @Test
        @DisplayName("implements Iterable interface")
        void implementsIterable() {
            assertTrue(stringList instanceof Iterable);
        }

        @Test
        @DisplayName("iterator on empty list has no next")
        void emptyIterator() {
            java.util.Iterator<String> iter = stringList.iterator();
            assertFalse(iter.hasNext());
        }

        @Test
        @DisplayName("iterator visits all elements in order")
        void iteratorVisitsAllInOrder() {
            stringList.addLast("first");
            stringList.addLast("second");
            stringList.addLast("third");
            
            java.util.Iterator<String> iter = stringList.iterator();
            assertTrue(iter.hasNext());
            assertEquals("first", iter.next());
            assertTrue(iter.hasNext());
            assertEquals("second", iter.next());
            assertTrue(iter.hasNext());
            assertEquals("third", iter.next());
            assertFalse(iter.hasNext());
        }

        @Test
        @DisplayName("enhanced for loop works")
        void enhancedForLoop() {
            stringList.addLast("a");
            stringList.addLast("b");
            stringList.addLast("c");
            
            StringBuilder sb = new StringBuilder();
            for (String s : stringList) {
                sb.append(s);
            }
            assertEquals("abc", sb.toString());
        }

        @Test
        @DisplayName("iterator next() throws NoSuchElementException when exhausted")
        void iteratorThrowsWhenExhausted() {
            stringList.addLast("only");
            java.util.Iterator<String> iter = stringList.iterator();
            iter.next(); // consume the only element
            
            assertThrows(java.util.NoSuchElementException.class, () -> iter.next());
        }

        @Test
        @DisplayName("multiple iterators are independent")
        void multipleIteratorsIndependent() {
            stringList.addLast("x");
            stringList.addLast("y");
            
            java.util.Iterator<String> iter1 = stringList.iterator();
            java.util.Iterator<String> iter2 = stringList.iterator();
            
            iter1.next(); // advance iter1
            
            // iter2 should still be at beginning
            assertEquals("x", iter2.next());
        }

        @Test
        @DisplayName("iterator works with null elements")
        void iteratorWithNulls() {
            stringList.addLast("first");
            stringList.addLast(null);
            stringList.addLast("third");
            
            java.util.Iterator<String> iter = stringList.iterator();
            assertEquals("first", iter.next());
            assertNull(iter.next());
            assertEquals("third", iter.next());
        }
    }

    /**
     * @author Julia Reynolds
     */
    @Nested
    @DisplayName("Sorting")
    class selectionSortTests
    {
        @Test
        void selectionSortSortsElements()
        {
            GenericLinkedList<String> list = new GenericLinkedList<>();

            list.addLast("Charlie");
            list.addLast("Alice");
            list.addLast("Bob");

            list.selectionSort();

            assertEquals("[Alice --> Bob --> Charlie]", list.toString());
        }

        @Test
        void selectionSortHandlesSingleElement()
        {
            GenericLinkedList<String> list = new GenericLinkedList<>();
            list.addLast("Only");

            list.selectionSort();  // Should not crash

            assertEquals("[Only]", list.toString());
        }

        @Test
        void selectionSortHandlesEmptyList()
        {
            GenericLinkedList<String> list = new GenericLinkedList<>();

            list.selectionSort();  // Should not crash

            assertEquals("[]", list.toString());
        }

        @Test
        void selectionSortHandlesAlreadySorted()
        {
            GenericLinkedList<String> list = new GenericLinkedList<>();
            list.addLast("A");
            list.addLast("B");
            list.addLast("C");

            list.selectionSort();

            assertEquals("[A --> B --> C]", list.toString());
        }
    }

    @Nested
    @DisplayName("Duplicating List")
    class copyListTest
    {
        @Test
        @DisplayName("correctly copies list")
        void copyListFiveElements()
        {
            GenericLinkedList<String> list, copy;

            list = new GenericLinkedList<>();

            list.addFirst("5");
            list.addFirst("4");
            list.addFirst("3");
            list.addFirst("2");
            list.addFirst("1");

            copy = list.copyList();

            assertEquals("[1 --> 2 --> 3 --> 4 --> 5]", copy.toString());
        }

        @Test
        @DisplayName("returns empty list when copying empty list")
        void copyEmptyList()
        {
            GenericLinkedList<String> list, copy;

            list = new GenericLinkedList<>();

            copy = list.copyList();

            assertEquals("[]", copy.toString());
        }

        @Test
        @DisplayName("can edit the copy of the list without editing the original list")
        void editingCopyDoesntChangeOriginal()
        {
            GenericLinkedList<String> list, copy;

            list = new GenericLinkedList<>();

            list.addFirst("5");
            list.addFirst("4");
            list.addFirst("3");
            list.addFirst("2");
            list.addFirst("1");

            copy = list.copyList();

            copy.remove("3");
            copy.remove("1");
            copy.addFirst("Edited");

            assertEquals("[Edited --> 2 --> 4 --> 5]", copy.toString());
            assertEquals("[1 --> 2 --> 3 --> 4 --> 5]", list.toString());
        }
    }
}
