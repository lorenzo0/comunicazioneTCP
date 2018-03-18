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
public class ThreadServer extends Thread{
    
    //instanza server e client
    ServerClasse server1;
    ClientClasse client1;

    /*istanzio client e server con quelli ricevuti nel costruttore quando si istanzia 
    l'oggetto ThreadServer nelle classi esecutibili come Client e Server */
    public ThreadServer(ServerClasse server1, ClientClasse client1) {
        this.server1 = server1;
        this.client1 = client1;
    }
    
    //viene automaticamente invocato quando si usa tc.start() nella classe esecutibile server/client
    @Override
    public void run(){
        //leggo l'username del client
        client1.getUsername();
        //server1.iniziaAscolto();  
        
        /*while(server1.connessioneAperta()==true){
            server1.riceviMessaggiDalClient();
            server1.mandaMessaggiAlClient();
            server1.riceviMessaggiDalClient();*/
        //}
        
        //finchè è online, il server può ricevere messaggi dal client
        while(server1.isOnline()==true){
            server1.riceviMessaggiDalClient(client1);
        }
        
    }
    
} 