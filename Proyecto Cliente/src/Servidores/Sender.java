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
    
    public void start(int port, int origen) throws IOException{
        String ip = "192.168.250.3";
        //int port = 4001;
        Socket socket = new Socket(ip, port);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("Enviar preparando...");
        Paquete paquete = new Paquete();
        paquete.setOrigen(origen);
        paquete.setDestino(port);
        paquete.setTime(123);
        System.out.println("Enviar paquete..."+paquete.getOrigen());
        
        oos.writeObject(paquete);
        System.out.println("he terminao");
        socket.close();
    }
    
}
