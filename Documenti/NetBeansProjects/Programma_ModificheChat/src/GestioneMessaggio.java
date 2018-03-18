
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
    
    /*istanzio client e server con quelli ricevuti nel costruttore
    quando si istanzia l'oggetto GestioneMessaggio nelle classi esecutibili come Client e Server */
    public GestioneMessaggio(ClientClasse c1, ServerClasse s1) {
        this.c1=c1;
        this.s1=s1;
//      s1 = new ServerClasse(2000);
//      c1 = new ClientClasse(2000, "localhost");
//        gm1 = new GestioneMessaggio();
    }
    
    /*doppio costruttore, per non dover istanziare nuovi oggetti nelle classi
    ClientClasse e ServerClasse*/
    public GestioneMessaggio() {
    }
    
    /*metodo per gestire tutti i messaggi che passano con gli stream nel canale di comunicazione aperto
    se non viene richiamato nessun messaggio automatico, la stringa letta viene inoltrata all'host destinatario senza modifiche*/
    public String richiamaMessaggiAutomatici(String messaggioInInputDaChat, String scopriChie)
    {
        
        switch (messaggioInInputDaChat)
        {
            case "/smile":
                return ":)";
                
            case "/echo":
                if(scopriChie.equals("c"))
                {
                    c1.echo();
                }
                else
                {
                    s1.echo();
                }
                
            case "/inLinea":
                if(scopriChie.equals("c"))
                {
                    c1.inLinea();
                }
                else
                {
                    s1.inLinea();
                }
                
            case "/nonInLinea":
                if(scopriChie.equals("c"))
                {
                    c1.nonInLinea();
                }
                else
                {
                    s1.nonInLinea();
                }
              
            case "/autore":
                if(scopriChie.equals("c"))
                {
                    c1.setUsername();
                }
                else
                {
                    s1.setUsername();
                }
                
            case "/end":
                if(scopriChie.equals("c"))
                {
                    c1.chiudiConnessione();
                }
                else
                {
                    s1.chiudiConnessione();
                }
                
                
            default:
                return messaggioInInputDaChat;
        }
    }
    
} 