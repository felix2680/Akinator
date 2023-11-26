package com.util;

import com.Akinator.Arbol;
import com.Conexion.ConexionMySQL;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilidades {

    static ArrayList<Datos> datos = new ArrayList<>();
    static String tabla = "instrucciones";
    static String columna1 = "pSi";
    static String columna2 = "pNo";
    static String columna3 = "fdata";
    
    public static void saveLearn(Arbol A1) {
        A1.preOrden();
    }

    public static void loadLearn(Arbol A1) {
        try {
            // Obtener la conexión a la base de datos
            try (Connection connection = ConexionMySQL.obtenerConexion()) {
                String sql = String.format("SELECT %s,%s,%s FROM %s",columna1,columna2,columna3,tabla);
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        int numRegistro = 0;
                        while (resultSet.next()) {
                            String pSi = resultSet.getString("pSi");
                            String pNo = resultSet.getString("pNo");
                            String dato = resultSet.getString("fdata");

                            Datos d = new Datos();
                            d.pSi = pSi;
                            d.pNo = pNo;
                            d.dato = dato;
                            datos.add(d);
                            numRegistro++;
                        }

                        if (numRegistro > 0) {
                            A1.preCarga(datos);
                        }
                    }
                }finally{
                    String delteSql = String.format("DELETE FROM %s",tabla);
                    try(PreparedStatement statement = connection.prepareStatement(delteSql)){
                        statement.executeUpdate();
                    }
                }
                
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
