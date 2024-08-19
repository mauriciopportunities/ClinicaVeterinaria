package com.mycompany.clinicapetshopboys3000;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Pacientes {
    private int idPaciente;
    private String nombrePaciente;
    private String nombreCliente;
    private String direccion;
    private String telefono;
    private String especie;
    private String raza;
    private String sexo;
    private String pelaje;
    private String fechaNacimiento;
    private boolean fallecido;
    private boolean extraviado;

    private static final String FILE_NAME = "pacientes.txt";
    private static List<Pacientes> listaDePacientes = new ArrayList<>();

    public Pacientes(int idPaciente, String nombrePaciente, String nombreCliente, String direccion, String telefono,
                     String especie, String raza, String sexo, String pelaje, String fechaNacimiento,
                     boolean fallecido, boolean extraviado) {
        this.idPaciente = idPaciente;
        this.nombrePaciente = nombrePaciente;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.telefono = telefono;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.pelaje = pelaje;
        this.fechaNacimiento = fechaNacimiento;
        this.fallecido = fallecido;
        this.extraviado = extraviado;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPelaje() {
        return pelaje;
    }

    public void setPelaje(String pelaje) {
        this.pelaje = pelaje;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isFallecido() {
        return fallecido;
    }

    public void setFallecido(boolean fallecido) {
        this.fallecido = fallecido;
    }

    public boolean isExtraviado() {
        return extraviado;
    }

    public void setExtraviado(boolean extraviado) {
        this.extraviado = extraviado;
    }

    @Override
    public String toString() {
        return idPaciente + "|" + nombrePaciente + "|" + nombreCliente + "|" + direccion + "|" + telefono + "|" +
               especie + "|" + raza + "|" + sexo + "|" + pelaje + "|" + fechaNacimiento + "|" +
               fallecido + "|" + extraviado;
    }

    public static void agregarPaciente(Pacientes paciente) {
        listaDePacientes.add(paciente);
        guardarPacientesEnArchivo();
    }

    public static void eliminarPaciente(int idPaciente) {
        listaDePacientes.removeIf(p -> p.getIdPaciente() == idPaciente);
        guardarPacientesEnArchivo();
    }

    public static Pacientes buscarPacientePorNombreCliente(String nombreCliente) {
        for (Pacientes paciente : listaDePacientes) {
            if (paciente.getNombreCliente().equalsIgnoreCase(nombreCliente)) {
                return paciente;
            }
        }
        return null;
    }

    public static void editarPaciente(int idPaciente, Pacientes pacienteEditado) {
        for (int i = 0; i < listaDePacientes.size(); i++) {
            if (listaDePacientes.get(i).getIdPaciente() == idPaciente) {
                listaDePacientes.set(i, pacienteEditado);
                guardarPacientesEnArchivo();
                break;
            }
        }
    }

    public static void cargarPacientesDesdeArchivo() {
        listaDePacientes.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] partes = line.split("\\|");
                if (partes.length == 12) {
                    Pacientes paciente = new Pacientes(
                            Integer.parseInt(partes[0]),
                            partes[1],
                            partes[2],
                            partes[3],
                            partes[4],
                            partes[5],
                            partes[6],
                            partes[7],
                            partes[8],
                            partes[9],
                            Boolean.parseBoolean(partes[10]),
                            Boolean.parseBoolean(partes[11])
                    );
                    listaDePacientes.add(paciente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarPacientesEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Pacientes paciente : listaDePacientes) {
                bw.write(paciente.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Pacientes> getListaDePacientes() {
        return listaDePacientes;
    }
}




