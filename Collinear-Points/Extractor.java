package com.company;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  JARED BORDERS (jlb0082@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public class Extractor {

   /** raw data: all (x,y) points from source data. */
   private Point[] points;

   /** lines identified from raw data. */
   private SortedSet<Line> lines;

   /**
    * Builds an extractor based on the points in the file named by filename.
    */
   public Extractor(String filename) {
   
      try {
      
         Scanner scan = new Scanner(new File(filename));
      
         int n = scan.nextInt();
         points = new Point[n];
      
         int count = 0;
         while (scan.hasNext() && count <= n) {
         
            int x = scan.nextInt();
            int y = scan.nextInt();
            Point pair = new Point(x, y);
            points[count] = pair;
         
            count++;
         }
      
      }
      
      catch (Exception e) {
         System.out.println("File not found");
      }
   
   }

   /**
    * Builds an extractor based on the points in the Collection named by pcoll.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }

   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      Iterator itr = lines.iterator();
   
      double slope1;
      double slope2; 
      double slope3;
      for (int p = 3; p < points.length; p++) {
         for (int q = 2; q < p; q++) {
            for (int r = 1; r < q; r++) {
               for (int s = 0; s < r; s++) {
               
                  slope1 = points[p].slopeTo(points[q]);
                  slope2 = points[p].slopeTo(points[r]);
                  slope3 = points[p].slopeTo(points[s]);
               
                  if (slope1 == slope2 && slope2 == slope3) {
                     Line newLine = new Line();
                  
                     newLine.add(points[p]);
                     newLine.add(points[q]);
                     newLine.add(points[r]);
                     newLine.add(points[s]);
                  
                     lines.add(newLine);
                  }
               }
            }
         }
      }
   
      return lines;
   }

   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      Iterator itr = lines.iterator();
   
      Point[] sortedCopy = Arrays.copyOf(points, points.length);
   
      for (int p = 0; p < points.length; p++) {
      
           // sort based on slope to point p
         Arrays.sort(sortedCopy, points[p].slopeOrder);
      
      
         int sameSlope = 0;
         for (int a = 0; a < points.length - 1; a = a + sameSlope) {
         
               // reset
            sameSlope = 0;
            int i = 0;
         
               // since sortedCopy is sorted with reference to p,
               // check how many consecutive points exist w/ same slope
            while (a + i < points.length && points[p].slopeOrder.compare(sortedCopy[a], sortedCopy[a + i]) == 0) {
               i++;
               sameSlope++;
            }
         
         
               // if 3 or more points have same slope w/ p, make new line.
            if (sameSlope >= 3) {
            
               Line newLine = new Line();
               newLine.add(points[p]);
            
               for (int z = 0; z < sameSlope; z++) {
                  newLine.add(sortedCopy[a + z]);
               }
               lines.add(newLine);
            
            }
         
         
         }
      
      
      }
   
      return lines;
   }

}
