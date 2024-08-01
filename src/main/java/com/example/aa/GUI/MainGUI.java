package com.example.aa.GUI;

import com.example.aa.Components.DateLabelFormatter;
import com.example.aa.Entities.Team;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Properties;

public class MainGUI {
    private List<Team> teamList;
    private JComboBox<Team> selectTeamMandante;
    private JComboBox<Team> selectTeamVisitante;
    private JTextField golsMandanteField;
    private JTextField golsVisitanteField;
    private JDatePicker datePicker;
    private Elements elements = new Elements();
    private JTable tableClassification;
    private boolean isUpdating = false;

    public MainGUI(List<Team> teamList) {
        this.teamList = teamList;

        JFrame frame = new JFrame("Main GUI");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);

        JLabel jlTeamMandante = new JLabel("Time Mandante");
        selectTeamMandante = elements.createTeamsSelect(teamList);
        JLabel jlTeamVisitante = new JLabel("Time Visitante");
        selectTeamVisitante = elements.createTeamsSelect(teamList);
        JLabel jlDate = new JLabel("Data da Partida");
        JLabel jlGolsMandante = new JLabel("Gols Mandante");
        JLabel jlGolsVisitante = new JLabel("Gols Visitante");

        golsMandanteField = new JTextField(10);
        golsVisitanteField = new JTextField(10);

        selectTeamMandante.addItemListener(e -> {
            if (!isUpdating) {
                updateVisitanteComboBox();
            }
        });
        selectTeamVisitante.addItemListener(e -> {
            if (!isUpdating) {
                updateMandanteComboBox();
            }
        });

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Hoje");
        p.put("text.month", "Mês");
        p.put("text.year", "Ano");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        panel.add(jlTeamMandante);
        panel.add(selectTeamMandante);
        panel.add(jlTeamVisitante);
        panel.add(selectTeamVisitante);
        panel.add(jlDate);
        panel.add((Component) datePicker);
        panel.add(jlGolsMandante);
        panel.add(golsMandanteField);
        panel.add(jlGolsVisitante);
        panel.add(golsVisitanteField);

        frame.add(panel, BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona margem


        Object[][] obj = new Object[0][10];
        String[] columns = {"Rank", "Nome", "Pontos", "Saldo de gols", "Vitórias", "Empates", "Derrotas", "Gols Marcados", "Gols Sofridos", "Aproveitamento"};
        tableClassification = elements.createTable("Classificação", obj, columns);

        JScrollPane scrollPane = new JScrollPane(tableClassification);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
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
        selectTeamMandante.removeAllItems();
        for (Team team : teamList) {
            selectTeamMandante.addItem(team);
        }
        updateTable();
    }

    private void updateTable() {
        Object[][] data = new Object[teamList.size()][10];
        for (int i = 0; i < teamList.size(); i++) {
            Team team = teamList.get(i);
            data[i][0] = i + 1;
            data[i][1] = team.getName();
            data[i][2] = team.getPoints();
            data[i][3] = team.getGoalDifference();
            data[i][4] = team.getWins();
            data[i][5] = team.getDraws();
            data[i][6] = team.getLosses();
            data[i][7] = team.getGoalsScored();
            data[i][8] = team.getGoalsConceded();
            data[i][9] = team.getPerformance();
        }
        DefaultTableModel model = (DefaultTableModel) tableClassification.getModel();
        model.setDataVector(data, new String[]{"Rank", "Nome", "Pontos", "Saldo de gols", "Vitórias", "Empates", "Derrotas", "Gols Marcados", "Gols Sofridos", "Aproveitamento"});
    }

    private void updateVisitanteComboBox() {
        isUpdating = true;
        Team selectedMandante = (Team) selectTeamMandante.getSelectedItem();
        selectTeamVisitante.removeAllItems();
        for (Team team : teamList) {
            if (team != selectedMandante) {
                selectTeamVisitante.addItem(team);
            }
        }
        isUpdating = false;
    }

    private void updateMandanteComboBox() {
        isUpdating = true;
        Team selectedVisitante = (Team) selectTeamVisitante.getSelectedItem();
        selectTeamMandante.removeAllItems();
        for (Team team : teamList) {
            if (team != selectedVisitante) {
                selectTeamMandante.addItem(team);
            }
        }
        isUpdating = false;
    }
}
