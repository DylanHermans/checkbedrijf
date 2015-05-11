package com.example.dylan_hermans.checkbedrijf;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    Button BtnZoeken = null;
    EditText TxtZoekWaarden = null;
    TextView TxtResultaten;
    static String url= "http://www.checkbedrijf.be/test/zoek.php?zoek=";
    String uri= null;
    String Bedrijven = null;

    BufferedReader in = null;
    String data = null;
    ProgressBar Pb;
    List<MyTask> tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pb = (ProgressBar) findViewById(R.id.progressBar1);
        Pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();

        addListenerOnButton();

    }

    public void addListenerOnButton() {

        TxtZoekWaarden =(EditText)findViewById(R.id.TxtZoekWaarde);
        TxtResultaten =(TextView)findViewById(R.id.TxtZoekResultaten);
        TxtResultaten.setMovementMethod(new ScrollingMovementMethod());
        TxtResultaten.setText("");
        BtnZoeken=(Button)findViewById(R.id.ButZoeken);
        BtnZoeken.setOnClickListener(new OnClickListener() {
                                      public void onClick(View vw) {
                                          if (isOnline()) {
                                              requestData();
                                          }else {
                                              Toast.makeText(MainActivity.this, "netwerk niet berijkbaar", Toast.LENGTH_LONG).show();
                                          }


                                           //makeGetRequest();
                                          //TxtResultaten.setText(TxtZoekWaarden.getText());
                                      }
                                  });

    }

    private void requestData() {
        uri = url+TxtZoekWaarden.getText();
        MyTask task = new MyTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
    }

    private void makeGetRequest() {
        uri = url+TxtZoekWaarden.getText();
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Bedrijven = null;
                        Bedrijven = url + response;
                        TxtResultaten.setText("");
                        TxtResultaten.setText(Bedrijven);
                        response = null;

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError err) {
                        Toast.makeText(MainActivity.this,err.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
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
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnectedOrConnecting()){
           return true;
        }else {return false;}
    }

    private class MyTask extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            TxtResultaten.append("start de http request \n");
            if (tasks.size()==0){
                Pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... params) {

            for (int i = 0; i< params.length; i++){
                publishProgress("werken met " + params[i]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "afgesloten de http request";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TxtResultaten.append(s + "\n");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            TxtResultaten.append(values[0]+"\n");
            tasks.remove(this);
            if (tasks.size()==0){
                Pb.setVisibility(View.INVISIBLE);
            }

        }
    }


}
