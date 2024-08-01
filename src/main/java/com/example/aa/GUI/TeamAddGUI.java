package com.example.aa.GUI;

import com.example.aa.Entities.Team;

import javax.swing.*;
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
        setContentPane(contentPane);
        setTitle("Adicionar Time");
        setModal(true);
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

        // Call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // Call onCancel() on ESCAPE
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
