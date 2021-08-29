package com.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MouseFeeder {

  public static void main(String[] args) {
    Boolean continueExecution = Boolean.TRUE;
    List<Integer> result = new ArrayList<>();
    while (continueExecution) {
      Scanner sc = new Scanner(System.in);
      Integer noOfTestCase = null;
      String input = sc.nextLine();
      //Validate input
      Boolean isValidInput = validateInput(input, 1, 200);
      if (!isValidInput) {
        continue;
      }
      noOfTestCase = Integer.parseInt(input);

      for (int i = 0; i < noOfTestCase; i++) {
        Integer noOfArrayelements = null;
        input = sc.next();
        Boolean isValid = validateInput(input, 1, 1000);
        if (!isValid) {
          i -= 1;
          continue;
        }
        noOfArrayelements = Integer.parseInt(input);

        ArrayList<Integer> inputList = new ArrayList<>();
        // Get input of array elements
        for (int j = 0; j < noOfArrayelements; j++) {
          int value;
          try {
            value = sc.nextInt();
          } catch (NoSuchElementException nsee) {
            System.out.println("Please enter valid integer between 1 and 10000.");
            j -= 1;
            continue;
          }
          if (value < 1||value > 10000) {
            System.out.println("Please enter valid integer between 1 and 10000.");
            j -= 1;
            continue;
          }
          inputList.add(value);
        }
        int maxSum = findMaxSum(inputList);
        result.add(maxSum);
      }
      continueExecution = Boolean.FALSE;
    }
    result.stream().forEach(r->{
      System.out.println(r);
    });
  }

  /**
   * Validates the input.
   * @param input
   * @param lowerLimit
   * @param upperLimit
   * @return true if valid otherwise false
   */
  public static Boolean validateInput(String input, Integer lowerLimit, Integer upperLimit) {
    Integer inputInteger = null;
    try {
      if (input.equalsIgnoreCase("exit")) {
        System.exit(0);
      }
      inputInteger = Integer.parseInt(input);
    } catch (NumberFormatException nfe) {
      System.out.println(
          "Please enter valid integer between " + lowerLimit + " and " + upperLimit + " or type exit to Exit.");
      return Boolean.FALSE;
    }
    if (inputInteger < lowerLimit||inputInteger > upperLimit) {
      System.out.println("Please enter integer between " + lowerLimit + " and " + upperLimit);
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }

  /**
   * Finds maximum sum of array elements excluding 2 consecutive elements
   * @param inputList
   * @return sum of elements
   */
  private static int findMaxSum(List<Integer> inputList) {
    int includeElement = inputList.get(0);
    int excludeElement = 0;
    int newExclude;

    for (int i = 1; i < inputList.size(); i++) {
      newExclude = (includeElement > excludeElement) ? includeElement : excludeElement;

      includeElement = excludeElement + inputList.get(i);
      excludeElement = newExclude;
    }

    return ((includeElement > excludeElement) ? includeElement : excludeElement);
  }

}
