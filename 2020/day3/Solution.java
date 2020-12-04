import java.io.*;
import java.util.*;

class Solution {
  public static int trees(List<String> map, int rightwardSlope, int downwardStep) {
    int trees = 0;

    for (int i = 0; i < map.size(); i += downwardStep) { // i is line in map
      String mapLine = map.get(i);
      char sym = mapLine.charAt(((i * rightwardSlope)/downwardStep) % mapLine.length());

      if (sym == '#') {
        trees++;
      }
    }

    return trees;
  }

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

    List<String> map = new ArrayList<>();
    try {
      BufferedReader in = new BufferedReader(new FileReader(args[1]));
      String line = in.readLine();
      while (line != null) {
        map.add(line);
        line = in.readLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    int trees31 = trees(map, 3, 1);

    switch (step) {
      case '1':
        System.out.println(trees31);
        break;
      case '2':
        System.out.println((long)trees(map, 1, 1) *
                           (long)trees31 *
                           (long)trees(map, 5, 1) *
                           (long)trees(map, 7, 1) *
                           (long)trees(map, 1, 2));
        break;
      default:
        System.err.println("undefined step");
        System.exit(1);
    }
  }
}
