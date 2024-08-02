package com.example.aa.GUI;

import com.example.aa.Entities.Match;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MatchListGUI extends JDialog {
    private JTable tableMatches;
    private Elements elements = new Elements();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public MatchListGUI(List<Match> matchList) {
        setTitle("Listar Partidas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Object[][] obj = new Object[matchList.size()][5];
        String[] columns = {"Data", "Time Mandante", "Time Visitante", "Gols Time Mandante", "Gols Time Visitante"};
        updateTable(matchList);

        JScrollPane scrollPane = new JScrollPane(tableMatches);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void updateTable(List<Match> matchList) {
        Object[][] data = new Object[matchList.size()][5];
        for (int i = 0; i < matchList.size(); i++) {
            Match match = matchList.get(i);
            data[i][0] = formatDate(match.getDataPartida());
            data[i][1] = match.getTimeMandante();
            data[i][2] = match.getTimeVisitante();
            data[i][3] = match.getGolMandante();
            data[i][4] = match.getGolVisitante();
        }

        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Data", "Time Mandante", "Time Visitante", "Gols Time Mandante", "Gols Time Visitante"});
        tableMatches = new JTable(model);
    }

    private String formatDate(Date date) {
        return dateFormat.format(date);
    }
}
