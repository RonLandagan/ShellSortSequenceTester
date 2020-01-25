import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.math.*;

public class P05{

      public static void main(String args[]){
     
      //Create array of Strings of info to be sorted
      String[] dataToBeSorted = readInput(args[0]);
      
      //Create each increment sequence array
      int[] h1 = createH1(dataToBeSorted.length);
      int[] h2 = createH2(dataToBeSorted.length);
      int[] h3 = createH3(dataToBeSorted.length);
      int[] h4 = createH4(dataToBeSorted.length);
      
      //Create the ShellSort object using the data to be sorted
      ShellSort study1 = new ShellSort(dataToBeSorted);
      
      //Begin timing the sorting using each increment sequence array
      long start = 0L;
      long duration1 = 0L, duration2 = 0L, duration3 = 0L, duration4 = 0L;
      long comparison1 = 0L, comparison2 = 0L, comparison3 = 0L, comparison4 = 0L;
      
      int numberOfTrials = Integer.parseInt(args[1]);
      int j = 0;
      while(j < numberOfTrials){
         //Time using the h1 sequence
         study1.resetComparisons();
         start = System.nanoTime(); 
         study1.sortUsing(h1);
         duration1 += System.nanoTime() - start;
         comparison1 += study1.getComparisons();
         
         //Time using the h2 sequence
         study1.resetComparisons();
         start = System.nanoTime(); 
         study1.sortUsing(h2);
         duration2 += System.nanoTime() - start;
         comparison2 += study1.getComparisons();
   
         //Time using the h3 sequence
         study1.resetComparisons();
         start = System.nanoTime(); 
         study1.sortUsing(h3);
         duration3 += System.nanoTime() - start;
         comparison3 += study1.getComparisons();
         
         //Time using the h4 sequence
         study1.resetComparisons();
         start = System.nanoTime(); 
         study1.sortUsing(h4);
         duration4 += System.nanoTime() - start;
         comparison4 += study1.getComparisons();
         
         study1 = new ShellSort(dataToBeSorted);
         j++;
      }
      
      //Print timing results
      printResults(numberOfTrials, duration1, duration2, duration3, duration4,comparison1, 
                     comparison2, comparison3, comparison4, h1, h2, h3, h4);
   }
   
/*
 * Function: printResults
 * -----------------------
 *  Takes in all results of the ShellSort comparisons, formats them, and displays them.
 *
 *  numberOfTrials: Total number of trials.
 *  dur1: Total duration of ShellSort sorts using the first ShellSort increment sequence.
 *  dur2: Total duration of ShellSort sorts using the second ShellSort increment sequence.
 *  dur3: Total duration of ShellSort sorts using the third ShellSort increment sequence.
 *  dur4: Total duration of ShellSort sorts using the fourth ShellSort increment sequence.
 *  comp1: Number of total comparisons using the first ShellSort increment sequence.
 *  comp2: Number of total comparisons using the second ShellSort increment sequence.
 *  comp3: Number of total comparisons using the third ShellSort increment sequence.
 *  comp4: Number of total comparisons using the fourth ShellSort increment sequence.
 *  h1: The first ShellSort increment sequence.
 *  h2: The second ShellSort increment sequence.
 *  h3: The third ShellSort increment sequence.
 *  h4: The fourth ShellSort increment sequence.
 *
 *  returns: nothing.
 */     
   public static void printResults(int numberOfTrials, long dur1, long dur2, long dur3, long dur4,
                                     long comp1, long comp2, long comp3, long comp4, int[] h1,
                                     int[] h2, int[] h3, int[] h4){
      
      System.out.println("The number of trials is " + numberOfTrials);
      
      System.out.println("seq 1:");
      printArray(h1);
      System.out.println("");
      
      System.out.println("seq 2:");
      printArray(h2);
      System.out.println("");
      
      System.out.println("seq 3:");
      printArray(h3);
      System.out.println("");
      
      System.out.println("seq 4:");
      printArray(h4);
      System.out.println("");
      
      //Create the average number of comparisons
      long avgC1 = comp1/numberOfTrials;
      long avgC2 = comp2/numberOfTrials;
      long avgC3 = comp3/numberOfTrials;
      long avgC4 = comp4/numberOfTrials;
      
      //Create the averate sorting times
      double avgT1 = (double)dur1/(numberOfTrials*1000000);
      double avgT2 = (double)dur2/(numberOfTrials*1000000);
      double avgT3 = (double)dur3/(numberOfTrials*1000000);
      double avgT4 = (double)dur4/(numberOfTrials*1000000);
      
      System.out.println("Comparisons:\n");
      
      System.out.println("Average comparisons for Sequence 1 is " + avgC1);
      System.out.println("Average comparisons for Sequence 2 is " + avgC2);
      System.out.println("Average comparisons for Sequence 3 is " + avgC3);
      System.out.println("Average comparisons for Sequence 4 is " + avgC4);
      System.out.println("--------------------------------------");
      
      System.out.println("Average Time:\n");
      System.out.println("Average Time using Sequence h[i] = 2^i for " + numberOfTrials + 
                           " trials is\t\t\t\t\t" + avgT1 + " ms");
      System.out.println("Average Time using Sequence h[i] = 4^i - 3*2^i + 1 for " + numberOfTrials + 
                           " trials is\t" + avgT2 + " ms");
      System.out.println("Average Time using Sequence h[i] = 2^p*3^q for " + numberOfTrials + 
                           " trials is\t\t\t\t" + avgT3 + " ms");
      System.out.println("Average Time using Sequence h[i] = 2^i - 1 for " + numberOfTrials + 
                           " trials is\t\t\t\t" + avgT4 + " ms");
      System.out.println("--------------------------------------");
      
      System.out.println("Ratios:\n");
      System.out.printf("T1/T2 =\t%.3f ms\n", avgT1/avgT2);
      System.out.printf("T1/T3 =\t%.3f ms\n", avgT1/avgT3);
      System.out.printf("T1/T4 =\t%.3f ms\n", avgT1/avgT4);
      System.out.printf("T2/T3 =\t%.3f ms\n", avgT2/avgT3);
      System.out.printf("T2/T4 =\t%.3f ms\n", avgT2/avgT4);
      System.out.printf("T3/T4 =\t%.3f ms\n", avgT3/avgT4);
   }
   
/*
 * Function: createH1
 * -----------------------
 *  Takes in an int and creates an array containing the sequence, 2^i, up to
 *  half of the taken int.
 *
 *  length: The length of the array to be sorted
 *
 *  returns: An pointer pointing to an array of ints.
 */  
   public static int[] createH1(int length){
      Stack<Integer> tempStack = new Stack<Integer>();
      int x = 1;
      
      //Pushes all values of the sequence into Stack
      while(x < length/2){
         tempStack.push(x);
         x = x * 2;
      }
      
      //Pops all values of the Stack into an array
      int[] returnArray = new int[tempStack.size()];
      int size = tempStack.size();
      for(int i=0; i<size; i++){
         returnArray[i] = tempStack.pop();
      }
      
      return returnArray;
   }
   
/*
 * Function: createH2
 * -----------------------
 *  Takes in an int and creates an array containing the sequence, 4^i - 3*2^i + 1,
 *  up to half of the taken int.
 *
 *  length: The length of the array to be sorted
 *
 *  returns: An pointer pointing to an array of ints.
 */  
   public static int[] createH2(int length){
      Stack<Integer> tempStack = new Stack<Integer>();
      int x = 1, i = 0;
      
      //Pushes all values of the sequence into Stack
      while(x < length/2){
         i++;
         tempStack.push(x);
         x = (int)Math.pow(4,i) + (int)(3*Math.pow(2,i-1)) + 1;
      }
      
      //Pops all values of the Stack into an array
      int[] returnArray = new int[tempStack.size()];
      int size = tempStack.size();
      for(i=0; i<size; i++){
         returnArray[i] = tempStack.pop();
      }
      
      return returnArray;
   }
   
/*
 * Function: createH3
 * -----------------------
 *  Takes in an int and creates an array containing the sequence, 2^p*3^q,
 *  up to half of the taken int.
 *
 *  length: The length of the array to be sorted
 *
 *  returns: An pointer pointing to an array of ints.
 */ 
   public static int[] createH3(int length){
      Stack<Integer> tempStack = new Stack<Integer>();
      int i = 1;
      
      tempStack.push(1);
      
      //Pushes all values of the sequence into Stack
      do{
         //push all pow(2,i)*pow(3,j--)
         for(int j=i; j>=0; j--){
            if(Math.pow(2,i)*Math.pow(3,j) < length/2)
               tempStack.push((int)Math.pow(2,i)*(int)Math.pow(3,j));
         }
         for(int j=i-1; j>=0; j--){
            if(Math.pow(2,j)*Math.pow(3,i) < length/2)
               tempStack.push((int)Math.pow(2,j)*(int)Math.pow(3,i));
         }
         i++;
      }
      while((Math.pow(2,i)) < length/2);
      
      //Pops all values of the Stack into an array
      int[] returnArray = new int[tempStack.size()];
      int size = tempStack.size();
      for(i=0; i<size; i++){
         returnArray[i] = tempStack.pop();
      }
      
      Arrays.sort(returnArray);
      reverseArray(returnArray);
      
      return returnArray;
   }
   
/*
 * Function: createH4
 * -----------------------
 *  Takes in an int and creates an array containing the sequence, 2^i - 1,
 *  up to half of the taken int.
 *
 *  length: The length of the array to be sorted
 *
 *  returns: An pointer pointing to an array of ints.
 */ 
   public static int[] createH4(int length){
      Stack<Integer> tempStack = new Stack<Integer>();
      int x = 1, i = 1;
      
      //Pushes all values of the sequence into Stack
      while(x < length/2){
         i++;
         tempStack.push(x);
         x = (int)Math.pow(2,i) - 1;
      }
      
      //Pops all values of the Stack into an array
      int[] returnArray = new int[tempStack.size()];
      int size = tempStack.size();
      for(i=0; i<size; i++){
         returnArray[i] = tempStack.pop();
      }
      
      return returnArray;
   }
   
/*
 * Function: printArray
 * -----------------------
 *  Takes in a pointer to an array of ints and prints the whole array.
 *
 *  arr: A pointer to an array of ints.
 *
 *  returns: Nothing.
 */
   public static void printArray(int[] arr){
      for(int i=0; i<arr.length; i++){
         System.out.print(arr[i] + " ");
      }
      System.out.print("\n");
   }

/*
 * Function: reverseArray
 * -----------------------
 *  Takes in a pointer to an array of ints and reverses the whole array.
 *
 *  arr: A pointer to an array of ints.
 *
 *  returns: Nothing.
 */ 
   public static void reverseArray(int[] arr){
      for(int i = 0; i < arr.length / 2; i++){
         int temp = arr[i];
         arr[i] = arr[arr.length - i - 1];
         arr[arr.length - i - 1] = temp;
      }
   }

   
/*
 * Function: readInput
 * -----------------------
 *  Reads each line of an input file and pushes each string into a Stack.
 *  The final stack is then returned
 *
 *  inputFileName: The name of the file to be made into a Stack.
 *
 *  returns: A Stack of Strings.
 */  
   public static String[] readInput(String inputFilename){
      Queue<String> tempQueue = new LinkedList<>();
      try{
        
            BufferedReader in = new BufferedReader(new FileReader(inputFilename));
            String str = "";
            while( (str = in.readLine()) != null)
               {tempQueue.add(str);}
            in.close();
		} 
      catch(IOException e) {   //catches an error in reading a file
            System.out.println(e.getMessage());
      }

      String[] returnArray = new String[tempQueue.size()];
      int size = tempQueue.size();
      for(int i=0; i<size; i++){
         returnArray[i] = tempQueue.poll();
      } 
        
      return returnArray;
   }
   
   public static void uselessLoop(){
      for(int i=0; i< 1000; i++){
      }
   }
}