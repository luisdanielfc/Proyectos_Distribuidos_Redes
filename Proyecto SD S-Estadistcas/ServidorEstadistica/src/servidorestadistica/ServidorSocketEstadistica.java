/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorestadistica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorSocketEstadistica extends Thread {
    ServerSocket listener;
    Server.ServerClient[] serverClients = new Server.ServerClient[3];
    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    Server.NonServerClient myself; 
    public String serverAddress;
    public Server serv;
    
    public ServidorSocketEstadistica() throws IOException, InterruptedException {
        serv = new Server();
        this.serverAddress = "127.0.0.1";
        myself = serv.new  NonServerClient(serverAddress, PORT);
        if(myself.newConnectionToServer()){
        } else {
            this.start();
            myself = serv.new NonServerClient(serverAddress, PORT);
            TimeUnit.SECONDS.sleep(1);
            myself.newConnectionToServer();
        }
    }
    
    public boolean soyCoordinador() throws IOException  {
        return myself.grandulon();
    }
    
    public boolean hostAvailabilityCheck() { 
        try (Socket s = new Socket("127.0.0.1", 8901)) {
            return true;
        } catch (IOException ex) {
            /* ignore */
        }
        return false;
    }  
    
    public void run() {
        try {
            listener = new ServerSocket(8901);
        } catch (IOException ex) {
            Logger.getLogger(ServidorSocketEstadistica.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("WAITING FOR SERVERS");
        try {
            while (true) {
                Server serv = new Server();
                for(int i = 0; i < 3; i++){
                    serverClients[i] = serv.new ServerClient(listener.accept(), i);
                    System.out.println("SERVER " + i + " CONNECTED");
                }
                
                System.out.println("ALL SERVERS CONNECTED");
                
                for(int i = 0; i < 3; i++){
                    serverClients[i].start();
                    serverClients[i].allClients(serverClients);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorSocketEstadistica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                listener.close();
            } catch (IOException ex) {
                Logger.getLogger(ServidorSocketEstadistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class Server {
    class NonServerClient extends Thread implements Cloneable{
        Socket socket; 
        private BufferedReader in;
        private PrintWriter out;    
        private int numeroGrandulon;
        private String serverAddress;
        private int port;
        
        public NonServerClient(String serverAddress, int port) throws IOException{
                this.serverAddress = serverAddress;
                this.port = port;
        }
        
        public boolean newConnectionToServer() { 
            try {
                this.socket = new Socket(serverAddress, port); 
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);                  
                return true;
            } catch (IOException ex) {
                /* ignore */
            }
            return false;
        }   
        
        public boolean grandulon() throws IOException  {
            if(in.readLine().startsWith("TODOS-CONECTADOS")){}
            
            out.println("solicito-servidor");
            
            while(true){
                if(in.ready()){
                    String cm = in.readLine();
                    System.out.println(cm);
                    if(cm.startsWith("TRUE")){
                        return true;
                    } else {
                        return false;               
                    }   
                }
            }       
        }
        
        public void run() {
            while(true){
                try {
                    if(in.ready()){
                        switch (in.readLine()) {
                            case "servidor-rmi":
                                
                                break;
                        }
                    }
                } catch (IOException ex) {   
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
    }
    
    class ServerClient extends Thread {
        Socket socket;
        BufferedReader input;
        PrintWriter output;
        int servNumber = 0;
        ServerClient sc[];
        // thread handler to initialize stream fields
        public ServerClient(Socket socket, int servNumber) {
            this.servNumber = servNumber;
            this.socket = socket;
            try {
                input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
                );
                output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
        
        public void allClients(ServerClient sc[]) {
            this.sc = sc; 
        }
        
        public void run() {
            try {
                // The thread is only started after everyone connects.
                //output.println("GAME STARTED");
                // Repeatedly get commands from the client and process them.
                //output.println("message");
                        output.println("TODOS-CONECTADOS");     
                        while(true){
                            if(input.ready()){
                                switch (input.readLine()) {
                                    case "solicito-servidor": 
                                        System.out.println("solicitud de ser el servidor de estadisticas de " + servNumber);
                                        if((sc.length - 1) == servNumber) {            
                                            output.println("TRUE");
                                        } else  {
                                            output.println("FALSE");
                                        }
                                        break;
                                }

                                String command = input.readLine();
                                //command.startsWith("");
                                //output.println("");
                            }   
                            /*
                            output.println("fe-de-vida");
                            while(true){
                                int i = 0; 
                                if(input.ready()){
                                    String cm = input.readLine();
                                    if(cm.startsWith("fe-de-vida")){
                                        break;
                                    }
                                }
                                if(i++ == 10000){
                                    output.println("servidor-rmi");                                  
                                }
                            }*/
                        }  
            } catch (IOException e) {
                System.out.println("error: " + e);
            } finally {
                try {socket.close();} catch (IOException e) {}
            }
        }
    }
   
}
