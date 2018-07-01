/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidores;

import Entidades.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
         Cliente c1 = new Cliente(4001);
         Cliente c2 = new Cliente(4002);
         Cliente c3 = new Cliente(4003);
         Thread t1 = new Thread(c1);
         Thread t2 = new Thread(c2);
         Thread t3 = new Thread(c3);
         t1.start();
         t2.start();
         t3.start();
    }

    @Override
    public void run() {

        ServerSocket ss;
        
        try {
            ss = new ServerSocket(port);
            System.out.println("Servidor iniciado, esperando");
            Socket socket = ss.accept();           
            System.out.println("Entro socket por el puerto: "+port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            int cont = 0;
            
            while(cont < 3){
                Transporte t = new Transporte();
                t = (Transporte)ois.readObject();
                Sender s = new Sender(t, port+10);
                Thread thr = new Thread(s);
                thr.start();
                cont++;
            }
            
            ss.close();
        
        } catch (EOFException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    
}
