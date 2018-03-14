
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
<<<<<<< HEAD
import java.util.logging.Level;
import java.util.logging.Logger;
=======
>>>>>>> origin/master

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
<<<<<<< HEAD
//   GestioneMessaggio gm1;
    
    
    public GestioneMessaggio() {
//      s1 = new ServerClasse(2000);
//      c1 = new ClientClasse(2000, "localhost");
//        gm1 = new GestioneMessaggio();
    }
    
    public int trovaChiScrive()
    {
        if(c1!=null)
        {
            return 1; //client scrive
        }
        else
        {
            return 0; //server risponde
        }
    }
    
=======
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
>>>>>>> origin/master
    
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
    
<<<<<<< HEAD
    public void richiamaMessaggiAutomatici(String messaggioInInputDaChat)
    {
        System.out.println("Sono nel metodo");
        
        switch (messaggioInInputDaChat)
        {
            case "/autore":
            System.out.println("Sono nello switch");
            
            if(trovaChiScrive() == 1)
            {
                //scrive client
                c1.inserisciUsername();
            }
            else
            {
                //scrive server
                s1.inserisciUsername();
            }
        }
    }
    public String richiamaMessaggiAutomaticiCiuchetti(String messaggioInInputDaChat)
    {
        System.out.println("Sono nel metodo");
        
        switch (messaggioInInputDaChat)
        {
            case "/smile":
                return ":-)";
            default:
                return messaggioInInputDaChat;
        }
    }
    
    }
=======
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
>>>>>>> origin/master
