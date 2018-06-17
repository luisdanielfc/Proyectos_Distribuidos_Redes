/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author luisfrediani
 */
public class Paquete {
    int time;
    String destino;
    String origen;
    double[] tiempos;
    
    public Paquete(){
    
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public double[] getTiempos() {
        return tiempos;
    }

    public void setTiempos(double[] tiempos) {
        this.tiempos = tiempos;
    }
    
    
}
