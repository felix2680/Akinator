package com.util;

import com.Akinator.Arbol;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Utilidades {

    static Datos d;
    static ArrayList<Datos> datos = new ArrayList<>();

    public static char leerChar() {
        char c = ' ';
        try {
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
            c = reader.readLine().charAt(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

    public static String leerString() {
        String s = "";
        try {
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
            s = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public static void saveLearn(Arbol A1) {
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter("src/com/util/akinator.txt"));
            A1.preOrden();
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadLearn(Arbol A1) {
        try {
            int numRegistro = 0;
            BufferedReader archivo = new BufferedReader(new FileReader("src/com/util/akinator.txt"));
            String linea;
            while ((linea = archivo.readLine()) != null) {
                String[] partes = linea.split(",");
                d = new Datos();
                d.pSi = partes[0];
                d.pNo = partes[1];
                d.dato = partes[2];
                datos.add(d);
                numRegistro++;
            }

            archivo.close();
            if (numRegistro > 0) {
                A1.preCarga(datos);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
