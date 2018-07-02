package implementacionestadistica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
        if(this.cPaquetes != 0){
            return this.tEnvioTotal / this.cPaquetes;             
        } 
        
        return 0;
    }
    
    public int cantidadPaquetesReenviados() {
        return this.cPaquetesReenviados; 
    }
    
    public float porcentajeConCargaMax() {
        if(this.tEnvioTotal != 0)
            return this.tEnvioCargaMax * 100 / this.tEnvioTotal;
        
        return 0;
    }
    
    public float porcentajeEntranYSalen() {
        if(this.cPaquetes != 0)
            return this.cPaquetesPrimerIntento * 100 / this.cPaquetes;
        return 0;
    }
}
