
import java.io.IOException;
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
 * @author Studenti
 */
public class GestisciTuttiIMessaggi {
    
    Socket connection;
    GestioneMessaggio gm = new GestioneMessaggio(connection);

    public GestisciTuttiIMessaggi(Socket connection) {
        this.connection = connection;
    }
    
    public void richiediTuttiIMetodi()
    {
        try {
            gm.autore(connection);
            gm.echo(connection);
            gm.end(connection);
            gm.inLinea(connection);
            gm.messaggioSalutoUscita(connection);
            gm.nonInLinea(connection);
        } catch (IOException ex) {
            Logger.getLogger(GestisciTuttiIMessaggi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
