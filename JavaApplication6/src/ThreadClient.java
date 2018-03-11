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
    
    ClientClasse client1;

    public ThreadClient(ClientClasse client1) {
        this.client1 = client1;
    }
    
    public void run(){
        while(client1.connessioneAperta()==true){
            client1.ricevoMessaggioDalServer();
        }
    }
}