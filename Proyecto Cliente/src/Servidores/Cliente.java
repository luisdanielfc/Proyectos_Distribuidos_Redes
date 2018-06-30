/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidores;

import Entidades.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author luisfrediani
 */
public class Cliente {

    public Cliente(){}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        //Calendar cal = Calendar.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        //String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        //System.out.println( sdf.format(cal.getTime()) );
        /*String ip = "127.0.0.1";
        int port = 4000;
        Socket socket = new Socket("localhost", port);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("Enviar preparando...");
        Paquete paquete = new Paquete();
        paquete.setOrigen(127);
        paquete.setDestino(port);
        paquete.setTime(123);
        System.out.println("Enviar paquete...");
        
        oos.writeObject(paquete);
        System.out.println("he terminao");*/
        int port = 4000;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Servidor iniciado, esperando");
         Paquete paquete = new Paquete();
        
        while(true){
            Socket socket = ss.accept();
            System.out.println("Entro socket");
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
          
           paquete = (Paquete)ois.readObject();
           
           System.out.println(paquete.getDestino());
           System.out.println(paquete.getOrigen());
           System.out.println(paquete.getTime());
           socket.close();
           break;
        }
        ss.close();
        Sender sender = new Sender();
        sender.start(port, paquete.getOrigen()+1);
    }
    
}
