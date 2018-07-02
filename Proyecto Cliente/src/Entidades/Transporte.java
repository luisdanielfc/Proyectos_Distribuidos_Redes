/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author luisfrediani
 */
public class Transporte implements Serializable {
    int origen;
    ArrayList<Paquete> paquetes = new ArrayList<Paquete>();
    long time;

    public Transporte() {
    }

    public Transporte(int origen, long time) {
        this.origen = origen;
        this.time = time;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public ArrayList<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(ArrayList<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public double getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    
    
    
    
}
