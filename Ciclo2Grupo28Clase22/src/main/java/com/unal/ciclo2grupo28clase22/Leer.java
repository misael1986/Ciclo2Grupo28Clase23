/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unal.ciclo2grupo28clase22;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Usuario
 */
public class Leer extends JFrame implements ActionListener {

    JComboBox Cgrupo;
    JButton leer;
    JTextArea areaImpresion;

    public Leer() {
        this.setSize(500, 500);
        this.setTitle("Tripulantes por Grupo");
        this.setLayout(null);
        
        Cgrupo= new JComboBox();
        for (int g = 1; g <= 68; g++) {
            Cgrupo.addItem(g);
        }
        Cgrupo.setBounds(40, 40, 80, 30);
        
                
        leer= new JButton("Consultar");
        leer.addActionListener(this);
        leer.setBounds(130, 40, 100, 30);
        
        areaImpresion= new JTextArea();
        areaImpresion.setBounds(10, 80, 460, 400);
        
        this.add(Cgrupo);
        this.add(leer);
        this.add(areaImpresion);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GuardarEnBD lectura= new GuardarEnBD();
        int valorGrupo=Integer.parseInt(Cgrupo.getSelectedItem().toString());
        areaImpresion.setText(""+lectura.leerTripulantes(valorGrupo));
    }

}
