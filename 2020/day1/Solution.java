import java.io.*;
import java.util.*;
import java.nio.file.Files;

class Solution {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("need input file argument");
      System.exit(1);
    }
    
    try {
      Scanner in = new Scanner(new File(args[0]));
      
      Set<Integer> lookFor = new HashSet<>();

      while (in.hasNextInt()) {
        int found = in.nextInt();
        if (lookFor.contains(found)) { // found our match
          System.out.println(found * (2020-found));
          System.exit(0);
        } else { // add the complement of what we found to lookFor
          lookFor.add(2020-found);
        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}
