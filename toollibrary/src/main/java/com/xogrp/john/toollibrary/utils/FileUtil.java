package com.xogrp.john.toollibrary.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by john on 23/01/2017.
 */

public class FileUtil {

    private static final String TAKE_COUPLE_PHOTO_TEMP_PATH = "/take_couple_photo_temp.jpg";
    private static final String SELECT_COUPLE_PHOTO_TEMP_PATH = "/select_couple_photo_temp.jpg";

    private static final String COUPLE_PHOTO_TEMP_PATH = "/couple_photo_temp.jpg";
    private static final String COUPLE_PHOTO_TEMP_CROP_PATH = "/couple_photo_temp_crop.jpg";

    public static File getPlannerProjectAbsolutePathFile(){
        File dir = new File(String.format("%s/%s", Environment.getExternalStorageDirectory().getAbsolutePath(), "xogrp"));
        if(!dir.exists()){
            dir.mkdir();
        }
        return dir;
    }

    public static File getTakeCouplePhotoTempPathFile(){
        return new File(String.format("%s/%s", getPlannerProjectAbsolutePathFile(), TAKE_COUPLE_PHOTO_TEMP_PATH));
    }

    public static File getSelectCouplePhotoTempPathFile(){
        return new File(String.format("%s/%s", getPlannerProjectAbsolutePathFile(), SELECT_COUPLE_PHOTO_TEMP_PATH));
    }

    public static File getCouplePhotoTempCropPathFile(){
        return new File(String.format("%s/%s", getPlannerProjectAbsolutePathFile(), COUPLE_PHOTO_TEMP_CROP_PATH));
    }

    /**
     * fixed the Google Photos App get permission issue
     */
    public static Uri convertToValidUri(Context context, Uri data, File outputFile) {
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(data);
            if(inputStream != null){
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
                bufferedInputStream = new BufferedInputStream(inputStream);
                byte[] buffers = new byte[1024];
                while (bufferedInputStream.read(buffers) > 0){
                    bufferedOutputStream.write(buffers);
                }
                bufferedOutputStream.flush();

            }
        }  catch (IOException ignore) {
        }finally {
            if(bufferedOutputStream != null){
                try {
                    bufferedOutputStream.close();
                    bufferedOutputStream = null;
                } catch (IOException exception) {
                }
            }
            if(bufferedInputStream != null){
                try {
                    bufferedInputStream.close();
                    bufferedInputStream = null;
                } catch (IOException exception) {
                }
            }
        }
        return Uri.fromFile(outputFile);
    }
}
