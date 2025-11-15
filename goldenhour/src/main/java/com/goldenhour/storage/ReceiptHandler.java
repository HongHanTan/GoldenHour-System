package com.goldenhour.storage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReceiptHandler {
    public static void appendReceipt(String receiptText) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        File dir = new File("data/receipts");
        if (!dir.exists()) dir.mkdirs();
        String filename = "data/receipts/receipts_" + date + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(receiptText);
            bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing receipt file.");
        }
    }
}
