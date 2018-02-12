
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Studenti
 */
public class GestioneMessaggio {
    
    String messaggio;

    public GestioneMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
    
    public void autore(String s)
    {
        System.out.println("Il messaggio è stato inviato dall'utente: "+s);
    }
    
    public void inLinea(ServerSocket sS)
    {
        if(sS.isClosed() != true)
        {
            System.out.println("L'utente è in linea...");
        }
    }
    
    public void nonInLinea(ServerSocket sS)
    {
        if(sS.isClosed() == true)
        {
            System.out.println("L'utente non è in linea...");
        }
    }
    
    public void echo(DataInputStream dis, Socket connection) throws IOException
    {
        String stringaLetta;
        stringaLetta = dis.readUTF();
        
        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.writeUTF(stringaLetta);
        dos.flush();
    }
    
    public void end(ServerSocket sS) throws IOException
    {
        sS.close();
        
        if(sS.isClosed() == true)
        {
            System.out.println("La connessione è chiusa...");
        }
        else
        {
            System.out.println("La connessione non è stata chiusa, server in ascolto...");
        }
    }
    
    public void smile(DataInputStream dis, Socket connection) throws IOException
    {
        String stringaLetta;
        String smile = ":)";
        stringaLetta = dis.readUTF();
        String stringaDaInviare = "";
        
//        if(stringaLetta.equals(smile))
//        {
//            stringaDaInviare = stringaDaInviare.replaceAll(":)", imageToAdd);
//        }
    }
}
