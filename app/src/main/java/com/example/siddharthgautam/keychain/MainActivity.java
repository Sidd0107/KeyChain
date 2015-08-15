package com.example.siddharthgautam.keychain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.TextView;
import com.example.siddharthgautam.keychain.appObj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends ActionBarActivity {
    Button store;
    Button read;
    Button clear;
    Button show;
    EditText appName;
    EditText userName;
    EditText pass;
    //String FILENAME = "passwords.txt";
    TextView out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        store=(Button)findViewById(R.id.button);
        read=(Button)findViewById(R.id.button2);
       // show=(Button)findViewById(R.id.button5);
        appName = (EditText) findViewById(R.id.nameInput);
        userName = (EditText) findViewById(R.id.usernameIn);
        pass = (EditText) findViewById(R.id.PassIn);
        out = (TextView) findViewById(R.id.Output) ;
        out.setText("Welcome");

        clear=(Button)findViewById(R.id.button4);
        //Setting its onclick listener
        store.setOnClickListener(new View.OnClickListener() {
                                     @Override
             public void onClick(View store) {
                 String appl = String.valueOf(appName.getText());
                 if (appl.toString()==" "){
                     out.setText("Please Enter appl", TextView.BufferType.EDITABLE);
                 }
                 String userNm = String.valueOf(userName.getText());
                 if (userNm.toString()==" "){
                     out.setText("Please Enter username", TextView.BufferType.EDITABLE);
                 }
                 String pswd = String.valueOf(pass.getText());
                 if (pswd.toString()==" "){
                     out.setText("Please Enter password", TextView.BufferType.EDITABLE);
                 }
                 appObj item=new appObj(appl, userNm, pswd);
                 SharedPreferences.Editor storage = getSharedPreferences(appl, MODE_PRIVATE).edit();
                 SharedPreferences.Editor index = getSharedPreferences(appl, MODE_PRIVATE).edit();

                 storage.putString("k1", appl);
                 storage.putString("k2", userNm);
                 storage.putString("k3", pswd);
                 storage.commit();
                 out.setText("Saved! You may enter another entry");

/*
                 try {
                     File myFile = new File("index.txt");
                     if(!myFile.exists()) {
                         myFile.createNewFile();
                     }
                     FileOutputStream output = new FileOutputStream(myFile);
                     output.write(appl.getBytes());
                     output.close();
                 }
                 catch (IOException e) {
                     Log.e("Exception", "File write failed: " + e.toString());
                     out.setText(e.getLocalizedMessage());
                 }
*/
             }
         });

       read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View read) {
                //Intent intent = new Intent(getApplicationContext(), ViewkeychainActivity.class);
                //startActivity(intent);
                String appl = String.valueOf(appName.getText());
                if (appl.toString()==" "){
                    out.setText("Please Enter appl", TextView.BufferType.EDITABLE);
                }
                SharedPreferences sharedPref= getSharedPreferences(appl, MODE_PRIVATE);
                String userNm=sharedPref.getString("k2", "Not Found");
                String pswd=sharedPref.getString("k3", "Not Found");
                out.setText("Username: "+userNm+" password: "+pswd);
            }
        });
        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View read) {
                String appl = String.valueOf(appName.getText());
                if (appl.toString()==" "){
                    out.setText("Please Enter appl", TextView.BufferType.EDITABLE);
                }
                SharedPreferences preferences = getSharedPreferences(appl, 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

            }
            });
        /*
        show.setOnClickListener(new View.OnClickListener() {
            public void onClick(View read) {
                String ret = "";
                try {

                    FileInputStream infile = new FileInputStream("index.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            infile));
                    if (infile != null) {
                        String result=br.readLine();
                        while ((result != null)) {
                            ret = ret + result;
                            result=br.readLine();
                        }
                        br.close();
                        infile.close();
                        //ret = stringBuilder.toString();
                        out.setText(ret);
                    }
                } catch (FileNotFoundException e) {
                    Log.e("login activity", "File not found: " + e.toString());
                } catch (IOException e) {
                    Log.e("login activity", "Can not read file: " + e.toString());
                }

            }
        });
    */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
