package com.example.aa;

import com.example.aa.Components.ReadCSV;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> columns = Arrays.asList(1, 2, 3, 4);
        ReadCSV readCSV = new ReadCSV();

        Map<Integer, List<String>> data = readCSV.readCSV(columns);

        data.forEach((rowIndex, rowValues) -> {
            System.out.println("Linha " + rowIndex + ": " + rowValues);
        });
    }
}
