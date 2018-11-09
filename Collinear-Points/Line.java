package com.company;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Line.java
 * Models a line segment as a sorted set of points.
 *
 * @author  JARED BORDERS (jlb0082@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 10/31/18
 *
 */
public class Line implements Comparable<Line>, Iterable<Point> {

   SortedSet<Point> line;

   /**
    * Creates a new line containing no points.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Line() {
      line = new TreeSet<Point>();
   }

   /**
    * Creates a new line with containing all distinct collinear points in the
    * Collection c.
    */
   public Line(Collection<Point> c) {
   
      line = new TreeSet<Point>();
   
      Iterator<Point> i = c.iterator();
   
      while (i.hasNext()) {
         add(i.next());
      }
   }

   /**
    * Adds the point p to this line if p is collinear with all points already
    * in the line and p itself is not already in the line. Returns true if this
    * line is changed as a result, false otherwise.
    */
   public boolean add(Point p) {
   
      // p itself is already in the line
      if (line.contains(p)) {
         return false;
      }
   
      // line is empty, therefore no duplicates
      if (line.isEmpty()) {
         line.add(p);
         return true;
      }
      
      //  Adds the point p to this line if p is collinear with all points already
      //  in the line
      else {
         if (line.first().slopeTo(p) == line.last().slopeTo(p)) {
            line.add(p);
            return true;
         }
      
      }
   
      return false;
   }

   /**
    * Returns the first (minimum) point in this line or null if this line
    * contains no points.
    */
   public Point first() {
   
      if (!line.isEmpty()) {
         return line.first();
      }
   
      return null;
   }

   /**
    * Returns the last (maximum) point in this line or null if this line
    * contains no points.
    */
   public Point last() {
   
      if (!line.isEmpty()) {
         return line.last();
      }
   
      return null;
   }

   /**
    * Returns the number of points in this line.
    */
   public int length() {
      return line.size();
   }

   /**
    * Compares this line with the specified line for order. Returns a negative
    * integer, zero, or a positive integer if this line is less than, equal to,
    * or greater than the specified line. Lines are ordered first by their
    * first point then by their last point. An empty line is less than any
    * non-empty line, and all empty lines are equal. All three properties of
    * compareTo as specified in the Comparable interface are met, and this
    * implementation is consistent with equals.
    */
   @Override
   public int compareTo(Line that) {
   
      // all empty lines are equal
      if (line.size() == 0 && that.length() == 0) {
         return 0;
      }
      
      //  An empty line is less than any non-empty line
      else if (line.size() == 0) {
         return -1;
      }
      
      //  An empty line is less than any non-empty line
      else if (that.length() == 0) {
         return 1;
      }
   
      // compare first points via method defined in Point
      int first = line.first().compareTo(that.first());
   
      if (first > 0) {
         return 1;
      }
      
      else if (first < 0) {
         return -1;
      }
   
      // compare last points via method defined in Point
      int last = line.last().compareTo(that.last());
   
      if (last < 0) {
         return -1;
      }
      
      else if (last > 0) {
         return 1;
      }
   
      // this line is equal to that line
      return 0;
   }

   /**
    * Provide an iterator over all the points in this line. The order in which
    * points are returned must be ascending natural order.
    */
   @Override
   public Iterator<Point> iterator() {
      Iterator<Point> iterator;
      iterator = line.iterator();
      return iterator;
   }

   /**
    * Return true if this line's first and last points are equal to the
    * parameter's first and last points. Empty lines are equal to each other
    * but are not equal to any non-empty line.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj == this) {
         return true;
      }
      if (!(obj instanceof Line)) {
         return false;
      }
      Line that = (Line) obj;
      if ((this.length() == 0) && (that.length() == 0)) {
         return true;
      }
      if ((this.length() == 0) && (that.length() != 0)) {
         return false;
      }
      if ((this.length() != 0) && (that.length() == 0)) {
         return false;
      }
      return (this.first().equals(that.first())) && (this.last().equals(that.last()));
   }

   /**
    * Return a string representation of this line.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   @Override
   public String toString() {
      if (length() == 0) {
         return "";
      }
      StringBuilder s = new StringBuilder();
      for (Point p : line) {
         s.append(p + " -> ");
      }
      s = s.delete(s.length() - 4, s.length());
      return s.toString();
   }

}