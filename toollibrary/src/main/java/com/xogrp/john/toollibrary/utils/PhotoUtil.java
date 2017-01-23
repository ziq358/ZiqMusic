package com.xogrp.john.toollibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.List;

/**
 * Created by john on 23/01/2017.
 */

public class PhotoUtil {

    public static boolean takePhoto(Activity activity, int requestCode, File outputFile) {
        if(activity != null){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));
            activity.startActivityForResult(intent, requestCode);
            return true;
        }
        return false;
    }

    public static boolean selectPhoto(Activity activity, int requestCode){
        if(activity != null){
            Intent intentPicture = new Intent(Intent.ACTION_PICK, null);
            intentPicture.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            List<ResolveInfo> resolveInfos = activity.getPackageManager().queryIntentActivities(intentPicture, PackageManager.MATCH_DEFAULT_ONLY);
            if(resolveInfos != null && !resolveInfos.isEmpty()) {
                activity.startActivityForResult(intentPicture, requestCode);
                return true;
            }
        }
        return false;
    }

    public static Uri getInputUriAfterSelectPhoto(Context context, Uri data, File outputFile){
        return FileUtil.convertToValidUri(context, data, outputFile);
    }

    public static void getCroppedPhoto(Activity activity,int requestCode, Uri inputUri, Uri outputUri) {
        if(activity != null){
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(inputUri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 3);//截图框的比例
            intent.putExtra("aspectY", 2);
            intent.putExtra("outputX", 300);//输出的图像的尺寸
            intent.putExtra("outputY", 200);
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            List<ResolveInfo> resolveInfos = activity.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if(resolveInfos != null && !resolveInfos.isEmpty()){
                activity.startActivityForResult(intent, requestCode);
            }
        }
    }

}
