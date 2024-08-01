package com.example.aa.GUI;

import com.example.aa.Entities.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainGUI {
    private List<Team> teamList;
    private JComboBox<Team> selectTeamMandante;
    private JComboBox<Team> selectTeamVisitante;
    private Elements elements = new Elements();
    private JTable tableClassification;

    public MainGUI(List<Team> teamList) {
        this.teamList = teamList;

        JFrame frame = new JFrame("Main GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel jlTeamMandante = new JLabel("Time Mandante");
        jlTeamMandante.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        selectTeamMandante = elements.createTeamsSelect(teamList);
        selectTeamMandante.addActionListener(e -> updateVisitanteList());

        JLabel jlTeamVisitante = new JLabel("Time Visitante");
        jlTeamVisitante.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        selectTeamVisitante = elements.createTeamsSelect(teamList);
        selectTeamVisitante.addActionListener(e -> updateMandanteList());

        panel.add(jlTeamMandante);
        panel.add(selectTeamMandante);
        panel.add(jlTeamVisitante);
        panel.add(selectTeamVisitante);

        mainPanel.add(panel, BorderLayout.NORTH);

        Object[][] obj = new Object[0][10];
        String[] columns = {"Rank", "Nome", "Pontos", "Saldo de gols", "Vitórias", "Empates", "Derrotas", "Gols Marcados", "Gols Sofridos", "Aproveitamento"};
        tableClassification = elements.createTable("Classificação", obj, columns);

        JScrollPane scrollPane = new JScrollPane(tableClassification);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu teamMenu = new JMenu("Gerenciar Times");

        JMenuItem addTeam = new JMenuItem("Adicionar Time");
        JMenuItem editTeam = new JMenuItem("Editar Time");
        JMenuItem purgeTeam = new JMenuItem("Remover Time");

        addTeam.addActionListener(e -> openAddTeamFrame());
        editTeam.addActionListener(e -> openEditTeamFrame());
        purgeTeam.addActionListener(e -> openPurgeTeamFrame());

        teamMenu.add(addTeam);
        teamMenu.add(editTeam);
        teamMenu.add(purgeTeam);
        menuBar.add(teamMenu);

        return menuBar;
    }

    private void openAddTeamFrame() {
        JDialog teamFrame = new TeamAddGUI(teamList, this);
        teamFrame.setSize(300, 200);
        teamFrame.setVisible(true);
    }

    private void openEditTeamFrame() {
        JDialog teamFrame = new TeamEditGUI(teamList, this);
        teamFrame.setSize(300, 200);
        teamFrame.setVisible(true);
    }

    private void openPurgeTeamFrame() {
        JDialog teamFrame = new TeamPurgeGUI(teamList, this);
        teamFrame.setSize(300, 200);
        teamFrame.setVisible(true);
    }

    public void updateTeamList(List<Team> updatedTeamList) {
        this.teamList = updatedTeamList;
        updateMandanteList();
        updateVisitanteList();
        updateTable();
    }

    private void updateMandanteList() {
        Team selectedVisitante = (Team) selectTeamVisitante.getSelectedItem();
        selectTeamMandante.removeAllItems();
        for (Team team : teamList) {
            if (selectedVisitante == null || !team.equals(selectedVisitante)) {
                selectTeamMandante.addItem(team);
            }
        }
    }

    private void updateVisitanteList() {
        Team selectedMandante = (Team) selectTeamMandante.getSelectedItem();
        selectTeamVisitante.removeAllItems();
        for (Team team : teamList) {
            if (selectedMandante == null || !team.equals(selectedMandante)) {
                selectTeamVisitante.addItem(team);
            }
        }
    }

    private void updateTable() {
        Object[][] data = new Object[teamList.size()][10];
        for (int i = 0; i < teamList.size(); i++) {
            Team team = teamList.get(i);
            data[i][0] = i + 1;
            data[i][1] = team.getName();
            data[i][2] = team.getPoints();
            // Complete os outros dados de acordo com os atributos da classe Team
        }
        DefaultTableModel model = (DefaultTableModel) tableClassification.getModel();
        model.setDataVector(data, new String[] {"Rank", "Nome", "Pontos", "Saldo de gols", "Vitórias", "Empates", "Derrotas", "Gols Marcados", "Gols Sofridos", "Aproveitamento"});
    }
}
