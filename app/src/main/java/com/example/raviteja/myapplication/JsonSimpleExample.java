package com.example.raviteja.myapplication;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonSimpleExample {
    public static void fastTrack(Context context) {

        JSONObject obj = new JSONObject();

        try {
            obj.put("age", new Integer(100));
            obj.put("name", "Ravi");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray list1 = new JSONArray();
        JSONObject pnObj = new JSONObject();
        try {
            pnObj.put("num", "99009900");
            pnObj.put("type", "mhgchmc");
            list1.put(pnObj);
            obj.put("phoneNumber", list1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        File root = android.os.Environment.getExternalStorageDirectory();
        System.out.println("\nExternal file system root: "+root);

            // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

            File dir = new File (root.getAbsolutePath() + "/downloadgfdsgfd");
            dir.mkdirs();
            File file = new File(dir, "avinash.json");

            try {
                FileOutputStream f = new FileOutputStream(file);
                PrintWriter pw = new PrintWriter(f);
                pw.println(obj.toString());
                pw.flush();
                pw.close();
                f.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("******* File not found. Did you" +
                        " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.println("\n\nFile written to "+file);

        System.out.print(obj.toString());
    }




}
