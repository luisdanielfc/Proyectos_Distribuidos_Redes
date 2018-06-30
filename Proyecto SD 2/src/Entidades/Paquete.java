/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author luisfrediani
 */
public class Paquete {
    double time;
    int origen;
    int destino;

    public Paquete() {
    }

    public Paquete(double time, int origen, int destino) {
        this.time = time;
        this.origen = origen;
        this.destino = destino;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
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
    
    
}
