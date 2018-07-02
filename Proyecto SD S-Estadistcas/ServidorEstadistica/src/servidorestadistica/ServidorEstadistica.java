/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorestadistica;

import implementacionestadistica.ImplementacionEstadistica;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author mac
 */
public class ServidorEstadistica {    
    public static void main(String[] args) throws IOException, InterruptedException {
        ImplementacionEstadistica e = null;
        ServidorSocketEstadistica sse; 
        sse = new ServidorSocketEstadistica();
        if(sse.soyCoordinador()){
            System.out.println("soy coordinador");
            try {
                Registry reg = LocateRegistry.createRegistry(1099);
                e = new ImplementacionEstadistica();
                
                reg.rebind("mi_estadistica", e);
                System.out.println("Servidor listo...");
                
                Scanner reader = new Scanner(System.in);  // Reading from System.in
                while(true){
                    String n = "";
                    System.out.println("Selecciona la estadistica que deseas ver");
                    System.out.println("1 - Promedio de envio y llegada");
                    System.out.println("2 - Cantidad de paquetes reenviados");
                    System.out.println("3 - Porcentaje de paquetes con carga max");
                    System.out.println("4 - Porcentaje de paquetes que entran y salen al primer intento");
                    n = reader.nextLine(); // Scans the next token of the input as an int.
                        switch(n){
                            case "1": 
                                System.out.println(e.getPromedioEnvioYLlegada());
                                break;
                            case "2": 
                                System.out.println(e.cantidadPaquetesReenviados());
                                break;
                            case "3":
                                System.out.println(e.porcentajeConCargaMax());
                                break;
                            case "4": 
                                System.out.println(e.porcentajeEntranYSalen());
                                break;
                            default: 
                                System.out.println("No es una opcion");
                                break;             
                        }
                }
            } catch(Exception ex) {
                   ex.printStackTrace();
            }           
        }
        sse.myself.start();
    }
}
