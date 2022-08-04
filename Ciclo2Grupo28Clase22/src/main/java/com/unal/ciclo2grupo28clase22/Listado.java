package com.unal.ciclo2grupo28clase22;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class Listado extends JFrame {

    String[] titulos;
    String[][] datos;
    JTable tabla;
    
    Listado() {
        this.setTitle("Listado de Tripulantes");
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        
        
        titulos = new String[]{"ID", "Nombres", "Apellidos", "Grupo"};
        GuardarEnBD datosDB = new GuardarEnBD();
        datos = datosDB.listarTripulantes();
        tabla = new JTable(datos, titulos);
        JScrollPane panel = new JScrollPane(tabla);

        this.add(panel);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
