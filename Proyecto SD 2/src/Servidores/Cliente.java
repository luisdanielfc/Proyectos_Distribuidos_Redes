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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisfrediani
 */
public class Cliente implements Runnable {
    
    int port;
    public Cliente(){}
    
    public Cliente(int port){
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket ss;
        try {
            ss = new ServerSocket(port);
            while(true){
                Socket socket = ss.accept();
                System.out.println("Entro socket por el puerto: "+port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Transporte t = (Transporte)ois.readObject();
                TimeUnit.SECONDS.sleep(20);
                System.out.println("Llego un transporte "+t.getTime());
                socket.close();
                break;
            }
            ss.close();
        } catch (EOFException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //System.out.println("Servidor iniciado, esperando");
        
        
        
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
