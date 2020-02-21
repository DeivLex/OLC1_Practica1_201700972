/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import static practica1.ABBGraficar.id;
import static practica1.ABBGraficar.rais;

public class ArbolBinario {
    static ArrayList<String> valorSig = new ArrayList();
    static ArrayList<String> Siguientes = new ArrayList();
    static ArrayList<String> idSig = new ArrayList();
    static ArrayList<String> Estados = new ArrayList();
    static ArrayList<String> idEs = new ArrayList();
    

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
            if(!a.izquierdo.isAnulable()&&a.derecho.isAnulable()){
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
            if(!a.izquierdo.isAnulable()&&!a.derecho.isAnulable()){
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
    
    public void estados(){
        int ese=0;
        for (int i = 0; i < raiz.getPrimeros().size(); i++) {
            idEs.add("S0");
            Estados.add(raiz.getPrimeros().get(i));
        }
        /*int control=1;
        while(control!=0){
        
        }*/
        String sig="";
        for (int j = 1; j <= id; j++) {
            for (int i = 0; i < Siguientes.size(); i++) {
                if(Integer.toString(j).equalsIgnoreCase(idSig.get(i))){
                sig+=Siguientes.get(i)+", ";
                }
            }
            System.out.println(valorSig.get(j-1)+" ----> "+j+" ----> "+sig);
            if(existeEs(sig)==false){
                ese++;
                for (int i = 0; i < Siguientes.size(); i++) {
                if(Integer.toString(j).equalsIgnoreCase(idSig.get(i))){
                idEs.add("S"+Integer.toString(ese));
                Estados.add(Siguientes.get(i));
                }
            }
            }
            System.out.println("Existe: "+existeEs(sig));
            sig="";
        }
        //Imprimir estados
        String esta="";
        for (int j = 0; j < ese; j++) {
            for (int i = 0; i < Estados.size(); i++) {
                String kk="S"+Integer.toString(j);
                if(kk.equalsIgnoreCase(idEs.get(i))){
                esta+=Estados.get(i)+", ";
                }
            }
            System.out.println("S"+Integer.toString(j)+" ----> "+esta);
            esta="";
        }
    }
    public boolean existeEs(String filtro){
        String sig="";
        for (int i = 0; i < idEs.size(); i++) {
                if (i==0) {
                    for (int j = 0; j < idEs.size(); j++) {
                        if (idEs.get(0).equalsIgnoreCase(idEs.get(j))) {
                            sig+=Estados.get(j)+", ";
                        }
                    }
                    if (filtro.equalsIgnoreCase(sig)) {
                        return true;
                    }
                    sig="";
                }else if (!idEs.get(i).equals(idEs.get(i-1))) {
                    for (int j = 0; j < idEs.size(); j++) {
                        if (idEs.get(i).equalsIgnoreCase(idEs.get(j))) {
                            sig+=Estados.get(j)+", ";
                        }
                    }
                    if (filtro.equalsIgnoreCase(sig)) {
                        return true;
                    }
                    sig="";
                }
        }
        return false;
    }
    
    public void CodigoSig(String codigoSig) {
        FileWriter fichero = null;
        PrintWriter escritor;
        try
        {
            fichero = new FileWriter("siguientes.dot");
            escritor = new PrintWriter(fichero);
            escritor.print(codigoSig);
        } 
        catch (Exception e){
            System.err.println("Error al escribir el archivo siguientes.dot");
        }finally{
           try {
                if (null != fichero)
                    fichero.close();
           }catch (Exception e2){
               System.err.println("Error al cerrar el archivo siguientes.dot");
           } 
        }                        
        try{
          Runtime rt = Runtime.getRuntime();
          rt.exec( "dot -Tjpg -o siguientes.jpg siguientes.dot");

          Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo siguientes.dot");
        }            
    }
    
    public void graficarSig(){
        String hojavalor="Hoja Valor";
        String hojaid="Hoja Id";
        String hojasig="Siguientes";
        String sig="";
        for (int j = 1; j <= id; j++) {
            for (int i = 0; i < Siguientes.size(); i++) {
                if(Integer.toString(j).equalsIgnoreCase(idSig.get(i))){
                sig+=Siguientes.get(i)+", ";
                }
            }
            System.out.println(valorSig.get(j-1)+" ----> "+j+" ----> "+sig);
            if(valorSig.get(j-1).equalsIgnoreCase("'<'")){
            hojavalor+="|'"+(char)92+"<'";
            }else if(valorSig.get(j-1).equalsIgnoreCase("'>'")){
            hojavalor+="|'"+(char)92+">'";
            }else{
            hojavalor+="|"+valorSig.get(j-1);
            }
            hojaid+="|"+j;
            hojasig+="|"+sig;
            sig="";
        }
        String codigo="digraph D {\n" +
        "\n" +
        "    node [fontname=\"Arial\"];\n" +
        "    node_A [shape=record label=\"{"+hojavalor+"}|{"+hojaid+"}|{"+hojasig+"--}\"];\n" +
        "\n" +
        "}";
        CodigoSig(codigo);
    }
     
}
