package samples.linhtruong.com.utils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/7/17 - 11:02.
 * @organization VED
 */

public class RegexUtils {

    public static String regexNumber(String str) {
        return str.replaceAll("\\d", "");
    }

    public static String regexText(String str) {
//        return str.replaceAll("\\D", "");
//        return str.replaceAll("[0..9]", "");
        return str.replaceAll("[^0-9]?", "");
    }

    public static String regexLetter(String str) {
//        return str.replaceAll("[a-b0-1]", "");
        return str.replaceAll("[^a^9]", "");
    }

    public static String regexWord(String str) {
        return str.replaceAll("\\w", "");
    }

    public static String regexNonword(String str) {
        return str.replaceAll("\\W", "");
    }

    public static void regexCombine1(String str) {
        System.out.println(str.matches("\\w.*"));
        String[] splitString = str.split("\\s+");
        for (String s : splitString) {
            System.out.println(s);
        }

        System.out.println(str.replaceAll("\\s+", "\t"));
    }

    // returns true if the string matches exactly "true"
    public static boolean isTrue(String s) {
        return s.matches("true");
    }

    // returns true if the string matches exactly "true" or "True"
    public static boolean isTrueVersion2(String s) {
        return s.matches("[tT]rue");
    }

    // returns true if the string matches exactly "true" or "True"
    // or "yes" or "Yes"
    public static boolean isTrueOrYes(String s) {
        return s.matches("[tT]rue|[yY]es");
    }

    // returns true if the string contains exactly "true"
    public static boolean containsTrue(String s) {
        return s.matches(".*true.*");
    }


    // returns true if the string contains of three letters
    public static boolean isThreeLetters(String s) {
        return s.matches("[a-zA-Z]{3}");
        // simpler from for
//                return s.matches("[a-Z][a-Z][a-Z]");
    }


    // returns true if the string does not have a number at the beginning
    public static boolean isNoNumberAtBeginning(String s) {
        return s.matches("^[^\\d].*");
    }

    // returns true if the string contains a arbitrary number of characters except b
    public static boolean isIntersection(String s) {
        return s.matches("([\\w&&[^b]])*");
    }

    // returns true if the string contains a number less then 300
    public static boolean isLessThenThreeHundred(String s) {
//        return s.matches("[^0-9]*[12]?[0-9]{1,2}[^0-9]*");
        return s.matches("[^\\d]*[12]?[\\d]{1,2}[^\\d]*");
    }

}
