/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidores;

import Entidades.Paquete;
import Entidades.Transporte;
import implementacionestadistica.IServidorEstadistica;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
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
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, RemoteException, NotBoundException {
        
        Scanner s = new Scanner(System.in);
        ArrayList<Transporte> transportes = new ArrayList<Transporte>();
        
        //Se le asignan a cada transporte los paquetes
            for(int i = 0; i < 3; i++){
                System.out.println("Cuantos Paquetes quiere almacenar en camion "+(i+1)+"? (Hasta 5)");
                String o = s.nextLine();
                int x = Integer.parseInt(o);
                Transporte t = new Transporte();
                t.setOrigen(1);
                ArrayList<Paquete> paquetes = new ArrayList<Paquete>();
                Registry myReg = LocateRegistry.getRegistry("192.168.250.7", 1099);
                IServidorEstadistica ise = (IServidorEstadistica) myReg.lookup("mi_estadistica");
                int add=5;
            
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
                    add = add*i;
                    float tiempo = (p.getDestino()-1)*20 + (p.getDestino()-1)*10 + add;
                    ise.paqueteEnviado();
                    ise.tiempoDeEnvioPaquete(tiempo);
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
            //socket.();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = null;
            Registry myReg = LocateRegistry.getRegistry("192.168.250.7", 1099);
            IServidorEstadistica ise = (IServidorEstadistica) myReg.lookup("mi_estadistica");
            //pw.print("syn");
            
            /*
            int cont = 0;
            while(cont < 500){
                if(br.ready()){
                    s = br.readLine();
                    break;
                }
                else cont++;
                
            }*/
            
            
           // if("ack".equals(s)){
                //System.out.println("Recibi ACK");
                if(t.getPaquetes().size() == 5){
                    
                    ise.tiempoConCargaMaxima(10);
                }
                oos.writeObject(t);
                System.out.println("Transporte Salio al puerto: "+port);
            //}
            
                //TimeUnit.SECONDS.sleep(5);
                //cont++;
            //}

            socket.close();
            Cliente c = new Cliente(port+10);
            c.run();
            //Thread t1 = new Thread(c);
            //t1.start();
        
            
        } catch (IOException ex) {
            //System.out.println("se cayo el gafo");
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedOperationException ex){
            ex.printStackTrace();
        }
   }
    
    public String check(){
        String[] ips = null;
        //Estan en el orden de sucesores
        ips[0] = "";
        ips[1] = "";
        ips[2] = "";
        ips[3] = "";
        ips[4] = "";
        ips[5] = "";
        
        return null;
    }
    
}
