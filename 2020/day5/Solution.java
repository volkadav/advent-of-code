import java.io.*;
import java.util.*;

class Solution {
  public static int seatRow(String rowCode) {
    rowCode = rowCode.replaceAll("F","0");
    rowCode = rowCode.replaceAll("B","1");

    return Integer.parseInt(rowCode, 2);
  }

  public static int seatCol(String colCode) {
    colCode = colCode.replaceAll("L","0");
    colCode = colCode.replaceAll("R","1");

    return Integer.parseInt(colCode, 2);
  }
  
  public static int seatID(String seatCode) {
    if (!seatCode.matches("[FB]{7}[LR]{3}")) {
      return -1;
    }

    return (seatRow(seatCode.substring(0,7)) * 8) + 
            seatCol(seatCode.substring(7,10));
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
    if (!(step == '1' || step == '2')) {
        System.err.println("undefined step");
        System.exit(1);
    }

    try {
      BufferedReader in = new BufferedReader(new FileReader(args[1]));
      String line = in.readLine();

      switch (step) {
        case '1':
          int highestSeatID = -1;

          while (line != null) {
            int seatID = seatID(line);
            if (seatID > highestSeatID) {
              highestSeatID = seatID;
            }
            line = in.readLine();
          }

          System.out.println(highestSeatID);
          break;
        case '2':
          List<Integer> seatIDs = new ArrayList<>();
          while (line != null) {
            seatIDs.add(seatID(line));
            line = in.readLine();
          }
          Collections.sort(seatIDs);

          for (int i = 0; i < seatIDs.size() - 2; i++) {
            if ((seatIDs.get(i)) + 2 != seatIDs.get(i + 2)) { // found a gap in the middle
              System.out.println(seatIDs.get(i) + 1); // id of only empty seat
            }
          }

          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
