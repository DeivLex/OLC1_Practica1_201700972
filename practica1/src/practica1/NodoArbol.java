/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Clase que representa un nodo del árbol binario de búsqueda.
 * @author Erick Navarro
 */
public class NodoArbol {
    final String valor;
    private boolean anulable;
    private ArrayList<String> primeros = new ArrayList();
    private ArrayList<String> ultimos = new ArrayList();
    final int identificador;
    public  NodoArbol izquierdo;
    public NodoArbol derecho;
    private static int correlativo=1;
    private final int id;    

    public NodoArbol(String valor) {
        this.valor = valor;
        this.identificador=0;
        this.izquierdo = null;
        this.derecho = null;
        this.id=correlativo++;        
    }

    public NodoArbol(String valor, int identificador) {
        this.valor = valor;
        this.identificador=identificador;
        this.izquierdo = null;
        this.derecho = null;
        this.id=correlativo++;     
    }

    public NodoArbol(String valor, boolean anulable, int identificador) {
        this.valor = valor;
        this.anulable = anulable;
        this.identificador = identificador;
        this.izquierdo = null;
        this.derecho = null;
        this.id=correlativo++; 
    }
    

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public ArrayList<String> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(ArrayList<String> primeros) {
        this.primeros = primeros;
    }

    public ArrayList<String> getUltimos() {
        return ultimos;
    }

    public void setUltimos(ArrayList<String> ultimos) {
        this.ultimos = ultimos;
    }
    
    

    public void graficar(String path) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try
        {
            fichero = new FileWriter("aux_grafico.dot");
            escritor = new PrintWriter(fichero);
            escritor.print(getCodigoGraphviz());
        } 
        catch (Exception e){
            System.err.println("Error al escribir el archivo aux_grafico.dot");
        }finally{
           try {
                if (null != fichero)
                    fichero.close();
           }catch (Exception e2){
               System.err.println("Error al cerrar el archivo aux_grafico.dot");
           } 
        }                        
        try{
          Runtime rt = Runtime.getRuntime();
          rt.exec( "dot -Tjpg -o "+path+" aux_grafico.dot");

          Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo aux_grafico.dot");
        }            
    }

    private String getCodigoGraphviz() {
        return "digraph grafica{\n" +
               "rankdir=TB;\n" +
               "node [shape = record, style=filled, fillcolor=seashell2];\n"+
                getCodigoInterno()+
                "}\n";
    }

    private String getCodigoInterno() {
        String etiqueta;
        String p="";
        String u="";
        for (int i = 0;i<primeros.size() ; i++) {
            p+=primeros.get(i)+", ";
        }
        for (int i = 0;i<ultimos.size() ; i++) {
            u+=ultimos.get(i)+", ";
        }
        String auxvalor=valor;
            if(valor.equalsIgnoreCase("'<'")){
            auxvalor="'"+(char)92+"<'";
            }else if(valor.equalsIgnoreCase("'>'")){
            auxvalor="'"+(char)92+">'";
            }else{
            auxvalor=valor;
            }
        if(izquierdo==null && derecho==null){
            etiqueta="nodo"+id+" [ label =\""+auxvalor+"\\nAnulable="+anulable+"\\nPrimeros="+p+"\\nUltimos="+u+"\\nid="+identificador+"\"];\n";
        }else{
            etiqueta="nodo"+id+" [ label =\"<C0>|"+auxvalor+"\\nAnulable="+anulable+"\\nPrimeros="+p+"\\nUltimos="+u+"|<C1>\"];\n";
        }
        if(izquierdo!=null){
            etiqueta=etiqueta + izquierdo.getCodigoInterno() +
               "nodo"+id+":C0->nodo"+izquierdo.id+"\n";
        }
        if(derecho!=null){
            etiqueta=etiqueta + derecho.getCodigoInterno() +
               "nodo"+id+":C1->nodo"+derecho.id+"\n";                    
        }
        return etiqueta;
    } 
}
