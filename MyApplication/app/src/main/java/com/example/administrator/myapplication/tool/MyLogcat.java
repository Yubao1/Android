package com.example.administrator.myapplication.tool;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.myapplication.MyApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 86188 on 2020/7/3.
 */

public class MyLogcat {
    private boolean isPrint = true;
    private  int maxFileSize = 1024 * 1024;
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS",Locale.CHINESE);
    public void d(Class<?> cls,String message) {
        if (isPrint) {
            Log.d(cls.getSimpleName(),message);
            writeFile(message);
        }
    }

    private File getLogFile(@NonNull String folderName, @NonNull String fileName) {


        File folder = new File(folderName);
        if (!folder.exists()) {
            //TODO: What if folder is not created, what happens then?
            folder.mkdirs();
        }

        String[] files=folder.list();
        int filecount=files.length;
        File newFile= null;
        File existingFile = null;

        newFile = new File(folder, String.format("%s_%s.log", fileName, 0));
        while (newFile.exists()) {
            existingFile = newFile;
            newFile = new File(folder, String.format("%s_%s.log", fileName, filecount));
        }

        if (existingFile != null) {
            if (existingFile.length() >= maxFileSize) {
                if(filecount>=5){
                    for(int i=0;i<filecount;i++){
                        File localfile= new File(folder.getPath()+ File.separator+files[i]);
                        if(localfile.exists()){
                            localfile.delete();
                        }
                    }
                }else{
                    existingFile.renameTo(newFile);
                }
                newFile=new File(folder, String.format("%s_%s.log", fileName, 0));
                return newFile;
            }
            return existingFile;
        }

        return newFile;
    }

    private void writeFile(String message) {
        FileWriter fileWriter = null;
        String folder = MyApplication.diskPath + File.separatorChar + "usblogger";
        File logFile = getLogFile(folder, "Logs");
        date.setTime(System.currentTimeMillis());
        String time = dateFormat.format(date);
        try {
            fileWriter = new FileWriter(logFile, true);
            fileWriter.append(time + "-------------" + message);
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            if (fileWriter != null) {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e1) { /* fail silently */ }
            }
        }
    }
}
