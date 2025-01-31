/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicas.matriz;

/**
 *
 * @author Jostin & Sebastian Orellana
 */
public class Matriz {

    private Integer[][] matriz;
    private Integer nroFila;
    private Integer nroColumna;

    public void crearMatriz(int fila, int colum) {
        matriz = new Integer[fila][colum];
        nroFila = fila;
        nroColumna = colum;
    }

    public Integer[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Integer[][] matriz) {
        this.matriz = matriz;
    }

    public Integer[][] matrizRandom() {
        for (int i = 0; i < nroFila; i++) {
            for (int j = 0; j < nroColumna; j++) {
                matriz[i][j] = (int) (Math.random() * 100);
            }
        }
        return matriz;
    }

    public Integer[][] nroMultiplos(Integer nro) {
        Integer[][] matrizResultante = new Integer[nroFila][nroColumna];
        for (int i = 0; i < nroFila; i++) {
            for (int j = 0; j < nroColumna; j++) {
                if (matriz[i][j] % nro != 0) {
                    matrizResultante[i][j] = matriz[i][j]; 
                } else {
                    matrizResultante[i][j] = 0;
                }
            }
        }
        return matrizResultante;
    }
    
    public boolean esPrimo(int nro) {
        if (nro <= 1) {
            return false;
        } 
        for (int i = 2; i <= Math.sqrt(nro); i++) { 
            if (nro % i == 0) {
                return false; 
            }
        }
        return true; 
    }

    public Integer[][] eliminarPrimos(){
        Integer[][] matrizResultante = new Integer[nroFila][nroColumna];
        for (int i = 0; i < nroFila; i++) {
            for (int j = 0; j < nroColumna; j++) {
                if (!esPrimo(matriz[i][j])) {
                    matrizResultante[i][j] = matriz[i][j]; 
                } else {
                    matrizResultante[i][j] = 0;
                }
            }
        }
        return matrizResultante;
    }
}
