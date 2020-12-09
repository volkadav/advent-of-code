import java.io.*;
import java.util.*;
import java.util.regex.*;

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

    Map<String, Map<String, Integer>> bagInfo = new HashMap<>();
    try {
      BufferedReader in = new BufferedReader(new FileReader(args[1]));
      String line = in.readLine();
      Pattern bagInfoPattern = Pattern.compile("^(\\d+) (\\w+ \\w+) bag.*");
      while (line != null) {
        System.out.println(line);
        String[] bagSubstrs = line.split(" bags contain ");
        String bagType = bagSubstrs[0];

        if (bagSubstrs[1].equals("no other bags.")) {
          bagInfo.put(bagType, new HashMap<String,Integer>()); // empty map
        } else {
          Map<String, Integer> innerBags = new HashMap<>(); // map of inner bag type to allowed count
          for (String innerBagInfo : bagSubstrs[1].split(",")) {
            System.out.println(innerBagInfo);
            Matcher m = bagInfoPattern.matcher(innerBagInfo);
            innerBags.put(m.group(2), Integer.parseInt(m.group(1)));
          }
          bagInfo.put(bagType, innerBags);
        }

        line = in.readLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    System.out.println(bagInfo.keySet());

    switch (step) {
      case '1':
        break;
      case '2':
        break;
    }
  }
}
