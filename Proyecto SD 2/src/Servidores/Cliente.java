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
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author luisfrediani
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //Calendar cal = Calendar.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        //String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        //System.out.println( sdf.format(cal.getTime()) );
        String ip = "127.0.0.1";
        int port = 4000;
        Socket socket = new Socket(ip, port);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        Paquete paquete = new Paquete();
        paquete.setOrigen(Integer.parseInt(ip));
        paquete.setDestino(port);
        paquete.setTime(123);
        
        oos.writeObject(paquete);
        System.out.println("he terminao");
    }
    
}
