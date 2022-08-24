/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author SP
 */
public class ProductoNoRefrigerado extends Producto {

    public ProductoNoRefrigerado(String nombre, String codigo, double temperatura, double valorBase, double costo) {
        super(nombre, codigo, temperatura, valorBase, costo);
    }
    



    public ProductoNoRefrigerado() {
        
             
    }


    public void calcularCostoDeAlmacenamiento(){
    
        this.setCosto(getValorBase() * 1.1);
        
    }
}
