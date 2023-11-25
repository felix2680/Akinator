
import com.util.Datos;
import com.util.Utilidades;
import com.Akinator.Arbol;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static Arbol A1 = new Arbol();
    static ArrayList<Datos> datos = new ArrayList<>();
    static Datos d;

    public static void main(String[] args) {
        char continuar;
        char salvar;

        System.out.println("***********************************");
        System.out.println("*  AKINATOR DE POKEMONS  *");
        System.out.println("***********************************");

        System.out.println("Cargando la base de conocimiento....");
        loadLearn();
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
