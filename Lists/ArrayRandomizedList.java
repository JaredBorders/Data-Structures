import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * ArrayRandomizedList.java.
 * Randomized list collection; a list with order defined as "random
 * order." The order is "random" in the sense
 * that the element accessed by either the sample or remove method is selected
 * uniformly at random from the current elements in the list. In addition, an
 * iterator on a randomized list will sequentially access each element in some
 * uniformly random sequence. Simultaneous iterators on the same randomized
 * list are independent of each other. That is, they will with high probability
 * have different iteration sequences.
 *
 * @author   JARED BORDERS
 * @version  11/6/18
 */

public class ArrayRandomizedList<T> implements RandomizedList<T> {
    private T[] elements;
    private int size;
    private static final int DEFAULT_LENGTH = 10;

    /** Constrcutor for ArrayRandomizedList */
    @SuppressWarnings("unchecked")
    public ArrayRandomizedList() {
        this.elements = (T[]) new Object[DEFAULT_LENGTH];
        this.size = 0;
    }

    /** returns the size of the list */
    public int size() { return size; }

    /** checks if the list is empty */
    public boolean isEmpty() { return size == 0; }

    /** Adds an element to the list */
    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        // resize if full
        if (size == elements.length) {
            resize(elements.length * 2);
        }

        elements[size] = element;
        size++;
    }

    /** removes an element from the list AT RANDOM */
    public T remove() {
        if (this.isEmpty()) { return null; }

        Random r = new Random();
        int random = r.nextInt(size);
        // x is what will be returned
        T x = elements[random];
        elements[random] = null;

        // if element just removed isn't located at end of list, just replace recently removed element's
        // index with the element at the end of the list (at index size - 1)
        if (random != (size - 1)) {
            elements[random] = elements[size - 1];
            elements[size - 1] = null;
        }
        size --;

        // check if less than 25% full
        if (size > 0 && size < elements.length / 4) {
            resize(elements.length/2);
        }

        return x;
    }

    /** returns an element from the list AT RANDOM */
    public T sample() {
        if (this.isEmpty()) { return null; }

        int random = new Random().nextInt(size);
        return elements[random];
    }

    /** resize array used to store elements */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] arr = (T[]) new Object[capacity];
        for (int i = 0; i < size(); i++) {
            arr[i] = elements[i];
        }
        elements = arr;
    }


    /** Creates and returns an iterator over the elements of this list */
    public Iterator<T> iterator() {
        return new ArrayIterator(elements, size);
    }

    /** Nested class that defines the behavior of the ArrayIterator */
    public class ArrayIterator implements Iterator<T> {
        private T[] items;
        private int count;
        private int current;

        /** Constructor */
        public ArrayIterator(T[] array, int newSize) {
            count = newSize;
            items = array;
        }

        /** checks if the iterator has a next element */
        public boolean hasNext() { return current < count; }

        /**
         * Returns the next element in the list, or throws a NoSuchElementException if
         * there are no more elements.
         */
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Random random = new Random();
            int value = random.nextInt(count);

            // save element to return
            T next = items[value];

            if (value != (count - 1)) { //(Size - 1) = last index
                items[value] = items[count - 1];
                items[count - 1] = next;
            }

            count--;
            return next;
        }

        /** Remove() will only throw UnsupportedOperationException */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}