package com.example.raviteja.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main2Activity extends AppCompatActivity {

    Context c;
    String s,special_id,special_name,special_gender,special_address;
    String myString;
    TextView t,up;
    StringBuilder s1= new StringBuilder();
    InputStream inputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        up=(TextView)findViewById(R.id.updationKosam);
        try {
            inputStream = getAssets().open("avi.json");

        } catch (IOException e) {
            e.printStackTrace();
        }


        retrieving(getJsonName());

    }

    public String getJsonName()
    {
        String json = null;

        try {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            json = new String(buffer, "UTF-8");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), json, Toast.LENGTH_LONG).show();
        return json;
    }

    public void retrieving(String json)
    {
        t = (TextView) findViewById(R.id.textView2);

            if (json != null) {
                JSONObject jsonRootObject = null;
                try {
                    jsonRootObject = new JSONObject(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray jsonArray = jsonRootObject.optJSONArray("contacts");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonInnerObject = null;
                    try {
                        jsonInnerObject = jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    special_id = jsonInnerObject.optString("id").toString();
                    special_name = jsonInnerObject.optString("name").toString();
                    special_gender = jsonInnerObject.optString("gender").toString();
                    special_address = jsonInnerObject.optString("address").toString();
                    //a1=jsonInnerObject.optString("mobile").toString();
                    s1.append(special_id+"  "+special_name+"  "+special_gender+"  "+special_address);
                    s1.append("\n");


                }


                t.setText(s1);

            }
        else
            {
                Toast.makeText(getApplicationContext(),"unable to fetch the json file", Toast.LENGTH_LONG).show();
            }
        try
        {
            inputStream.close();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }


    }
    public void please(View v)
    {
        Intent i = new Intent(this, Main3Activity.class);
        startActivity(i);
    }
}

