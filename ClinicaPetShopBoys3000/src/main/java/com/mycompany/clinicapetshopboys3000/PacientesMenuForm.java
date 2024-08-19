package com.mycompany.clinicapetshopboys3000;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PacientesMenuForm extends JFrame {
    // Componentes para Agregar Paciente
    private JTextField nombrePacienteFieldAgregar;
    private JTextField nombreClienteFieldAgregar;
    private JTextField direccionFieldAgregar;
    private JTextField telefonoFieldAgregar;
    private JTextField especieFieldAgregar;
    private JTextField razaFieldAgregar;
    private JTextField sexoFieldAgregar;
    private JTextField pelajeFieldAgregar;
    private JTextField fechaNacimientoFieldAgregar;
    private JCheckBox fallecidoCheckBoxAgregar;
    private JCheckBox extraviadoCheckBoxAgregar;

    // Componentes para Editar Paciente
    private JTextField nombreClienteFieldEditarBuscar;
    private JTextField nombrePacienteFieldEditar;
    private JTextField nombreClienteFieldEditar;
    private JTextField direccionFieldEditar;
    private JTextField telefonoFieldEditar;
    private JTextField especieFieldEditar;
    private JTextField razaFieldEditar;
    private JTextField sexoFieldEditar;
    private JTextField pelajeFieldEditar;
    private JTextField fechaNacimientoFieldEditar;
    private JCheckBox fallecidoCheckBoxEditar;
    private JCheckBox extraviadoCheckBoxEditar;

    // Componentes para Buscar y Eliminar
    private JTextField nombreClienteFieldBuscar;
    private JTextField nombreClienteFieldEliminar;

    // Lista de Pacientes
    private JList<String> pacientesList;
    private DefaultListModel<String> pacientesListModel;

    public PacientesMenuForm() {
        setTitle("Menú de Gestión de Pacientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración del panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 192, 203)); // Color rosado pastel

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Agregar Paciente", createAgregarPacientePanel());
        tabbedPane.addTab("Listar Pacientes", createListarPacientesPanel());
        tabbedPane.addTab("Eliminar Paciente", createEliminarPacientePanel());
        tabbedPane.addTab("Buscar Paciente", createBuscarPacientePanel());
        tabbedPane.addTab("Editar Paciente", createEditarPacientePanel());

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        JButton volverButton = new JButton("Volver");
        volverButton.addActionListener(e -> dispose());
        volverButton.setBackground(Color.LIGHT_GRAY);
        volverButton.setForeground(Color.BLACK);
        volverButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(volverButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createAgregarPacientePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 192, 203)); // Color rosado pastel

        JPanel formPanel = new JPanel(new GridLayout(12, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(255, 192, 203)); // Color rosado pastel

        formPanel.add(new JLabel("Nombre Paciente:"));
        nombrePacienteFieldAgregar = new JTextField();
        formPanel.add(nombrePacienteFieldAgregar);

        formPanel.add(new JLabel("Nombre Cliente:"));
        nombreClienteFieldAgregar = new JTextField();
        formPanel.add(nombreClienteFieldAgregar);

        formPanel.add(new JLabel("Dirección:"));
        direccionFieldAgregar = new JTextField();
        formPanel.add(direccionFieldAgregar);

        formPanel.add(new JLabel("Teléfono:"));
        telefonoFieldAgregar = new JTextField();
        formPanel.add(telefonoFieldAgregar);

        formPanel.add(new JLabel("Especie:"));
        especieFieldAgregar = new JTextField();
        formPanel.add(especieFieldAgregar);

        formPanel.add(new JLabel("Raza:"));
        razaFieldAgregar = new JTextField();
        formPanel.add(razaFieldAgregar);

        formPanel.add(new JLabel("Sexo:"));
        sexoFieldAgregar = new JTextField();
        formPanel.add(sexoFieldAgregar);

        formPanel.add(new JLabel("Pelaje:"));
        pelajeFieldAgregar = new JTextField();
        formPanel.add(pelajeFieldAgregar);

        formPanel.add(new JLabel("Fecha de Nacimiento:"));
        fechaNacimientoFieldAgregar = new JTextField();
        formPanel.add(fechaNacimientoFieldAgregar);

        formPanel.add(new JLabel("Fallecido:"));
        fallecidoCheckBoxAgregar = new JCheckBox();
        formPanel.add(fallecidoCheckBoxAgregar);

        formPanel.add(new JLabel("Extraviado:"));
        extraviadoCheckBoxAgregar = new JCheckBox();
        formPanel.add(extraviadoCheckBoxAgregar);

        JButton agregarButton = new JButton("Agregar Paciente");
        agregarButton.setBackground(new Color(0, 255, 0)); // Verde brillante
        agregarButton.setForeground(Color.BLACK);
        agregarButton.setFocusPainted(false);
        agregarButton.addActionListener(e -> agregarPaciente());

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(agregarButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createListarPacientesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 192, 203)); // Color rosado pastel

        pacientesListModel = new DefaultListModel<>();
        pacientesList = new JList<>(pacientesListModel);
        JScrollPane scrollPane = new JScrollPane(pacientesList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Actualizar Lista");
        refreshButton.setBackground(new Color(255, 165, 0)); // Naranja brillante
        refreshButton.setForeground(Color.BLACK);
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> actualizarListaPacientes());

        panel.add(refreshButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createEliminarPacientePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 192, 203)); // Color rosado pastel

        JPanel formPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(255, 192, 203)); // Color rosado pastel

        formPanel.add(new JLabel("Nombre del Cliente:"));
        nombreClienteFieldEliminar = new JTextField();
        formPanel.add(nombreClienteFieldEliminar);

        JButton eliminarButton = new JButton("Eliminar Paciente");
        eliminarButton.setBackground(new Color(255, 0, 0)); // Rojo brillante
        eliminarButton.setForeground(Color.BLACK);
        eliminarButton.setFocusPainted(false);
        eliminarButton.addActionListener(e -> eliminarPaciente());

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(eliminarButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createBuscarPacientePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 192, 203)); // Color rosado pastel

        JPanel formPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(255, 192, 203)); // Color rosado pastel

        formPanel.add(new JLabel("Nombre del Cliente:"));
        nombreClienteFieldBuscar = new JTextField();
        formPanel.add(nombreClienteFieldBuscar);

        JButton buscarButton = new JButton("Buscar Paciente");
        buscarButton.setBackground(new Color(0, 0, 255)); // Azul brillante
        buscarButton.setForeground(Color.BLACK);
        buscarButton.setFocusPainted(false);
        buscarButton.addActionListener(e -> buscarPaciente());

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buscarButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createEditarPacientePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 192, 203)); // Color rosado pastel

        JPanel formPanel = new JPanel(new GridLayout(13, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(255, 192, 203)); // Color rosado pastel

        formPanel.add(new JLabel("Nombre del Cliente a Editar:"));
        nombreClienteFieldEditarBuscar = new JTextField();
        formPanel.add(nombreClienteFieldEditarBuscar);

        formPanel.add(new JLabel("Nombre Paciente:"));
        nombrePacienteFieldEditar = new JTextField();
        formPanel.add(nombrePacienteFieldEditar);

        formPanel.add(new JLabel("Nombre Cliente:"));
        nombreClienteFieldEditar = new JTextField();
        formPanel.add(nombreClienteFieldEditar);

        formPanel.add(new JLabel("Dirección:"));
        direccionFieldEditar = new JTextField();
        formPanel.add(direccionFieldEditar);

        formPanel.add(new JLabel("Teléfono:"));
        telefonoFieldEditar = new JTextField();
        formPanel.add(telefonoFieldEditar);

        formPanel.add(new JLabel("Especie:"));
        especieFieldEditar = new JTextField();
        formPanel.add(especieFieldEditar);

        formPanel.add(new JLabel("Raza:"));
        razaFieldEditar = new JTextField();
        formPanel.add(razaFieldEditar);

        formPanel.add(new JLabel("Sexo:"));
        sexoFieldEditar = new JTextField();
        formPanel.add(sexoFieldEditar);

        formPanel.add(new JLabel("Pelaje:"));
        pelajeFieldEditar = new JTextField();
        formPanel.add(pelajeFieldEditar);

        formPanel.add(new JLabel("Fecha de Nacimiento:"));
        fechaNacimientoFieldEditar = new JTextField();
        formPanel.add(fechaNacimientoFieldEditar);

        formPanel.add(new JLabel("Fallecido:"));
        fallecidoCheckBoxEditar = new JCheckBox();
        formPanel.add(fallecidoCheckBoxEditar);

        formPanel.add(new JLabel("Extraviado:"));
        extraviadoCheckBoxEditar = new JCheckBox();
        formPanel.add(extraviadoCheckBoxEditar);

        JButton editarButton = new JButton("Editar Paciente");
        editarButton.setBackground(new Color(0, 255, 255)); // Cian brillante
        editarButton.setForeground(Color.BLACK);
        editarButton.setFocusPainted(false);
        editarButton.addActionListener(e -> editarPaciente());

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(editarButton, BorderLayout.SOUTH);
        return panel;
    }

    private void agregarPaciente() {
        String nombrePaciente = nombrePacienteFieldAgregar.getText();
        String nombreCliente = nombreClienteFieldAgregar.getText();
        String direccion = direccionFieldAgregar.getText();
        String telefono = telefonoFieldAgregar.getText();
        String especie = especieFieldAgregar.getText();
        String raza = razaFieldAgregar.getText();
        String sexo = sexoFieldAgregar.getText();
        String pelaje = pelajeFieldAgregar.getText();
        String fechaNacimiento = fechaNacimientoFieldAgregar.getText();
        boolean fallecido = fallecidoCheckBoxAgregar.isSelected();
        boolean extraviado = extraviadoCheckBoxAgregar.isSelected();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pacientes.txt", true))) {
            writer.write(String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%b|%b%n",
                    nombrePaciente, nombreCliente, direccion, telefono, especie, raza, sexo, pelaje, fechaNacimiento, fallecido, extraviado));
            JOptionPane.showMessageDialog(this, "Paciente agregado con éxito.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar paciente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarListaPacientes() {
        pacientesListModel.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("pacientes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split("\\|");
                String nombrePaciente = datos[0];
                String nombreCliente = datos[1];
                pacientesListModel.addElement(nombrePaciente + "(" + nombreCliente + ")");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer pacientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPaciente() {
        String nombreCliente = nombreClienteFieldEliminar.getText();
        List<String> pacientes = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("pacientes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split("\\|");
                if (!datos[1].equals(nombreCliente)) {
                    pacientes.add(line);
                } else {
                    encontrado = true;
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("pacientes.txt"))) {
                for (String paciente : pacientes) {
                    writer.write(paciente);
                    writer.newLine();
                }
            }

            if (encontrado) {
                JOptionPane.showMessageDialog(this, "Paciente eliminado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "Paciente no encontrado.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar paciente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPaciente() {
        String nombreCliente = nombreClienteFieldBuscar.getText();
        try (BufferedReader reader = new BufferedReader(new FileReader("pacientes.txt"))) {
            String line;
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split("\\|");
                if (datos[1].equals(nombreCliente)) {
                    JOptionPane.showMessageDialog(this, "Paciente encontrado: " + line);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Paciente no encontrado.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar paciente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarPaciente() {
        String nombreClienteBuscar = nombreClienteFieldEditarBuscar.getText();
        String nombrePaciente = nombrePacienteFieldEditar.getText();
        String nombreCliente = nombreClienteFieldEditar.getText();
        String direccion = direccionFieldEditar.getText();
        String telefono = telefonoFieldEditar.getText();
        String especie = especieFieldEditar.getText();
        String raza = razaFieldEditar.getText();
        String sexo = sexoFieldEditar.getText();
        String pelaje = pelajeFieldEditar.getText();
        String fechaNacimiento = fechaNacimientoFieldEditar.getText();
        boolean fallecido = fallecidoCheckBoxEditar.isSelected();
        boolean extraviado = extraviadoCheckBoxEditar.isSelected();
        List<String> pacientes = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("pacientes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split("\\|");
                if (datos[1].equals(nombreClienteBuscar)) {
                    pacientes.add(String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%b|%b",
                            nombrePaciente, nombreCliente, direccion, telefono, especie, raza, sexo, pelaje, fechaNacimiento, fallecido, extraviado));
                    encontrado = true;
                } else {
                    pacientes.add(line);
                }
            }

            if (encontrado) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("pacientes.txt"))) {
                    for (String paciente : pacientes) {
                        writer.write(paciente);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(this, "Paciente editado con éxito.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Paciente no encontrado.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al editar paciente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PacientesMenuForm().setVisible(true);
        });
    }
}



    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

