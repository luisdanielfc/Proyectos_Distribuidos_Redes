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
import java.net.Socket;

/**
 *
 * @author luisfrediani
 */
public class Sender {
    
    public Sender(){
          
    }
    
    public void start(int port) throws IOException{
        String ip = "127.0.0.1";
        //int port = 4001;
        Socket socket = new Socket("localhost", port);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("Enviar preparando...");
        Paquete paquete = new Paquete();
        paquete.setOrigen(127);
        paquete.setDestino(port);
        paquete.setTime(123);
        System.out.println("Enviar paquete..."+paquete.getOrigen());
        
        oos.writeObject(paquete);
        System.out.println("he terminao");
    }
    
}
