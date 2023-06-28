import java.util.Scanner;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> arbol = new AVLTree<>();

        //Insertar valores en el árbol AVL
        //Convertimos los caracteres a su entero ASCII usando el casteo hacia int
        System.out.println("Ingresar Palabra");

        Scanner scanner = new Scanner(System.in);
        String palabra = scanner.nextLine().toUpperCase();
        int largopalabra= palabra.length();
        int[] arrayChar = new int[largopalabra];
        
        for (int i = 0; i < largopalabra; i++) {
            arrayChar[i]= palabra.charAt(i);
        }
        for (int i = 0; i < arrayChar.length; i++) {
            System.out.println(arrayChar[i]);
        }

        for (int i = 0; i < arrayChar.length; i++) {
            arbol.insert((int) arrayChar[i]);
        }

       // arbol.insert((int) 'A');    //Esto contiene un numero (64)
       // arbol.insert((int) 'B');
       // arbol.insert((int) 'C');
       // arbol.insert((int) 'D');
       // arbol.insert((int) 'E');
        

        // //Buscar un valor
        // boolean resultado = arbol.search((int) 'C');
        // System.out.println("¿Se encontró el valor 'C'? " + resultado);

        // //Obtener el valor mínimo
        // int minimo = arbol.getMin();
        // System.out.println("Valor mínimo: " + (char) minimo);

        // //Obtener el valor máximo
        // int maximo = arbol.getMax();
        // System.out.println("Valor máximo: " + (char) maximo);

        // //Obtener el padre de un nodo
        // Nodo<Integer> nodo = arbol.searchNodo(arbol.raiz, (int) 'C');
        // Nodo<Integer> padre = arbol.parent(nodo);
        // System.out.println("Padre de 'C': " + (char) padre.valor.intValue());

        // //Obtener el hijo izquierdo de un nodo
        // Nodo<Integer> hijoIzquierdo = arbol.leftSon(nodo);
        // System.out.println("Hijo izquierdo de 'C': " + (char) hijoIzquierdo.valor.intValue());

        // //Obtener el hijo izquierdo de un nodo
        // Nodo<Integer> hijoDerecho = arbol.rightSon(nodo);
        // System.out.println("Hijo izquierdo de 'C': " + (char) hijoDerecho.valor.intValue());

        // //Eliminar un valor
        // arbol.remove((int) 'C');
        // resultado = arbol.search((int) 'C');
        // System.out.println("¿Se encontró el valor 'C'? " + resultado);
        arbol.imprimirArbol();
        AVLTreePrinter printer = new AVLTreePrinter(arbol);
        printer.imprimirArbol();
    }

    }
