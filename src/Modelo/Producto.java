/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



/**
 *
 * @author SP
 */
public class Producto {
    
    private String nombre;
    private String id;
    private double temperatura;
    private double valorBase;
    Connection conn = Conexion.getConnection();
    Statement st;
    ResultSet rs;
    

    public Producto(String nombre, String id, double temperatura, double valorBase) {
        this.nombre = nombre;
        this.id = id;
        this.temperatura = temperatura;
        this.valorBase = valorBase;
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
    
    
    
    
    
    
    
    
    public boolean guardar(Producto prod) {
        boolean resp = false;
        try {
            
            String sql = "insert into producto(id, nombre, temperatura, valorbase) values('" + prod.getId() + "','" + prod.getNombre() + "', '" + prod.getTemperatura() + "','" + prod.getValorBase()+ "')";
            
            st = conn.createStatement();
            st.execute(sql);
            resp = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resp;
    }
    
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
                
                
                listProd.add(prod);
                
                
                
                
            }
            

        } catch (Exception e) {
            System.out.println(e);
        }

        return listProd;
    }
    
    public boolean eliminar(Producto produ) {
        boolean resp = false;
        try {
            
            System.out.println("clioncito");
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
    
    public boolean Editar(Producto produ) {
        boolean resp = false;
        try {
            
            String sql = "update producto set nombre = '" + produ.getNombre()+ "',"
                    + " temperatura ='" + produ.getTemperatura()+ "', valorbase= '" + produ.getValorBase() + "' WHERE id ='" + produ.getId() + "'";
            st = conn.createStatement();
            st.execute(sql);

            resp = true;
        } catch (Exception e) {
            resp = false;
            System.out.println(e);
        }
        return resp;
    }
    
    public boolean validaciones(Producto prod){
        boolean resp = false;
        
        if (prod.getId().equals(null)) {
            
        }
        
        return resp;
    }
    
    @Override
    public String toString() {
        return this.getClass().getName() + "{" + "nombre=" + nombre + ", id=" + id + ", temperatura=" + temperatura + ", valorBase=" + valorBase + '}';
    }
           
    
}
