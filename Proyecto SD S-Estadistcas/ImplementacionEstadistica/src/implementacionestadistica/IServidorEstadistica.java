/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacionestadistica;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author mac
 */
public interface IServidorEstadistica extends Remote {
    public void paqueteEnviado() throws RemoteException; 
    public void tiempoDeEnvioPaquete(float t) throws RemoteException; 
    public void paqueteReenviado() throws RemoteException; 
    public void tiempoConCargaMaxima(float t) throws RemoteException; 
    public void paquetePrimerIntento() throws RemoteException; 
    public float getPromedioEnvioYLlegada() throws RemoteException;
    public int cantidadPaquetesReenviados() throws RemoteException;
    public float porcentajeConCargaMax() throws RemoteException;
    public float porcentajeEntranYSalen() throws RemoteException;
}
