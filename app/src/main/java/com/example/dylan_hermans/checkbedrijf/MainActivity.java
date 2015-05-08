package com.example.dylan_hermans.checkbedrijf;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    Button BtnZoeken = null;
    EditText TxtZoekWaarden = null;
    TextView TxtResultaten;


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

                                          TxtResultaten.setText(TxtZoekWaarden.getText());
                                      }
                                  });


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
