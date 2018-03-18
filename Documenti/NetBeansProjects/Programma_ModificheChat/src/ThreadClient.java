/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Pisanò
 */
public class ThreadClient extends Thread{
    
    //instanza client e server
    ClientClasse client1;
    ServerClasse server1;

    /*istanzio client e server con quelli ricevuti nel costruttore quando si istanzia 
    l'oggetto ThreadServer nelle classi esecutibili come Client e Server */
    public ThreadClient(ClientClasse client1, ServerClasse server1) {
        this.client1 = client1;
        this.server1 = server1;
    }
    
    //viene automaticamente invocato quando si usa tc.start() nella classe esecutibile server/client
    @Override
    public void run(){
        //leggo l'username del server
        server1.getUsername();
        //client1.connessioneAlServer();
        
      /*while(client1.connessioneAperta()==true){
           client1.ricevoMessaggioDalServer();
           client1.inviaMessaggioAlServer();
      }*/
      
        //finchè è online, il client può ricevere messaggi dal client
        while(client1.isOnline()==true)
        {
           client1.ricevoMessaggioDalServer(server1);
        }
        
    }
} 
