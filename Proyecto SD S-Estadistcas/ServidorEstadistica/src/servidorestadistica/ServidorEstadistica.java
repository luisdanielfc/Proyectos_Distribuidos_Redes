/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorestadistica;

import implementacionestadistica.ImplementacionEstadistica;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author mac
 */
public class ServidorEstadistica {    
    public static void main(String[] args) throws IOException, InterruptedException {
        ServidorSocketEstadistica sse; 
        sse = new ServidorSocketEstadistica();
        if(sse.soyCoordinador()){
            System.out.println("soy coordinador");
            try {
                Registry reg = LocateRegistry.createRegistry(1099);
                ImplementacionEstadistica e = new ImplementacionEstadistica();
                
                reg.rebind("mi_estadistica", e);
                System.out.println("Servidor listo...");
            } catch(Exception ex) {
                   ex.printStackTrace();
            }           
        }
        
        sse.myself.start();
    }
}
