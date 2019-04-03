package com.gz.camming.mvp.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by camming on 2019/4/3.
 */

public class PhotoUtils {

    /**
     * 打开系统相机
     *
     * */
    public static Intent openCamera(Context context,String path,String image){

        Intent intent = null;
        // 判断存储卡是否可以用，可用进行存储
        if (FileUtil.hasSdcard()) {
            //设定拍照存放到自己指定的目录,可以先建好
            FileUtil.createTmpDir(context,path);
            Uri pictureUri;
            File pictureFile = new File(path, image);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                pictureUri = FileProvider.getUriForFile(context, XAppUtils.getPackageInfo().packageName+".fileprovider", pictureFile);

            } else {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pictureUri = Uri.fromFile(pictureFile);
            }

            if (intent != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
               // context.startActivityForResult(intent, requestId);
            }
        }else{
            ToastUtils.show(context,"没有sd卡！");
        }
        return intent;
    }

    /**
     * 打开相册
     *
     * */
    public static  Intent openPhoteAlbum(Context context,String path){
        Intent intentFromGallery = null;
        if (FileUtil.hasSdcard()) {
            //设定拍照存放到自己指定的目录,可以先建好
            FileUtil.createTmpDir(context, path);
            intentFromGallery  = new Intent(Intent.ACTION_PICK, null);
            intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//            startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
        }else{
            ToastUtils.show(context,"没有sd卡！");
        }
        return intentFromGallery;
    }


    /**
     * 裁剪原始的图片
     * @param  uri 如果是相册返回，uri参数就传null ,如果相机返回的裁剪，全部参数都要填
     * @param requestCode 裁剪的请求code
     */
    public static Uri cropRawPhoto(Activity context, Uri uri, String path, String name,int requestCode) {

        if(uri==null)
            uri = getImageContentUri(context,path,name);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);

        //startActivityForResult(intent, CODE_RESULT_REQUEST); //直接调用此代码在小米手机有异常，换以下代码
        //String mLinshi = System.currentTimeMillis() + CROP_IMAGE_FILE_NAME;
        File mFile = new File(path, name);
//        mHeadCachePath = mHeadCacheFile.getAbsolutePath();

        Uri mUriPath = Uri.parse("file://" + mFile.getAbsolutePath());
        //将裁剪好的图输出到所建文件中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPath);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //注意：此处应设置return-data为false，如果设置为true，是直接返回bitmap格式的数据，耗费内存。设置为false，然后，设置裁剪完之后保存的路径，即：intent.putExtra(MediaStore.EXTRA_OUTPUT, uriPath);
//        intent.putExtra("return-data", true);
        intent.putExtra("return-data", false);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


        context.startActivityForResult(intent, requestCode);
        return mUriPath;
    }

    private static Uri getImageContentUri(Context context,String path,String name) {
        File file =  new File(path,name);
        String filePath = file.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID },
                MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (file.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }


    /**
     *
     * 保存裁剪图片到SD卡
     * */
    public static boolean saveBitmapSDcart(Context context,Uri uri,String path ,String name){
        File file = null;
        try {
            //保存文件到sd
            Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
            file = FileUtil.saveFile(path,name,bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file!=null;
    }
}
