<<<<<<< HEAD
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

>>>>>>> origin/master
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
<<<<<<< HEAD
    boolean online;
=======
>>>>>>> origin/master
    ServerSocket sSocket;
    Socket connection;
    String stringaLetta, stringaInput;
    String username = "";
    
    Scanner x = new Scanner(System.in);
    public static final String ColoreBlu = "\u001B[34m";
    public static final String ColoreRed = "\u001B[31m";
    public static final String ColoreReset = "\u001B[0m";
    
<<<<<<< HEAD
    GestioneMessaggio gm1;

    public ServerClasse(int port, GestioneMessaggio gm1) {
        this.port = port;
        online = false;
        this.gm1=gm1;
//      gm1 = new GestioneMessaggio();
=======
    GestioneMessaggio gm1 = new GestioneMessaggio();

    public ServerClasse(int port) {
        this.port = port;
>>>>>>> origin/master
    }
    
    public void iniziaAscolto()
    {
        try {
            System.out.println("Server in attesa di connessioni...");
            sSocket = new ServerSocket(port);
            connection = sSocket.accept();
<<<<<<< HEAD
            online=true;
=======
>>>>>>> origin/master
        } catch (IOException ex) {
            Logger.getLogger(ServerClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mandaMessaggiAlClient()
    {
        try {
            DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            stringaInput = x.nextLine();
<<<<<<< HEAD
            gm1.richiamaMessaggiAutomatici(stringaInput);
            outputServer.writeUTF(stringaInput);
            outputServer.flush();
            
//            gm1.trovaChiScrive();
            
=======
            outputServer.writeUTF(stringaInput);
            outputServer.flush();
            
            gm1.trovaChiScrive();
            gm1.richiamaMessaggiAutomatici(stringaInput);
>>>>>>> origin/master
            
        } catch (IOException ex) {
            Logger.getLogger(ServerClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void riceviMessaggiDalClient()
    {
        try {
            DataInputStream inputClient = new DataInputStream(connection.getInputStream());
            stringaLetta = inputClient.readUTF();
            System.out.println(ColoreRed + stringaLetta + ColoreReset);
        } catch (IOException ex) {
            Logger.getLogger(ServerClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            sSocket.close();
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
