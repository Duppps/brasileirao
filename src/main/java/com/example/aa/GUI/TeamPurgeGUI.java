package com.example.aa.GUI;

import com.example.aa.Entities.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TeamPurgeGUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private List<Team> teamList;
    private MainGUI mainGUI;
    private JComboBox<Team> selectTeam;

    public TeamPurgeGUI(List<Team> teamList, MainGUI mainGUI) {
        this.teamList = teamList;
        this.mainGUI = mainGUI;

        setTitle("Remover Time");
        setModal(true);
        setSize(300, 200);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JLabel lblSelectTeam = new JLabel("Time: ");
        selectTeam = new JComboBox<>(teamList.toArray(new Team[0]));

        JPanel northPanel = new JPanel();
        northPanel.add(lblSelectTeam);
        northPanel.add(selectTeam);
        contentPane.add(northPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonOK = new JButton("Remover");
        buttonCancel = new JButton("Cancelar");
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

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
        Team selectedTeam = (Team) selectTeam.getSelectedItem();
        if (selectedTeam != null) {
            teamList.remove(selectedTeam);
            mainGUI.updateTeamList(teamList);
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
