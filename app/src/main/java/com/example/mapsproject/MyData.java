package com.example.mapsproject;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

public class MyData {
    public boolean login(String userId,String password)
    {
        final String SERVER_URL = "http://192.168.43.33:8080/GMap_Project/UserLogin.jsp";
        final String CHAR_SET = "UTF-8";
        OutputStream output = null;
        String response = "";
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String data = URLEncoder.encode("userId", CHAR_SET) + "=" + URLEncoder.encode(userId, CHAR_SET);
            data += "&" + URLEncoder.encode("password", CHAR_SET) + "=" + URLEncoder.encode(password, CHAR_SET);
            URLConnection connection = new URL(SERVER_URL).openConnection();
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);
            output = null;
            output = connection.getOutputStream();
            output.write(data.getBytes(CHAR_SET));
            output.flush();
            output.close();

            InputStream responseStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET));
            String line = null;
            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        if(response.trim().equals("VALID"))
            return true;
        else
            return false;
    }



    public String Signup (String userId,String name,String password,String taxino,String pno)
    {
        final String SERVER_URL = "http://192.168.43.33:8080/GMap_Project/UserSignup.jsp";
        final String CHAR_SET = "UTF-8";
        OutputStream output = null;
        String response = "";
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String data = URLEncoder.encode("userId", CHAR_SET) + "=" + URLEncoder.encode(userId, CHAR_SET);
            data += "&" + URLEncoder.encode("name", CHAR_SET) + "=" + URLEncoder.encode(name, CHAR_SET);
            data += "&" + URLEncoder.encode("password", CHAR_SET) + "=" + URLEncoder.encode(password, CHAR_SET);
            data += "&" + URLEncoder.encode("taxino", CHAR_SET) + "=" + URLEncoder.encode(taxino, CHAR_SET);
            data += "&" + URLEncoder.encode("pno", CHAR_SET) + "=" + URLEncoder.encode(pno, CHAR_SET);
            URLConnection connection = new URL(SERVER_URL).openConnection();
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);
            output = null;
            output = connection.getOutputStream();
            output.write(data.getBytes(CHAR_SET));
            output.flush();
            output.close();


            InputStream responseStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET));
            String line = null;
            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public String savelocations (String tno, String userId, String latitude, String longitude, String date)
    {
        final String SERVER_URL = "http://192.168.43.33:8080/GMap_Project/locations.jsp";
        final String CHAR_SET = "UTF-8";
        OutputStream output = null;
        String response = "";
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String data = URLEncoder.encode("tno", CHAR_SET) + "=" + URLEncoder.encode(tno, CHAR_SET);
            data += "&" + URLEncoder.encode("userId", CHAR_SET) + "=" + URLEncoder.encode(userId, CHAR_SET);
            data += "&" + URLEncoder.encode("latitude", CHAR_SET) + "=" + URLEncoder.encode(latitude, CHAR_SET);
            data += "&" + URLEncoder.encode("longitude", CHAR_SET) + "=" + URLEncoder.encode(longitude, CHAR_SET);
            data += "&" + URLEncoder.encode("date", CHAR_SET) + "=" + URLEncoder.encode(date, CHAR_SET);
            URLConnection connection = new URL(SERVER_URL).openConnection();
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);
            output = null;
            output = connection.getOutputStream();
            output.write(data.getBytes(CHAR_SET));
            output.flush();
            output.close();


            InputStream responseStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET));
            String line = null;
            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }


    public boolean searchlocations (String tno, String userId , String date)
    {
        final String SERVER_URL = "http://192.168.43.33:8080/GMap_Project/searchlocations.jsp";
        final String CHAR_SET = "UTF-8";
        OutputStream output = null;
        String response = "";
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String data = URLEncoder.encode("tno", CHAR_SET) + "=" + URLEncoder.encode(tno, CHAR_SET);
            data += "&" + URLEncoder.encode("userId", CHAR_SET) + "=" + URLEncoder.encode(userId, CHAR_SET);
            data += "&" + URLEncoder.encode("date", CHAR_SET) + "=" + URLEncoder.encode(date, CHAR_SET);
            URLConnection connection = new URL(SERVER_URL).openConnection();
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);
            output = null;
            output = connection.getOutputStream();
            output.write(data.getBytes(CHAR_SET));
            output.flush();
            output.close();


            InputStream responseStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET));
            String line = null;
            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error:------->"+e.getMessage());
        }
        if(response.trim().equals("valid"))
            return true;
        else
            return false;
    }


    public String returntno (String userId)
    {
        final String SERVER_URL = "http://192.168.43.33:8080/GMap_Project/ReturnTaxino.jsp";
        final String CHAR_SET = "UTF-8";
        OutputStream output = null;
        String response = "";
        String line="";
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String data = URLEncoder.encode("userId", CHAR_SET) + "=" + URLEncoder.encode(userId, CHAR_SET);
            URLConnection connection = new URL(SERVER_URL).openConnection();
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);
            output = null;
            output = connection.getOutputStream();
            output.write(data.getBytes(CHAR_SET));
            output.flush();
            output.close();


            InputStream responseStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET));
            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error:------->"+e.getMessage());
        }
            return response;
    }

    public String returnLatLng (String tno, String userId , String date)
    {
        final String SERVER_URL = "http://192.168.43.33:8080/GMap_Project/ReturnLatLng.jsp";
        final String CHAR_SET = "UTF-8";
        OutputStream output = null;
        String response = "";
        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String data = URLEncoder.encode("tno", CHAR_SET) + "=" + URLEncoder.encode(tno, CHAR_SET);
            data += "&" + URLEncoder.encode("userId", CHAR_SET) + "=" + URLEncoder.encode(userId, CHAR_SET);
            data += "&" + URLEncoder.encode("date", CHAR_SET) + "=" + URLEncoder.encode(date, CHAR_SET);
            URLConnection connection = new URL(SERVER_URL).openConnection();
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);
            output = null;
            output = connection.getOutputStream();
            output.write(data.getBytes(CHAR_SET));
            output.flush();
            output.close();


            InputStream responseStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream,CHAR_SET));
            String line = null;
            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error:------->"+e.getMessage());
        }
        return response;
    }

}


