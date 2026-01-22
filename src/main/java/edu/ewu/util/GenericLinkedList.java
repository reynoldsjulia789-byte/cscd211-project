package edu.ewu.util;

import edu.ewu.model.Flashcard;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericLinkedList<T extends Comparable<T>> implements Iterable<T>                           // <T> is a type parameter that can hold any type of object
{
    // class inside a class (this inner class is for nodes of the linked list)
    // static means it doesn't need outer class instance (what does this mean?)
    private static class Node<T>
    {
        T       data;
        Node<T> next;

        Node(T data)
        {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;                                    // Reference to 1st node (null if empty)
    private int     size;

    public GenericLinkedList()                               // Construct empty list
    {
        this.head = new Node<>(null);                   // Create dummy node (data is null)
        this.size = 0;
    }

    /**
     * Uses head recursion to create a copy of the linked list.
     * @return duplicate of list
     */
    public GenericLinkedList<T> copyList()
    {
        GenericLinkedList<T> copy;                // defensive deep copy so internal list can't be changed
        Node<T>              current;

        copy    = new GenericLinkedList<>();
        current = head.next;

        return RecursiveCopyListHelper(copy, current);
    }

    private GenericLinkedList<T> RecursiveCopyListHelper(GenericLinkedList<T> copy, Node<T> current)
    {
        if (current == null)
        {
            return copy;
        }

        RecursiveCopyListHelper(copy, current.next);

        copy.addFirst(current.data);
        return copy;
    }

    public int size()
    {
        return this.size;
    }

    public boolean isEmpty()
    {
        return (size == 0);
    }

    /**
     * Adds a new node at the front of the list.
     * Has O(1) time complexity.
     *
     * @param data
     * @author Julia Reynolds
     */
    public void addFirst(T data)
    {
        Node<T> newNode;

        newNode = new Node<>(data);

//        if (head == null) // this block of code is only needed if you don't have a dummy head node
//        {
//            // Empty list: new node becomes head
//            this.head = newNode;
//        }
//        else
//        {
            // Non-empty: new node points to old head
            newNode.next   = head.next;
            this.head.next = newNode;
//        }

        size++;
    }

    /**
     * Adds a new node at the end of the list
     * Has O(n) time complexity because you have to traverse the entire list
     *
     * @param data
     * @author Julia Reynolds
     */
    public void addLast(T data)
    {
        Node<T> newNode;

        newNode = new Node<>(data);

//        if(head == null)  // need this block of code if no dummy
//        {
//            // Empty list
//            head = newNode;
//        }
//        else
//        {
            // Traverse to the end of the list starting from dummy
            Node<T> current = head;                         // temporary node used to traverse the list

            while (current.next != null)
            {
                current = current.next;
            }

            current.next = newNode;
//        }

        size++;
    }

    public boolean remove(T data)
    {
        // Removing data requires finding the node before the target so you can relink

//        if (head == null) // dont need if dummy head exists
//        {
//            return false;                                   // can't remove from an empty list
//        }
//
//        // Special case: removing head
//        if (head.data.equals(data))
//        {
//            head = head.next;
//            size--;
//            return true;
//        }

        // General case: find node before target
        Node<T> current = head;

        while (current.next != null)
        {
            if (current.next.data.equals(data))
            {
                // Found it: skip over the target node
                current.next = current.next.next;
                size --;
                return true;
            }

            current = current.next;                             // move through the list
        }

        return false;                                           // wasn't found and therefore wasn't removed
    }

    public boolean contains(T target)
    {
        Node<T> current = this.head.next;           // temporary node to iterate through the list, use .next to start from real 1st node

        while (current != null)                     // null indicates the end of the list
        {
            if ((current.data == null && target == null) || current.data.equals(target))        // we are doing a linear search for the target
            {
                return true;
            }

            current = current.next;                 // moves to the next node in the list
        }

        return false;
    }

    public T get(int index)
    {
        if (index < 0 || index >= this.size)
        {
            throw new IndexOutOfBoundsException("List size is " + this.size + ". Index " + index + " is out of bounds");
        }

        Node<T> current;
        int     currentidx;

        current     = head.next;                                // changed to head.next to start from real 1st node

        for (currentidx = 0; currentidx < index; currentidx++)
        {
            current = current.next;
        }

        return current.data;
    }

    public T getFirst()
    {
        if (this.size == 0)                                     // guide used isEmpty()
        {
            throw new NoSuchElementException("empty list");
        }

        return head.next.data;
    }

    public T getLast()
    {
        if (this.size == 0)
        {
            throw new NoSuchElementException("empty list");
        }

        Node<T> current;

        current = head.next;

        while (current.next != null)
        {
            current = current.next;
        }

        return current.data;
    }

    /**
     * Sorts the list in ascending order using selection sort.
     * Note: We swap DATA, not nodes, to keep it simple
     */
    public void selectionSort()
    {
        if (head.next == null || head.next.next == null)
        {
            return; // 0 or 1 elements, already sorted
        }

        // outer points to current position in sorted portion boundary
        Node<T> outer = head.next;

        while (outer.next != null)
        {
            // Find minimum in unsorted portion (from outer to end)
            Node<T> minNode  = outer;
            Node<T> inner    = outer.next;

            while (inner != null)
            {
                if (inner.data.compareTo(minNode.data) < 0)
                {
                    minNode  = inner;
                }

                inner        = inner.next;
            }

            // Swap data between outer & minNode
            if (minNode != outer)
            {
                T temp       = outer  .data;
                outer  .data = minNode.data;
                minNode.data = temp;
            }

            outer = outer.next;
        }

        /*
            When to swap data vs swap nodes

            Swap data:
                - simpler code
                - works when data is small
                - less efficient if data is large (copying)

            Swap nodes:
                - more complex (must track previous nodes)
                - efficient for large data objects
                - what you'd do in a real implementation
         */
    }

    public int countRecursive()
    {
        return countRecursiveHelper(head.next); // skip dummy node
    }

    private int countRecursiveHelper(Node<T> current)
    {
        // Base case: if the current node is null, we have reached the end
        if (current == null)
        {
            return 0;
        }

        // Recursive step: add 1 to count (counts this node) and add count of rest of list
        return 1 + countRecursiveHelper(current.next);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>
    {
        private Node<T> current = head.next;                 // start at first real node

        @Override
        public boolean hasNext()                            // returns true if more elements exist
        {
            return (current != null);
        }

        @Override
        public T next()
        {
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }

            T data  = current.data;
            current = current.next;

            return data;
        }
    }

    @Override
    public String toString()
    {
        if (head.next == null)     // head.next is real 1st node
        {
            return "[]";
        }

        /*
            StringBuilder builds strings
            Why use this rather than just adding strings?
            Adding strings together is an O(n^2) operation due to the immutability of the String class
            StringBuilder reduces this to O(n)
            When you add strings, String creates a temporary StringBuilder under the hood
         */
        StringBuilder builder;

        builder          = new StringBuilder("[");
        Node<T> current  = this.head.next;

        while (current != null)
        {
            builder.append(current.data);

            if (current.next != null)
            {
                builder.append(" --> ");
            }

            current = current.next;
        }

        builder.append("]");

        return builder.toString();
    }
}

/*
    Things that change with dummy head node (also called Sentinel):

    Operation:              No Dummy:               With Dummy:
    Empty check             head == null            head.next == null
    1st element             head                    head.next
    addFirst                Special case if null    Always insert after head
    addLast                 Special case if null    Just traverse (head never null)
    remove                  Special case for head   No special cases
 */
