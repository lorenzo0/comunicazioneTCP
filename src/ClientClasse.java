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
<<<<<<< HEAD
    boolean online;
=======
>>>>>>> origin/master
    int port;
    String stringaInput, stringaLetta="";
    String username = "";
    
    Scanner x = new Scanner(System.in);
    public static final String ColoreBlu = "\u001B[34m";
    public static final String ColoreRed = "\u001B[31m";
    public static final String ColoreReset = "\u001B[0m";
        
<<<<<<< HEAD
    GestioneMessaggio gm1; 
    
    public ClientClasse(int port, String serverAddress, GestioneMessaggio gm1)
    {
        this.port = port;
        this.serverAddress = serverAddress;
        online = false;
        this.gm1=gm1;
        //gm1 = new GestioneMessaggio();
=======
    GestioneMessaggio gm1 = new GestioneMessaggio();
    
    public ClientClasse(int port, String serverAddress)
    {
        this.port = port;
        this.serverAddress = serverAddress;
>>>>>>> origin/master
    }
        
    public boolean connessioneAperta()
    {
        if(!connection.isClosed()==true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void connessioneAlServer() 
    {
        try {
            connection = new Socket(serverAddress, port);
            System.out.println("Connessione con il server aperta!");
<<<<<<< HEAD
            online = true;
=======
>>>>>>> origin/master
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void inviaMessaggioAlServer()
    {
        try {
            DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            
            stringaInput = x.nextLine();
            outputServer.writeUTF(stringaInput);
            outputServer.flush();
            
<<<<<<< HEAD
//            gm1.trovaChiScrive();
//            gm1.richiamaMessaggiAutomatici(stringaInput);
=======
            gm1.trovaChiScrive();
            gm1.richiamaMessaggiAutomatici(stringaInput);
>>>>>>> origin/master
            
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ricevoMessaggioDalServer()
    {
        try {
            DataInputStream inputServer = new DataInputStream(connection.getInputStream());
            stringaLetta = inputServer.readUTF();
<<<<<<< HEAD
            
            System.out.println(ColoreBlu + gm1.richiamaMessaggiAutomaticiCiuchetti(stringaLetta) + ColoreReset);
=======
            System.out.println(ColoreBlu + stringaLetta + ColoreReset);
>>>>>>> origin/master
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void stampaMenuScelte()
    {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("In questo programma è possibile inviare al server diversi messaggi automatici come: ");
        System.out.println("/autore: Stampa il socket dell'host che ha inviato l'ultimo messaggio");
        System.out.println("/inLinea: Visualizza se l'host è in ascolto");
        System.out.println("/nonInLinea: Visualizza se l'host non è in ascolto");
        System.out.println("/echo: Invia l'ultimo messaggio presente nella conversazione");
        System.out.println("/end: Chiudi la connessione");
        System.out.println("Se non intendi utilizzare nessuna di queste opzioni, invia un normale messaggio!");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
<<<<<<< HEAD
=======

>>>>>>> origin/master
    }
    
    public void inserisciUsername()
    {
        System.out.println("Inserisci il tuo username: ");
        username = x.nextLine();
    }
<<<<<<< HEAD

    public boolean isOnline() {
        return online;
=======
    
    public String trovaUsername()
    {
        System.out.println(username);
        return username;
>>>>>>> origin/master
    }
    
    public void chiudiConnessione()
    {
        try {
            connection.close();
            System.out.println("Connessione chiusa!");
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/master
