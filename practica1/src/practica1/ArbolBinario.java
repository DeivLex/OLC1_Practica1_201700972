/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;
import static practica1.ABBGraficar.rais;

public class ArbolBinario {
    /**
     * Nodo raíz del árbol.
     */
    private NodoArbol raiz;
    /**
     * Constructor de la clase, incialmente la raíz es nula porque el árbol
     * está vacío.
     */
    public ArbolBinario() {
        raiz=null;
    }
    /**
     * Método que se encarga de insertar un valor en el árbol binario
     * de búsqueda.
     * @param val Valor específico que se desea insertar.
     */
    public void insertar(){
        if(raiz==null)
            raiz=rais;
    }
    /**
     * Método que genera una imagen del árbol binario de búsqueda en la ruta 
     * que se le indica. 
     * @param path Ruta específica en la que se guardará la imagen generada.
     */
    public void graficar(String path) {
        raiz.graficar(path);
    }
    /**
     * Método que imprime el recorrido inorden del árbol binario de búsqueda.
     */
    public void inorden(){
        System.out.println("Recorrido inorden del árbol binario de búsqueda:");
        inorden(raiz);
        System.out.println();
    }
    /**
     * Método privado que ejecuta la tarea de hacer un recorrido inorden del 
     * árbol binario de búsqueda.
     * @param a Nodo específico que se recorrerá conforme el método se llama 
     * recursivamente.
     */
    private void inorden(NodoArbol a){
        if(a==null)
            return;
        inorden(a.izquierdo);
        System.out.print(a.valor+",");
        inorden(a.derecho);
    }
}
