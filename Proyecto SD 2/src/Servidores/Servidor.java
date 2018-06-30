/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidores;

import Entidades.Paquete;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author luisfrediani
 */
public class Servidor {
    
    public Servidor(){}
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        String ip = "127.0.0.1";
        int port = 4000;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Servidor iniciado, esperando");
        
        while(true){
            Socket socket = ss.accept();
            System.out.println("Entro socket");
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
           Paquete paquete = new Paquete();
           paquete = (Paquete)ois.readObject();
           
           System.out.println(paquete.getDestino());
           System.out.println(paquete.getOrigen());
           System.out.println(paquete.getTime());
           
           break;
        }
        
       
    }
    
}
