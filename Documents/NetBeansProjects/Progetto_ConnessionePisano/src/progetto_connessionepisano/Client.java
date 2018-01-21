/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto_connessionepisano;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.util.Scanner;

/**
 *
 * @author Monica Ciuchetti
 * @author Lorenzo Pisanò
 * 
 */
public class Client {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //oggetto da usare per realizzare la connessione TCP
        Socket connection = null;
        //nome o IP del server
        String serverAddress = "localhost";
        //porta del server in ascolto
        int port = 2000;
        String stringaInput;
        Scanner x = new Scanner(System.in);

        //apertura della connessione al server sulla porta specificata
        try{
            connection = new Socket(serverAddress, port);
            System.out.println("Connessione aperta");
            
            /*creo un oggetto di tipo DataOutputStream che mi servirà a trasmettere il messaggio
            che il client inserirà da tastiera successivamente*/
            DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            
            /*faccio inserire all'utente una stringa di caratteri che sarà il messaggio
            trasferito al server tramite lo stream*/
            System.out.println("Inserisci una stringa: ");
            //leggo il valore inserito da tastiera grazie allo scanner nella variabile stringaInput
            stringaInput = x.next();
            
            //passo al server la stringa inserita dall'utente
            outputServer.writeBytes(stringaInput);
            //svuoto lo stream di eventuali caratteri all'interno con il metodo flush()
            outputServer.flush();
            //Stampo a schermo dell'esecuzione del client cosa dovrebbe arrivare al server *facoltativo*
            System.out.println("Il server leggerà: " + stringaInput);
            
        }
        catch(ConnectException e){
            System.err.println("Server non disponibile!");
        }
        catch(UnknownHostException e1){
            System.err.println("Errore DNS!");
        }

        catch(IOException e2){//
            System.err.println(e2);
            e2.printStackTrace();
        }

        //chiusura della connnessione
        finally{
                try {
            if (connection!=null)
                {
                    connection.close();
                    System.out.println("Connessione chiusa!");
                }
            }
            catch(IOException e){
                System.err.println("Errore nella chiusura della connessione!");
            }
        }
    }
}
