/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;



/**
 *
 * @author SP
 */
public class Producto {
    
    private String nombre;
    private String id;
    private double temperatura;
    private double valorBase;
    private double costo;
    
    Connection conn = Conexion.getConnection();
    Statement st;
    ResultSet rs;
    

    public Producto(String nombre, String id, double temperatura, double valorBase, double costo) {
        this.nombre = nombre;
        this.id = id;
        this.temperatura = temperatura;
        this.valorBase = valorBase;
        this.costo = costo;
    }

    

    
    
    
    public Producto(){
        
        
    }

    
    //GETTERS
    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getValorBase() {
        return valorBase;
    }
    
    public double getCosto() {
        return costo;
    }
    
    //SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }
    
    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    
    
    
    
    
    
    //METODO PARA GUARDAR UN PRODUCTO EN LA BASE DE DATOS
    public boolean guardar(Producto prod) {
        boolean resp = false;
        try {
            
            String sql = "insert into producto(id, nombre, temperatura, valorbase, costo) values('" + prod.getId() + "','" + prod.getNombre() + "', '" + prod.getTemperatura() + "','" + prod.getValorBase()+ "','" + prod.getCosto()+ "')";
            
            st = conn.createStatement();
            st.execute(sql);
            resp = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resp;
    }
    
    //METODO PARA CONSULTAR LOS PRODUCTOS EXISTENTES EN LA BASE DE DATOS
    public boolean buscar (Producto prod){
        boolean resp = false;
        
        
        try {
            String sql = "SELECT * FROM producto";
            st = conn.createStatement();
            st.execute(sql);
            resp = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resp;
    }
    
    //METODO PARA LISTAR LOS PRODUCTOS CONSULTADOS EN UNA LISTA  EN LA BASE DE DATOS
    public ArrayList<Producto> consultar() {
        ArrayList<Producto> listProd = new ArrayList();
        
        try {
            
            String sql = "SELECT * FROM producto";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            
            while (rs.next()) {
                
                Producto prod = new Producto();
                prod.setId(rs.getString(1));
                prod.setNombre(rs.getString(2));
                prod.setTemperatura(rs.getDouble(3));
                prod.setValorBase(rs.getDouble(4));
                prod.setCosto(rs.getDouble(5));
                
                
                listProd.add(prod);
                
                
                
                
            }
            

        } catch (Exception e) {
            System.out.println(e);
        }

        return listProd;
    }
    
    //METODO PARA ELIMINAR UN PRODUCTO EN LA BASE DE DATOS
    public boolean eliminar(Producto produ) {
        boolean resp = false;
        try {
            
            
            String sql = "DELETE FROM producto WHERE id ='" + produ.getId() + "'";
            st = conn.createStatement();
            st.execute(sql);

            resp = true;
        } catch (Exception e) {
            resp = false;
            System.out.println(e);
        }
        return resp;
    }
    
    //METODO PARA ACTUALIZAR UN PRODUCTO DE LA BASE DE DATOS
    public boolean Editar(Producto produ) {
        boolean resp = false;
        try {
            
            String sql = "update producto set nombre = '" + produ.getNombre()+ "',"
                    + " temperatura ='" + produ.getTemperatura()+ "', valorbase= '" + produ.getValorBase() + "', costo= '" + produ.getCosto()+ "' WHERE id ='" + produ.getId() + "'";
            st = conn.createStatement();
            st.execute(sql);

            resp = true;
        } catch (Exception e) {
            resp = false;
            System.out.println(e);
        }
        return resp;
    }
    
    //METODO PARA VALIDAR QUE UN PRODUCTO DE LA BASE DE DATOS YA EXISTE
    public boolean existente(String nombre) {
        boolean resp = false;
        try {
            String sql = "select nombre from producto where nombre = '" + nombre + "'";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                resp = true;
            } else {
                resp = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resp;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", id=" + id + ", temperatura=" + temperatura + ", valorBase=" + valorBase + ", costo=" + costo + '}';
    }
    
    
    
    

    
    
           
    
}
