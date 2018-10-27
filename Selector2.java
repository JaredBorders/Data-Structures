import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 */
public final class Selector2 {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
   
      if ((comp == null) || (coll == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
   
      // systematically examine each data element stored in a collection via iteratation to find min
      Iterator<T> itr = coll.iterator();
      T min = (T)itr.next();
   
      for (T element : coll) {
      
         if (comp.compare(min, element) > 0) {
            min = element;
         }
      
      }
   
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
   
      if ((comp == null) || (coll == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
   
      // systematically examine each data element stored in a collection via iteratation to find max
      Iterator<T> itr = coll.iterator();
      T max = (T)itr.next();
   
      for (T element : coll) {
      
         if (comp.compare(max, element) < 0) {
            max = element;
         }
      
      }
   
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
   
      if ((comp == null) || (coll == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      if (k < 1 || k > coll.size()) {
         throw new NoSuchElementException("there is no kth min value"); 
      }
   
   
      // sort a copy of array a, and then find duplicates
      ArrayList<T> copy = new ArrayList<T>(coll);
      java.util.Collections.sort(copy, comp);
   
      // count duplicates
      int duplicates = 0;
      int distinctValues = 0;
      for (int i = 0; i < copy.size() - 1; i++) {
      
         if (copy.get(i).equals(copy.get(i + 1))) {
            duplicates++;
         }
      
      }
   
      // the number of distinct values is the total number of values minus duplicates
      distinctValues = copy.size() - duplicates;
   
      if (k > distinctValues) {
         throw new NoSuchElementException("k is greater than the number of"
                 + " distinct values in the array");
      }
   
      // find kmin
      ArrayList<T> noRepeats = new ArrayList<T>(distinctValues);
   
      int c1 = 0;
      int c2 = 1;
   
      // f used for iteratation
      int f = 0;
   
      // i used to store correct sorted value at specified location in [noRepeats]
      int i = 0;
   
      while (f < copy.size()) {
      
         if (c2 < copy.size() && copy.get(c1).equals(copy.get(c2))) {
            c2++;
            f++;
         }
         
         else if (!(c2 > copy.size())) {
            noRepeats.add(copy.get(c1));
            c1 = c2;
            c2++;
            i++;
            f++;
         }
      
      }
   
      T kmin = noRepeats.get(k - 1);
   
      return kmin ;
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
   
   
      if ((comp == null) || (coll == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      if (k < 1 || k > coll.size()) {
         throw new NoSuchElementException("there is no kth max value"); 
      }
   
      // sort a copy of array a, and then find duplicates
      ArrayList<T> copy = new ArrayList<T>(coll);
      java.util.Collections.sort(copy, comp);
   
      // count duplicates
      int duplicates = 0;
      int distinctValues = 0;
      
      for (int i = 0; i < copy.size() - 1; i++) {
      
         if (copy.get(i).equals(copy.get(i + 1))) {
            duplicates++;
         }
      
      }
   
      // the number of distinct values is the total number of values minus duplicates
      distinctValues = copy.size() - duplicates;
   
      if (k > distinctValues) {
         throw new NoSuchElementException("k is greater than the number of"
                 + " distinct values in the array");
      }
   
      // find kmax
      ArrayList<T> noRepeats = new ArrayList<T>(distinctValues);
   
      int c1 = 0;
      int c2 = 1;
   
      // f used for iteratation
      int f = 0;
   
      // i used to store correct sorted value at specified location in [noRepeats]
      int i = 0;
   
      while (f < copy.size()) {
      
         if (c2 < copy.size() && copy.get(c1).equals(copy.get(c2))) {
            c2++;
            f++;
         }
         
         else if (!(c2 > copy.size())) {
            noRepeats.add(copy.get(c1));
            c1 = c2;
            c2++;
            i++;
            f++;
         }
      
      }
   
      T kmax = noRepeats.get(distinctValues - k);
   
      return kmax ;
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
   
      if ((comp == null) || (coll == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
   
      // determine how many values in coll fall in-between low and high
      Iterator<T> itr = coll.iterator();
      int count = 0;
      for (T n : coll) {
      
         if (comp.compare(n, low) >= 0 && comp.compare(n, high) <= 0) {
            count++;
         }
         
      }
   
      if (count == 0) {
         throw new NoSuchElementException();
      }
   
      // create and initialize new ArrayList "i" to store range of things
      ArrayList<T> i = new ArrayList<T>(count);
   
      // add values that meet criteria to collection i
      for (T n : coll) {
      
         if (comp.compare(n, low) >= 0 && comp.compare(n, high) <= 0) {
            i.add((T)n);
         }
      
      }
   
      return i;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
   
      if ((comp == null) || (coll == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
   
      // i records if there has been a value found to satisfy the "ceiling" value
      int i = 0;
      T ceiling = max(coll, comp);
   
      for (T n : coll) {
      
         if (comp.compare(n, key) >= 0 && comp.compare(n, ceiling) <= 0) {
            ceiling = n;
            i++;
         }
      
      }
   
      if (i == 0) {
         throw new NoSuchElementException("There is no qualifying value i");
      }
   
      return ceiling;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
   
      if ((comp == null) || (coll == null)) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
   
      // i records if there has been a value found to satisfy the "floor" value
      int i = 0;
      T floor = min(coll, comp);
   
      for (T n : coll) {
      
         if (comp.compare(n, key) <= 0 && comp.compare(n, floor) >= 0) {
            floor = n;
            i++;
         }
      
      }
   
      if (i == 0) {
         throw new NoSuchElementException("There is no qualifying value i");
      }
   
      return floor;
   }
}
