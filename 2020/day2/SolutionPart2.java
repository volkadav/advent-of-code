import java.io.*;
import java.util.*;
import java.util.regex.*;

class SolutionPart2 {
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
          int posA = Integer.parseInt(m.group(1));
          int posB = Integer.parseInt(m.group(2));
          char targetChar = m.group(3).charAt(0);
          String pwd = m.group(4);

          boolean posAfound = pwd.charAt(posA - 1) == targetChar;
          boolean posBfound = pwd.charAt(posB - 1) == targetChar;

          if ((posAfound && !posBfound) || (posBfound && !posAfound)) {
            validPasswords++;
          }
        }

        line = in.readLine();
      }

      System.out.println(validPasswords);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
