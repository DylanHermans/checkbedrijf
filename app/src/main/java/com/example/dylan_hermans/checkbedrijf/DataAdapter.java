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

public class DataAdapter extends ArrayAdapter<Data> {

    private Context context;
    @SuppressWarnings("unused")
    private List<Data> dataList;


    public DataAdapter(Context context, int resource, List<Data> objects) {
        super(context, resource, objects);
        this.context = context;
        this.dataList = objects;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view   = inflater.inflate(R.layout.item_bedrijf, parent, false);

        Data jaarg = dataList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        if (jaarg.getJaargang() != 0){
            tv.setText(jaarg.getJaargang()+ " " + jaarg.getWeergave());
        }


        tv.setText( jaarg.getWeergave());

        return view;

        }
}
