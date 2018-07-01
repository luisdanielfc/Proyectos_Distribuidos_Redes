/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidores;

import Entidades.Paquete;
import Entidades.Transporte;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisfrediani
 */
public class Sender implements Runnable {
    
    Transporte transporte = new Transporte();
    int port;
    public Sender(){
          
    }
    
    public Sender(Transporte t, int port) throws IOException{
        this.transporte = t;
        this.port = port;
        //start(port, t);
    }
    
    public void main(String[] args) throws IOException{
        //start(port, transporte);
    }
 

    @Override
    public void run() {
        
        String ip = "127.0.0.1";
        Socket socket;
        
        try {
            int cont = 0;
            
            while(cont < transporte.getPaquetes().size()){
                System.out.println("Procesando paquete...");
                TimeUnit.SECONDS.sleep(10);
                
                if (transporte.getPaquetes().get(cont).getDestino() == 2)
                    System.out.println("Paquete "+transporte.getPaquetes().get(cont).getNombre()+" Procesado");
                else
                    System.out.println("Paquete "+transporte.getPaquetes().get(cont).getNombre()+" no es de aqui, moviendo al camion");
                              
                System.out.println("Montando paquete...");
                cont++;
            }
            
            System.out.println("Saliero un camion...");
            TimeUnit.SECONDS.sleep(20);
            System.out.println("Conectandose al puerto de principal: "+port);
            socket = new Socket(ip, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(transporte);
            socket.close();
            System.out.println("he terminado");
            
        } catch (EOFException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
