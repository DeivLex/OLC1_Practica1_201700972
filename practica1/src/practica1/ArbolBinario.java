/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;
import java.util.ArrayList;
import static practica1.ABBGraficar.rais;

public class ArbolBinario {
    static ArrayList<String> Siguientes = new ArrayList();
    static ArrayList<String> idSig = new ArrayList();

    private NodoArbol raiz;

    public ArbolBinario() {
        raiz=null;
    }

    public void insertar(){
        if(raiz==null)
            raiz=rais;
    }

    public void graficar(String path) {
        raiz.graficar(path);
    }

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
    public void tablaSig(){
        System.out.println("Tabla siguientes:");
        tablaSig(raiz);
        System.out.println();
    }

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
        //validaciones
        if(a.valor.equals("and")){
            if(a.izquierdo.isAnulable()&&a.derecho.isAnulable()){
                ArrayList<String> p = new ArrayList();
                for (int i = 0; i < a.izquierdo.getPrimeros().size(); i++) {
                    p.add(a.izquierdo.getPrimeros().get(i));
                }
                for (int i = 0; i < a.derecho.getPrimeros().size(); i++) {
                    p.add(a.derecho.getPrimeros().get(i));
                }
                a.setPrimeros(p);
                ArrayList<String> u = new ArrayList();
                for (int i = 0; i < a.izquierdo.getUltimos().size(); i++) {
                    u.add(a.izquierdo.getUltimos().get(i));
                }
                for (int i = 0; i < a.derecho.getUltimos().size(); i++) {
                    u.add(a.derecho.getUltimos().get(i));
                }
                a.setUltimos(u);
                a.setAnulable(true);
            }else{
                a.setAnulable(false);
            }
            if(a.izquierdo.isAnulable()&&!a.derecho.isAnulable()){
                ArrayList<String> p= new ArrayList();
                for (int i = 0; i < a.izquierdo.getPrimeros().size(); i++) {
                    p.add(a.izquierdo.getPrimeros().get(i));
                }
                for (int i = 0; i < a.derecho.getPrimeros().size(); i++) {
                    p.add(a.derecho.getPrimeros().get(i));
                }
                a.setPrimeros(p);
                a.setUltimos(a.derecho.getUltimos());
            }
            if(a.derecho.isAnulable()&&!a.izquierdo.isAnulable()){
            a.setPrimeros(a.izquierdo.getPrimeros());
            ArrayList<String> u= new ArrayList();
                for (int i = 0; i < a.izquierdo.getUltimos().size(); i++) {
                    u.add(a.izquierdo.getUltimos().get(i));
                }
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
        //validacion or
        if(a.valor.equals("or")){
            if(a.izquierdo.isAnulable()||a.derecho.isAnulable()){
                a.setAnulable(true);
            }else{
                a.setAnulable(false);
            }
                ArrayList<String> p = new ArrayList();
                for (int i = 0; i < a.izquierdo.getPrimeros().size(); i++) {
                    p.add(a.izquierdo.getPrimeros().get(i));
                }
                for (int i = 0; i < a.derecho.getPrimeros().size(); i++) {
                    p.add(a.derecho.getPrimeros().get(i));
                }
                a.setPrimeros(p);
                ArrayList<String> u = new ArrayList();
                for (int i = 0; i < a.izquierdo.getUltimos().size(); i++) {
                    u.add(a.izquierdo.getUltimos().get(i));
                }
                for (int i = 0; i < a.derecho.getUltimos().size(); i++) {
                    u.add(a.derecho.getUltimos().get(i));
                }
                a.setUltimos(u);
        }
        //validacion simbolos
        if(a.valor.equalsIgnoreCase("*")||a.valor.equalsIgnoreCase("?")){
            a.setAnulable(true);
            a.setPrimeros(a.izquierdo.getPrimeros());
            a.setUltimos(a.izquierdo.getUltimos());
        }
        if(a.valor.equalsIgnoreCase("+")){
            a.setAnulable(false);
            a.setPrimeros(a.izquierdo.getPrimeros());
            a.setUltimos(a.izquierdo.getUltimos());
        }

        System.out.print(a.valor+",");
    }
    public void tablaSig(NodoArbol a){
        if(a==null)
            return;
        tablaSig(a.izquierdo);
        tablaSig(a.derecho);
        //codigo
        if(a.valor.equalsIgnoreCase("and")){
            for (int i = 0; i < a.izquierdo.getUltimos().size(); i++) {
                for (int j = 0; j < a.derecho.getPrimeros().size(); j++) {
                 idSig.add(a.izquierdo.getUltimos().get(i));
                 Siguientes.add(a.derecho.getPrimeros().get(j));
                }
            }
        }
        if (a.valor.equalsIgnoreCase("*")||a.valor.equalsIgnoreCase("+")) {
            for (int i = 0; i < a.izquierdo.getUltimos().size(); i++) {
                for (int j = 0; j < a.izquierdo.getPrimeros().size(); j++) {
                 idSig.add(a.izquierdo.getUltimos().get(i));
                 Siguientes.add(a.izquierdo.getPrimeros().get(j));
                }
            }
        }
    }
     
}
