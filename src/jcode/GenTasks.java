package jcode;

import com.jfoenix.controls.JFXButton;

public class GenTasks {

    public GenTasks() {
    }

    public static int returnCategoryNumber(String category) {
        if (GenTasks.containsIgnoreCase(category,"p")) {
            return 1;
        } else if (GenTasks.containsIgnoreCase(category,"chemistry")) {
            return 2;
        } else if (GenTasks.containsIgnoreCase(category,"biology")) {
            return 3;
        } else if (GenTasks.containsIgnoreCase(category,"M")) {
            return 4;
        } else if (GenTasks.containsIgnoreCase(category,"General")) {
            return 5;
        } else if (GenTasks.containsIgnoreCase(category,"Inventor")) {
            return 6;
        } else if (GenTasks.containsIgnoreCase(category,"invention")) {
            return 7;
        }

        return 0;
    }

    public static String returnCategoryNameScoreboard(int cat) {
        if (cat == 1) {
            return "PHY";
        } else if (cat == 2) {
            return "CHEM";
        } else if (cat == 3) {
            return "BIO";
        } else if (cat == 4) {
            return "MATH";
        } else if (cat == 5) {
            return "GENKNOW";
        } else if (cat == 6) {
            return "INVENTOR";
        } else if (cat == 7) {
            return "INVENTION";
        }

        return "";
    }

    public static boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }

}
