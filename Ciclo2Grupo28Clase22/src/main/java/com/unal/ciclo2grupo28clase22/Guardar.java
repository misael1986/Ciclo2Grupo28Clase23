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
public class Guardar extends JFrame implements ActionListener {

    JLabel Lnombre;
    JLabel Lapellido;
    JLabel Lgrupo;

    JTextField Tnombre;
    JTextField Tapellido;

    JComboBox Cgrupo;

    JButton guardar;
    JButton limpiar;

    public Guardar() {
        this.setSize(400, 400);
        this.setLayout(null);
        this.setTitle("Crear Tripulante");

        Lnombre = new JLabel("Nombres:");
        Lapellido = new JLabel("Apellidos:");
        Lgrupo = new JLabel("Grupo:");

        Tnombre = new JTextField();
        Tapellido = new JTextField();

        Cgrupo = new JComboBox();
        for (int g = 1; g <= 68; g++) {
            Cgrupo.addItem(g);
        }

        guardar = new JButton("Guardar");
        limpiar = new JButton("Limpiar");

        Font fuente = new Font("Tahoma", Font.PLAIN, 14);

        Lnombre.setBounds(40, 30, 80, 30);
        Lnombre.setFont(fuente);
        Tnombre.setBounds(140, 30, 200, 30);
        Tnombre.setFont(fuente);

        Lapellido.setBounds(40, 70, 80, 30);
        Lapellido.setFont(fuente);
        Tapellido.setBounds(140, 70, 200, 30);
        Tapellido.setFont(fuente);

        Lgrupo.setBounds(40, 110, 80, 30);
        Lgrupo.setFont(fuente);
        Cgrupo.setBounds(140, 110, 100, 30);
        Cgrupo.setFont(fuente);

        Font fuenteB = new Font("Tahoma", Font.BOLD, 17);//Objeto tipo Fuente Tipografica
        Color colorB = new Color(0, 140, 255);// Color basado en RGB

        limpiar.setBounds(76, 160, 110, 40);
        limpiar.setFont(fuenteB);//Asignar Fuente a un elemento
        limpiar.setForeground(Color.WHITE);//asignar color a la fuente de un elemento
        limpiar.setBackground(colorB);// asignar color de fondo a un elemento
        
        guardar.setBounds(216, 160, 110, 40);
        guardar.setFont(fuenteB);
        guardar.setForeground(Color.WHITE);
        guardar.setBackground(colorB);

        this.add(Lnombre);
        this.add(Lapellido);
        this.add(Lgrupo);

        this.add(Tnombre);
        this.add(Tapellido);
        this.add(Cgrupo);

        this.add(limpiar);
        limpiar.addActionListener(this);
        this.add(guardar);
        guardar.addActionListener(this);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.limpiar) {
            this.Tnombre.setText("");
            this.Tapellido.setText("");
            this.Cgrupo.setSelectedIndex(0);
        } else if (e.getSource() == this.guardar) {
            String nom = this.Tnombre.getText();
            String ape = this.Tapellido.getText();
            Integer gru = Integer.parseInt(this.Cgrupo.getSelectedItem().toString());

            GuardarEnBD grabado = new GuardarEnBD();
            boolean exito = grabado.guardarTripulante(nom, ape, gru);
            if (exito) {
                JOptionPane.showMessageDialog(null, "Tripulante guardado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "problema al guardar, intente nuevamente");
            }

        }

    }

}
