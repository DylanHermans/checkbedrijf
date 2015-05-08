package com.example.dylan_hermans.checkbedrijf;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class MainActivity extends Activity {
    Button BtnZoeken = null;
    EditText TxtZoekWaarden = null;
    TextView TxtResultaten;
    String url= "http://www.checkbedrijf.be/test/zoek.php?zoek=joske";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

    }

    public void addListenerOnButton() {
        TxtZoekWaarden =(EditText)findViewById(R.id.TxtZoekWaarde);
        TxtResultaten =(TextView)findViewById(R.id.TxtZoekResultaten);
        BtnZoeken=(Button)findViewById(R.id.ButZoeken);
        BtnZoeken.setOnClickListener(new OnClickListener() {
                                      public void onClick(View vw) {
                                          makeGetRequest();
                                         //TxtResultaten.setText(TxtZoekWaarden.getText());
                                      }
                                  });

    }
    private void makeGetRequest() {

        HttpClient client = new DefaultHttpClient();
        url = url+TxtZoekWaarden.getText();
        HttpGet request = new HttpGet(url);
        // replace with your url

        HttpResponse response;
        try {
            response = client.execute(request);

            Log.d("Response of GET request", response.toString());
            TxtResultaten.setText(response.toString());
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
