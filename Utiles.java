/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_Empresa;

import java.util.Random;

/**
 *
 * @author danny
 */
public class Utiles {
    public static boolean validate(String num) {
        boolean band = true;
        if(num.charAt(0) == '-') {
            num = num.substring(1);
        }
        //System.out.println(num);
        int cont_p = 0;
        for(int i = 0; i < num.length(); i++){
            if(!Character.isDigit(num.charAt(i)) && num.charAt(i) != '.') {
                band = false;
                break;
            } else if(num.charAt(i) == '.')
                cont_p++;
        }
        if(cont_p >= 2 )
            band = false;
        return band;
    }
    public static int transformStringInt(String num) {
        int resp = 0;
        if(Utiles.validate(num)) {
            resp = (int)Utiles.transformStringFloat(num);//Integer.parseInt(num);
        }
        return resp;
    }

    public static double transformStringDouble(String num) {
        double resp = 0;
        if(Utiles.validate(num)) {
            resp = Double.parseDouble(num);
        }
        return resp;
    }

    public static float transformStringFloat(String num) {
        float resp = 0;
        if(Utiles.validate(num)) {
            resp = Float.parseFloat(num);
        }
        return resp;
    }

    public static int generaNumeroRango(int minimo, int maximo) {
        Random random = new Random();
        //2 - 10
        //2 + 11 - 2   -----  2 + (9) 
        return minimo + random.nextInt((maximo + 1) - minimo);
    }

    // public static float generaNumeroRangoFloat(float minimo, float maximo) {
        Random random = new Random();
        //2 - 10
        //2 + 11 - 2   -----  2 + (9) 
        //return minimo+random.nextFloat((maximo + 1) - minimo);
    //}

    public static float redondear (float num) {
       float aux = num*100.00f;
       float aux1 = (float)((int)aux); 
       return (aux1/100.00f);
        // return (float)(Math.round(num*100.00)/100.00);
    }
    
    public static void printMatriz(Object[][] m){
        System.out.println("Print Matriz");
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j]+"\t");
            }
        }
    }
}
