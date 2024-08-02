package com.example.aa.GUI;

import com.example.aa.Entities.Match;
import com.example.aa.Entities.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TeamMatchesListGUI extends JDialog {
    private JTable tableMatches;
    private JComboBox<Team> selectTeam;
    private Elements elements = new Elements();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public TeamMatchesListGUI(List<Team> teamList, List<Match> matchList) {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel jlTeam = new JLabel("Selecionar Time:");
        selectTeam = elements.createTeamsSelect(teamList);
        selectTeam.addActionListener(e -> updateTable(matchList, (Team) selectTeam.getSelectedItem()));

        JPanel panel = new JPanel();
        panel.add(jlTeam);
        panel.add(selectTeam);
        add(panel, BorderLayout.NORTH);

        tableMatches = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableMatches);
        add(scrollPane, BorderLayout.CENTER);

        updateTable(matchList, null);
    }

    private void updateTable(List<Match> matchList, Team selectedTeam) {
        Object[][] data = matchList.stream()
                .filter(match -> selectedTeam == null ||
                        match.getTimeMandante().equals(selectedTeam) ||
                        match.getTimeVisitante().equals(selectedTeam))
                .map(match -> new Object[]{
                        formatDate(match.getDataPartida()),
                        match.getTimeMandante(),
                        match.getGolMandante(),
                        match.getTimeVisitante().getName(),
                        match.getGolVisitante()
                })
                .toArray(Object[][]::new);

        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Data", "Time Mandante", "Gols Time Mandante", "Visitante", "Gols Visitante"});
        tableMatches.setModel(model);
    }

    private String formatDate(Date date) {
        return dateFormat.format(date);
    }
}
