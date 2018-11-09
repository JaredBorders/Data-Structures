import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * DoubleEndedList.java. Uses nodes to create a double-ended list.
 * Elements can be inserted and deleted from either end of the list, but
 * not from any other location.
 *
 * @author JARED BORDERS
 * @version  11/6/18
 */
public class LinkedDoubleEndedList<T> implements DoubleEndedList<T> {
   private Node front;
   private Node rear;
   private int size;

   /**
    * Constructor
    */
   @SuppressWarnings("unchecked") //Gets rid of warning
   public LinkedDoubleEndedList() {
      this.front = null;
      this.rear = null;
      this.size = 0;
   }

   private class Node {
      private T element;
      private Node next;
      private Node prev;
   
      public Node(T e) { element = e; }
   }

   /** returns the size of the list */
   public int size() { 
      return size; }

   /** checks if the list is empty */
   public boolean isEmpty() { 
      return size == 0; }

   /**
    * Adds element to the front of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   public void addFirst(T element){
      if (element == null) { throw new IllegalArgumentException(); }
   
      Node n = new Node(element);
      if (front == null) {
         front = n;
         rear = n;
      } else {
         // rear doesnt change
         front.prev = n;
         n.next = front;
         front = n;
      }
   
      size++;
   }

   /**
    * Adds element to the end of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   public void addLast(T element){
      if (element == null) { throw new IllegalArgumentException(); }
   
      Node n = new Node(element);
      if (front == null) {
         front = n;
         rear = front;
      } else {
         // front doesnt change
         n.prev = rear;
         rear.next = n;
         rear = n;
      }
      size++;
   }

   /**
    * Delete and return the element at the front of the list.
    * If the list is empty, this method returns null.
    */
   public T removeFirst(){
      if (isEmpty()) { 
         return null; }
      T result = front.element;
      if (size == 1) {
         front = null;
         rear = null;
         size--;
         return result;
      }
      else {
         front = front.next;
         front.prev = null;
         size--;
         return result;
      }
   }

   /**
    * Delete and return the element at the end of the list.
    * If the list is empty, this method returns null.
    */
   public T removeLast(){
      if (isEmpty()) { 
         return null; }
      T result = rear.element;
      if (size == 1) {
         front = null;
         rear = null;
         size--;
         return result;
      } else {
         rear = rear.prev;
         rear.next = null;
         size--;
         return result;
      }
   }

   /** Creates and returns an iterator over the elements of this list */
   public Iterator<T> iterator() { 
      return new LinkedNodeIterator(); }

   /** Nested class that makes an iterator */
   private class LinkedNodeIterator implements Iterator<T> {
      private Node current = front;
   
      /** Checks if the next element exists */
      public boolean hasNext() {
         return current != null;
      }
   
      /**
       * Returns the next element in the list, or throws a NoSuchElementException if
       * there are no more elements.
       */
      public T next() {
         if (!hasNext()) { throw new NoSuchElementException(); }
         T next = current.element;
         current = current.next;
         return next;
      }
   
      /** Remove() will only throw UnsupportedOperationException */
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}