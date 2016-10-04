package com.example.raviteja.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class Main3Activity extends AppCompatActivity {
    InputStream inputStream = null;
    String json = null;
    TextView t1,t2;
    String s, special_id, special_name, special_gender, special_address;
    StringBuilder s1 = new StringBuilder();
    JSONObject x;
    JSONObject jsonRootObject = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        try {
            inputStream = getAssets().open("avi.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        t1 = (TextView) findViewById(R.id.textView3);
        if (json != null) {
            try {
                jsonRootObject = new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            x= function(jsonRootObject,"xcaxc","10101010");
        } catch (Exception e) {
            e.printStackTrace();
        }

        t2 = (TextView) findViewById(R.id.textView4);
        t2.setText(x.toString());
    }

    public static JSONObject function(JSONObject obj, String keyMain, String newValue) throws Exception {
        // We need to know keys of Jsonobject

        Iterator iterator = obj.keys();
        String key = null;
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            // if object is just string we change value in key
            if ((obj.optJSONArray(key)==null) && (obj.optJSONObject(key)==null)) {
                if ((key.equals(keyMain))) {
                    // put new value
                    obj.put(key, newValue);
                    return obj;
                }
            }

            // if it's jsonobject
            if (obj.optJSONObject(key) != null) {
                function(obj.getJSONObject(key), keyMain, newValue);
            }

            // if it's jsonarray
            if (obj.optJSONArray(key) != null) {
                JSONArray jArray = obj.getJSONArray(key);
                for (int i=0;i<jArray.length();i++) {
                    function(jArray.getJSONObject(i), keyMain, newValue);
                }
            }
        }
        return obj;
    }
}

