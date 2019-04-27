package com.example.android.mysqltest;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Background extends AsyncTask<String,Void,String> {
    AlertDialog alert;
   Context context;
 public  static String message="";

   public  Background(Context context)
   {
  this.context=context;
   }
    @Override
    protected void onPreExecute() {
        alert=new AlertDialog.Builder(context).create();
        alert.setTitle("Login status");
    }

    @Override
    protected void onPostExecute(String s) {
        alert.setMessage(s);
        alert.show();
        message=s;
        Log.d("message", message);

    }
    @Override
    protected String doInBackground(String... voids) {
        String  result="";
        String username=voids[0];
        String password=voids[1];
        //String autheticatting=voids[2];
        String constant="http:// 192.168.137.1/systemanalysisproject/login.php";
        try {
            URL url=new URL(constant);
            HttpURLConnection http=(HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream opt=http.getOutputStream();
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(opt,"UTF-8"));
            String data=URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+
                    "&&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");//+
                    //"&&"+URLEncoder.encode("autheticatting","UTF-8")+"="+URLEncoder.encode(autheticatting,"UTF-8");


            writer.write(data);
            writer.flush();
            writer.close();
            opt.close();

            InputStream ips= http.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
         String line="";
         while ((line=reader.readLine())!=null){
             result+=line;
         }
         reader.close();
         ips.close();
         http.disconnect();
         return result;

        }
        catch (MalformedURLException e){
         result=e.getMessage();
        }
        catch (IOException e){
            result =e.getMessage();
        }

        return result;
    }
}
