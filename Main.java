public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> arbol = new AVLTree<>();

        //Insertar valores en el árbol AVL
        //Convertimos los caracteres a su entero ASCII usando el casteo hacia int

        arbol.insert((int) 'A');
        arbol.insert((int) 'B');
        arbol.insert((int) 'C');
        arbol.insert((int) 'D');
        arbol.insert((int) 'E');

        //Buscar un valor
        boolean resultado = arbol.search((int) 'C');
        System.out.println("¿Se encontró el valor 'C'? " + resultado);

        //Obtener el valor mínimo
        int minimo = arbol.getMin();
        System.out.println("Valor mínimo: " + (char) minimo);

        //Obtener el valor máximo
        int maximo = arbol.getMax();
        System.out.println("Valor máximo: " + (char) maximo);

        //Obtener el padre de un nodo
        Nodo<Integer> nodo = arbol.searchNodo(arbol.raiz, (int) 'C');
        Nodo<Integer> padre = arbol.parent(nodo);
        System.out.println("Padre de 'C': " + (char) padre.valor.intValue());

        //Obtener el hijo izquierdo de un nodo
        Nodo<Integer> hijoIzquierdo = arbol.leftSon(nodo);
        System.out.println("Hijo izquierdo de 'C': " + (char) hijoIzquierdo.valor.intValue());

        //Obtener el hijo izquierdo de un nodo
        Nodo<Integer> hijoDerecho = arbol.rightSon(nodo);
        System.out.println("Hijo izquierdo de 'C': " + (char) hijoDerecho.valor.intValue());

        
    }
}