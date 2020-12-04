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

    List<Map<String,String>> passportBatch = new ArrayList<>();
    try {
      Map<String,String> entry = new HashMap<>();
      BufferedReader in = new BufferedReader(new FileReader(args[1]));
      String line = in.readLine();
      while (line != null) {
        if (line.matches("^\\s*$")) { // record terminator
          passportBatch.add(new HashMap<>(entry));
          entry.clear();
        } else {
          for (String field : line.split("\\s+")) {
            String[] components = field.split(":"); // k:v
            entry.put(components[0], components[1]);
          }
        }
        line = in.readLine();
      }
      // handle terminal record at EOF
      if (!entry.isEmpty()) {
        passportBatch.add(new HashMap<>(entry));
      }
      entry = null;
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    int validPassports = 0;
    List<String> requiredFields = Arrays.asList("byr","iyr","eyr","hgt","hcl","ecl","pid");
    for (Map<String,String> passport : passportBatch) {
      Set<String> keys = passport.keySet();
      switch (step) {
        case '1':
          if (keys.size() >= 7 && keys.containsAll(requiredFields)) { 
            validPassports++; 
          }
          break;
        case '2':
          if (keys.size() >= 7 && keys.containsAll(requiredFields)) {
            try {
              int byr = Integer.parseInt(passport.get("byr"));
              if (byr < 1920 || byr > 2002) {
                continue; // invalid byr
              }
              
              int iyr = Integer.parseInt(passport.get("iyr"));
              if (iyr < 2010 || iyr > 2020) {
                continue; // invalid iyr
              }
              
              int eyr = Integer.parseInt(passport.get("eyr"));
              if (eyr < 2020 || eyr > 2030) {
                continue; // invalid eyr
              }

              String height = passport.get("hgt");
              int h = -1; // height as int
              if (height.contains("in")) {
                h = Integer.parseInt(height.replaceAll("in",""));
                if (h < 59 || h > 76) {
                  continue; // invalid hgt
                }
              } else if (height.contains("cm")) {
                h = Integer.parseInt(height.replaceAll("cm",""));
                if (h < 150 || h > 193) {
                  continue; // invalid hgt
                }
              } else {
                continue; // invalid hgt, no units
              }

              if (!passport.get("hcl").matches("#[0-9a-f]{6}")) {
                continue; // invalid hcl
              }

              String ecl = passport.get("ecl");
              if (!(ecl.equals("amb") ||
                    ecl.equals("blu") ||
                    ecl.equals("brn") ||
                    ecl.equals("gry") ||
                    ecl.equals("grn") ||
                    ecl.equals("hzl") ||
                    ecl.equals("oth"))) {
                continue; // invalid ecl
              }
              
              String pid = passport.get("pid");
              if (!pid.matches("\\d{9}")) {
                continue; // invalid pid
              }
            } catch (NumberFormatException e) {
              e.printStackTrace();
              continue;
            }
            
            // if we've gotten this far, no invalid data detected
            validPassports++;
          }
          break;
      }
    }
    System.out.println(validPassports);
  }
}
