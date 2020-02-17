/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Clase que representa un nodo del árbol binario de búsqueda.
 * @author Erick Navarro
 */
public class NodoArbol {
    final String valor;
    public  NodoArbol izquierdo;
    public NodoArbol derecho;
    private static int correlativo=1;
    private final int id;    

    public NodoArbol(String valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
        this.id=correlativo++;        
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
        if(izquierdo==null && derecho==null){
            etiqueta="nodo"+id+" [ label =\""+valor+"\"];\n";
        }else{
            etiqueta="nodo"+id+" [ label =\"<C0>|"+valor+"|<C1>\"];\n";
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
