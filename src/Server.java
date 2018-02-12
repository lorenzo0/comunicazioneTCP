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
 * @author Monica Ciuchetti
 * @author Lorenzo Pisanò
 */
public class Server {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // porta del server maggiore di 1024 
        int port=2000;
        //oggetto ServerSocket necessario per accettare richieste dal client
        ServerSocket sSocket = null;
        //oggetto da usare per realizzare la connessione TCP
        Socket connection;
        String stringaLetta, stringaInput;
        Scanner x = new Scanner(System.in);

        while(true){
            try{
                
                // il server si mette in ascolto sulla porta voluta
                sSocket = new ServerSocket(port);
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                connection = sSocket.accept();
                System.out.println("Connessione stabilita!");
                System.out.println("Socket server: " + connection.getLocalSocketAddress());
                System.out.println("Socket client: " + connection.getRemoteSocketAddress());
                
                /*creo un oggetto di tipo DataInputStream che mi servirà a leggere il messaggio
                che il client ha inserito da tastiera precedentemente*/                
                DataInputStream inputClient = new DataInputStream(connection.getInputStream());
               
                /*leggo la stringa inviata dal Client con la funzione readUTF()
                e la inserisco nella variabile stringaLetta*/
                stringaLetta = inputClient.readUTF();
                //stampo a schermo del server cosa il client ha trasmesso nel messaggio letto
                System.out.println("Il Client ha trasmesso: " + stringaLetta);
                
                
                //fin qua ricevo e basta
                //ora faccio inviare al server una stringa al client
                
                /*creo un oggetto di tipo DataOutputStream che mi servirà a trasmettere il messaggio
                 che il client inserirà da tastiera successivamente*/
                DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            
                /*faccio inserire all'utente una stringa di caratteri che sarà il messaggio
                trasferito al client tramite lo stream*/
                System.out.println("Inserisci una stringa: ");
                //leggo il valore inserito da tastiera grazie allo scanner nella variabile stringaInput
                stringaInput = x.next();

                //passo al client la stringa inserita dal server
                outputServer.writeUTF(stringaInput);
                //svuoto lo stream di eventuali caratteri all'interno con il metodo flush()
                outputServer.flush();
                
                
            }
               catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
            
            //chiusura della connessione con il client
            try {
                if (sSocket!=null) sSocket.close();
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
        }
      }
}