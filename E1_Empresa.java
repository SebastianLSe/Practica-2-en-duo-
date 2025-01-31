package controlador.ejerciciospract;

import controlador.utilidades.Utiles;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import vista.modeloTablas.MTableE1Empresa;

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
