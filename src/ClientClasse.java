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
    int port;
    String stringaInput, stringaLetta="";
    Scanner x = new Scanner(System.in);
        
    GestioneMessaggio gm = new GestioneMessaggio(stringaLetta);
    
    public ClientClasse(int port, String serverAddress)
    {
        this.port = port;
        this.serverAddress = serverAddress;
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
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void inviaMessaggioAlServer()
    {
        try {
            DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            
            stringaInput = x.next();
            outputServer.writeUTF(stringaInput);
            outputServer.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ricevoMessaggioDalServer()
    {
        try {
            DataInputStream inputServer = new DataInputStream(connection.getInputStream());
            
            stringaLetta = inputServer.readUTF();
            System.out.println("Il Server ha detto: " + stringaLetta);
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stampaMenuScelte()
    {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("In questo programma è possibile inviare al server diversi messaggi automatici come: ");
        System.out.println("autore: Stampa il socket dell'host che ha inviato l'ultimo messaggio");
        System.out.println("inLinea: Visualizza se l'host è in ascolto");
        System.out.println("nonInLinea: Visualizza se l'host non è in ascolto");
        System.out.println("echo: Invia l'ultimo messaggio presente nella conversazione");
        System.out.println("end: Chiudi la connessione");
        System.out.println("Se non intendi utilizzare nessuna di queste opzioni, invia un normale messaggio!");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

    }
    
    public void scegliInBaseAlMenu(Socket connection, String stringaInput)
    {
        switch(stringaInput)
            {
                case "end":
        {
            try {
                gm.messaggioSalutoUscita(connection);
            } catch (IOException ex) {
                Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        {
            try {
                gm.end(connection);
            } catch (IOException ex) {
                Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                    break;

                case "autore":
                    gm.autore(connection);
                    break;

                case "inLinea":
                    gm.inLinea(connection);
                    break;

                case "nonInLinea":
                    gm.nonInLinea(connection);
                    break;

                case "echo":
        {
            try {
                gm.echo(connection);
            } catch (IOException ex) {
                Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                    break;    
            }
    }
        
        
}
