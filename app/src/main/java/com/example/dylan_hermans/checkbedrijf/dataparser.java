package com.example.dylan_hermans.checkbedrijf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan_Hermans on 12/05/2015.
 */
public class dataparser {
    public static List<Data> parseFeed(String content){
        try {

            Data jaarrekeningen = null;
            List<Data> jaarrekeningenList = new ArrayList<>();
            int begin = 0;
            int einde = 0;
            String jaarg= null;
            String weerg = null;
            String hyp= null;

            begin = content.indexOf("bedrijfsnaam'>");
            content = content.substring((begin+15));
            einde = content.indexOf("</p>");

            jaarrekeningen = new Data();
            jaarrekeningenList.add(jaarrekeningen);
            jaarrekeningen.setWeergave(content.substring(0,einde-1));

            content = content.substring((einde+5));
            begin = content.indexOf("bedrijfsstaat'>");
            content = content.substring((begin+16));
            einde = content.indexOf("</p>");

            jaarrekeningen = new Data();
            jaarrekeningenList.add(jaarrekeningen);
            jaarrekeningen.setWeergave(content.substring(0,einde-1));

            content = content.substring((einde+5));
            begin = content.indexOf("btwnummer'>");
            content = content.substring((begin+12));
            einde = content.indexOf("</p>");

            jaarrekeningen = new Data();
            jaarrekeningenList.add(jaarrekeningen);
            jaarrekeningen.setWeergave(content.substring(0,einde-1));

            content = content.substring((einde+5));
            begin = content.indexOf("bedrijfstype'>");
            content = content.substring((begin+15));
            einde = content.indexOf("</p>");

            jaarrekeningen = new Data();
            jaarrekeningenList.add(jaarrekeningen);
            jaarrekeningen.setWeergave(content.substring(0,einde-1));

            content = content.substring((einde+5));
            begin = content.indexOf("rsz'>");
            content = content.substring((begin+6));
            einde = content.indexOf("</p>");

            jaarrekeningen = new Data();
            jaarrekeningenList.add(jaarrekeningen);
            jaarrekeningen.setWeergave(content.substring(0,einde-1));

            content = content.substring((einde+5));
            begin = content.indexOf("<p>");
            content = content.substring((begin+4));
            einde = content.indexOf("</p>");

            jaarrekeningen = new Data();
            jaarrekeningenList.add(jaarrekeningen);
            jaarrekeningen.setWeergave(content.substring(0,einde-1));

            content = content.substring((einde+5));
            begin = content.indexOf("<p>");
            content = content.substring((begin+4));
            einde = content.indexOf("</p>");

            jaarrekeningen = new Data();
            jaarrekeningenList.add(jaarrekeningen);
            jaarrekeningen.setWeergave(content.substring(0,einde-1));

            content = content.substring((einde+5));
            begin = content.indexOf("<p>");
            content = content.substring((begin+4));
            einde = content.indexOf("</p>");

            jaarrekeningen = new Data();
            jaarrekeningenList.add(jaarrekeningen);
            jaarrekeningen.setWeergave(content.substring(0,einde-1));

            content = content.substring((einde+5));
            begin = content.indexOf("<p>");
            content = content.substring((begin+4));
            einde = content.indexOf("</p>");

            jaarrekeningen = new Data();
            jaarrekeningenList.add(jaarrekeningen);
            jaarrekeningen.setWeergave(content.substring(0,einde-1));
            content = content.substring((einde+5));

            einde = content.indexOf("jaarrekeningen'>");
            content = content.substring((einde+17));

            begin = content.indexOf("<a href='");
            while (begin>1){

                jaarrekeningen = new Data();
                jaarrekeningenList.add(jaarrekeningen);
                jaarrekeningen.setJaargang(Integer.parseInt(content.substring(0,begin-2)));

                einde = content.indexOf("' target='");
                jaarrekeningen.setHyplink(content.substring(begin+10,einde-1));
                content = content.substring(einde+18);
                begin = content.indexOf("</a>");
                jaarrekeningen.setWeergave(content.substring(0,begin-1));
                content = content.substring(begin+10);

                begin = content.indexOf("<a href='");

            }

            return jaarrekeningenList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
