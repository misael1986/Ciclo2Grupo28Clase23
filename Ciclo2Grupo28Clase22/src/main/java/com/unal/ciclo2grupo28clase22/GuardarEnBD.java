package com.unal.ciclo2grupo28clase22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class GuardarEnBD {

    Connection conn;

    GuardarEnBD() {
    }

    public void conectar() {
        String dbURL = "jdbc:mysql://127.0.0.1:3306/ejemploclase17";
        String username = "root";
        String password = "Admin123#";
        // conectar
        try {
            this.conn = DriverManager.getConnection(
                    dbURL, username, password);
            if (conn != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
    }

    //-------------------------------------------
    public void desconectar() {
        try {
            if (!conn.isClosed()) {
                conn.close();
                System.out.println("Desconectado");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //--------------------------------------------------
    public boolean guardarTripulante(String nombre, String apellido,
            Integer grupo) {

        boolean resultado = false;
        this.conectar();
        try {

            String sql = "INSERT INTO tripulantes (nombre,apellido,grupo)"
                    + " VALUES ( ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setInt(3, grupo);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(" Inserción exitosa !");
                resultado = true;
            }
            this.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    //--------------------------------------------------------
    public String leerTripulantes(Integer grupo) {
        this.conectar();
        String datos = "";
        try {

            String sql = "SELECT * FROM tripulantes WHERE grupo = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, grupo);

            ResultSet result = statement.executeQuery();
            int count = 0;
            while (result.next()) {

                String nombre = result.getString(2);
                String apellido = result.getString(3);
                int group = result.getInt(4);

                datos = datos + "\nNombre : " + nombre
                        + "\nApellido: " + apellido
                        + "\nGrupo : " + group + "\n";

            }
            this.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return datos;
    }

    //--------------------------------------------------------
    public String[][] listarTripulantes() {
        this.conectar();
        String datos[][] = null;

        try {

            String sql = "SELECT * FROM tripulantes";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            String sql_count = "SELECT count(*) as cant FROM tripulantes";
            Statement statement_count = conn.createStatement();
            ResultSet result_count = statement_count.executeQuery(sql_count);

            int count = 0;
            while (result_count.next()) {
                count = result_count.getInt("cant");
            }

            //System.out.println("Cantidad=" + count);
            datos = new String[count][4];

            int id;
            String nombre;
            String apellido;
            int grupo;
            int fila = 0;
            while (result.next()) {
                id = result.getInt(1);
                nombre = result.getString(2);
                apellido = result.getString(3);
                grupo = result.getInt(4);
                datos[fila][0] = String.valueOf(id);
                datos[fila][1] = nombre;
                datos[fila][2] = apellido;
                datos[fila][3] = String.valueOf(grupo);
                fila++;//fila=fila+1;
            }
            this.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return datos;
    }

    //--------------------------------------------------------
    public Integer[] llenarCombo() {
        this.conectar();
        Integer ids[] = null;

        try {

            String sql = "SELECT id FROM tripulantes";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            String sql_count = "SELECT count(*) as cant FROM tripulantes";
            Statement statement_count = conn.createStatement();
            ResultSet result_count = statement_count.executeQuery(sql_count);

            int count = 0;
            while (result_count.next()) {
                count = result_count.getInt("cant");
            }

            //System.out.println("Cantidad=" + count);
            ids = new Integer[count];

            int fila = 0;
            while (result.next()) {

                ids[fila] = result.getInt("id");

                fila++;//fila=fila+1;
            }
            this.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ids;
    }

    //--------------------------------------------------------
    public String[] buscarTripulantes(int id_tripulante) {
        this.conectar();
        String datos[] = null;

        try {

            String sql = "SELECT * FROM tripulantes WHERE id = " + id_tripulante;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            datos = new String[4];

            int id;
            String nombre;
            String apellido;
            int grupo;
            while (result.next()) {
                id = result.getInt(1);
                nombre = result.getString(2);
                apellido = result.getString(3);
                grupo = result.getInt(4);

                datos[0] = String.valueOf(id);
                datos[1] = nombre;
                datos[2] = apellido;
                datos[3] = String.valueOf(grupo);

            }
            //System.out.println(datos[0]+"\t"+datos[1]+"\t"+datos[2]+"\t"+datos[3]+"\t");
            this.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return datos;
    }

    //--------------------------------------------------
    public boolean actualizarTripulante(String nombre, String apellido,
            Integer grupo, Integer id_trip) {

        boolean resultado = false;
        this.conectar();
        try {

            String sql = "UPDATE tripulantes "
                    + " SET nombre = ?,"
                    + " apellido = ?,"
                    + " grupo = ?"
                    + " WHERE id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setInt(3, grupo);
            statement.setInt(4, id_trip);
            System.out.println(statement.toString());
            int rowsInserted = statement.executeUpdate();
            
            if (rowsInserted > 0) {
                System.out.println(" Actualizacion exitosa !");
                resultado = true;
            }
            this.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

}
