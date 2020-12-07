import java.io.*;
import java.util.*;

class Solution {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("need step switch [-1/-2] and input file path");
      System.exit(1);
    }

    if (args[0].length() != 2 && !args[0].matches("-\\d")) {
      System.err.println("step switch should be -1 or -2 indicating problem step one or two");
      System.exit(1);
    }
    char step = args[0].charAt(1);
    if (!(step == '1' || step == '2')) {
        System.err.println("undefined step");
        System.exit(1);
    }

    try {
      BufferedReader in = new BufferedReader(new FileReader(args[1]));
      String line = in.readLine();
      int sum = 0; // sum of the cardinalities of the answer sets
      Set<Character> answerSet = new HashSet<>();

      switch (step) {
        case '1':
          while (line != null) {
            if (line.matches("^\\s*$")) { // blank line = terminate group
              sum += answerSet.size();
              answerSet.clear();
            } else {
              for (Character c : line.toCharArray()) {
                answerSet.add(c);
              }
            }
            line = in.readLine();
          }
          // handle last blob of data at EOF
          sum += answerSet.size();
          answerSet = null;

          System.out.println(sum);
          break;
        case '2':
          boolean groupStarted = false;
          Set<Character> groupAnswers = new HashSet<>();

          while (line != null) {
            if (line.matches("^\\s*$")) {
              // finish group
              sum += groupAnswers.size();
              groupAnswers.clear();
              groupStarted = false;
            } else {
              // in a group
              if (!groupStarted) {
                // this is the start of the group
                groupStarted = true;
                for (Character c : line.toCharArray()) {
                  groupAnswers.add(c);
                }
              } else {
                // not at the start of a group
                Set<Character> newAnswers = new HashSet<>();
                for (Character c : line.toCharArray()) {
                  newAnswers.add(c);
                }
                groupAnswers.retainAll(newAnswers);
              }
            }
            line = in.readLine();
          }
          sum += groupAnswers.size();

          System.out.println(sum);
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
