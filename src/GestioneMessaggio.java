
import java.net.ServerSocket;

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
        
        if(sS.isClosed() == true)
        {
            System.out.println("Connessione chiusa");
        }
        else
        {
            System.out.println("Connessione aperta");
        }
    }
}
