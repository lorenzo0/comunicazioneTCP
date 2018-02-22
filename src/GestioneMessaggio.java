
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
 * @author Lorenzo Pisanò
 */
public class GestioneMessaggio {
    
    ClientClasse c1;
    ServerClasse s1;
    GestioneMessaggio gm1;
    
    String gestiscoIo;
    
    public GestioneMessaggio() {
    }
    
    public void trovaChiScrive()
    {
        if(c1!=null)
        {
            gestiscoIo = "Client";
        }
        else
        {
            gestiscoIo = "Server";
        }
    }
    
    public void autore(Socket connection)
    {
        c1.trovaUsername();
    }
    
    public void inLinea(Socket connection)
    {
        if(connection.isClosed() != true)
        {
            System.out.println("L'utente è in linea...");
        }
        else
        {
            System.out.println("L'utente non è in linea...");
        }
    }
    
    public void nonInLinea(Socket connection)
    {
        if(connection.isClosed() == true)
        {
            System.out.println("L'utente non è in linea...");
        }
        else
        {
            System.out.println("L'utente è in linea...");
        }
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
    
//    public void smile(DataInputStream dis, Socket connection) throws IOException
//    {
//        String stringaLetta;
//        String smile = ":)";
//        stringaLetta = dis.readUTF();
//        String stringaDaInviare = "";
//        
//        if(stringaLetta.equals(smile))
//        {
//            stringaDaInviare = stringaDaInviare.replaceAll(":)", imageToAdd);
//        }
//    }
    
    public void richiamaMessaggiAutomatici(String messaggioInInputDaChat)
    {
        String stringaAppoggio;
        
        if(messaggioInInputDaChat == "/autore")
        {
            if(gestiscoIo == "Client")
            {
                stringaAppoggio = c1.trovaUsername();
                System.out.println(stringaAppoggio);
            }
            else
            {
                s1.trovaUsername();
            }
        }
        
        if(messaggioInInputDaChat == "/end")
        {
            if(gestiscoIo == "Client")
            {
                c1.chiudiConnessione();
            }
            else
            {
                s1.chiudiConnessione();
            }
        }
    }
}