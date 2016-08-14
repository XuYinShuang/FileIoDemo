package com.zhiyuan3g.fileiodemo.unils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/11.
 */
public class IOHelper {

    public static boolean writeData(String fileName, String data) throws IOException {
        boolean result = false;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory() + "/" + fileName;
            File file = new File(path);
            if (!file.exists()) {
                //如果文件不存在，则创建
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] datas = data.getBytes();
            outputStream.write(datas);
            outputStream.close();
            outputStream.flush();
            result = true;
        }

        return result;
    }

    public static String readData(String fileName) throws IOException {
        String content = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory() + "/" + fileName;
            File file = new File(path);
            if (file.exists()) {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] datas = new byte[inputStream.available()];
                inputStream.read(datas);
                content = new String(datas);
            }
        }
        return content;
    }
}

