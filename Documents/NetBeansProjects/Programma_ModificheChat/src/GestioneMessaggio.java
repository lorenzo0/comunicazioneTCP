
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lorenzo Pisanò
 */
public class GestioneMessaggio {
    
    ClientClasse c1;
    ServerClasse s1;
//   GestioneMessaggio gm1;
    
    
    public GestioneMessaggio() {
//      s1 = new ServerClasse(2000);
//      c1 = new ClientClasse(2000, "localhost");
//        gm1 = new GestioneMessaggio();
    }
    
    
    public void echo(Socket connection) throws IOException
    {
        DataInputStream dis = new DataInputStream(connection.getInputStream());
        String stringaLetta;
        stringaLetta = dis.readUTF();
        
        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeUTF(stringaLetta);
        dos.flush();
    }
    
    public void end(Socket connection) throws IOException
    {
        connection.close();
        
        if(connection.isClosed() == true)
        {
            System.out.println("La connessione è chiusa...");
        }
        else
        {
            System.out.println("La connessione non è stata chiusa, server in ascolto...");
        }
    }
    
    public void messaggioSalutoUscita(Socket connection) throws IOException
    {
        String stringa = "Sto uscendo dalla chat, alla prossima!";
        
        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeUTF(stringa);
        dos.flush();
    }
    
    public String richiamaMessaggiAutomatici(String messaggioInInputDaChat)
    {
        
        switch (messaggioInInputDaChat)
        {
            case "/smile":
                return ":)";
                
            case "/echo":
                System.out.println("Hai chiesto di ripetere: " + messaggioInInputDaChat);
                return messaggioInInputDaChat;
                
            default:
                return messaggioInInputDaChat;
        }
    }
    
} 