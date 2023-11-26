package com.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    static final String URL = "jdbc:mysql://localhost:3306/akinator";
    static final String USUARIO = "root";
    static final String CONTRASENA = "";

    public static Connection obtenerConexion() {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conectando a la base de datos...");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("¡Conexión exitosa!");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el driver de MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        return conexion;
    }

    public static void cerrarConexion(Connection conexion) {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
