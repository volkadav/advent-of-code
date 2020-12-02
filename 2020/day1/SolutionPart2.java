import java.io.*;
import java.util.*;
import java.nio.file.Files;

class SolutionPart2 {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("need input file argument");
      System.exit(1);
    }
    
    List<Integer> data = new ArrayList<>();
    try {
      Scanner in = new Scanner(new File(args[0]));
      while (in.hasNextInt()) {
        data.add(in.nextInt());
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }

    Collections.sort(data);
    int data_sz = data.size();

    for (int i = 0; i < data_sz - 2; i++) {
      int left = i + 1; // idx to element to immediate right of i-th element
      int right = data_sz - 1; // idx to element at end of array

      while (left < right) {
        int thisSum = data.get(i) + data.get(left) + data.get(right);
        if (thisSum == 2020) {
          System.out.println(data.get(i) * data.get(left) * data.get(right));
          System.exit(0);
        } else if (thisSum < 2020) {
          left++;
        } else { // thisSum > 2020
          right--;
        }
      }
    }

    System.out.println("no triplet summing to 2020");
  }
}
