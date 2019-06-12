package com.safaricom.androidhackathon.utils;

public class Global {



    public static final String WEEK_FRAGMENT = "WEEK_FRAGMENT";
    public static final int WEEK_FRAG_INDEX =  92;
    public static final int NUMBER_OF_WEEKS = 52;

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
