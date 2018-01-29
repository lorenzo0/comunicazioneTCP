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
import java.net.ServerSocket;
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
        String stringaInput, stringaLetta;
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
            outputServer.writeUTF(stringaInput);
            //svuoto lo stream di eventuali caratteri all'interno con il metodo flush()
            outputServer.flush();
            //Stampo a schermo dell'esecuzione del client cosa dovrebbe arrivare al server *facoltativo*
            System.out.println("Il server leggerà: " + stringaInput);
            
            System.out.println("In attesa di un messaggio dal server...");
            
            //fino ad ora ho solo mandato una stringa al server
            //non chiudo la connessione al client perchè lo faccio rimanere in ascolto
            
            /*creo un oggetto di tipo DataInputStream che mi servirà a leggere il messaggio
            che il server ha inserito da tastiera precedentemente*/ 
            DataInputStream inputServer = new DataInputStream(connection.getInputStream());
               
            /*leggo la stringa inviata dal Server con la funzione readUTF()
            e la inserisco nella variabile stringaLetta*/
            stringaLetta = inputServer.readUTF();
            //stampo a schermo del server cosa il client ha trasmesso nel messaggio letto
            System.out.println("Il Server ha trasmesso: " + stringaLetta);
            
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
