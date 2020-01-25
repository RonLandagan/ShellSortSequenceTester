public class ShellSort{
   
   Comparable[] a;
   long comparisons;
   
   //Constructor
   ShellSort(Comparable[] a){
      this.a = a.clone();
      comparisons = 0;
   }
   
/*
 * Function: less
 * -----------------------
 *  Takes in two Comparables and returns a boolean value depending on if 
 *  the first Comparable is less than the second.
 *
 *  v: The first Comparable to be compared.
 *  w: The second Comparable to be compared
 *
 *  returns: a boolean depending on whether v is less than w.
 */ 
   private boolean less(Comparable v, Comparable w) {
      comparisons++;
      return v.compareTo(w) < 0;
   }
        
/*
 * Function: exch
 * -----------------------
 *  Takes in an array of Comparables and two ints. The function swaps the 
 *  values of the array at the indexes of the passed in ints.
 *
 *  a: The array holding values to be swapped.
 *  v: The index of the first value to be swapped.
 *  w: The index of the second value to be swapped.
 *
 *  returns: Nothing.
 */ 
   private void exch(Comparable[] a, int i, int j) {
       Comparable swap = a[i];
       a[i] = a[j];
       a[j] = swap;
   }

/*
 * Function: printArray
 * -----------------------
 *  Prints the private array of Comparables, a
 *
 *  returns: Nothing.
 */          
   public void printArray(){
      int l = a.length;
      for(int i = 0; i<l; i++){
         System.out.print(a[i] + " ");
      }
      System.out.println("");  
   }
   
/*
 * Function: getComparisons
 * -----------------------
 *  Returns the private long comparisons.
 *
 *  returns: Number of total comparisons.
 */       
   public long getComparisons(){
      return comparisons;
   }
   
/*
 * Function: resetComparisons
 * -----------------------
 *  Resets the private long of comparisons to 0.
 *
 *  returns: Nothing.
 */  
   public void resetComparisons(){
      comparisons = 0;
   }
   
/*
 * Function: sortUsing
 * -----------------------
 *  Takes in an array of ints and uses a ShellSort algorithm using
 *  the passed in array as an increment sequence
 *
 *  returns: Nothing.
 */ 
   public void sortUsing(int [] incs) { //take in a Comparable array and inc seq 
      int n = a.length;
      Comparable[] tempArray = a.clone();
      
      for(int k = 0; k < incs.length; k++) {
         int h = incs[k];
         for (int i = h; i < n; i++) {
            for (int j = i; j >= h && less(tempArray[j], tempArray[j-h]); j -= h) {
               exch(tempArray, j, j - h);
            }
         } 
      }
   }

}