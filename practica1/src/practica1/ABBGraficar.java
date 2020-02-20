/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import static practica1.ArbolBinario.Siguientes;
import static practica1.ArbolBinario.idSig;
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
        rais.derecho = new NodoArbol("#",false,id);             
        ArrayList<String> p1 = new ArrayList();
        ArrayList<String> u1 = new ArrayList();
        p1.add(Integer.toString(id));
        u1.add(Integer.toString(id));
        rais.derecho.setPrimeros(p1);
        rais.derecho.setUltimos(u1);
        
        prueba.insertar();
        prueba.postorden();
        Siguientes.clear();
        idSig.clear();
        prueba.tablaSig();
        String sig="";
        for (int j = 1; j <= id; j++) {
            for (int i = 0; i < Siguientes.size(); i++) {
                if(Integer.toString(j).equalsIgnoreCase(idSig.get(i))){
                sig+=Siguientes.get(i)+", ";
                }
            }
            System.out.println(j+" ----> "+sig);
            sig="";
        }
        prueba.graficar(filtro+".jpg");
        id=1;
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
             ArrayList<String> pu = new ArrayList();
             pu.add(Integer.toString(id));
             NodoArbol inicio  = new NodoArbol(temp,false,id);
             inicio.setPrimeros(pu);
             inicio.setUltimos(pu);
             id++;
             return inicio;
       }
    }
    static void  next(){
    mover ++;
    temp=Aux.get(mover);
    }
   
    }
    

