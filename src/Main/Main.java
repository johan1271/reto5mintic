/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Controlador.Controlador;
import Modelo.Conexion;
import Modelo.Producto;
import Modelo.ProductoNoRefrigerado;
import Modelo.ProductoRefrigerado;
import Vista.GUI;
import com.sun.jdi.connect.spi.Connection;

/**
 *
 * @author SP
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Producto p= new Producto();
        ProductoNoRefrigerado pN= new ProductoNoRefrigerado();
        ProductoRefrigerado pR= new ProductoRefrigerado();
        GUI view = new GUI();
        
        Controlador ct = new Controlador(p, pN, pR,view);
        ct.iniciar();
        
        
        
        
        
    }
    
}
