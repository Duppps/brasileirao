package com.example.aa.Components;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSV {
    //https://opencsv.sourceforge.net/ usar isso

    public void run(String file) {
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ",";
        try {

            br = new BufferedReader(new FileReader(file));
            while ((linha = br.readLine()) != null) {

                String[] pais = linha.split(csvDivisor);

                System.out.println("País [code= " + pais[pais.length-2]
                        + " , name=" + pais[pais.length-1] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
