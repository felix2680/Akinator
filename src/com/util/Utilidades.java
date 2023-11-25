package com.util;


import java.io.BufferedReader;
import java.io.IOException;

public class Utilidades {

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
}
