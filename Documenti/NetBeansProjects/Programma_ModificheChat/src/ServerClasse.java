
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
    String username = "Server";
    
    Scanner x = new Scanner(System.in);
    public static final String ColoreBlu = "\u001B[34m";
    public static final String ColoreRed = "\u001B[31m";
    public static final String ColoreReset = "\u001B[0m";
    
    GestioneMessaggio gm1;

    public ServerClasse(int port) {
        this.port = port;
        online = false;
        gm1 = new GestioneMessaggio();
    }
    
    public void iniziaAscolto()
    {
        try {
            System.out.println("Server in attesa di connessioni...");
            sSocket = new ServerSocket(port);
            connection = sSocket.accept();
            online=true;
        } catch (IOException ex) {
            Logger.getLogger(ServerClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mandaMessaggiAlClient()
    {
        String chie = "s";
        try {
            DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            //System.out.print(username + ":");
            stringaInput = x.nextLine();
            gm1.richiamaMessaggiAutomatici(stringaInput, chie);
            outputServer.writeUTF(stringaInput);
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
            DataInputStream inputClient = new DataInputStream(connection.getInputStream());
            stringaLetta = inputClient.readUTF();
            
            System.out.println(ColoreRed + c1.getUsername() + ": " + gm1.richiamaMessaggiAutomatici(stringaLetta, vuoto) + ColoreReset);
            
        } catch (IOException ex) {
            Logger.getLogger(ServerClasse.class.getName()).log(Level.SEVERE, null, ex);
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
                sSocket.close();
                connection.close();
                System.out.println("Connessione chiusa! (Server)");
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
} 
