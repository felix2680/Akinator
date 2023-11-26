package com.Akinator;

import com.util.Datos;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Arbol {

    Nodo pRaiz;
    int pos = 0;

    public Arbol() {
        pRaiz = null;
    }

    public void insertar(JFrame frame) {
        if (pRaiz == null) {
            pRaiz = new Nodo();
            JOptionPane.showMessageDialog(frame, "No puedo adivinar...");
            String nd = JOptionPane.showInputDialog(frame, "¿En qué estás pensando?");
            pRaiz.fdata = nd;
        } else {
            if (pRaiz.cuantosHijos() == 0) {
                int opcion = JOptionPane.showConfirmDialog(frame, "¿Estás pensando en " + pRaiz.fdata + "?", "Akinator", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.NO_OPTION) {
                    String nd = JOptionPane.showInputDialog(frame, "¿En qué estás pensando?");
                    pRaiz.pSi = new Nodo();
                    pRaiz.pSi.fdata = nd;
                    pRaiz.pNo = new Nodo();
                    pRaiz.pNo.fdata = pRaiz.fdata;
                    String caracteristica = JOptionPane.showInputDialog(frame, "Dame una característica que tenga " + pRaiz.pSi.fdata + " y que no tenga " + pRaiz.pNo.fdata + ":");
                    pRaiz.fdata = caracteristica;
                } else {
                    JOptionPane.showMessageDialog(frame, "¡¡Adiviné!!");
                }
            } else {
                int opcion = JOptionPane.showConfirmDialog(frame, pRaiz.fdata + "?", "Akinator", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    pRaiz.pSi.insertar(frame);
                } else {
                    pRaiz.pNo.insertar(frame);
                }
            }
        }
    }

    public void preOrden() {
        if (pRaiz == null) {
            System.out.println("No hay informacion para consultar");
        } else {
            pRaiz.preOrden();
        }
    }

    public void preCarga(ArrayList<Datos> d) {

        if (pRaiz == null) {
            pRaiz = new Nodo();
            if (d.get(pos).pSi.equals("1")) {
                pRaiz.pSi = new Nodo();
            }
            if (d.get(pos).pNo.equals("1")) {
                pRaiz.pNo = new Nodo();
            }
            pRaiz.preCarga(d);
        }
    }
}
