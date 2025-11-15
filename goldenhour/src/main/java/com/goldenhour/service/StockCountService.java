package com.goldenhour.service;
import com.goldenhour.categories.Model;
import com.goldenhour.categories.Outlet;
import com.goldenhour.storage.CSVHandler;
import com.goldenhour.utils.TimeUtils;

import java.util.*;

public class StockCountService {

    public static void performStockCount(String period) {
        Scanner sc = new Scanner(System.in);
        List<Model> stockList = CSVHandler.readStock();
        List<Outlet> outlets = CSVHandler.readOutlets();

        int totalChecked = 0;
        int tallyCorrect = 0;
        int mismatches = 0;
        //String outletCode = "";

        //System.out.print("Enter Outlet Code for Stock Count: ");
        //outletCode = sc.nextLine().trim();
        System.out.println("\n=== " + period + " Stock Count ===");
        System.out.println("Date: " + TimeUtils.getDate());
        System.out.println("Time: " + TimeUtils.getTime());

        for (Model s : stockList) {
            for (Outlet o : outlets) {
                System.out.println("Outlet: " + o.getOutletCode() + " - " + o.getOutletName());
                System.out.print("Model: " + s.getModelCode() + " – Counted: ");
                int counted = Integer.parseInt(sc.nextLine());
                System.out.println("Store Record: " + s.getStock(o.getOutletCode()));

                totalChecked++;

                if (counted == s.getStock(o.getOutletCode())) {
                    System.out.println("Stock tally correct.");
                    tallyCorrect++;
                } else {
                    System.out.println("! Mismatch detected (" + Math.abs(counted - s.getStock(o.getOutletCode())) + " unit difference)");
                    mismatches++;
                }
            }
            //sc.close();
        }

        System.out.println("\nTotal Models Checked: " + totalChecked);
        System.out.println("Tally Correct: " + tallyCorrect);
        System.out.println("Mismatches: " + mismatches);

        if (mismatches > 0) {
            System.out.println("Warning: Please verify stock.");
        }
        System.out.println(period + " stock count completed.");

        //sc.close();
    }
}