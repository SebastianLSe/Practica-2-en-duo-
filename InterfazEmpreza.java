/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_Empresa;

import Ejercicio_Empresa.E1_Empresa;
import Ejercicio_Empresa.Utiles;
import javax.swing.JOptionPane;
import Ejercicio_Empresa.MTableE1Empresa;

/**
 *
 * @author Jostin Vasquez & Sebastian Orellana
 */
public class InterfazEmpreza extends javax.swing.JDialog {

    private final E1_Empresa ejEmpresa = new E1_Empresa();
    private MTableE1Empresa mTablePractica1 = new MTableE1Empresa();
    private Integer fila = -1;

    
    public InterfazEmpreza(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void cargarTabla() {
        mTablePractica1.setEjEmpresa(ejEmpresa);
        tbltabla.setModel(mTablePractica1);
        tbltabla.updateUI();
    }

    private Boolean verificarDatosIn() {
        return txtVentasMensuales.getText().trim().isEmpty() || txtGastosMensuales.getText().trim().isEmpty();
    }

    private void limpiar() {
        // txtMes.setText("");
        txtVentasMensuales.setText("");
        txtGastosMensuales.setText("");
        lblPromedioVentas.setText("");
        lblTotalGastos1.setText("");
        lblTotalVentas.setText("");
        cargarTabla();
        fila = -1;
        tbltabla.clearSelection();
    }

    public Boolean comprobar() {
        lblAnioInforme.setText(txtanioIn.getText());
        Integer anioIn = (ejEmpresa.comprobarAnio(txtanioIn.getText()));
        System.out.println("anioIn " + anioIn);
        if (anioIn != 0) {
            return true;
        }
        return false;
    }

    private void registrarDatosIn() {
        if (verificarDatosIn()) {
            JOptionPane.showMessageDialog(this, "Llene todos los campos");
        } else {
            if (Utiles.validate(txtVentasMensuales.getText().trim())) {
                Float venta = Utiles.transformStringFloat(txtVentasMensuales.getText().trim());
                Float gasto = Utiles.transformStringFloat(txtGastosMensuales.getText().trim());
                if (fila == -1) {
                    if (ejEmpresa.guardarDatos(venta, gasto)) {
                        limpiar();
                        lblPromedioVentas.setText(ejEmpresa.promedioVentas().toString());
                        lblTotalGastos1.setText(ejEmpresa.totalGastos().toString());
                        lblTotalVentas.setText(ejEmpresa.totalVentas().toString());
                        lbl5CantidadMasVentas.setText(ejEmpresa.mesVentaMasAlta());
                        lbl7MesCantVentasBaja.setText(ejEmpresa.mesVentaMasBaja());
                        lbl6GastoAlto.setText(ejEmpresa.mesGastoMasAlto());
                        lbl8MesCantBaja.setText(ejEmpresa.mesGastoMasBajo());

                        lbl1MesMasVentas.setText(ejEmpresa.mesMasAlta());
                        lbl2MesMasGastos.setText(ejEmpresa.mesGastoAlto());
                        lbl3MesMasBajo.setText(ejEmpresa.mesMasBaja());
                        lbl4MesGastoBajo.setText(ejEmpresa.mesGastoBaja());

                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar o se lleno", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (ejEmpresa.modificarDatos(venta, gasto, fila)) {
                        limpiar();
                        lblPromedioVentas.setText(ejEmpresa.promedioVentas().toString());
                        lblTotalGastos1.setText(ejEmpresa.totalGastos().toString());
                        lblTotalVentas.setText(ejEmpresa.totalVentas().toString());
                        lbl5CantidadMasVentas.setText(ejEmpresa.mesVentaMasAlta());
                        lbl7MesCantVentasBaja.setText(ejEmpresa.mesVentaMasBaja());
                        lbl6GastoAlto.setText(ejEmpresa.mesGastoMasAlto());
                        lbl8MesCantBaja.setText(ejEmpresa.mesGastoMasBajo());

                        lbl1MesMasVentas.setText(ejEmpresa.mesMasAlta());
                        lbl2MesMasGastos.setText(ejEmpresa.mesGastoAlto());
                        lbl3MesMasBajo.setText(ejEmpresa.mesMasBaja());
                        lbl4MesGastoBajo.setText(ejEmpresa.mesGastoBaja());
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un numero", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void crearArreglos() {
        ejEmpresa.comprobarAnio((txtanioIn.getText().trim()));
        ejEmpresa.crear();
        // Panel Datos
        txtanioIn.setEditable(false);
        btnGenerar.setEnabled(false);
        btnGuardar.setEnabled(true);
        txtVentasMensuales.setEnabled(true);
        txtGastosMensuales.setEnabled(true);
        // Panel Empresa
        tbltabla.setEnabled(true);
        btnModificar.setEnabled(true);
        btnGuardar.setEnabled(true);
        btntGuardarArchivo.setEnabled(true);
        // Panel Resumen
        lblPromedioVentas.setEnabled(true);
        lblTotalGastos1.setEnabled(true);
        lblTotalVentas.setEnabled(true);
        lblAnioInforme.setEnabled(true);
        lbl1MesMasVentas.setEnabled(true);
        lbl2MesMasGastos.setEnabled(true);
        lbl3MesMasBajo.setEnabled(true);
        lbl4MesGastoBajo.setEnabled(true);
        lbl5CantidadMasVentas.setEnabled(true);
        lbl6GastoAlto.setEnabled(true);
        lbl7MesCantVentasBaja.setEnabled(true);
        lbl8MesCantBaja.setEnabled(true);

        cargarTabla();
    }

    private void cargarDatos() {
        fila = tbltabla.getSelectedRow();
        if (fila >= 0) {
            if (ejEmpresa.getVentasMensuales()[fila] != null && ejEmpresa.getGastosMensual()[fila] != null) {
                txtVentasMensuales.setText(ejEmpresa.getVentasMensuales()[fila].toString());
                txtGastosMensuales.setText(ejEmpresa.getGastosMensual()[fila].toString());
            } else {
                fila = -1;
                txtVentasMensuales.setText("");
                txtGastosMensuales.setText("");
                JOptionPane.showMessageDialog(this, "Seleccione un dato valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            fila = -1;
            JOptionPane.showMessageDialog(this, "Seleccione un dato de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTxtAnioInforme3 = new javax.swing.JLabel();
        lblAnioInforme1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnGenerar = new javax.swing.JButton();
        txtVentasMensuales = new javax.swing.JTextField();
        txtGastosMensuales = new javax.swing.JTextField();
        txtanioIn = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelEmpresa = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbltabla = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btntGuardarArchivo = new javax.swing.JButton();
        panelResumen = new javax.swing.JPanel();
        lbltxtTtalVentas = new javax.swing.JLabel();
        lbltxtTtlGastos = new javax.swing.JLabel();
        lbltxtPromVentas = new javax.swing.JLabel();
        lblTotalVentas = new javax.swing.JLabel();
        lblTotalGastos = new javax.swing.JLabel();
        lblTotalGastos1 = new javax.swing.JLabel();
        lblPromedioVentas = new javax.swing.JLabel();
        lblTxtAnioInforme = new javax.swing.JLabel();
        lblAnioInforme = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblTxtAnioInforme1 = new javax.swing.JLabel();
        lblTxtAnioInforme2 = new javax.swing.JLabel();
        lbl1MesMasVentas = new javax.swing.JLabel();
        lbl5CantidadMasVentas = new javax.swing.JLabel();
        lbl2MesMasGastos = new javax.swing.JLabel();
        lbl6GastoAlto = new javax.swing.JLabel();
        lblTxtAnioInforme4 = new javax.swing.JLabel();
        lblTxtAnioInforme5 = new javax.swing.JLabel();
        lbl3MesMasBajo = new javax.swing.JLabel();
        lbl4MesGastoBajo = new javax.swing.JLabel();
        lbl7MesCantVentasBaja = new javax.swing.JLabel();
        lbl8MesCantBaja = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jLabel6.setText("TOTAL GASTOS:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("$");

        lblTxtAnioInforme3.setText("GASTO MAS ALTO:");

        lblAnioInforme1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAnioInforme1.setText("0");
        lblAnioInforme1.setEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("AÑO DEL INFORME:");

        jLabel2.setText("GASTOS MENSUALES:");

        jLabel3.setText("VENTAS MENSUALES:");

        btnGuardar.setText("GUARDAR");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGenerar.setText("GENERAR");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        txtVentasMensuales.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtVentasMensuales.setEnabled(false);
        txtVentasMensuales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVentasMensualesActionPerformed(evt);
            }
        });

        txtGastosMensuales.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGastosMensuales.setEnabled(false);

        txtanioIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtanioInActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("$");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("$");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(175, 175, 175))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(166, 166, 166)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(165, 165, 165)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtGastosMensuales, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(txtVentasMensuales, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtanioIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGenerar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtanioIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGenerar))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtVentasMensuales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGastosMensuales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panelEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EMPRESA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        tbltabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "MES", "VENTAS MENSUALES", "GASTOS MENSTUALES"
            }
        ));
        tbltabla.setEnabled(false);
        jScrollPane5.setViewportView(tbltabla);

        btnModificar.setText("MODIFICAR");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btntGuardarArchivo.setText("GUARDAR ARCHIVO");
        btntGuardarArchivo.setEnabled(false);
        btntGuardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntGuardarArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEmpresaLayout = new javax.swing.GroupLayout(panelEmpresa);
        panelEmpresa.setLayout(panelEmpresaLayout);
        panelEmpresaLayout.setHorizontalGroup(
            panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(panelEmpresaLayout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addComponent(btntGuardarArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar)))
                .addContainerGap())
        );
        panelEmpresaLayout.setVerticalGroup(
            panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpresaLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btntGuardarArchivo))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        panelResumen.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RESUMEN ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        lbltxtTtalVentas.setText("TOTAL VENTAS:");

