package com.codigo;

import javax.swing.*;
import java.awt.event.*;

public class FormularioCarreras extends JFrame {

    JComboBox<String> comboEscuelas;
    JButton botonConsultar;
    JTextArea areaResultados;

    public FormularioCarreras() {

        setTitle("Consulta de Carreras UASD");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Seleccione una escuela:");
        label.setBounds(30, 30, 200, 30);
        add(label);

        comboEscuelas = new JComboBox<>();
        comboEscuelas.setBounds(30, 70, 200, 30);
        add(comboEscuelas);

        botonConsultar = new JButton("Consultar Carreras");
        botonConsultar.setBounds(250, 70, 180, 30);
        add(botonConsultar);

        areaResultados = new JTextArea();
        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBounds(30, 130, 400, 180);
        add(scroll);

        App.cargarEscuelas(comboEscuelas);

        botonConsultar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                App.consultarCarreras(areaResultados,comboEscuelas);
            }
        });
    }
}
