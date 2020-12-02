import java.io.*;
import java.util.*;
import java.util.regex.*;

class Solution {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("need input file name");
      System.exit(1);
    }
  
    Pattern linePattern = Pattern.compile("(\\d+)-(\\d+)\\s+(\\w):\\s+(\\w+)");
    int validPasswords = 0;

    try {
      BufferedReader in = new BufferedReader(new FileReader(args[0]));

      String line = in.readLine();
      while (line != null) {
        Matcher m = linePattern.matcher(line);
        
        if (m.find()) {
          int min = Integer.parseInt(m.group(1));
          int max = Integer.parseInt(m.group(2));
          Character targetChar = m.group(3).charAt(0);
          String pwd = m.group(4);

          int found = 0;
          for (Character pwdChar : pwd.toCharArray()) {
            if (pwdChar.equals(targetChar)) {
              found++;
            }
          }

          if (found >= min && found <= max) {
            validPasswords++;
          }
        }

        line = in.readLine();
      }

      System.out.println(validPasswords);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
  }
}
