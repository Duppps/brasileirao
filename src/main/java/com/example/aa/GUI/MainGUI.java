package com.example.aa.GUI;

import com.example.aa.Components.ReadCSV;
import com.example.aa.Entities.Match;
import com.example.aa.Entities.Team;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainGUI {
    private List<Team> teamList;
    private List<Match> matchList;
    private JComboBox<Team> selectTeamMandante;
    private JComboBox<Team> selectTeamVisitante;
    private JDatePicker datePicker;
    private Elements elements = new Elements();
    private JTable tableClassification;
    private JFrame mainGUI;

    public MainGUI(List<Team> teamList, List<Match> matchList) throws FileNotFoundException {
        this.teamList = teamList;
        this.matchList = matchList;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Integer> csvColumns = Arrays.asList(1, 3, 7, 8, 17, 18);
        ReadCSV csvReader = new ReadCSV();
        Map<Integer, List<String>> csvData = csvReader.readColumns(csvColumns);

        JFrame frame = new JFrame("Main GUI");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);

        frame.add(panel, BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        Object[][] obj = new Object[0][10];
        String[] columns = {"Rank", "Nome", "Pontos", "Saldo de gols", "Vitórias", "Empates", "Derrotas", "Gols Marcados", "Gols Sofridos", "Aproveitamento"};
        tableClassification = elements.createTable("Classificação", obj, columns);

        JScrollPane scrollPane = new JScrollPane(tableClassification);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        this.mainGUI = frame;

        csvData.forEach((i, a) -> {
            String teamName = a.get(2);
            boolean teamExists = teamList.stream().anyMatch(team -> team.getName().equals(teamName));

            if (!teamExists) {
                Team newTeam = new Team(a.get(2), a.get(1), "empty");
                teamList.add(newTeam);
            }
        });

        csvData.forEach((i, d) -> {
            Team timeMandante = teamList.stream().filter(team -> team.getName().equals(d.get(2))).findFirst().orElse(null);
            Team timeVisitante = teamList.stream().filter(team -> team.getName().equals(d.get(3))).findFirst().orElse(null);

            try {
                Date matchDate = dateFormat.parse(d.get(0));
                if (timeMandante != null && timeVisitante != null) {
                    Match match = new Match(matchDate, timeMandante, timeVisitante, Integer.valueOf(d.get(4)), Integer.valueOf(d.get(5)));
                    matchList.add(match);

                    updateTeamPoints(timeMandante, timeVisitante, Integer.valueOf(d.get(4)), Integer.valueOf(d.get(5)));
                }
            } catch (ParseException e) {
                System.out.println("Erro ao converter a data: " + e.getMessage());
            }
        });

        updateTable();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu teamMenu = new JMenu("Gerenciar Times");
        JMenu matchesMenu = new JMenu("Gerenciar Partidas");

        JMenuItem addMatch = new JMenuItem("Adicionar Partida");
        JMenuItem listMatches = new JMenuItem("Listar Partidas");

        JMenuItem addTeam = new JMenuItem("Adicionar Time");
        JMenuItem editTeam = new JMenuItem("Editar Time");
        JMenuItem purgeTeam = new JMenuItem("Remover Time");
        JMenuItem listTeamsMatches = new JMenuItem("Listar Partidas");

        addTeam.addActionListener(e -> openAddTeamFrame());
        editTeam.addActionListener(e -> openEditTeamFrame());
        purgeTeam.addActionListener(e -> openPurgeTeamFrame());
        listTeamsMatches.addActionListener(e -> openListTeamsFrame());

        addMatch.addActionListener(e -> openAddMatchesFrame());
        listMatches.addActionListener(e -> openListMatchesFrame());

        teamMenu.add(addTeam);
        teamMenu.add(editTeam);
        teamMenu.add(purgeTeam);
        teamMenu.add(listTeamsMatches);

        matchesMenu.add(addMatch);
        matchesMenu.add(listMatches);

        menuBar.add(teamMenu);
        menuBar.add(matchesMenu);

        return menuBar;
    }

    private void openAddTeamFrame() {
        JDialog teamFrame = new TeamAddGUI(teamList, this);
        teamFrame.setSize(300, 200);
        teamFrame.setLocationRelativeTo(mainGUI);
        teamFrame.setVisible(true);
    }

    private void openEditTeamFrame() {
        JDialog teamFrame = new TeamEditGUI(teamList, this);
        teamFrame.setSize(300, 200);
        teamFrame.setLocationRelativeTo(mainGUI);
        teamFrame.setVisible(true);
    }

    private void openPurgeTeamFrame() {
        JDialog teamFrame = new TeamPurgeGUI(teamList, this);
        teamFrame.setSize(300, 200);
        teamFrame.setLocationRelativeTo(mainGUI);
        teamFrame.setVisible(true);
    }

    private void openListTeamsFrame() {
        JDialog teamFrame = new TeamMatchesListGUI(teamList, matchList);
        teamFrame.setTitle("Lista de Partidas");
        teamFrame.setSize(300, 200);
        teamFrame.setLocationRelativeTo(mainGUI);
        teamFrame.setVisible(true);
    }

    private void openAddMatchesFrame() {
        JDialog teamFrame = new MatchAddGUI(matchList, teamList, this);
        teamFrame.setSize(450, 330);
        teamFrame.setLocationRelativeTo(mainGUI);
        teamFrame.setVisible(true);
    }

    private void openListMatchesFrame() {
        JDialog teamFrame = new MatchListGUI(matchList);
        teamFrame.setSize(550, 450);
        teamFrame.setLocationRelativeTo(mainGUI);
        teamFrame.setVisible(true);
    }

    public void updateTeamList(List<Team> updatedTeamList) {
        this.teamList = updatedTeamList;
        updateTable();
    }

    public void updateMatchList(List<Match> matches) {
        this.matchList = matches;
        updateTable();
    }

    public void updateTable() {
        teamList.sort((team1, team2) -> {
            int pointsComparison = team2.getPoints().compareTo(team1.getPoints());
            if (pointsComparison != 0) {
                return pointsComparison;
            }
            return team2.getGoalDifference().compareTo(team1.getGoalDifference());
        });

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
            data[i][9] = team.getPerformance() + "%";
        }

        DefaultTableModel model = (DefaultTableModel) tableClassification.getModel();
        model.setDataVector(data, new String[]{"Rank", "Nome", "Pontos", "Saldo de gols", "Vitórias", "Empates", "Derrotas", "Gols Marcados", "Gols Sofridos", "Aproveitamento"});
    }

    public void updateTeamPoints(Team teamMandante, Team teamVisitante, Integer golsMandante, Integer golsVisitante) {
        if (teamMandante == null || teamVisitante == null) {
            return;
        }

        teamMandante.updateGoals(golsMandante, golsVisitante);
        teamVisitante.updateGoals(golsVisitante, golsMandante);

        updateTable();
    }
}