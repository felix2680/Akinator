package com.Akinator;

import com.Conexion.ConexionMySQL;
import com.util.Datos;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Nodo {

    String tabla = "instrucciones";
    String columna1 = "pSi";
    String columna2 = "pNo";
    String columna3 = "fdata";
    
    String fdata;
    Nodo pSi;
    Nodo pNo;
    static int pos = 0;

    Nodo() {
        pSi = null;
        pNo = null;
    }

    void insertar(JFrame frame) {
        if (cuantosHijos() == 0) {
            int opcion = JOptionPane.showConfirmDialog(frame, "¿Estás pensando en " + fdata + "?", "Akinator", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.NO_OPTION) {
                String nd = JOptionPane.showInputDialog(frame, "¿En qué estás pensando?");
                pSi = new Nodo();
                pSi.fdata = nd;
                pNo = new Nodo();
                pNo.fdata = fdata;
                String caracteristica = JOptionPane.showInputDialog(frame, "Dame una característica que tenga " + pSi.fdata + " y que no tenga " + pNo.fdata + ":");
                fdata = caracteristica;
            } else {
                JOptionPane.showMessageDialog(frame, "¡¡Adiviné!!");
            }
        } else {
            int opcion = JOptionPane.showConfirmDialog(frame, fdata + "?", "Akinator", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                pSi.insertar(frame);
            } else {
                pNo.insertar(frame);
            }
        }
    }

    int cuantosHijos() {
        int numHijos = 0;
        if (pNo == null && pSi == null) {
            numHijos = 0;
        }
        if ((pNo == null && pSi != null) || (pNo != null && pSi == null)) {
            numHijos = 1;
        }
        if (pNo != null && pSi != null) {
            numHijos = 2;
        }
        return numHijos;
    }

    void preOrden() {
        try {
            // Obtener la conexión a la base de datos
            try (Connection connection = ConexionMySQL.obtenerConexion()) {
                // Llamar al método que realiza el recorrido y guarda en la base de datos
                preOrden(connection);
            }
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void preOrden(Connection connection) throws SQLException, IOException {
        // Utilizar PreparedStatement para prevenir SQL injection
        String sql = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)", tabla, columna1, columna2, columna3);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Establecer los valores de los parámetros
            statement.setString(1, (pSi != null ? "1" : "0"));
            statement.setString(2, (pNo != null ? "1" : "0"));
            statement.setString(3, fdata);

            // Ejecutar la consulta SQL
            statement.executeUpdate();
        }

        // Llamar recursivamente para los nodos hijos
        if (pSi != null) {
            pSi.preOrden(connection);
        }

        if (pNo != null) {
            pNo.preOrden(connection);
        }
    }

    void preCarga(ArrayList<Datos> d) {

        fdata = d.get(pos).dato;
        pos++;
        if (pSi != null) {
            if (d.get(pos).pSi.equals("1")) {
                pSi.pSi = new Nodo();
            }
            if (d.get(pos).pNo.equals("1")) {
                pSi.pNo = new Nodo();
            }
            pSi.preCarga(d);
        }
        if (pNo != null) {
            if (d.get(pos).pSi.equals("1")) {
                pNo.pSi = new Nodo();
            }
            if (d.get(pos).pNo.equals("1")) {
                pNo.pNo = new Nodo();
            }
            pNo.preCarga(d);
        }
    }
}
