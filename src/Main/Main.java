/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Controlador.Controlador;
import Modelo.Conexion;
import Modelo.Producto;
import Vista.GUI;
import com.sun.jdi.connect.spi.Connection;

/**
 *
 * @author SP
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Producto p= new Producto();
        
        Producto p1 = new Producto("1","3",4.0,4.5);
        GUI view = new GUI();
        
        Controlador ct = new Controlador(p, view);
        ct.iniciar();
        
        
        p.setId("2");
        
        p.setNombre("culion");
        
        
    }
    
}
