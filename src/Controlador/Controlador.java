/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import Modelo.ProductoNoRefrigerado;
import Modelo.ProductoRefrigerado;
import Vista.GUI;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SP
 */
public class Controlador implements ActionListener {

    private Producto producto;
    private ProductoNoRefrigerado productoNr;
    private ProductoRefrigerado productoR;
    private GUI view;
    private boolean b;

    DefaultTableModel tableModel = new DefaultTableModel();

    public Controlador(Producto producto, ProductoNoRefrigerado productoNr, ProductoRefrigerado productoR, GUI view) {
        this.producto = producto;
        this.productoNr = productoNr;
        this.productoR = productoR;
        this.view = view;

        //textFields
        this.view.nameTf.addActionListener(this);
        this.view.idTf.addActionListener(this);
        this.view.tempTf.addActionListener(this);
        this.view.vbTf.addActionListener(this);

        //buttons 
        this.view.readBtn.addActionListener(this);
        this.view.deleteBtn.addActionListener(this);
        this.view.updateBtn.addActionListener(this);
        this.view.saveBtn.addActionListener(this);

        CrearTabla();

        this.view.productsTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int fila = view.productsTable.getSelectedRow();

                    view.idTf.setText((String) view.productsTable.getValueAt(fila, 0));
                    view.nameTf.setText((String) view.productsTable.getValueAt(fila, 1));
                    view.tempTf.setText((String) view.productsTable.getValueAt(fila, 2));
                    view.vbTf.setText((String) view.productsTable.getValueAt(fila, 3));

                    JOptionPane.showMessageDialog(null, "Seleccionaste el producto: " + view.productsTable.getValueAt(fila, 1));

                }
            }
        });

        this.view.vbTf.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();

                boolean numeros = key >= 48 && key <= 57;

                if (!numeros) {
                    evt.consume();
                }

                if (view.idTf.getText().trim().length() == 10) {
                    evt.consume();
                }

            }
        });

        this.view.tempTf.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();

                boolean numeros = key >= 48 && key <= 57;

                if (!numeros) {
                    evt.consume();
                }

                if (view.idTf.getText().trim().length() == 10) {
                    evt.consume();
                }

            }
        });

    }

    public void CrearTabla() {

        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Temperatura");
        tableModel.addColumn("Valor base");
        tableModel.addColumn("Costo");
        view.productsTable.setModel(tableModel);

    }

    public void iniciar() {
        view.setVisible(true);
        view.setTitle("Reto 5");
        view.setLocationRelativeTo(null);

    }

    public void limpiar() {
        view.nameTf.setText("");
        view.idTf.setText("");
        view.tempTf.setText("");
        view.vbTf.setText("");

        tableModel.setRowCount(0);

    }

    public void listar(Producto prod) {
        ArrayList<Producto> listProd = prod.consultar();
        String[] fila = new String[5];
        for (Producto producto1 : listProd) {
            fila[0] = producto1.getId();
            fila[1] = producto1.getNombre();
            fila[2] = String.valueOf(producto1.getTemperatura());
            fila[3] = String.valueOf(producto1.getValorBase());
            fila[4] = String.valueOf(producto1.getCosto());
            tableModel.addRow(fila);
        }

    }

    public boolean setProduct() {
        boolean resp = false;

        //view.idTf.getText().isEmpty() || view.nameTf.getText().isEmpty() || view.tempTf.getText().isEmpty() ||  view.vbTf.getText().isEmpty()
        if (view.idTf.getText().isEmpty() || view.nameTf.getText().isEmpty() || view.tempTf.getText().isEmpty() || view.vbTf.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Falta campos o hay un error de escritura en ellos");

            resp = false;
        } else {

            if (Double.parseDouble(view.tempTf.getText()) >= 0 && Double.parseDouble(view.tempTf.getText()) <= 20) {

                productoR.setId(view.idTf.getText());
                productoR.setNombre(view.nameTf.getText());
                productoR.setTemperatura(Double.parseDouble(view.tempTf.getText()));
                productoR.setValorBase(Double.parseDouble(view.vbTf.getText()));
                productoR.calcularCostoDeAlmacenamiento();

                producto = copy(productoR);;

            } else {

                productoNr.setId(view.idTf.getText());
                productoNr.setNombre(view.nameTf.getText());
                productoNr.setTemperatura(Double.parseDouble(view.tempTf.getText()));
                productoNr.setValorBase(Double.parseDouble(view.vbTf.getText()));
                productoNr.calcularCostoDeAlmacenamiento();

                producto = copy(productoNr);

            }

            resp = true;

        }

        return resp;
    }

    public Producto copy(ProductoNoRefrigerado prodNr) {

        Producto prod = new Producto();

        prod.setId(prodNr.getId());
        prod.setNombre(prodNr.getNombre());
        prod.setTemperatura(prodNr.getTemperatura());
        prod.setValorBase(prodNr.getValorBase());
        prod.setCosto(prodNr.getCosto());

        return prod;

    }

    public Producto copy(ProductoRefrigerado prodR) {

        Producto prod = new Producto();

        prod.setId(prodR.getId());
        prod.setNombre(prodR.getNombre());
        prod.setTemperatura(prodR.getTemperatura());
        prod.setValorBase(prodR.getValorBase());
        prod.setCosto(prodR.getCosto());

        return prod;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //EVENTO BOTON PARA GUARDAR UN PRODUCTO EN LA BASE DE DATOS

        if (ae.getSource() == view.saveBtn) {

            if (setProduct()) {
                if (producto.existente(producto.getNombre())) {
                    JOptionPane.showMessageDialog(null, "Este producto ya existe");
                } else {
                    if (producto.guardar(producto) && producto.getId() != null) {
                        limpiar();
                        listar(producto);
                        JOptionPane.showMessageDialog(null, "Se guardó con exito");

                    } else {
                        JOptionPane.showMessageDialog(null, "error al guardado");

                    }

                }

            }

            

            //EVENTO BOTON PARA ACTUALIZAR UN PRODUCTO EN LA BASE DE DATOS
        } else if (ae.getSource() == view.updateBtn) {

            if (setProduct()) {
                if (producto.existente(producto.getNombre())) {
                    JOptionPane.showMessageDialog(null, "Este producto ya existe");
                } else {

                    if (producto.Editar(producto)) {
                        limpiar();
                        listar(producto);
                        JOptionPane.showMessageDialog(null, "Se actualizó con exito");

                    } else {
                        JOptionPane.showMessageDialog(null, "error al actualizar");

                    }

                }

            }

            //EVENTO BOTON PARA ELIMINAR UN PRODUCTO EN LA BASE DE DATOS
        } else if (ae.getSource() == view.deleteBtn) {

            if (setProduct()) {
                if (producto.eliminar(producto)) {
                    producto.setId(null);
                    limpiar();
                    listar(producto);
                    JOptionPane.showMessageDialog(null, "Se eliminó con exito");

                } else {
                    JOptionPane.showMessageDialog(null, "error al eliminar");
                    limpiar();
                }
            }

        } else if (ae.getSource() == view.readBtn) {
            //EVENTO BOTON PARA CONSULTAR LOS PRODUCTOS EXISTENTES EN LA BASE DE DATOS
            limpiar();
            listar(producto);

            if (producto.buscar(producto)) {
                JOptionPane.showMessageDialog(null, "Se busco con exito");
            } else {
                JOptionPane.showMessageDialog(null, "error al buscar");
            }

        }

    }

}
