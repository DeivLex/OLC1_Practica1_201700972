/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;
import java.util.ArrayList;
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
    public void postorden(){
        System.out.println("Recorrido postorden del árbol binario de búsqueda:");
        postorden(raiz);
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
     private void postorden(NodoArbol a){
        if(a==null)
            return;
        postorden(a.izquierdo);
        postorden(a.derecho);
        if(a.valor=="and"){
            if(a.izquierdo.isAnulable()&&a.izquierdo.isAnulable()){
                ArrayList<String> p = new ArrayList();
                p=a.izquierdo.getPrimeros();
                for (int i = 0; i < a.derecho.getPrimeros().size(); i++) {
                    p.add(a.derecho.getPrimeros().get(i));
                }
                a.setPrimeros(p);
                ArrayList<String> u = new ArrayList();
                u=a.izquierdo.getUltimos();
                for (int i = 0; i < a.derecho.getUltimos().size(); i++) {
                    u.add(a.derecho.getUltimos().get(i));
                }
                a.setUltimos(u);
                a.setAnulable(true);
            }else{
                a.setAnulable(false);
            }
            if(a.izquierdo.isAnulable()){
                ArrayList<String> p = new ArrayList();
                p=a.izquierdo.getPrimeros();
                for (int i = 0; i < a.derecho.getPrimeros().size(); i++) {
                    p.add(a.derecho.getPrimeros().get(i));
                }
                a.setPrimeros(p);
                a.setUltimos(a.derecho.getUltimos());
            }
            if(a.derecho.isAnulable()){
            a.setPrimeros(a.izquierdo.getPrimeros());
            ArrayList<String> u = new ArrayList();
                u=a.izquierdo.getUltimos();
                for (int i = 0; i < a.derecho.getUltimos().size(); i++) {
                    u.add(a.derecho.getUltimos().get(i));
                }
                a.setUltimos(u);
            }
            if(!a.izquierdo.isAnulable()&&!a.izquierdo.isAnulable()){
                a.setPrimeros(a.izquierdo.getPrimeros());
                a.setUltimos(a.derecho.getUltimos());
            }
            
            
        }
        if(a.valor=="or"){
                ArrayList<String> p = new ArrayList();
                p=a.izquierdo.getPrimeros();
                for (int i = 0; i < a.derecho.getPrimeros().size(); i++) {
                    p.add(a.derecho.getPrimeros().get(i));
                }
                a.setPrimeros(p);
                ArrayList<String> u = new ArrayList();
                u=a.izquierdo.getUltimos();
                for (int i = 0; i < a.derecho.getUltimos().size(); i++) {
                    u.add(a.derecho.getUltimos().get(i));
                }
                a.setUltimos(u);
            if(a.izquierdo.isAnulable()||a.izquierdo.isAnulable()){
                a.setAnulable(true);
            }else{
                a.setAnulable(false);
            }
        }
        if(a.valor=="*"||a.valor=="?"){
            a.setAnulable(true);
            a.setPrimeros(a.izquierdo.getPrimeros());
            a.setUltimos(a.izquierdo.getUltimos());
        }
        if(a.valor=="+"){
            a.setAnulable(false);
            a.setPrimeros(a.izquierdo.getPrimeros());
            a.setUltimos(a.izquierdo.getUltimos());
        }
        
        System.out.print(a.valor+",");
    }
}
