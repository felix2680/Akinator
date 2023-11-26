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

    public static void saveLearn(Arbol A1) {
        A1.preOrden();
    }

    public static void loadLearn(Arbol A1) {
        try {
            // Obtener la conexión a la base de datos
            try (Connection connection = ConexionMySQL.obtenerConexion()) {
                String sql = "SELECT pSi, pNo, fdata FROM instrucciones";
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
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
