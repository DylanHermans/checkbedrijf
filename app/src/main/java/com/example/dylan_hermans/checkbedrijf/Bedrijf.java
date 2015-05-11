package com.example.dylan_hermans.checkbedrijf;


public class Bedrijf {
    private int postcode = 0;
    private String nbblink = null;
    private String naambedrijf = null;


    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getNbblink() {
        return nbblink;
    }

    public void setNbblink(String nbblink) {
        this.nbblink = nbblink;
    }

    public String getNaambedrijf() {
        return naambedrijf;
    }

    public void setNaambedrijf(String naambedrijf) {
        this.naambedrijf = naambedrijf;
    }
}
