package com.example.dylan_hermans.checkbedrijf;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dylan_Hermans on 12/05/2015.
 */
public class BedrijfAdapter extends ArrayAdapter<Bedrijf> {


    private Context context;
    @SuppressWarnings("unused")
    private List<Bedrijf> bedrijfList;


    public BedrijfAdapter(Context context, int resource, List<Bedrijf> objects) {
        super(context, resource, objects);
        this.context = context;
        this.bedrijfList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view   = inflater.inflate(R.layout.item_bedrijf, parent, false);

        Bedrijf bedrijf = bedrijfList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        tv.setText(bedrijf.getNaambedrijf()+ " (" + bedrijf.getPostcode() + ")");

        return view;

    }
}
