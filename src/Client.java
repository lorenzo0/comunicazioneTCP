/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
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
        Scanner x = new Scanner(System.in);

        //apertura della connessione al server sulla porta specificata
        try{
            connection = new Socket(serverAddress, port);
            System.out.println("Connessione aperta");
            
            /*creo un oggetto di tipo DataOutputStream che mi servirà a trasmettere il messaggio
            che il client inserirà da tastiera successivamente*/
            DataOutputStream outputServer = new DataOutputStream(connection.getOutputStream());
            
            /*creo un oggetto di tipo SimpleDateFormat che mi permette di 
            ricavare la data del giorno di oggi e l'orario corrente*/
            SimpleDateFormat dataAttuale; 
            dataAttuale = new SimpleDateFormat();
            //associo la data e l'orario ad una string per passarla come messaggio al server
            String dataStr = dataAttuale.format(new Date());
            //gli dico l'ordine della data che voglio (giorno/mese/anno)
            dataAttuale.applyPattern("dd/MM/yyyy");
            //stampo se mi serve per verificare il funzionamento
            //System.out.println(dataStr);
            
            
            //passo al server la stringa inserita dall'utente
            outputServer.writeUTF(dataStr);
            //svuoto lo stream di eventuali caratteri all'interno con il metodo flush()
            outputServer.flush();
            //Stampo a schermo dell'esecuzione del client cosa dovrebbe arrivare al server *facoltativo*
            System.out.println("Il server leggerà: " + dataStr);
            
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