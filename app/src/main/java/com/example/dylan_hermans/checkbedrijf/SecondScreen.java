package com.example.dylan_hermans.checkbedrijf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan_Hermans on 18/05/2015.
 */
public class SecondScreen extends Activity {
    Button BtnZoeken = null;
    TextView TxtResultaten = null;
    EditText TxtZoekWaarden = null;
    static String url= "http://www.globalcomputers.be/site/checkbedrijf/data.php?id=";
    String uri= null;
    String Bedrijven = null;
    ListView viewbedrijven = null;
    ProgressBar Pb;
    List<MyTask> tasks;
    List<Bedrijf> bedrijflijst;
    String prevousActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_layout);
        Intent activityBedrijfsData = getIntent();

        prevousActivity = activityBedrijfsData.getExtras().getString("BedrijfsDataActivity");
        Pb = (ProgressBar) findViewById(R.id.progressBar2);
        Pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();

        addListenerOnButton();

        if (isOnline()) {
            requestData();
        }else {
            Toast.makeText(SecondScreen.this, "netwerk niet berijkbaar", Toast.LENGTH_LONG).show();
        }

    }

    public void addListenerOnButton() {

        TxtZoekWaarden =(EditText)findViewById(R.id.TxtZoekWaarde2);
        BtnZoeken=(Button)findViewById(R.id.ButZoeken2);
        BtnZoeken.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vw) {
                String zoekertje = String.valueOf(TxtZoekWaarden.getText());
                Intent goingBack = new Intent();
                goingBack.putExtra("FunctieZoekString",zoekertje );
                setResult(RESULT_OK,goingBack);
                finish();
            }
        });

    }
    private void requestData() {
        uri = url + prevousActivity;
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnectedOrConnecting()){
            return true;
        }else {return false;}
    }


    private class MyTask extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            if (tasks.size()==0){
                Pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... params) {

            Bedrijven = HTTPbedrijven.getData(params[0]);

            return Bedrijven;
        }

        @Override
        protected void onPostExecute(String s) {

            tasks.remove(this);
            if (tasks.size()==0){
                Pb.setVisibility(View.INVISIBLE);
            }

            TxtResultaten = (TextView) findViewById(R.id.TxtDataView2);
            TxtResultaten.setText(s);

        }

        @Override
        protected void onProgressUpdate(String... values) {
        }
    }

}
