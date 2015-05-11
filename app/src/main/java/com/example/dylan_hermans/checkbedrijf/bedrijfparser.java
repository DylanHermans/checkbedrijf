package com.example.dylan_hermans.checkbedrijf;


import java.util.ArrayList;
import java.util.List;

public class bedrijfparser {
    public static List<Bedrijf> parseFeed(String content){
        try {

            Bedrijf bedrijf = null;
            List<Bedrijf> bedrijfList = new ArrayList<>();
            int begin = 0;
            int einde = 0;
            String naam = null;
            String nbb = null;
            String postcode = null;

            while (content.length()>10){

// looooooooooooooooooooooooooooooooop
                begin = content.indexOf("<i>");
                einde = content.indexOf("</i>");
                postcode = content.substring((begin+4),(einde-1));

                begin = content.indexOf("nbb-link'>");
                einde = content.indexOf("</span>");
                nbb = content.substring((begin+10),einde);

                begin = content.indexOf("naam-bedrijf'>");
                einde = content.indexOf("</p>");
                naam = content.substring((begin+14),(einde-7));

                content = content.substring(einde+4);

                bedrijf = new Bedrijf();
                bedrijfList.add(bedrijf);

                bedrijf.setNaambedrijf(naam.toString());
                bedrijf.setNbblink(nbb.toString());
                bedrijf.setPostcode(Integer.parseInt(postcode.toString()));

// einde loopppp
            }

            return bedrijfList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
