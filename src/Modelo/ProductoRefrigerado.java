/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author SP
 */
public class ProductoRefrigerado extends Producto {

    public ProductoRefrigerado(String nombre, String codigo, double temperatura, double valorBase, double costo) {
        super(nombre, codigo, temperatura, valorBase, costo);
    }
    
    public ProductoRefrigerado() {
        
    }
    
    public void calcularCostoDeAlmacenamiento(){
        
        this.setCosto(getValorBase() * 1.2);
    }
    
}
