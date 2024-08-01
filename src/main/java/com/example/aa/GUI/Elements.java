package com.example.aa.GUI;

import com.example.aa.Entities.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.List;

public class Elements {
    public JComboBox<Team> createTeamsSelect(List<Team> list) {
        JComboBox<Team> select = new JComboBox<>();
        for (Team team : list) {
            select.addItem(team);
        }
        return select;
    }

    public JTable createTable(String name, Object[][] data, String[] columns) {
        DefaultTableModel model = new DefaultTableModel(data, columns);

        return new JTable(model);
    }
}
