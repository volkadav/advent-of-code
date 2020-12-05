import java.io.*;
import java.util.*;

class Solution {
  public static int seatRow(String rowCode) {
    int row = 0;
    for (Character c : rowCode.toCharArray()) {
      switch (c) {
        case 'F':

          break;
        case 'B':

          break;
      }
    }
    return row;
  }

  public static int seatColumn(String colCode) {
    int minCol = 0, maxCol = 7;
    for (Character c : colCode.toCharArray()) {
      switch (c) {
        case 'R':
          
          break;
        case 'L':

          break;
      }
    }
    return col;
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

    switch (step) {
      case '1':
        int highestSeatID = -1;
        
        try {
          BufferedReader in = new BufferedReader(new FileReader(args[0]));
          String line = in.readLine();
          while (line != null) {
            int seatID = seatID(line);
            if (seatID > highestSeatID) {
              highestSeatID = seatID;
            }
            line = in.readLine();
          }
        } catch (Exception e) {
          e.printStackTrace();
          System.exit(1);
        }

        System.out.println(highestSeatID);
        break;
      case '2':
        break;
    }
  }
}
