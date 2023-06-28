import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
class AVLTree<T extends Comparable<T>> {
    Nodo<T> raiz;

    // Metodos del arbol
    //search()

    //encontrando el nodo
    Nodo<T> searchNodo(Nodo<T> nodo, T valor) {
        if (nodo == null || valor.compareTo(nodo.valor) == 0)
            return nodo;

        if (valor.compareTo(nodo.valor) < 0)
            return searchNodo(nodo.izquierdo, valor);

        return searchNodo(nodo.derecho, valor);
    }
    //extrayendo el valor del nodo
    boolean search(T valor) {
        Nodo<T> resultado = searchNodo(raiz, valor);
        return resultado != null;
    }


    //getMin()

    //encontrando el nodo
    Nodo<T> getNodoMin(Nodo<T> nodo) {
        Nodo<T> actual = nodo;

        while (actual.izquierdo != null)
            actual = actual.izquierdo;

        return actual;
    }
    //extrayendo el valor del nodo
     T getMin() {
        Nodo<T> nodoMinimo = getNodoMin(raiz);
        return nodoMinimo.valor;
    }


    //getMax()

    //encontrando el nodo
    Nodo<T> getNodoMax(Nodo<T> nodo) {
        Nodo<T> actual = nodo;

        while (actual.derecho != null)
            actual = actual.derecho;

        return actual;
    }
    //extrayendo el valor del nodo
    T getMax() {
        Nodo<T> nodoMaximo = getNodoMax(raiz);
        return nodoMaximo.valor;
    }


    //parent()
    //para buscar el nodo usamos search nodo

    //Obtenemos el nodo padre
     Nodo<T> obtenerPadre(Nodo<T> nodo) {
        return nodo.padre;
    }
    //Devolvemos el valor del nodo padre.
     Nodo<T> parent(Nodo<T> nodo) {
        return obtenerPadre(nodo);
    }


    //son()
    //Se dividiran presentaran los 2 hijos, derecho e izquierdo

    //leftSon()
    //Obtenemos el hijo izquierdo
    Nodo<T> obtenerHijoIzquierdo(Nodo<T> nodo) {
        return nodo.izquierdo;
    }
    //Devolvemos el valor del hijo izquierdo
     Nodo<T> leftSon(Nodo<T> nodo) {
        return obtenerHijoIzquierdo(nodo);
    }

    //rightSon()
    //obtenemos el hijo derecho
    Nodo<T> obtenerHijoDerecho(Nodo<T> nodo) {
        return nodo.derecho;
    }
    //Devolvemos el valor dle hijo derecho
    Nodo<T> rightSon(Nodo<T> nodo) {
        return obtenerHijoDerecho(nodo);
    }


    //insert()

    // Insertar un nodo
    Nodo<T> insertarNodo(Nodo<T> nodo, T valor) {
        // Realizar la inserción en un árbol binario de búsqueda
        //Si el nodo esta vacio.
        if (nodo == null)
            return (new Nodo<>(valor));

        //Para la insercion de cadena de caracteres String se hara uso del metodo compareTo:
        //Segun Geeksforgeeks.org los resultados que puede devolver este metodo son:
        //if string1 > string2, it returns positive number
        //if string1 < string2, it returns negative number
        //if string1 == string2, it returns 0

        //Si el valor del nodo es negativo se inserta en el lado izquierdo    
        if (valor.compareTo(nodo.valor) < 0) {
            nodo.izquierdo = insertarNodo(nodo.izquierdo, valor);
            nodo.izquierdo.padre = nodo;    //Se indica el nuevo nodo padre del hijo recien asignado

        //Si el valor del nodo es positivo se inserta en el lado derecho 
        } else if (valor.compareTo(nodo.valor) > 0) {
            nodo.derecho = insertarNodo(nodo.derecho, valor);
            nodo.derecho.padre = nodo;      //Se indica el nuevo nodo padre del hijo recien asignado
            
        } else // No se permiten valores duplicados
            return nodo;


        // Actualizar la altura del nodo actual
        nodo.altura = alturaMax(getAltura(nodo.izquierdo), getAltura(nodo.derecho)) + 1;

        // Obtener el factor de equilibrio del nodo actual
        int factorEquilibrio = obtenerFactorEquilibrio(nodo);

        // Caso de rotación hacia la izquierda
        if (factorEquilibrio > 1 && valor.compareTo(nodo.izquierdo.valor) < 0)
            return rotacionDerecha(nodo);

        // Caso de rotación hacia la derecha
        if (factorEquilibrio < -1 && valor.compareTo(nodo.derecho.valor) > 0)
            return rotacionIzquierda(nodo);

        // Caso de rotación doble a la izquierda
        if (factorEquilibrio > 1 && valor.compareTo(nodo.izquierdo.valor) > 0) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }

