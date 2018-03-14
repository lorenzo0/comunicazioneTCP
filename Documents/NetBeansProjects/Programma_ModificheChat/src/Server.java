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
        GestioneMessaggio g = new GestioneMessaggio();
        
        ServerClasse server1 = new ServerClasse(2000, g);
        ThreadServer ts = new ThreadServer(server1);
        
        //server1.iniziaAscolto();
        ts.start();
        
        
//        while(!server1.connessioneAperta()==false)
//        {
//            server1.mandaMessaggiAlClient();
//        }
        
    }  
} 