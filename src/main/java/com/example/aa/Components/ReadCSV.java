package com.example.aa.Components;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadCSV {

    public Map<Integer, List<String>> readColumns(List<Integer> columns) throws FileNotFoundException {
        String filePath = "src/main/resources/brasileirao_2022.csv";
        Map<Integer, List<String>> data = new HashMap<>();

        try {
            FileReader fileReader = new FileReader(filePath);
            CSVReader csvReader = new CSVReader(fileReader);

            List<String[]> rows = csvReader.readAll();

            if (!rows.isEmpty()) {
                rows.remove(0);
            }

            for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
                String[] row = rows.get(rowIndex);
                List<String> selectedColumns = columns.stream()
                        .filter(columnIndex -> columnIndex < row.length)
                        .map(columnIndex -> row[columnIndex])
                        .collect(Collectors.toList());
                data.put(rowIndex, selectedColumns);
            }

        } catch (IOException | CsvException e) {
            System.out.println(e);
        }

        return data;
    }

}