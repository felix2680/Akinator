package com.Akinator;


import com.util.Datos;
import com.util.Utilidades;
import java.util.ArrayList;

public class Arbol {

    Nodo pRaiz;
    int pos = 0;

    public Arbol() {
        pRaiz = null;
    }

    public void insertar(String nd) {
        char opc;
        String caracteristica;
        if (pRaiz == null) {
            pRaiz = new Nodo();
            System.out.println("No puedo adivinar... ");
            System.out.println("En que estas pensando ? ");
            nd = Utilidades.leerString();
            pRaiz.fdata = nd;
        } else {
            if (pRaiz.cuantosHijos() == 0) {
                System.out.println("¿ Estas pensando en " + pRaiz.fdata + "?");
                opc = Utilidades.leerChar();
                if (opc != 'S' && opc != 's') {
                    System.out.println("¿En que estas pensando ? ");
                    nd = Utilidades.leerString();
                    pRaiz.pSi = new Nodo();
                    pRaiz.pSi.fdata = nd;
                    pRaiz.pNo = new Nodo();
                    pRaiz.pNo.fdata = pRaiz.fdata;
                    System.out.println("Dame una caracteristica que tenga " + pRaiz.pSi.fdata + " y que no tenga "
                            + pRaiz.pNo.fdata + ":");
                    caracteristica = Utilidades.leerString();
                    pRaiz.fdata = caracteristica;
                } else {
                    System.out.println("¡¡Adivine!!");
                }
            } else {
                System.out.println("¿" + pRaiz.fdata + "?");
                opc = Utilidades.leerChar();
                if (opc == 'S' || opc == 's') {
                    pRaiz.pSi.insertar(nd);
                } else {
                    pRaiz.pNo.insertar(nd);
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
