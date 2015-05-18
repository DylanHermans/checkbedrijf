package com.example.dylan_hermans.checkbedrijf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    ListView viewbedrijven = null;
    ProgressBar Pb;
    List<MyTask> tasks;
    List<Bedrijf> bedrijflijst;
    String ZOEKWAARDEN = null;

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
        BtnZoeken=(Button)findViewById(R.id.ButZoeken);
        BtnZoeken.setOnClickListener(new OnClickListener() {
                                      public void onClick(View vw) {
                                          if (isOnline()) {
                                              ZOEKWAARDEN = TxtZoekWaarden.getText().toString();
                                              requestData();
                                          }else {
                                              Toast.makeText(MainActivity.this, "netwerk niet berijkbaar", Toast.LENGTH_LONG).show();
                                          }

                                          //TxtResultaten.setText(TxtZoekWaarden.getText());
                                      }
                                  });

    }

    private void requestData() {
        uri = url+ZOEKWAARDEN;
        MyTask task = new MyTask();
        task.execute(uri);
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
            if (s == null){
                Toast.makeText(MainActivity.this,"geen data gevonden", Toast.LENGTH_LONG).show();
            }

            bedrijflijst = bedrijfparser.parseFeed(s);
            if (bedrijflijst != null){
                BedrijfAdapter adapter = new BedrijfAdapter(MainActivity.this, R.layout.item_bedrijf,bedrijflijst);
                viewbedrijven = (ListView) findViewById(R.id.list);
                viewbedrijven.setAdapter(adapter);

                viewbedrijven.setOnItemClickListener(new  AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> parent, View viewclicked, int position, long id) {

                                // String textClick=(String)parent.getItemAtPosition(position);
                                // String textklick = viewclicked.toString();
                                // TxtZoekWaarden.setText(textClick);
                                //String requestname = bedrijflijst.get(position).getNaambedrijf().toString();
                                //int requestpost = bedrijflijst.get(position).getPostcode();
                                //String requestnnb = bedrijflijst.get(position).getNbblink().toString();
                                //TxtZoekWaarden.setText(requestname + " (" + requestpost + ")");
                                //if (isOnline()) {
                                //    requestData2(requestnnb);
                                //}else {
                                //    Toast.makeText(MainActivity.this, "netwerk niet berijkbaar", Toast.LENGTH_LONG).show();
                                //}

                                Intent getDataScreenIntent = new Intent(getBaseContext(), SecondScreen.class);

                                final int result = 1;

                                String tijdelijkelink = bedrijflijst.get(position).getNbblink().toString();

                                getDataScreenIntent.putExtra("BedrijfsDataActivity",tijdelijkelink);

                                startActivityForResult(getDataScreenIntent,result);



                            }
                        }
                );










            }else {
                Toast.makeText(MainActivity.this,"het gaan niet om een zoek functie maar een enkel bedrijf", Toast.LENGTH_LONG).show();
            }



        }

        @Override
        protected void onProgressUpdate(String... values) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ZOEKWAARDEN = data.getStringExtra("FunctieZoekString");
        TxtZoekWaarden.setText(ZOEKWAARDEN);
        if (isOnline()) {
            requestData();
        }else {
            Toast.makeText(MainActivity.this, "netwerk niet berijkbaar", Toast.LENGTH_LONG).show();
        }

    }
}
