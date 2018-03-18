/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Pisanò
 */
public class Server {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //instanza client e server
        ClientClasse client1 = new ClientClasse(2000,"localhost");
        ServerClasse server1 = new ServerClasse(2000);
        
        //instanza thread che permette la corretta ricezione dei messaggi spediti dal server
        ThreadServer ts = new ThreadServer(server1, client1);
        //instanza gestione messaggio per gestire correttamente la comunicazione
        GestioneMessaggio g = new GestioneMessaggio(client1, server1);
        
        //server si mette in ascolto e aspetta che qualche client richieda di connettersi
        server1.iniziaAscolto();
        ts.start(); 
        client1.stampaMenuScelte();
        
        //finchè la connessione è aperta, il server può mandare messaggi al server
        while(server1.isOnline()==true)
        {
            server1.mandaMessaggiAlClient();
        }
        
    }  
} 