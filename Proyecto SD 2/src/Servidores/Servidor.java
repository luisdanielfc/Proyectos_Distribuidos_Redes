/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidores;

import Entidades.Paquete;
import Entidades.Transporte;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisfrediani
 */
public class Servidor implements Runnable {
    
    int port;
    //ArrayList<Transporte> transportes = new ArrayList<Transporte>();
    Transporte t = new Transporte();
    public Servidor(){}
    
    public Servidor(int port, Transporte transporte){
        this.port = port;
        this.t = transporte;
    }
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        
        Scanner s = new Scanner(System.in);
        ArrayList<Transporte> transportes = new ArrayList<Transporte>();
        
        //Se le asignan a cada transporte los paquetes
            for(int i = 0; i < 3; i++){
                System.out.println("Cuantos Paquetes quiere almacenar en camion "+(i+1)+"? (Hasta 5)");
                String o = s.nextLine();
                int x = Integer.parseInt(o);
                Transporte t = new Transporte();
                t.setOrigen(1);
                ArrayList<Paquete> paquetes = new ArrayList<Paquete>(5);
            
                for(int j = 0; j < x; j++){               
                    Paquete p = new Paquete();
                    System.out.println("Ingrese nombre de paquete");
                    p.setNombre(s.nextLine());
                    p.setOrigen(1);
                    System.out.println("Ingrese destino del paquete (numero del 2 al 4)");
                    p.setDestino(Integer.parseInt(s.nextLine()));
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.SECOND, 0);
                    long time = cal.getTimeInMillis();
                    p.setTime(time);
                    paquetes.add(p);
                }
            
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.SECOND, 0);
                long time = cal.getTimeInMillis();
                t.setTime(time);
                t.setPaquetes(paquetes);
                transportes.add(t);
                System.out.println("Transporte "+(i+1)+" Listo");               
            }
            
        Servidor s1 = new Servidor(4001, transportes.get(0));
        Servidor s2 = new Servidor(4002, transportes.get(1));
        Servidor s3 = new Servidor(4003, transportes.get(2));
        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s2);
        Thread t3 = new Thread(s3);
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        t2.start();
        TimeUnit.SECONDS.sleep(5);
        t3.start();
       
    }

    @Override
    public void run() {
        
        String ip = "127.0.0.1";
        Socket socket;
        
        try {
            socket = new Socket("localhost", port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            int cont = 0;
            
            //while(cont < 3){
                oos.writeObject(t);
                System.out.println("Transporte Salio al puerto: "+port);
                //TimeUnit.SECONDS.sleep(5);
                //cont++;
            //}

            socket.close();
            Cliente c = new Cliente(port + 10);
            Thread t1 = new Thread(c);
            t1.start();
        
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } 
   }
    
}
