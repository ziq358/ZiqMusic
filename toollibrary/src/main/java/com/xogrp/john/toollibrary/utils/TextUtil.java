package com.xogrp.john.toollibrary.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.widget.TextView;

import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class TextUtil {

    public static boolean isTextEmptyOrNull(String text) {
        return text == null || text.isEmpty() || text.equals("");
    }

    public static String getDefaultValueIfNeed(String value) {
        return isTextEmptyOrNull(value) ? "" : value;
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

    /**
     * 对于有些字体会把数字往下移动，设置这个属性使得文字，数字都在同一水平线上
     * */
    public static void enabledLiningNumbers(TextView textView){
        if (SDKVersionUtil.isGreaterThanOrEqualTo21()) {
            textView.setFontFeatureSettings("lnum");
        }
    }


    private static final Pattern PATTERN_VALID_EMAIL_ADDRESS = Pattern.compile("^[a-zA-Z0-9+][+\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
    public static boolean isValidEmail(String email) {
        return PATTERN_VALID_EMAIL_ADDRESS.matcher(email).matches();
    }



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

















}
