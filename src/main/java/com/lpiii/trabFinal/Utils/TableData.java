package com.lpiii.trabFinal.Utils;

import java.util.List;
import java.util.Map;

public class TableData {
    private List<String> columns; // Nomes das colunas
    private List<Map<String, Object>> rows; // Linhas, onde cada linha é um mapa de chave-valor (coluna -> valor)

    public TableData(List<String> columns, List<Map<String, Object>> rows) {
        this.columns = columns;
        this.rows = rows;
    }

    // Getters e Setters
    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }
}