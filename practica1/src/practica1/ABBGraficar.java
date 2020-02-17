/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;

public class ABBGraficar {
    static int mover =0;
    static ArrayList<String> lis = new ArrayList();
    static String temp;
    static ArbolBinario prueba = new ArbolBinario(); 
    public static NodoArbol rais =new NodoArbol("and");
    
    public static void main(String[] args) {
        lis.add("or");
        mover =0;
        lis.add("and");
        lis.add("a");
        lis.add("b");
        lis.add("c");
        temp = lis.get(0);
        
        
        rais.izquierdo= crear();
       rais.derecho = new NodoArbol("#");
       prueba.insertar();
        prueba.inorden();
        prueba.graficar("arbol_texto.jpg");
       
    }
   static  NodoArbol crear(){
       if(temp.equals("and")){
          NodoArbol inicio  = new NodoArbol("and");
                  next();
                  inicio.izquierdo = crear();
                        next();
                  inicio.derecho = crear(); 
                        return  inicio;
       }else if(temp.equals("or")){
          NodoArbol inicio  = new NodoArbol("or");
                  next();
                  inicio.izquierdo = crear();
                        next();
                  inicio.derecho = crear(); 
                        return  inicio;
       }else{
             NodoArbol inicio  = new NodoArbol(temp);
             return inicio;
       }
    }
    static void  next(){
        
       mover ++;
    temp=lis.get(mover);
    }
   
    }
    

