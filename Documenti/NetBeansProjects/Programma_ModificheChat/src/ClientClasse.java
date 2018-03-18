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
            //Cerco una connessione, se il server è in ascolto mi connetto
            connection = new Socket(serverAddress, port);
            System.out.println("Connessione con il server aperta!");
            //variabile che mi serve per capire se il client è online o offline
            online = true;
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void inviaMessaggioAlServer()
    {
        //serve per capire nella classe gestione messaggio se chi manda il messaggio è un client o un server
        String chie = "c";
        
        try {
            //prendo la stringa inserita dall'utente e creo uno stream per comunicare con il server
            DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            //System.out.print(username + ":");
            stringaInput = x.nextLine();
            
            //richiamo il metodo della classe gestione messaggio
            gm1.richiamaMessaggiAutomatici(stringaInput, chie);
            
            //mando con lo stream il messaggio inserito dall'utente
            outputServer.writeUTF(stringaInput);
            //svuoto lo stream
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
            //creo uno stream che mi permette di leggere i messaggi che il server sta mandando
            DataInputStream inputServer = new DataInputStream(connection.getInputStream());
            //inserisco il messaggio letto nella variabile stringaLetta
            stringaLetta = inputServer.readUTF();
            
            //stampo con una determinata formattazzione il messaggio a video
            System.out.println(ColoreBlu + s1.getUsername() + ": " + gm1.richiamaMessaggiAutomatici(stringaLetta, vuoto) + ColoreReset);
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void echo()
    {
        try {
            //creo uno stream che mi permette di leggere i messaggi che il server sta mandando
            DataInputStream inputServer = new DataInputStream(connection.getInputStream());
            //inserisco il messaggio letto nella variabile stringaLetta
            stringaLetta = inputServer.readUTF();
            
            //prendo l'ultima stringa inserita presente nella comunicazione
            DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            //mando con lo stream il messaggio inserito dall'utente
            outputServer.writeUTF(stringaLetta);
            //svuoto lo stream
            outputServer.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inLinea()
    {
        //se è true il client è in linea
        online = true;
        System.out.println("Il client è ora online!");
    }
    
    public void nonInLinea()
    {
        //se è false il client non è in linea
        online = false;
        System.out.println("Il client è ora offline!");
    }
    
    
    public void stampaMenuScelte()
    {
        //mi serve per far capire all'utente quali sono i servizi che il programma offre e come usufruirne
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
        //se l'utente usa /autore, questo metodo permette di cambiare l'username dell'utente
        System.out.println("Inserisci il tuo username: ");
        username = x.nextLine();
    }

    public String getUsername() {
        //ritorna una stringa che contiene l'username attuale del client
        return username;
    }

    public boolean isOnline() {
        return online;
    }
    
    public void chiudiConnessione()
    {
        try {
            //se la connessione è aperta
            if(connection != null)
            {
                //la chiudo
                connection.close();
                System.out.println("Connessione chiusa! (Client)");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
