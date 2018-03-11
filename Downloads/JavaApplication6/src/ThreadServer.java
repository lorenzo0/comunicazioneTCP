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
    
    ServerClasse server1;

    public ThreadServer(ServerClasse server1) {
        this.server1 = server1;
    }
    
    public void run(){
        while(server1.connessioneAperta()==true){
            server1.riceviMessaggiDalClient();
        }
    }
    
}