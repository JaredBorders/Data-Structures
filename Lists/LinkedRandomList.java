import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * LinkedRandomList.java.
 * DOESNT MEET TIME RESTRAINTS
 * Pretty much just practice (same function as ArrayRandomizedList, but slower)
 *
 * @author   JARED BORDERS
 * @version  11/6/18
 */

public class LinkedRandomList<T> implements RandomizedList<T> {
    private Node front;
    private Node rear;
    private int size;

    /**
     * Constructor
     */
    @SuppressWarnings("unchecked") //Gets rid of warning
    public LinkedRandomList() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    private class Node {
        private T element;
        private Node next;
        private Node prev;
        public Node(T e) {
            element = e;
        }
    }

    /** Returns the number of elements in this list */
    public int size() {
        return size;
    }

    /** Returns true if list contains no elements; false otherwise */
    public boolean isEmpty() {
        return size == 0;
    }

    public int length(Node n) {
        Node p = n;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next; }
        return len; }

    /**
     * Adds the specified element to this list. If the element is null, this
     * method throws an IllegalArgumentException.
     */
    public void add(T element){
        if (element == null) { throw new IllegalArgumentException(); }

        Node n = new Node(element);
        if (isEmpty()) {
            front = n;
            rear = n;
        } else {
            n.prev = rear;
            rear.next = n;
            rear = n; }
        size++; }

    /**
     * Selects and removes an element selected uniformly at random from the
     * elements currently in the list. If the list is empty this method returns
     * null.
     */
    public T remove() {
        if (this.isEmpty()) { return null; }

        Random random = new Random();
        int index = random.nextInt(size);

        Node n = front;
        Node prev = null;
        for (int i = 1; i < index; i++) {
            prev = n;
            n = n.next; }

        // save element to return
        T result = n.element;
        if (n == front) { front = front.next; }
        else { prev.next = n.next; }
        size--;

        return result; }



    public boolean removeNotRandom(T element) {
        // Locate
        Node n = front;
        Node prev = null;
        while ((n != null) &&
                (!n.element.equals(element))) {
            prev = n;
            n = n.next; }

        if (n == null) { return false; }

        if (size == 1) {
            front = null;
            size = 0;
            return true; }

        if (n == front) {
            front = front.next;
            front.prev = null; }

        else {
            n.prev.next = n.next;
            if (n.next != null) { n.next.prev = n.prev; } }

        size--;
        return true; }

    public T sample() {
        if (this.isEmpty()) { return null; }

        Random random = new Random();
        int index = random.nextInt(size);

        Node n = front;
        Node prev = null;
        for (int i = 1; i < index; i++) {
            prev = n;
            n = n.next; }
        // save element to return
        T result = n.element;

        return result; }

    /** Creates and returns an iterator over the elements of this list */
    public Iterator<T> iterator() { return new ArrayIterator(); }

    /** Nested class that makes an iterator */
    public class ArrayIterator implements Iterator<T> {
        private Node current = front;
        private Node prev;
        private int newSize = size;

        /** Checks if the next element exists */
        public boolean hasNext() {
            return current != null;
        }

        /** Returns the next element (if there) in the list */
        public T next() {
            if (!hasNext()) { throw new NoSuchElementException(); }

            Random random = new Random();
            if (newSize == 0) { newSize = size; }
            int index = random.nextInt(newSize);

            Node prev = null;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next; }

            // save element to return
            T result = current.element;

            if (index != (newSize)) {
                add(result);
                removeNotRandom(current.element);
                newSize--; }

            current = front;
            size--;
            return result; }

        /** Remove() will only throw UnsupportedOperationException */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
