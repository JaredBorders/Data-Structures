import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   JARED BORDERS
*
*/
public final class Selector1 {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector1() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is set to null or it's length is zero"); 
      }
    
      int min = a[0];
      for (int n : a) {
         if (n < min) { 
            min = n; 
         } 
      }
      return min; 
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is set to null or it's length is zero"); 
      }
    
      int max = a[0];
      for (int n : a) {
         if (n > max) { 
            max = n; 
         } 
      }
      return max; 
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is set to null or it's length is zero"); 
      }
      if (k < 1 || k > a.length) {
         throw new IllegalArgumentException("there is no kth minimum value"); 
      }
   
   // sort a copy of array a, and then find duplicates 
      int[] copy = Arrays.copyOf(a, a.length);
      Arrays.sort(copy);
      int duplicates = 0;
      int distinctValues = 0;
      for (int i = 0; i < copy.length - 1; i++) {
         if (copy[i] == copy[i + 1]) {
            duplicates++;
         }
      }
      // the number of distinct values is the total number of values minus duplicates
      distinctValues = copy.length - duplicates;
      
      if (k > distinctValues) {
         throw new IllegalArgumentException("k is greater than the number of"
            + " distinct values in the array");
      }
      
    // find kmin
      int[] noRepeats = new int[distinctValues];
      int c1 = 0;
      int c2 = 1;
      // f used for iteratation
      int f = 0;
      // i used to store correct sorted value at specified location in [noRepeats]
      int i = 0;
      while (f < copy.length) {
      
         if (c2 < copy.length && copy[c1] == copy[c2]) {
            c2++;
            f++;   
         }
         
         else if (!(c2 > copy.length)) {
            noRepeats[i] = copy[c1];
            c1 = c2;
            c2++;
            i++;
            f++;
         }  
      }
      
      int kmin = noRepeats[k - 1];
      return kmin ;
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is set to null or it's length is zero"); 
      }
      if (k < 1 || k > a.length) {
         throw new IllegalArgumentException("there is no kth minimum value"); 
      }
   
   // sort a copy of array a, and then find duplicates 
      int[] copy = Arrays.copyOf(a, a.length);
      Arrays.sort(copy);
      int duplicates = 0;
      int distinctValues = 0;
      for (int i = 0; i < copy.length - 1; i++) {
         if (copy[i] == copy[i + 1]) {
            duplicates++;
         }
      }
      // the number of distinct values is the total number of values minus duplicates
      distinctValues = copy.length - duplicates;
      
      if (k > distinctValues) {
         throw new IllegalArgumentException("k is greater than the number of"
            + " distinct values in the array");
      }
      
        // find kmax
      int[] noRepeats = new int[distinctValues];
      int c1 = 0;
      int c2 = 1;
      // f used for iteratation
      int f = 0;
      // i used to store correct sorted value at specified location in [noRepeats]
      int i = 0;
      while (f < copy.length) {
      
         if (c2 < copy.length && copy[c1] == copy[c2]) {
            c2++;
            f++;   
         }
         
         else if (!(c2 > copy.length)) {
            noRepeats[i] = copy[c1];
            c1 = c2;
            c2++;
            i++;
            f++;
         }  
      }
    
      int kmax = noRepeats[distinctValues - k];
      return kmax;
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is set to null or it's length is zero"); 
      }
         
   // how many values in [a] fall in-between low and high
      int count = 0;   
      for (int n : a) {
         if (n >= low && n <= high) {
            count++; 
         }
      }
       
    // create new array "range" to store range of values
      int[] range = new int[count];
      
    // add values that meet criteria to array just created  
      int i = 0;
      for (int n : a) {
         if (n >= low && n <= high) {
            range[i] = n;
            i++; 
         }
      }
    
      return range;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is set to null or it's length is zero"); 
      }
      
      int ceiling = max(a);
      // i records if there has been a value found to satisfy the method "ceiling"
      int i = 0;
      for (int n : a) {
         if (n >= key && n <= ceiling) {
            ceiling = n;
            i++;
         }
      }
      if (i == 0) {
         throw new IllegalArgumentException("There is no qualifying value i"); 
      }
   
      return ceiling;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is set to null or it's length is zero"); 
      }
      int floor = min(a);
      // i records if there has been a value found to satisfy the method "floor"
      int i = 0;
      for (int n : a) {
         if (n <= key && n >= floor) {
            floor = n;
            i++;
         }
      }
      if (i == 0) {
         throw new IllegalArgumentException("There is no qualifying value i"); 
      }
   
      return floor;
   }

}
