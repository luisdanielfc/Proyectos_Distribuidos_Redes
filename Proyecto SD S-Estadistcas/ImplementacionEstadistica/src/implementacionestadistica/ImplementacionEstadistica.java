package implementacionestadistica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidorestadistica.ServidorSocketEstadistica;

public class ImplementacionEstadistica extends UnicastRemoteObject implements IServidorEstadistica{
    private int cPaquetes; 
    private float tEnvioTotal;
    private float tEnvioCargaMax;
    private int cPaquetesReenviados;
    private int cPaquetesPrimerIntento;
    
    public ImplementacionEstadistica() throws RemoteException{
        this.cPaquetes = 0;
        this.tEnvioTotal = 0;
        this.tEnvioCargaMax = 0; 
        this.cPaquetesPrimerIntento = 0;
        this.cPaquetesReenviados = 0; 
    }
    
    @Override
    public void paqueteEnviado() throws RemoteException {
        this.cPaquetes++;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tiempoDeEnvioPaquete(float t) throws RemoteException {
        this.tEnvioTotal += t; 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paqueteReenviado() throws RemoteException {
        this.cPaquetesReenviados++;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tiempoConCargaMaxima(float t) throws RemoteException {
        this.tEnvioCargaMax += t; 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paquetePrimerIntento() throws RemoteException {
        this.cPaquetesPrimerIntento++;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public float getPromedioEnvioYLlegada() {
        return this.tEnvioTotal / this.cPaquetes;
    }
    
    public int cantidadPaquetesReenviados() {
        return this.cPaquetesReenviados; 
    }
    
    public float porcentajeConCargaMax() {
        return this.tEnvioCargaMax * 100 / this.tEnvioTotal;
    }
    
    public float porcentajeEntranYSalen() {
        return this.cPaquetesPrimerIntento * 100 / this.cPaquetes;
    }
}
