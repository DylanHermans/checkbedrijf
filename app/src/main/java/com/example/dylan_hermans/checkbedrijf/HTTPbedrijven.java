package com.example.dylan_hermans.checkbedrijf;


import android.net.http.AndroidHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HTTPbedrijven {
    public static String getData(String uri){
        AndroidHttpClient client = AndroidHttpClient.newInstance("androidAgent");
        HttpGet request = new HttpGet(uri);
        HttpResponse response;

        try {
            response = client.execute(request);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            client.close();
        }
    }




}
