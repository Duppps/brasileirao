package com.example.aa.GUI;

import com.example.aa.Entities.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TeamEditGUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private List<Team> teamList;
    private MainGUI mainGUI;
    private JComboBox<Team> selectTeam;
    private JTextField jtTeamName;
    private JTextField jtStadiumName;
    private JTextField jtCityName;

    public TeamEditGUI(List<Team> teamList, MainGUI mainGUI) {
        this.teamList = teamList;
        this.mainGUI = mainGUI;

        setTitle("Editar Time");
        setModal(true);
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setLayout(new BorderLayout());

        JLabel lblSelectTeam = new JLabel("Selecione o Time: ");
        selectTeam = new JComboBox<>(teamList.toArray(new Team[0]));
        selectTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateFields((Team) selectTeam.getSelectedItem());
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Nome do Time: "));
        jtTeamName = new JTextField();
        inputPanel.add(jtTeamName);
        inputPanel.add(new JLabel("Nome do Estádio: "));
        jtStadiumName = new JTextField();
        inputPanel.add(jtStadiumName);
        inputPanel.add(new JLabel("Cidade: "));
        jtCityName = new JTextField();
        inputPanel.add(jtCityName);

        JPanel northPanel = new JPanel();
        northPanel.add(lblSelectTeam);
        northPanel.add(selectTeam);
        add(northPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        add(buttonPanel, BorderLayout.SOUTH);

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

        if (selectTeam.getSelectedItem() != null) {
            populateFields((Team) selectTeam.getSelectedItem());
        }
    }

    private void populateFields(Team team) {
        jtTeamName.setText(team.getName());
        jtStadiumName.setText(team.getStadium());
        jtCityName.setText(team.getCity());
    }

    private void onOK() {
        Team selectedTeam = (Team) selectTeam.getSelectedItem();
        if (selectedTeam != null) {
            selectedTeam.setName(jtTeamName.getText());
            selectedTeam.setStadium(jtStadiumName.getText());
            selectedTeam.setCity(jtCityName.getText());
            mainGUI.updateTeamList(teamList);
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
