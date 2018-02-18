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
    ServerSocket sSocket;
    Socket connection;
    String stringaLetta, stringaInput;
    Scanner x = new Scanner(System.in);

    public ServerClasse(int port) {
        this.port = port;
    }
    
    public void iniziaAscolto()
    {
        try {
            System.out.println("Server in attesa di connessioni...");
            sSocket = new ServerSocket(port);
            connection = sSocket.accept();
        } catch (IOException ex) {
            Logger.getLogger(ServerClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mandaMessaggiAlClient()
    {
        try {
            DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            
            stringaInput = x.next();
            outputServer.writeUTF(stringaInput);
            outputServer.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void riceviMessaggiDalClient()
    {
        try {
            DataInputStream inputClient = new DataInputStream(connection.getInputStream());
            
            stringaLetta = inputClient.readUTF();
            System.out.println("Il Client ha detto: " + stringaLetta);
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
    
}
