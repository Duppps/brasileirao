package com.example.aa.GUI;

import com.example.aa.Entities.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class TeamAddGUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField jtTeamName;
    private JTextField jtStadiumName;
    private JTextField jtCityName;
    private List<Team> teamList;
    private MainGUI mainGUI;

    public TeamAddGUI(List<Team> teamList, MainGUI mainGUI) {
        this.teamList = teamList;
        this.mainGUI = mainGUI;

        // Initialize the contentPane and layout
        setTitle("Adicionar Time");
        setModal(true);
        setSize(350, 250);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(4, 2));

        jtTeamName = new JTextField();
        jtStadiumName = new JTextField();
        jtCityName = new JTextField();

        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancelar");

        contentPane.add(new JLabel("Nome:"));
        contentPane.add(jtTeamName);
        contentPane.add(new JLabel("Estádio:"));
        contentPane.add(jtStadiumName);
        contentPane.add(new JLabel("Cidade:"));
        contentPane.add(jtCityName);
        contentPane.add(buttonOK);
        contentPane.add(buttonCancel);

        setContentPane(contentPane);

        getRootPane().setDefaultButton(buttonOK);

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
        Team team = new Team(jtTeamName.getText(), jtStadiumName.getText(), jtCityName.getText());
        teamList.add(team);
        mainGUI.updateTeamList(teamList);
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
