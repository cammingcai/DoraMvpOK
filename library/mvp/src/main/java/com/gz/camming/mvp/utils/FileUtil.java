package com.gz.camming.mvp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.gz.camming.mvp.iml.UpdateProgressListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class FileUtil {

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static File saveFile(String filePath, String fileName, Bitmap bitmap){
        boolean bool = makeDir(filePath);
        if(!bool){
            throw new RuntimeException("创建文件夹失败");
            //return null;
        }
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] bytes = baos.toByteArray();
        try {
            File file = new File(filePath, fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String createTmpDir(Context context, String path){
        String tmpDir = path;
        if(!makeDir(path)){
            tmpDir = context.getExternalFilesDir(path).getAbsolutePath();
        }
        return tmpDir;
    }

    /**
     * 判断目录是否存在
     *
     * */
    public static boolean makeDir(String dirPath) {
        File file = new File(dirPath);
       // Log.i("FileUtil","file.exists()="+file.exists());
        if (!file.exists()) {
          //  Log.i("FileUtil","file.mkdirs()="+file.mkdirs());
            return file.mkdirs();
        } else {
            return true;
        }
    }

    /**
     * 写入文件到sd卡
     * 如下载apk
     *
     * */
    public static boolean writeToFile(ResponseBody body, File file, UpdateProgressListener listener) {
        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;

            long currentLength = 0;
            long totalLength = body.contentLength();
            try {
                if(listener!=null)
                    listener.start();
                byte[] fileReader = new byte[2048];
                inputStream =body.byteStream();
                outputStream = new FileOutputStream(file);

                int len;
                while ((len = inputStream.read(fileReader))!=-1) {
                    outputStream.write(fileReader, 0, len);
                    currentLength += len;
                    Log.d("FileUtils","当前长度: " + currentLength);
                    int progress = (int) (100 * currentLength / totalLength);
                    listener.update(progress);
                    Log.d("FileUtils","当前进度: " + progress);
                }
                outputStream.flush();
                if(listener!=null)
                    listener.success();
                return true;
            } catch (IOException e) {
                if(listener!=null)
                    listener.error();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }if (outputStream != null) {
                    outputStream.close();}
            }
        } catch (IOException e) {
            if(listener!=null)
                listener.error();
            return false;
        }
    }
}
