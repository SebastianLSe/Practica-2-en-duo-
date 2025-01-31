/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_Empresa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Jostin Vasquez & Sebastian Orellana
 */
public class E1_Empresa {

    private String anio;
    private String[] mes;
    private Float[] ventasMensual;
    private Float[] gastosMensual;
    private String[][] data;

    public String getAnio() {
        return anio;
    }

    public String[] getMes() {
        return mes;
    }

    public Float[] getVentasMensuales() {
        return ventasMensual;
    }

    public Float[] getGastosMensual() {
        return gastosMensual;
    }

    public void crear() {
        this.gastosMensual = new Float[12];
        this.ventasMensual = new Float[12];
        this.mes = new String[12];
        this.mes[0] = "Enero";
        this.mes[1] = "Febrero";
        this.mes[2] = "Marzo";
        this.mes[3] = "Abril";
        this.mes[4] = "Mayo";
        this.mes[5] = "Junio";
        this.mes[6] = "Julio";
        this.mes[7] = "Agosto";
        this.mes[8] = "Septiembre";
        this.mes[9] = "Octubre";
        this.mes[10] = "Noviembre";
        this.mes[11] = "Diciembre";
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public Float promedioVentas() {
        Float promedio = 0.0f;
        Float cont = 0.0f;
        for (int i = 0; i < ventasMensual.length; i++) {
            if (ventasMensual[i] != null) {
                cont++;
                promedio += ventasMensual[i];
            }
        }
        return Utiles.redondear(promedio / cont);
    }

    public String mesVentaMasAlta() {
        int mesMasVentas = 0;
        Float ventaMaxima = ventasMensual[0];
        for (int i = 0; i < ventasMensual.length; i++) {
            if (ventasMensual[i] != null && ventasMensual[i] > ventaMaxima) {
                ventaMaxima = ventasMensual[i];  
                mesMasVentas = i;
            }
        }
        return String.valueOf(ventaMaxima);
    }

    public String mesMasAlta() {
        int mesMasVentas = 0;
        Float ventaMaxima = ventasMensual[0];
        for (int i = 0; i < ventasMensual.length; i++) {
            if (ventasMensual[i] != null && ventasMensual[i] > ventaMaxima) {
                ventaMaxima = ventasMensual[i]; 
                mesMasVentas = i;
            }
        }
        return mes[mesMasVentas]; 
    }

    public String mesVentaMasBaja() {
        int mesConMenosVenta = 0;
        Float ventaMinimo = ventasMensual[0];
        for (int i = 1; i < ventasMensual.length; i++) {
            if (ventasMensual[i] != null && ventasMensual[i] < ventaMinimo) {
                ventaMinimo = ventasMensual[i];
                mesConMenosVenta = i;
            }
        }
        return String.valueOf(ventaMinimo);
    }

    public String mesMasBaja() {
        int mesConMenorVenta = 0;
        Float ventaMinima = ventasMensual[0];
        for (int i = 0; i < ventasMensual.length; i++) {
            if (ventasMensual[i] != null && ventasMensual[i] < ventaMinima) {
                ventaMinima = ventasMensual[i]; 
                mesConMenorVenta = i;
            }
        }
        return mes[mesConMenorVenta]; 
    }

    public String mesGastoMasAlto() {
        int mesMasVentas = 0;
        Float ventaMaxima = gastosMensual[0];
        for (int i = 0; i < gastosMensual.length; i++) {
            if (gastosMensual[i] != null && gastosMensual[i] > ventaMaxima) {
                ventaMaxima = gastosMensual[i]; 
                mesMasVentas = i;
            }
        }
        return String.valueOf(ventaMaxima);
    }

    public String mesGastoAlto() {
        int mesMasVentas = 0;
        Float ventaMaxima = gastosMensual[0];
        for (int i = 0; i < gastosMensual.length; i++) {
            if (gastosMensual[i] != null && gastosMensual[i] > ventaMaxima) {
                ventaMaxima = gastosMensual[i];
                mesMasVentas = i;
            }
        }
        return mes[mesMasVentas]; 
    }

    public String mesGastoMasBajo() {
        int mesConMenosGasto = 0;
        Float gastoMinimo = gastosMensual[0];
        for (int i = 1; i < gastosMensual.length; i++) {
            if (gastosMensual[i] != null && gastosMensual[i] < gastoMinimo) {
                gastoMinimo = gastosMensual[i];
                mesConMenosGasto = i;
            }
        }
        return String.valueOf(gastoMinimo);
    }

    public String mesGastoBaja() {
        int mesConMenorVenta = 0;
        Float ventaMinima = gastosMensual[0];
        for (int i = 0; i < gastosMensual.length; i++) {
            if (gastosMensual[i] != null && gastosMensual[i] < ventaMinima) {
                ventaMinima = gastosMensual[i];
                mesConMenorVenta = i;
            }
        }
        return mes[mesConMenorVenta]; 
    }

    public Float totalVentas() {
        Float suma = 0.0f;
        for (int i = 0; i < ventasMensual.length; i++) {
            if (ventasMensual[i] != null) {
                suma += ventasMensual[i];
            }
        }
        return Utiles.redondear(suma);
    }

    public Float totalGastos() {
        Float suma = 0.0f;
        for (int i = 0; i < gastosMensual.length; i++) {
            if (gastosMensual[i] != null) {
                suma += gastosMensual[i];
            }
        }
        return Utiles.redondear(suma);
    }

    public Integer verificarDatos() {
        for (int i = 0; i < ventasMensual.length; i++) {
            if (ventasMensual[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public Boolean guardarDatos(Float ventas, Float gastos) {
        int pos = verificarDatos();
        System.out.println("pos " + pos);
        if (pos >= 0) {
            ventasMensual[pos] = ventas;
            gastosMensual[pos] = gastos;
            System.out.println("Guardo en" + pos);
            return true;
        } else {
            return false;
        }
    }

    public Integer comprobarAnio(String anio) {
        Integer x = Integer.parseInt(anio);
        if (x >= 2020 && x <= 2025) {
            return x;
        }
        return 0;
    }

    public Boolean modificarDatos(Float ventasMens, Float gastosMens, Integer pos) {
        if (pos >= 0 && pos < 12) {
            this.ventasMensual[pos] = ventasMens;
            this.gastosMensual[pos] = gastosMens;
            return true;
        }
        return false;
    }

    public Boolean generarFiles() {
        if (ventasMensual != null && gastosMensual != null) {
            String pathVentas = "data" + File.separatorChar + "ventas.txt";
            String pathGastos = "data" + File.separatorChar + "gastos.txt";
            try {
                FileWriter fileVentas = new FileWriter(pathVentas);
                FileWriter fileGastos = new FileWriter(pathGastos);
                for (int i = 0; i < ventasMensual.length; i++) {
                    if (ventasMensual[i] != null) {
                        fileVentas.write(ventasMensual[i].toString() + "\n");
                        fileVentas.flush();
                    }
                }
                fileVentas.close();
                for (int i = 0; i < gastosMensual.length; i++) {
                    if (gastosMensual[i] != null) {
                        fileGastos.write(gastosMensual[i].toString() + "\n");
                        fileGastos.flush();
                    }
                }
                fileGastos.close();
                return true;
            } catch (Exception e) {
                return false;
            }

        }
        return false;
    }

}
