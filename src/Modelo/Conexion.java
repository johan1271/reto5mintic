/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author SP
 */
public class Conexion {
    

    private static Connection con = null;

    private Conexion() throws Exception {
        try {
            Class.forName("org.sqlite.JDBC");
            // conectarme a la base de datos
            
            con = DriverManager.getConnection("jdbc:sqlite:reto5.db");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {

        if (con == null) {
            try {
                new Conexion();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return con;
    }

    public static void Desconectar() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    
    
}
