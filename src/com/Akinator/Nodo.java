package com.Akinator;

import com.util.Datos;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class Nodo {

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
            try (BufferedWriter file = new BufferedWriter(new FileWriter("src/com/util/akinator.txt", true))) {
                preOrden(file);
                }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void preOrden(BufferedWriter file) throws IOException {
        file.write((pSi != null ? "1," : "0,") + (pNo != null ? "1," : "0,") + fdata + "\n");
        if (pSi != null) {
            pSi.preOrden(file);
        }

        if (pNo != null) {
            pNo.preOrden(file);
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