        lbltxtTtlGastos.setText("TOTAL GASTOS:");

        lbltxtPromVentas.setText("PROMEDIO DE VENTAS:");

        lblTotalVentas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalVentas.setText("0.0");
        lblTotalVentas.setEnabled(false);

        lblTotalGastos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblTotalGastos1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalGastos1.setText("0.0");
        lblTotalGastos1.setEnabled(false);

        lblPromedioVentas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPromedioVentas.setText("0.0");
        lblPromedioVentas.setEnabled(false);

        lblTxtAnioInforme.setText("AÑO DEL INFORME:");

        lblAnioInforme.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAnioInforme.setText("0");
        lblAnioInforme.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("$");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("$");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("$");

        lblTxtAnioInforme1.setText("VENTA MAS ALTA:");

        lblTxtAnioInforme2.setText("GASTO MAS ALTO:");

        lbl1MesMasVentas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl1MesMasVentas.setEnabled(false);

        lbl5CantidadMasVentas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl5CantidadMasVentas.setText("0.0");
        lbl5CantidadMasVentas.setEnabled(false);

        lbl2MesMasGastos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl2MesMasGastos.setEnabled(false);

        lbl6GastoAlto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl6GastoAlto.setText("0.0");
        lbl6GastoAlto.setEnabled(false);

