/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;

/**
 *
 * @author luisfrediani
 */
public class Paquete implements Serializable {
    long time;
    int origen;
    int destino;
    String nombre;

    public Paquete() {
    }

    public Paquete(long time, int origen, int destino, String nombre) {
        this.time = time;
        this.origen = origen;
        this.destino = destino;
    }

    public double getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
}
