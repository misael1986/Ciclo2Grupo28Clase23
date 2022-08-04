package com.unal.ciclo2grupo28clase22;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class EjemploTabla extends JFrame {

    public EjemploTabla() {
        this.setTitle("Tabla de medalleria juegos olimpicos Tokyo 2020");
        String[] encabezados = {"Pais", "Oro", "Plata", "Bronce"};
        String[][] valores = {
            {"China", "29", "17", "16"},
            {"Estados Unidos", "22", "25", "17"},
            {"Japon", "17", "6", "10"}
        };
        JTable table = new JTable(valores, encabezados);
        //                        datos   , titulos
        JScrollPane jsp = new JScrollPane(table);
        this.add(jsp);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