        lblTxtAnioInforme4.setText("VENTA MAS BAJA:");

        lblTxtAnioInforme5.setText("GASTO MAS BAJO:");

        lbl3MesMasBajo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl3MesMasBajo.setEnabled(false);

        lbl4MesGastoBajo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl4MesGastoBajo.setEnabled(false);

        lbl7MesCantVentasBaja.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl7MesCantVentasBaja.setText("0.0");
        lbl7MesCantVentasBaja.setEnabled(false);

        lbl8MesCantBaja.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl8MesCantBaja.setText("0.0");
        lbl8MesCantBaja.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("$");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("$");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("$");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("$");

        javax.swing.GroupLayout panelResumenLayout = new javax.swing.GroupLayout(panelResumen);
        panelResumen.setLayout(panelResumenLayout);
        panelResumenLayout.setHorizontalGroup(
            panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltxtPromVentas)
                    .addComponent(lbltxtTtalVentas)
                    .addComponent(lbltxtTtlGastos)
                    .addComponent(lblTxtAnioInforme))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumenLayout.createSequentialGroup()
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPromedioVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalGastos1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelResumenLayout.createSequentialGroup()
                                .addComponent(lblTxtAnioInforme4)
                                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelResumenLayout.createSequentialGroup()
                                        .addGap(180, 180, 180)
                                        .addComponent(lblTotalGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelResumenLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl3MesMasBajo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(112, 112, 112)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelResumenLayout.createSequentialGroup()
                                .addComponent(lblTxtAnioInforme2)
                                .addGap(18, 18, 18)
                                .addComponent(lbl2MesMasGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelResumenLayout.createSequentialGroup()
                                .addComponent(lblTxtAnioInforme5)
                                .addGap(18, 18, 18)
                                .addComponent(lbl4MesGastoBajo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelResumenLayout.createSequentialGroup()
                        .addComponent(lblAnioInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(lblTxtAnioInforme1)
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelResumenLayout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl6GastoAlto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbl8MesCantBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl7MesCantVentasBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelResumenLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lbl1MesMasVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl5CantidadMasVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelResumenLayout.setVerticalGroup(
            panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(lblTotalGastos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelResumenLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTxtAnioInforme)
                    .addComponent(lblAnioInforme)
                    .addComponent(lblTxtAnioInforme1)
                    .addComponent(lbl1MesMasVentas)
                    .addComponent(lbl5CantidadMasVentas)
                    .addComponent(jLabel9))
                .addGap(19, 19, 19)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltxtTtalVentas)
                    .addComponent(lblTotalVentas)
                    .addComponent(jLabel8)
                    .addComponent(lbl2MesMasGastos)
                    .addComponent(lbl6GastoAlto)
                    .addComponent(lblTxtAnioInforme2)
                    .addComponent(jLabel12))
                .addGap(16, 16, 16)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumenLayout.createSequentialGroup()
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbltxtTtlGastos)
                            .addComponent(lblTotalGastos1)
                            .addComponent(jLabel10))
                        .addGap(19, 19, 19)
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbltxtPromVentas)
                            .addComponent(lblPromedioVentas)
                            .addComponent(jLabel11)))
                    .addGroup(panelResumenLayout.createSequentialGroup()
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTxtAnioInforme4)
                            .addComponent(lbl3MesMasBajo)
                            .addComponent(lbl7MesCantVentasBaja)
                            .addComponent(jLabel13))
                        .addGap(16, 16, 16)
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl4MesGastoBajo)
                            .addComponent(lbl8MesCantBaja)
                            .addComponent(lblTxtAnioInforme5)
                            .addComponent(jLabel14))))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        registrarDatosIn();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        // TODO add your handling code here:
        if (comprobar()) {
            crearArreglos();
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un año válido entre 2020 a 2025", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void txtanioInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtanioInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtanioInActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        cargarDatos();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btntGuardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntGuardarArchivoActionPerformed
        // TODO add your handling code here:
        if (ejEmpresa.generarFiles() == true) {
            JOptionPane.showMessageDialog(this, "Guardado correctamente", "OK!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Hubo un error, intente mas tarde.", "xD", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btntGuardarArchivoActionPerformed

    private void txtVentasMensualesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVentasMensualesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVentasMensualesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazEmpreza.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazEmpreza.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazEmpreza.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazEmpreza.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InterfazEmpreza dialog = new InterfazEmpreza(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btntGuardarArchivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl1MesMasVentas;
    private javax.swing.JLabel lbl2MesMasGastos;
    private javax.swing.JLabel lbl3MesMasBajo;
    private javax.swing.JLabel lbl4MesGastoBajo;
    private javax.swing.JLabel lbl5CantidadMasVentas;
    private javax.swing.JLabel lbl6GastoAlto;
    private javax.swing.JLabel lbl7MesCantVentasBaja;
    private javax.swing.JLabel lbl8MesCantBaja;
    private javax.swing.JLabel lblAnioInforme;
    private javax.swing.JLabel lblAnioInforme1;
    private javax.swing.JLabel lblPromedioVentas;
    private javax.swing.JLabel lblTotalGastos;
    private javax.swing.JLabel lblTotalGastos1;
    private javax.swing.JLabel lblTotalVentas;
    private javax.swing.JLabel lblTxtAnioInforme;
    private javax.swing.JLabel lblTxtAnioInforme1;
    private javax.swing.JLabel lblTxtAnioInforme2;
    private javax.swing.JLabel lblTxtAnioInforme3;
    private javax.swing.JLabel lblTxtAnioInforme4;
    private javax.swing.JLabel lblTxtAnioInforme5;
    private javax.swing.JLabel lbltxtPromVentas;
    private javax.swing.JLabel lbltxtTtalVentas;
    private javax.swing.JLabel lbltxtTtlGastos;
    private javax.swing.JPanel panelEmpresa;
    private javax.swing.JPanel panelResumen;
    private javax.swing.JTable tbltabla;
    private javax.swing.JTextField txtGastosMensuales;
    private javax.swing.JTextField txtVentasMensuales;
    private javax.swing.JTextField txtanioIn;
    // End of variables declaration//GEN-END:variables
}
