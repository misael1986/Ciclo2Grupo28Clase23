/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unal.ciclo2grupo28clase22;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class Actualizar extends JFrame implements ActionListener {

    JLabel Lbusqueda;
    JComboBox Clistado;
    JButton buscar;

    JLabel Lnombre;
    JLabel Lapellido;
    JLabel Lgrupo;

    JTextField Tnombre;
    JTextField Tapellido;

    JComboBox Cgrupo;

    JButton actualizar;

    Actualizar() {
        this.setSize(400, 400);
        this.setLayout(null);
        this.setTitle("Actualizar Tripulante");

        Lbusqueda = new JLabel("Buscar:");

        GuardarEnBD ids = new GuardarEnBD();
        Integer idsTripulantes[] = ids.llenarCombo();
        Clistado = new JComboBox(idsTripulantes);
        buscar = new JButton("Buscar");

        Lnombre = new JLabel("Nombres:");
        Lapellido = new JLabel("Apellidos:");
        Lgrupo = new JLabel("Grupo:");

        Tnombre = new JTextField();
        Tapellido = new JTextField();

        Cgrupo = new JComboBox();
        for (int g = 1; g <= 68; g++) {
            Cgrupo.addItem(g);
        }

        actualizar = new JButton("Actualizar");
        Font fuente = new Font("Tahoma", Font.PLAIN, 14);

        Lbusqueda.setBounds(40, 10, 60, 30);
        Lbusqueda.setFont(fuente);
        Clistado.setBounds(110, 10, 100, 30);
        Clistado.setFont(fuente);
        buscar.setBounds(226, 5, 110, 40);
        buscar.setFont(fuente);

        Lnombre.setBounds(40, 100, 80, 30);
        Lnombre.setFont(fuente);
        Tnombre.setBounds(140, 100, 200, 30);
        Tnombre.setFont(fuente);

        Lapellido.setBounds(40, 140, 80, 30);
        Lapellido.setFont(fuente);
        Tapellido.setBounds(140, 140, 200, 30);
        Tapellido.setFont(fuente);

        Lgrupo.setBounds(40, 180, 80, 30);
        Lgrupo.setFont(fuente);
        Cgrupo.setBounds(140, 180, 100, 30);
        Cgrupo.setFont(fuente);

        Font fuenteB = new Font("Tahoma", Font.BOLD, 17);//Objeto tipo Fuente Tipografica
        Color colorB = new Color(0, 140, 255);// Color basado en RGB
        actualizar.setBounds(208, 230, 130, 40);
        actualizar.setFont(fuenteB);
        actualizar.setBackground(colorB);
        actualizar.setForeground(Color.WHITE);

        buscar.setFont(fuenteB);
        buscar.setForeground(Color.WHITE);
        buscar.setBackground(new Color(51, 102, 0));

        this.add(Lbusqueda);
        this.add(Clistado);
        this.add(buscar);
        buscar.addActionListener(this);
        this.add(Lnombre);
        this.add(Tnombre);
        this.add(Lapellido);
        this.add(Tapellido);
        this.add(Lgrupo);
        this.add(Cgrupo);
        this.add(actualizar);
        actualizar.addActionListener(this);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GuardarEnBD tripulante = new GuardarEnBD();
        if (e.getSource() == this.buscar) {
            int id = Integer.parseInt(this.Clistado.getSelectedItem().toString());
            String resultado[] = tripulante.buscarTripulantes(id);
            this.Tnombre.setText(resultado[1]);
            this.Tapellido.setText(resultado[2]);
            this.Cgrupo.setSelectedItem(Integer.parseInt(resultado[3]));
        } else if (e.getSource() == this.actualizar) {

            if (!this.Tapellido.getText().equals("") &&
                    !this.Tnombre.getText().equals("")) {

                boolean exito = tripulante.actualizarTripulante(this.Tnombre.getText(),
                        this.Tapellido.getText(),
                        Integer.parseInt(this.Cgrupo.getSelectedItem().toString()),
                        Integer.parseInt(this.Clistado.getSelectedItem().toString()));
                if (exito) {
                    JOptionPane.showMessageDialog(null, "Tripulante Actualizado con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "problema al actualizar, intente nuevamente");
                }
            }

        }

    }

}
