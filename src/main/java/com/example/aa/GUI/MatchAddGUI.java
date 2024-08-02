package com.example.aa.GUI;

import com.example.aa.Components.DateLabelFormatter;
import com.example.aa.Components.NumberOnlyFilter;
import com.example.aa.Entities.Match;
import com.example.aa.Entities.Team;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class MatchAddGUI extends JDialog {
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel contentPane;
    private List<Match> matchList;
    private List<Team> teamList;
    private MainGUI mainGUI;
    private JComboBox<Team> selectTeamMandante;
    private JComboBox<Team> selectTeamVisitante;
    private JDatePickerImpl datePicker;
    private Elements elements = new Elements();
    private JTextField golsMandanteField;
    private JTextField golsVisitanteField;
    private boolean isUpdating = false;

    public MatchAddGUI(List<Match> matchList, List<Team> teamList, MainGUI mainGUI) {
        this.matchList = matchList;
        this.teamList = teamList;
        this.mainGUI = mainGUI;

        contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        Dimension inputSize = new Dimension(100, 25);

        JLabel jlTeamMandante = new JLabel("Time Mandante");
        selectTeamMandante = elements.createTeamsSelect(teamList);
        JLabel jlTeamVisitante = new JLabel("Time Visitante");
        selectTeamVisitante = elements.createTeamsSelect(teamList);
        JLabel jlDate = new JLabel("Data da Partida");
        JLabel jlGolsMandante = new JLabel("Gols Mandante");
        JLabel jlGolsVisitante = new JLabel("Gols Visitante");

        golsMandanteField = new JTextField();
        golsMandanteField.setPreferredSize(inputSize); // Define o tamanho fixo
        golsVisitanteField = new JTextField();
        golsVisitanteField.setPreferredSize(inputSize); // Define o tamanho fixo

        ((AbstractDocument) golsMandanteField.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        ((AbstractDocument) golsVisitanteField.getDocument()).setDocumentFilter(new NumberOnlyFilter());

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
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(jlTeamMandante, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        contentPane.add(selectTeamMandante, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPane.add(jlTeamVisitante, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        contentPane.add(selectTeamVisitante, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        contentPane.add(jlDate, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        contentPane.add(datePicker, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        contentPane.add(jlGolsMandante, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        contentPane.add(golsMandanteField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        contentPane.add(jlGolsVisitante, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        contentPane.add(golsVisitanteField, gbc);

        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(buttonPanel, gbc);

        setContentPane(contentPane);
        setTitle("Adicionar Partida");
        setModal(true);
        setSize(600, 500);
        setLocationRelativeTo(null);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        Date dataPartida = (Date) datePicker.getModel().getValue();
        Team teamMandante = (Team) selectTeamMandante.getSelectedItem();
        Team teamVisitante = (Team) selectTeamVisitante.getSelectedItem();
        Integer golsMandante = Integer.parseInt(golsMandanteField.getText());
        Integer golsVisitante = Integer.parseInt(golsVisitanteField.getText());

        Match match = new Match(dataPartida, teamMandante, teamVisitante, golsMandante, golsVisitante);

        matchList.add(match);
        mainGUI.updateMatchList(matchList);

        mainGUI.updateTeamPoints(teamMandante, teamVisitante, golsMandante, golsVisitante);
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void updateVisitanteComboBox() {
        isUpdating = true;
        Team selectedMandante = (Team) selectTeamMandante.getSelectedItem();
        selectTeamVisitante.removeAllItems();
        for (Team team : this.teamList) {
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
        for (Team team : this.teamList) {
            if (team != selectedVisitante) {
                selectTeamMandante.addItem(team);
            }
        }
        isUpdating = false;
    }
}
