/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import static practica1.home.ER;
import static practica1.home.NameER;

public class ABBGraficar {
    static int mover =0;
    static String temp;
    static ArbolBinario prueba = new ArbolBinario(); 
    public static NodoArbol rais =new NodoArbol("and");
    static ArrayList<String> Aux = new ArrayList();
    static int id=1;
    
    public static void main(String filtro) {
        Aux.clear();
        for (int i = 0; i < ER.size(); i++) {
            if (NameER.get(i).equals(filtro)) {
                Aux.add(ER.get(i));
            }
        }
        mover =0;
        temp = Aux.get(0);
        rais.izquierdo= crear();
        rais.derecho = new NodoArbol("#");
        prueba.insertar();
        prueba.inorden();
        prueba.graficar(filtro+".jpg");
       
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
       }else if(temp.equals("+")||temp.equals("*")||temp.equals("?")){
          NodoArbol inicio  = new NodoArbol(temp);
                  next();
                  inicio.izquierdo = crear();
                  return inicio;
       }else{
             NodoArbol inicio  = new NodoArbol(temp+"\\nid = "+Integer.toString(id));
             id++;
             return inicio;
       }
    }
    static void  next(){
        
       mover ++;
    temp=Aux.get(mover);
    }
   
    }
    

