package com.xogrp.john.toollibrary.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;

import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class ZiqTextUtils {

    public static SpannableStringBuilder getImageViewText(Context context, String text, int size, int drawableId, String splitString){
        SpannableStringBuilder build = new SpannableStringBuilder(String.format(text, size));
        int start = text.indexOf(splitString);
        int end = start + splitString.length();
        Drawable drawable = context.getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
        build.setSpan(imageSpan, start-2, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return build;
    }


    private static final Pattern PATTERN_VALID_EMAIL_ADDRESS = Pattern.compile("^[a-zA-Z0-9+][+\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");


    public static boolean isValidEmail(String email) {
        return PATTERN_VALID_EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isTextEmptyOrNull(String text) {
        return text == null || text.isEmpty() || "null".equalsIgnoreCase(text)||"(null)".equals(text);
    }

    public static boolean isEmpty(String text) {
        return text.isEmpty()||text.equals("");
    }


    public static boolean isEmptyJsonField(String value){
        return isTextEmptyOrNull(value) || value.equals("[]") || value.equals("{}");
    }

    public static String getDefaultValueIfNeed(String value) {
        return isTextEmptyOrNull(value) ? "" : value;
    }


    public static String[] splitStr(String str, String splitStr,
                                    boolean isNullSign) {
        String[] arrayStr = new String[2];
        if (isTextEmptyOrNull(str)) {
            arrayStr[0] = "";
            arrayStr[1] = "";
        } else {
            StringTokenizer st = new StringTokenizer(str, splitStr);
            arrayStr[0] = st.nextToken();
            if (isNullSign) {
                arrayStr[1] = str.substring(arrayStr[0].length(), str.length());
            } else {
                arrayStr[1] = str.substring(arrayStr[0].length() + 1,
                        str.length());
            }

        }
        return arrayStr;
    }

    // add you need

    public static int findCharFromString(char c, String text){
        int count = 0;
        if(text != null) {
            char[] charArray = text.toCharArray();
            for (char ch : charArray) {
                if (ch == c) {
                    count ++;
                }
            }
        }
        return count;
    }

    public static String removeAllSpace(String str){
        return str.replaceAll(" ","");
    }

    public static String cutFirstCharAndUpperCase(String str){
        if(str != null && str.length() > 0){
            return String.valueOf(str.charAt(0)).toUpperCase();
        }
        return str;
    }

    public static String upperCaseFirstChar(String str){
        int len = str == null? 0 : str.length();
        if(len > 0){
            String firstChar = String.valueOf(str.charAt(0)).toUpperCase();
            if(len > 1){
                firstChar = firstChar + str.substring(1, len);
            }
            return firstChar;
        }
        return str;
    }
}
