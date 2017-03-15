/* Project - COMP 3270
*@author Daniel Brackin (dlb0031)
*@version 10 Nov 2016
*/

import java.util.Random;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Project {
   static Random rand = new Random();
   static long calTime;
   static int low = 0, high = 0;
   static int leftsum = 0, rightsum = 0, cross = 0;
   static int[] inputArray;
   static boolean upload;
   static int size = 0;
   static long startTime = 0, endTime = 0, totalTime = 0;


   public static void main(String args[]) throws IOException {
   
   // Declaration of files and PrintWriter
      File inputFile = new File("input.txt");
      File outputFile = new File("output.txt");
      File timeFile = new File("time.txt");
   
      PrintWriter out = new PrintWriter(outputFile);
      PrintWriter time = new PrintWriter(timeFile);
   
      String output;
      
      //int size = 0;
      // int[] inputArray;
   
       // Testing cases: 
      /*
      int min_1 = 0, start_1 = 0, end_1 = 0;
      int[] testArray = {1, -2, 3, 7, -9, 5, 6, 7, -2, 0, 5, 2, -8, 1, -9, 0, -8, 19};
      int[] testArray3 = {1, -2, 3, 7, -9, 5};
   
      double[] test2Array = {-2.2, -1.3, -5.6, -3.5, -7.4};
      int newsize = 18;
      int newsize2 = 6;
      
      output = algorithm1(min_1, start_1, end_1, testArray, newsize,calTime); // Algorithm1 will replace min, start, end
      System.out.println(output);
      output = algorithm2(min_1, start_1, end_1, testArray, newsize, calTime); // Algorithm1 will replace min, start, end
      System.out.println(output);
      output = algorithm3(min_1, start_1, end_1, testArray, newsize, calTime); // Algorithm2 will replace min, start, end
      System.out.println(output);
      */
   
      Scanner read = new Scanner(System.in);
      String answer = "";
      int min = 0, start = 0, end = 0;
      int select, choice;
      String selection;
      String file = "";
      int newTime = 0;
      //inputArray = new int[0];
   
      
      // PART 1: Creating output file with min, left index, right index
      System.out.println("Project 2 has started");
      System.out.println("Part 1: Testing successful creation of output file.");
      
      System.out.print("Would you like to open your own input.txt file? (0 - No, 1 - Yes): ");
      choice = read.nextInt();
        
      switch(choice) {
      
         case 0: 
            System.out.print("Enter size of input series: ");
            size = read.nextInt();
          
         // Create random input.txt with size and store values in array - NEEDED for ALL algorithms
            generateRandomFile(size, inputFile);
            System.out.println("Finished generating input file of size and random ints.");
         
            scanFileAddArray(inputFile, inputArray, size);
            System.out.println("Finished scanning the input file and adding to array.");
            break;
         
         case 1:        
            System.out.print("Enter the file name of the .txt file?: ");
            selection = read.next();
            File uploadFile = new File(selection);
            scanFileAddArray(uploadFile, inputArray, size);
            System.out.println("Finished scanning the uploaded input file and adding to array.");
            break;
            
         default: 
            
      }
      
         
      // Algorithm 1
      output = algorithm1(min, start, end, inputArray, size, newTime); // Algorithm1 will replace min, start, end
      out.println(output);    // Generate line for output.txt
      System.out.println("Output added to output.txt for Algorithm 1.");
      
      // Algorithm 2
      output = algorithm2(min, start, end, inputArray, size, newTime); // Algorithm2 will replace min, start, end
      out.println(output);    // Generate line for output.txt
      System.out.println("Output added to output.txt for Algorithm 2.");
      
      // Algorithm 3
      output = algorithm3(min, start, end, inputArray, size, newTime); // Algorithm3 will replace min, start, end
      out.println(output);    // Generate line for output.txt
      System.out.println("Output added to output.txt for Algorithm 3.");
      out.close();   
      System.out.println("File: output.txt is ready.");
      
      
      // PART 2: Testing Array of Sizes
      
      System.out.println("Part 2: Executing algorithms for different array sizes.");
      System.out.print("Start at 100 or 5000?: ");
      select = read.nextInt();
      
      // User picks which arrays to test. 
      int[] arraySizes;
      
      if (select == 100) {
         arraySizes = new int[] {100, 500, 1000, 1500, 2000, 
               2500, 3000, 3500, 4000, 4500, 
               5000, 5500, 6000, 6500, 7000, 
               7500, 8000, 8500, 9000, 9500, 
               10000, 15000, 20000};
      }
      else { // Added  15000, 20000, 25000, 50000, 100000 
                        // 200000, 300000, 500000 for testing purposes.
         arraySizes = new int[] {5000, 5500, 6000, 6500, 7000, 
               7500, 8000, 8500, 9000, 9500, 
               10000, 15000, 20000};
      }
      
      // Declaration of variables for Part 2: 
      String finalAverage = "";
      int min1 = 0, start1 = 0, end1 = 0;
      long currentTime = 0;
      long startTime = 0, endTime = 0;
      long average = 0;
      long avg2 , avg3;
         
      // Runs each algorithm on each size given.    
         
      for (int s: arraySizes) {
         // Create random input.txt with size -> (s) and store values in array - NEEDED for ALL algorithms
         //int[] newArray = new int[s];
         generateRandomFile(s, inputFile);
         scanFileAddArray(inputFile, inputArray, s);
         
         // Algorithm 1
         System.out.println("Algorithm 1 generated for: " + s);
         startTime = System.nanoTime();
         algorithm1(min1, start1, end1, inputArray, s, calTime); // Algorithm1 will replace min, start, end;
         endTime = System.nanoTime();
         totalTime = endTime - startTime;
         calTime = totalTime / s;
      
         time.print(calTime + ",");
          // Generate line for time.txt
          
       
      }
      
   // Reset times
      startTime = 0;
      endTime = 0;
      time.println(""); 
      
      for (int s: arraySizes) {
         // Create random input.txt with size (s) and store values in array - NEEDED for ALL algorithms
         //int[] newArray = new int[s];
         generateRandomFile(s, inputFile);
         scanFileAddArray(inputFile, inputArray, s);
         
          // Algorithm 2
         System.out.println("Algorithm 2 generated for: " + s);
         startTime = System.nanoTime();
         algorithm2(min1, start1, end1, inputArray, s, calTime); // Algorithm2 will replace min, start, end
         endTime = System.nanoTime();
         totalTime = endTime - startTime;
         calTime = totalTime / s;
      
         time.print(calTime + ",");
      }
      // Reset times
      startTime = 0;
      endTime = 0;
      time.println("");           // Print new line
      
      for (int s: arraySizes) {
         // Create random input.txt with size (s) and store values in array - NEEDED for ALL algorithms
         //int[] newArray = new int[s];
         generateRandomFile(s, inputFile);
         scanFileAddArray(inputFile, inputArray, s);
         
          // Algorithm 3
         System.out.println("Algorithm 3 generated for: " + s);
         startTime = System.nanoTime();
         algorithm3(min1, start1, end1, inputArray, s, calTime); // Algorithm3 will replace min, start, end
         endTime = System.nanoTime();
         totalTime = endTime - startTime;
         calTime = totalTime / s;
      
         time.print(calTime + ",");
      }
               
      System.out.println("File: time.txt is ready.");
      time.close();
   }
         
   
   
 /* Kadane-based implementation: Dynamic programming
 *  Complexity: O(n)
 *
 *  Sources used: https://kartikkukreja.wordpress.com/2013/06/17/kadanes-algorithm/
 *                https://cyberzhg.gitbooks.io/clrs/content/Chapter_04_Divide_and_Conquer/exercises_4.1.html  
 */        

   public static String algorithm1(int minimum, int startValue , int endValue, int[] array, int array_size, long time) {
      String result = "";
      int leftside = 0, rightside = 0, cmin = 0;
      int leftmin = 0, rightmin = 0;
      
      // Checking for only positives
      int value_found = checkForNegatives(array);
      if (array[value_found] >= 0) {
         startValue = endValue = value_found;
         result = minimum + "," + startValue + "," + endValue; 
      }
      // Assuming a negative value exists
      else {
         array_size = array.length;
         for (int i = 0; i < array_size - 1; i++) {
            cmin += array[i];
            
            if (cmin < minimum) {
               minimum = cmin;
               rightside = i; 
               leftmin = leftside;
               rightmin = rightside;
            }
            
            if (cmin > 0) {
               cmin = 0;
               leftside = i + 1;
               rightside = i + 1;
            }
         }
         // Values from min, leftmin, and rightmin
         startValue = leftmin;
         endValue = rightmin;   
      }
      result = minimum + "," + startValue + "," + endValue; 
      return result;
   }



/* Brute-Force based implementation
 *  Complexity: O(n^2)
 *
 *  Sources used: https://cyberzhg.gitbooks.io/clrs/content/Chapter_04_Divide_and_Conquer/exercises_4.1.html
 *                https://atekihcan.github.io/CLRS/E04.01-02/
 *                https://copingwithcomputers.com/2014/05/17/maximum-subarray-problem/
 */    
   
   public static String algorithm2(int minimum, int startValue , int endValue, int[] array, int array_size, long time) {
      int leftside = 0, rightside = 0, minsum = 0, currentsum = 0;
      
      String result = "";        
      // Checking for only positives
      int value_found = checkForNegatives(array);
      if (array[value_found] >= 0) {
         startValue = endValue = value_found;
         result = minimum + "," + startValue + "," + endValue; 
      }
      // Assuming a negative value exists
      else {
         array_size = array.length;
         minsum = 0;
         for (int i = 0; i < array.length; i++) {
            currentsum = 0;
            for (int j = i; j < array.length; j++) {
               currentsum += array[j];
               if (currentsum < minsum) {
                  minsum = currentsum;
                  leftside = i;
                  rightside = j;
               }
            }
         }
         startValue = leftside; 
         endValue = rightside;
      }
   
      result = minsum + "," + startValue + "," + endValue; 
      return result;
   }

 /* Cubic-based implementation
 *  Complexity: O(n^3)
 *
 *  Sources used: http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 *                http://stackoverflow.com/questions/7778186/how-to-return-maximum-sub-array-in-kadanes-algorithm
 *                http://cs.slu.edu/~goldwamh/courses/slu/csci314/2012_Fall/lectures/maxsubarray/
 */ 

   public static String algorithm3(int minimum, int startValue , int endValue, int[] array, int array_size,  long time) {
      startTime = System.nanoTime();
      String result = "";        
      int min;
      int mySum;
   // Checking for only positives
      int value_found = checkForNegatives(array);
      if (array[value_found] >= 0) {
         startValue = endValue = value_found;
         result = minimum + "," + startValue + "," + endValue; 
      }
      else {
         min = 0;
         array_size = array.length;
         for(int a = 0; a < array_size; a++) {
            for(int b = a; b < array_size; b++) {
               mySum = 0;
               for(int c = a; c <= b; c++) {
                  mySum += array[c];
                  if (mySum < min) {
                     min = mySum;
                     startValue = a;
                     endValue = b;
                  }
               }
            }
         }
      
         result = min + "," + startValue + "," + endValue; 
      }
      return result;
   
   }

 /* Method to find the min between two numbers
   *
   */
   static int min(int one, int two) {
      int myMin =  Math.min(one, two);
      return myMin;
   }
   /* Method to generate random numbers for input.txt.
   *
   */
   public static void generateRandomFile(int times, File iFile) throws IOException {
      // Initialzies a random counter
      PrintWriter out = new PrintWriter(iFile);
      int randomNumber = 0;
      // Print size of negative/positive/zeroes 
      out.println(times);
   
      for(int i = 1; i <= times; i++) {
         // Random numbers between -1000 to 1000
         // int newValue = lowerbound  + rando.nextInt(upperbound - lowerbound);
         randomNumber = rand.nextInt(2001) + (-1000);
         // If last number, do not add comma!
         if (i == times) {
            out.print(randomNumber);
         }
         // Otherwise, add comma...
         else {
            out.print(randomNumber);
            out.print(",");
         }
      }
      out.close();
   }
  /* Method to scan numbers from input.txt and assign to array. 
   *
   */
   public static void scanFileAddArray(File file, int[] array, int array_size) throws IOException {
     // Scan values in from file
      Scanner scan = new Scanner(file);
      scan.useDelimiter(",|\\s+");
      
      String stringValue;
      int intValue;
      
      int count = -1;
      array_size = scan.nextInt();
      array = new int [array_size];
      scan.nextLine();
      // While scanning, parse string values into type int, convert the final
      while (scan.hasNext()) {
         count++;
         stringValue = scan.next();
         intValue = Integer.parseInt(stringValue);
         array[count] = intValue;
      }
      // size = array_size;
      
      // Copy values to array for 
      //if (upload == true) {
      inputArray = new int[array_size]; 
      
      int counter = -1;
      for (int val: array) {
         counter++;
         inputArray[counter] = val;
      }
      
      //}
      
      scan.close();
   }
  /* Method to look for negatives from a given array.
   *
   */
   public static int checkForNegatives(int[] checkArray) {
      int number = checkArray[0];
      int min = 0; 
      for (int j = 1; j < checkArray.length; j++) {         
         if (checkArray[j] < number) {
            number = checkArray[j];
            min = j;
         }
      }
      return min;
   }
}