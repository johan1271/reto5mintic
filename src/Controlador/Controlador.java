/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import Vista.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private GUI view;
    private boolean b;
    
    DefaultTableModel tableModel = new DefaultTableModel();

    public Controlador(Producto producto, GUI view) {
        this.producto = producto;
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
                    
                    JOptionPane.showMessageDialog(null, fila);
                    System.out.println("culioeros");
                    
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
            tableModel.addRow(fila);
        }

    }
    
    
    public boolean setProduct(){
        boolean resp = false;
        
        if (view.idTf.getText().isEmpty() || view.nameTf.getText().isEmpty() || view.tempTf.getText().isEmpty() ||  view.vbTf.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Por favor complete los campos");
            
            resp = false;
        } else{
            producto.setId(view.idTf.getText());
            producto.setNombre(view.nameTf.getText());
            producto.setTemperatura(Double.parseDouble(view.tempTf.getText()));
            producto.setValorBase(Double.parseDouble(view.vbTf.getText()));
            resp = true;
        
        }
        
        return resp;
    } 
    
    public void check(){
        if (producto.guardar(producto)) {
            limpiar();
            listar(producto);
            JOptionPane.showMessageDialog(null, "Se guardó con exito");
                
                
        } else {
            
            JOptionPane.showMessageDialog(null, "error al guardado");
            limpiar();
        
        }  
    
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == view.saveBtn) {
            
            
            
            
            if (setProduct()) {
                if (producto.guardar(producto) && producto.getId() != null) {
                    limpiar();
                    listar(producto);
                    JOptionPane.showMessageDialog(null, "Se guardó con exito");

                } else {
                    JOptionPane.showMessageDialog(null, "error al guardado");

                }
            } 

            System.out.println(producto);
            
            
            
            
        } else if (ae.getSource() == view.updateBtn) {
            
            
            
            if (setProduct()) {
                if (producto.Editar(producto)) {
                    limpiar();
                    listar(producto);
                    JOptionPane.showMessageDialog(null, "Se actualizó con exito");

                } else {
                    JOptionPane.showMessageDialog(null, "error al actualizar");

                }
            } 
            
            
            
            
            
        } else if (ae.getSource() == view.deleteBtn) {
            
            
 
            setProduct();
            
            
            
            if (producto.eliminar(producto)) {
                producto.setId(null);
                limpiar();
                listar(producto);
                JOptionPane.showMessageDialog(null, "Se eliminó con exito");
                
                
            } else {
                JOptionPane.showMessageDialog(null, "error al eliminar");
                limpiar();
            }
            
            
        } else if (ae.getSource() == view.readBtn) {
            
            
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
