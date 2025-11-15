package com.goldenhour.categories;

//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

public class Model {
    private String modelCode;
    private double price;
    private Map<String, Integer> stockPerOutlet; // key: outlet code, value: quantity

    public Model(String modelCode, double price) {
        this.modelCode = modelCode;
        this.price = price;
        this.stockPerOutlet = new HashMap<>();
    }

    public void setStock(String outletCode, int quantity) {
        stockPerOutlet.put(outletCode, quantity);

    }

    public int getStock(String outletCode) {
        return stockPerOutlet.getOrDefault(outletCode, 0);
    }

    public String getModelCode() {
        return modelCode;
    }

    public double getPrice() {
        return price;
    }


    public Map<String, Integer> getStockPerOutlet() {
        return stockPerOutlet;
    }

    @Override
    public String toString() {
        return "Model{" +
                "modelCode='" + modelCode + '\'' +
                ", price=" + price +
                ", stockPerOutlet=" + stockPerOutlet +
                '}';
    }

    public static Model fromCSV(String line, String[] outletCodes) {
        String[] data = line.split(",");
        String modelCode = data[0];
        double price = Double.parseDouble(data[1]);
        Model model = new Model(modelCode, price);

        for (int i = 2; i < data.length; i++) {

            int outletIndex = i-2;
            
            if (outletIndex < outletCodes.length) {
                String outletCode = outletCodes[outletIndex];
                int quantity = Integer.parseInt(data[i]);
                model.setStock( outletCode, quantity);
            }
        }

        return model;
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(modelCode).append(",").append(price);
        for (String outlet : stockPerOutlet.keySet()) {
            sb.append(",").append(stockPerOutlet.get(outlet));
        }
        return sb.toString();
    }
}