/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Pisanò
 */
public class ClientClasse {
    
    Socket connection;
    String serverAddress;
    boolean online;
    int port;
    String stringaInput, stringaLetta="";
    String username = "Client";
    
    Scanner x = new Scanner(System.in);
    public static final String ColoreBlu = "\u001B[34m";
    public static final String ColoreRed = "\u001B[31m";
    public static final String ColoreReset = "\u001B[0m";
        
    GestioneMessaggio gm1; 
    public ClientClasse(int port, String serverAddress)
    {
        this.port = port;
        this.serverAddress = serverAddress;
        online = false;
        gm1 = new GestioneMessaggio();
    }
    public void connessioneAlServer() 
    {
        try {
            connection = new Socket(serverAddress, port);
            System.out.println("Connessione con il server aperta!");
            online = true;
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void inviaMessaggioAlServer()
    {
        String chie = "c";
        
        try {
            DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            //System.out.print(username + ":");
            stringaInput = x.nextLine();
            
            gm1.richiamaMessaggiAutomatici(stringaInput, chie);
            
            outputServer.writeUTF(stringaInput);
            outputServer.flush();
            
//            gm1.trovaChiScrive();
            
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ricevoMessaggioDalServer(ServerClasse s1)
    {
        String vuoto = "";
        try {
            DataInputStream inputServer = new DataInputStream(connection.getInputStream());
            stringaLetta = inputServer.readUTF();
            
            System.out.println(ColoreBlu + s1.getUsername() + ": " + gm1.richiamaMessaggiAutomatici(stringaLetta, vuoto) + ColoreReset);
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void echo()
    {
        try {
            DataInputStream inputServer = new DataInputStream(connection.getInputStream());
            stringaLetta = inputServer.readUTF();
            
            DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            outputServer.writeUTF(stringaLetta);
            outputServer.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inLinea()
    {
        online = true;
        System.out.println("Il client è ora online!");
    }
    
    public void nonInLinea()
    {
        online = false;
        System.out.println("Il client è ora offline!");
    }
    
    
    public void stampaMenuScelte()
    {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("In questo programma è possibile inviare al server diversi messaggi automatici come: ");
        System.out.println("/autore: Cambia l'username dell'host che lo richiede");
        System.out.println("/inLinea: Cambia lo stato dell'host che lo richiede in 'online'");
        System.out.println("/nonInLinea: Cambia lo stato dell'host che lo richiede in 'offline'");
        System.out.println("/echo: Invia l'ultimo messaggio presente nella conversazione");
        System.out.println("/end: Chiudi la connessione dell'host che lo richiede");
        System.out.println("Se non intendi utilizzare nessuna di queste opzioni, invia un normale messaggio!");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }
    
    public void setUsername()
    {
        System.out.println("Inserisci il tuo username: ");
        username = x.nextLine();
    }

    public String getUsername() {
        return username;
    }

    public boolean isOnline() {
        return online;
    }
    
    public void chiudiConnessione()
    {
        try {
            if(connection != null)
            {
                connection.close();
                System.out.println("Connessione chiusa! (Client)");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
