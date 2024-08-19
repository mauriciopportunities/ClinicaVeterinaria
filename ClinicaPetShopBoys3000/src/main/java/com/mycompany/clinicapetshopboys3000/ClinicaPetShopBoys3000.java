package com.mycompany.clinicapetshopboys3000;

import javax.swing.*;
import java.awt.*;

public class ClinicaPetShopBoys3000 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Clínica PetShopBoys 3000");
            mainFrame.setSize(900, 700);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setLayout(new BorderLayout());
            mainFrame.setLocationRelativeTo(null);

            JPanel welcomePanel = new JPanel(new BorderLayout());
            welcomePanel.setBackground(new Color(250, 250, 250));
            JLabel welcomeLabel = new JLabel("Bienvenido a la Clínica PetShopBoys 3000", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
            welcomeLabel.setForeground(new Color(60, 63, 65));
            welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
            mainFrame.add(welcomePanel, BorderLayout.CENTER);

            JMenuBar menuBar = new JMenuBar();
            menuBar.setBackground(new Color(30, 144, 255));

            JMenu menu = new JMenu("Menú Principal");
            menu.setForeground(Color.WHITE);
            menu.setFont(new Font("Arial", Font.PLAIN, 16));

            JMenuItem pacientesItem = new JMenuItem("Gestionar Pacientes");
            pacientesItem.setBackground(new Color(30, 144, 255));
            pacientesItem.setForeground(Color.WHITE);
            pacientesItem.addActionListener(e -> abrirFormulario(new PacientesMenuForm()));

            menu.add(pacientesItem);
            menuBar.add(menu);

            mainFrame.setJMenuBar(menuBar);
            mainFrame.setVisible(true);
        });
    }

    private static void abrirFormulario(JFrame form) {
        form.setVisible(true);
    }
}
