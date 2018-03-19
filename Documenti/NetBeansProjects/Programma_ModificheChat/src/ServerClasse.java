
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
import java.io.IOException;

/**
 *
 * @author Lorenzo Pisanò
 */
public class ServerClasse {
    
    int port;
    boolean online;
    ServerSocket sSocket;
    Socket connection;
    String stringaLetta, stringaInput;
    String username;
    
    Scanner x = new Scanner(System.in);
    public static final String ColoreCyan = "\\u001B[36m";
    public static final String ColoreRed = "\u001B[31m";
    public static final String ColoreReset = "\u001B[0m";
    
    GestioneMessaggio gm1;

    public ServerClasse(int port) {
        this.port = port;
        online = false;
        gm1 = new GestioneMessaggio(this);
        username = "Server";
    }
    
    //Server si mette in ascolto quando viene invocato questo metodo
    public void iniziaAscolto()
    {
        try {
            System.out.println("Server in attesa di connessioni...");
            //in una determinata porta
            sSocket = new ServerSocket(port);
            //aspetta un client che si connette
            connection = sSocket.accept();
            //variabile che mi dice se il server è online o offline
            online=true;
        } catch (IOException ex) {
            Logger.getLogger(ServerClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //metodo per mandare messaggi al client
    public void mandaMessaggiAlClient()
    {
        //serve per capire nella classe gestione messaggio se chi manda il messaggio è un client o un server
        String chie = "s";
        try {
             //prendo la stringa inserita dall'utente e creo uno stream per comunicare con il client
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
            Logger.getLogger(ServerClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void riceviMessaggiDalClient(ClientClasse c1)
    {
        String vuoto = "";
        try {
            //creo uno stream che mi permette di leggere i messaggi che il client sta mandando
            DataInputStream inputClient = new DataInputStream(connection.getInputStream());
            //inserisco il messaggio letto nella variabile stringaLetta
            stringaLetta = inputClient.readUTF();
            
            //stampo con una determinata formattazzione il messaggio a video
            System.out.println(ColoreRed + c1.getUsername() + ": " + gm1.richiamaMessaggiAutomatici(stringaLetta, vuoto) + ColoreReset);
            
        } catch (IOException ex) {
            Logger.getLogger(ServerClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void echo()
    {
        try {
            //creo uno stream che mi permette di leggere i messaggi che il client sta mandando
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
        //se è true il server è in linea
        online = true;
        System.out.println("Il server è ora online!");
    }
    
    public void nonInLinea()
    {
        //se è false il server non è in linea
        online = false;
        System.out.println("Il server è ora offline!");
    }
    
    public void setUsername()
    {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Inserisci il tuo nuovo username: ");
            this.username=input.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void setUsername()
//    {
//        //se l'utente usa /autore, questo metodo permette di cambiare l'username dell'utente
//        System.out.println("Inserisci il tuo username: ");
//        username = x.next();
//    }
    
    public String getUsername() {
        //metodo che ritorna una stringa che contiene l'username attuale del client
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
                //chiudo il server socket
                sSocket.close();
                //la chiudo
                connection.close();
                System.out.println("Connessione chiusa! (Server)");
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
} 