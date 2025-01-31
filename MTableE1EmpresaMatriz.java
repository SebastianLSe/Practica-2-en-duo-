/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_Empresa;

import Ejercicio_Empresa.E1_Empresa;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author danny
 */
public class MTableE1EmpresaMatriz extends AbstractTableModel {
    private E1_Empresa ejEmpresa;

    public E1_Empresa getEjEmpresa() {
        return ejEmpresa;
    }

    public void setEjEmpresa(E1_Empresa ejEmpresa) {
        this.ejEmpresa = ejEmpresa;
    }
    

    @Override
    public int getRowCount() {
        return ejEmpresa.getMes().length;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        if (arg1 == 0) {
            switch (arg0) {
                case 0: return "Enero";
                case 1: return "Febrero";
                case 2: return "Marzo";
                case 3: return "Abril";
                case 4: return "Mayo";
                case 5: return "Junio";
                case 6: return "Julio";
                case 7: return "Agosto";
                case 8: return "Septiembre";
                case 9: return "Octubre";
                case 10: return "Noviembre";
                case 11: return "Diciembre";
                default: return null;
            }
        }        
        if (arg1 == 1) {
            return ejEmpresa.getVentasMensuales()[arg0]; 
        } else if (arg1 == 2) {
            return ejEmpresa.getGastosMensual()[arg0];
        }
        switch (arg1) {            
            case 1: return null;
            case 2: return ejEmpresa.getVentasMensuales();
            case 3: return ejEmpresa.getGastosMensual();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0: return "Meses";            
            case 1: return "Ventas Mensuales";
            case 2: return "Gastos Mensuales";
            default: return null;
        }
    }
    
}