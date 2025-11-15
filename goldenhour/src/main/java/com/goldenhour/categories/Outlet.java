package com.goldenhour.categories;

public class Outlet {
    private String outletCode;
    private String outletName;

    public Outlet(String outletCode, String outletName) {
        this.outletCode = outletCode;
        this.outletName = outletName;
    }  
     
    public String getOutletCode() {
        return outletCode;
    }
    public String getOutletName() {
        return outletName;
    }

    public static Outlet fromCSV(String csvLine) {
        String[] data = csvLine.split(",");
        String outletCode = data[0];
        String outletName = data[1];
        return new Outlet(outletCode, outletName);
    }

}