        // Caso de rotación doble a la derecha
        if (factorEquilibrio < -1 && valor.compareTo(nodo.derecho.valor) < 0) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }


    void insert(T valor) {
        raiz = insertarNodo(raiz, valor);
    }


    //remove()


    // Eliminar un nodo del árbol AVL
    Nodo<T> eliminarNodo(Nodo<T> raiz, T valor) {
        // Realizar la eliminación en un árbol binario de búsqueda
        if (raiz == null)
            return raiz;
        //Se busca el nodo a eliminar usando el metodo compareTo, 
        //Segun Geeksforgeeks.org los resultados que puede devolver este metodo son:
        //if string1 > string2, it returns positive number
        //if string1 < string2, it returns negative number
        //if string1 == string2, it returns 0
        
        if (valor.compareTo(raiz.valor) < 0)
            raiz.izquierdo = eliminarNodo(raiz.izquierdo, valor);
        else if (valor.compareTo(raiz.valor) > 0)
            raiz.derecho = eliminarNodo(raiz.derecho, valor);
        else {
            // Nodo encontrado, realizar la eliminación

            // Caso 1 Nodo con uno o ningún hijo
            if ((raiz.izquierdo == null) || (raiz.derecho == null)) {
                Nodo<T> temp;               //nodo temporal
                if (raiz.izquierdo != null)
                    temp = raiz.izquierdo;
                else
                    temp = raiz.derecho;

                // Caso de ningún hijo
                if (temp == null) {
                    temp = raiz;
                    raiz = null;    //eliminando el nodo
                } else { // Caso de un hijo
                    raiz = temp;
                    raiz.padre = temp.padre;//Reemplazando al padre por el hijo
                }
                temp = null;
            } else {
                // Caso 2: Nodo con dos hijos
                Nodo<T> sucesor = getNodoMin(raiz.derecho);//creando nodo sucesor

                // Copiar los valores del sucesor al nodo actual
                raiz.valor = sucesor.valor;

                // Eliminar el sucesor
                raiz.derecho = eliminarNodo(raiz.derecho, sucesor.valor);
                       }
                }

                // Si el árbol tenía solo un nodo, no se necesita hacer más nada
                if (raiz == null)
                return raiz;

        // Actualizar la altura del nodo actual
        raiz.altura =  alturaMax(getAltura(raiz.izquierdo), getAltura(raiz.derecho)) +1;

        // Obtener el factor de equilibrio del nodo actual
        int factorEquilibrio = obtenerFactorEquilibrio(raiz);

        // Caso de rotación izquierda-izquierda
        if (factorEquilibrio > 1 && obtenerFactorEquilibrio(raiz.izquierdo) >= 0)
            return rotacionDerecha(raiz);

        // Caso de rotación izquierda-derecha
        if (factorEquilibrio > 1 && obtenerFactorEquilibrio(raiz.izquierdo) < 0) {
            raiz.izquierdo = rotacionIzquierda(raiz.izquierdo);
            return rotacionDerecha(raiz);
        }

        // Caso de rotación derecha-derecha
        if (factorEquilibrio < -1 && obtenerFactorEquilibrio(raiz.derecho) <= 0)
            return rotacionIzquierda(raiz);

        // Caso de rotación derecha-izquierda
        if (factorEquilibrio < -1 && obtenerFactorEquilibrio(raiz.derecho) > 0) {
            raiz.derecho = rotacionDerecha(raiz.derecho);
            return rotacionIzquierda(raiz);
        }

        return raiz;
    }

     void remove(T valor) {
        raiz = eliminarNodo(raiz, valor);
    }


    //Metodos Auxiliares

    // Obtener la altura de un nodo
    int getAltura(Nodo<T> nodo) {
        if (nodo == null)
            return 0;
        return nodo.altura;
    }

    // Obtener la altura maxima entre los nodos
    int alturaMax(int a, int b) {
        return Math.max(a, b);
    }

     // Obtener el factor de equilibrio de un nodo
    int obtenerFactorEquilibrio(Nodo<T> nodo) {
        if (nodo == null)
            return 0;
        return getAltura(nodo.izquierdo) - getAltura(nodo.derecho);
    }



    //Rotaciones del Arbol

    // Rotación a la derecha
    Nodo<T> rotacionDerecha(Nodo<T> y) {
        Nodo<T> x = y.izquierdo;
        Nodo<T> temp = x.derecho;

        // Realizar rotación
        x.derecho = y;
        y.izquierdo = temp;

        // Actualizar alturas
        y.altura = alturaMax(getAltura(y.izquierdo), getAltura(y.derecho)) + 1;
        x.altura = alturaMax(getAltura(x.izquierdo), getAltura(x.derecho)) + 1;

        // Actualizar padre
        if (temp != null)
            temp.padre = y;
        x.padre = y.padre;
        y.padre = x;

        // Devolver nueva raíz
        return x;
    }

    // Rotación a la izquierda
    Nodo<T> rotacionIzquierda(Nodo<T> x) {
        Nodo<T> y = x.derecho;
        Nodo<T> temp = y.izquierdo;

        // Realizar rotación
        y.izquierdo = x;
        x.derecho = temp;

        // Actualizar alturas
        x.altura = alturaMax(getAltura(x.izquierdo), getAltura(x.derecho)) + 1;
        y.altura = alturaMax(getAltura(y.izquierdo), getAltura(y.derecho)) + 1;

        // Actualizar padre
        if (temp != null)
            temp.padre = x;
        y.padre = x.padre;
        x.padre = y;

        // Devolver nueva raíz
        return y;
    }


        //Imprimir en consola
     public void imprimirArbol() {
        imprimirEnOrden(raiz);
        System.out.println();
    }

    private void imprimirEnOrden(Nodo<T> nodo) {
        if (nodo != null) {
            
            imprimirEnOrden(nodo.izquierdo);
            int valornum = (int)nodo.valor;
            char valorchar = (char)valornum;
            System.out.print(valorchar + " ");
            imprimirEnOrden(nodo.derecho);
        }
    }


}


