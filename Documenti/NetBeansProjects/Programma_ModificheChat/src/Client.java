/*
+ * To change this license header, choose License Headers in Project Properties.
+ * To change this template file, choose Tools | Templates
+ * and open the template in the editor.
+ */

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
+ *
+ * @author Lorenzo Pisanò
+ * 
+ */
public class Client{
    /**
+     * @param args the command line arguments
+     */
    public static void main(String[] args)
    {
        
        
        ServerClasse server1 = new ServerClasse(2000);
        
        ClientClasse client1 = new ClientClasse(2000,"localhost");
        ThreadClient tc = new ThreadClient(client1, server1);
        
        GestioneMessaggio g = new GestioneMessaggio(client1, server1);
        
        client1.connessioneAlServer();
        tc.start();
        client1.stampaMenuScelte();
       
        
        while(client1.isOnline()==true)
        {
            client1.inviaMessaggioAlServer();
        }
       
    }
} 