package com.Akinator;


import com.util.Datos;
import com.util.Utilidades;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Nodo {

    String fdata;
    Nodo pSi;
    Nodo pNo;
    static int pos = 0;

    Nodo() {
        pSi = null;
        pNo = null;
    }

    void insertar(String nd) {
        char opc;
        String caracteristica;
        if (cuantosHijos() == 0) {
            System.out.println("¿ Estas pensando en " + fdata + "?");
            opc = Utilidades.leerChar();
            if (opc != 'S' && opc != 's') {
                System.out.println("¿En que estas pensando ? ");
                nd = Utilidades.leerString();
                pSi = new Nodo();
                pSi.fdata = nd;
                pNo = new Nodo();
                pNo.fdata = fdata;
                System.out.println("Dame una caracteristica que tenga " + pSi.fdata + " y que no tenga "
                        + pNo.fdata + ":");
                caracteristica = Utilidades.leerString();
                fdata = caracteristica;
            } else {
                System.out.println("¡¡Adivine!!");
            }
        } else {
            System.out.println("¿" + fdata + "?");
            opc = Utilidades.leerChar();
            if (opc == 'S' || opc == 's') {
                pSi.insertar(nd);
            } else {
                pNo.insertar(nd);
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
            try (BufferedWriter file = new BufferedWriter(new FileWriter("src/akinator.txt", true))) {
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
