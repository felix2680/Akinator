
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class CBTNodo {

    String fdata;
    CBTNodo pSi;
    CBTNodo pNo;

    CBTNodo() {
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
                pSi = new CBTNodo();
                pSi.fdata = nd;
                pNo = new CBTNodo();
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
            BufferedWriter file = new BufferedWriter(new FileWriter("src/akinator.txt", true));
            preOrden(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void preOrdenNombre(BufferedWriter file) throws IOException {
        file.write((pSi != null ? "1," : "0,") + (pNo != null ? "1," : "0,") + fdata + "\n");

        if (pSi != null) {
            pSi.preOrdenNombre(file);
        }

        if (pNo != null) {
            pNo.preOrdenNombre(file);
        }
    }

    void preOrdenCaracteristica(BufferedWriter file) throws IOException {
        file.write((pSi != null ? "1," : "0,") + (pNo != null ? "1," : "0,") + fdata + "\n");

        if (pSi != null) {
            pSi.preOrdenCaracteristica(file);
        }

        if (pNo != null) {
            pNo.preOrdenCaracteristica(file);
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
}

class CArbol {

    CBTNodo pRaiz;

    CArbol() {
        pRaiz = null;
    }

    void insertar(String nd) {
        char opc;
        String caracteristica;
        if (pRaiz == null) {
            pRaiz = new CBTNodo();
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
                    pRaiz.pSi = new CBTNodo();
                    pRaiz.pSi.fdata = nd;
                    pRaiz.pNo = new CBTNodo();
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

    void preOrden() {
        if (pRaiz == null) {
            System.out.println("No hay informacion para consultar");
        } else {
            pRaiz.preOrden();
        }
    }

    void preOrdenNombre() {
        if (pRaiz == null) {
            System.out.println("No hay información para consultar");
        } else {
            try {
                BufferedWriter file = new BufferedWriter(new FileWriter("src/akinator_nombre.txt"));
                pRaiz.preOrdenNombre(file);
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void preOrdenCaracteristica() {
        if (pRaiz == null) {
            System.out.println("No hay información para consultar");
        } else {
            try {
                BufferedWriter file = new BufferedWriter(new FileWriter("src/akinator_caracteristica.txt"));
                pRaiz.preOrdenCaracteristica(file);
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Utilidades {

    static char leerChar() {
        char c = ' ';
        try {
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
            c = reader.readLine().charAt(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }

    static String leerString() {
        String s = "";
        try {
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
            s = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}

public class Main {

    static CArbol A1 = new CArbol();

    public static void main(String[] args) {
        char cargar;
        char continuar;
        char salvar;

        System.out.println("***********************************");
        System.out.println("*  AKINATOR DE HEROES Y VILLANOS  *");
        System.out.println("***********************************");

        System.out.print("Desea cargar la Base de Conocimientos? ");
        cargar = Utilidades.leerChar();

        if (cargar == 'S' || cargar == 's') {
            System.out.println("Cargando la base de conocimiento....");
            loadLearn();
            System.out.println("Presione ENTER para continuar...");
            Utilidades.leerString();
        }

        do {
            A1.insertar("");
            System.out.print("¿Deseas continuar? ");
            continuar = Utilidades.leerChar();
        } while (continuar == 'S' || continuar == 's');

        System.out.print("Desea guardar la Base de Conocimientos? ");
        salvar = Utilidades.leerChar();

        if (salvar == 'S' || salvar == 's') {
            System.out.println("Guardando la base de conocimiento....");
            saveLearn();
        }
    }

     static void saveLearn() {
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter("src/akinator.txt"));
            A1.preOrden();
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    

    static void loadLearn() {
        try {
            int numRegistro = 0;
            BufferedReader archivo = new BufferedReader(new FileReader("src/akinator.txt"));
            String linea;
            char delimitador = ',';

            while ((linea = archivo.readLine()) != null) {
                String[] partes = linea.split(",");
                A1.pRaiz = new CBTNodo();
                A1.pRaiz.fdata = partes[2];
                numRegistro++;
            }

            archivo.close();

            if (numRegistro > 0) {
                A1.preOrden();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
