/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baseonweb;

import baseonweb.projbeans.Init_Str;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sick
 */
public class LisSock {
    private ServerSocket ss;
    private  Socket clients;
    private ClientTr ct;
    private Init_Str inis;
    public LisSock(int port,int timeout,Init_Str inis) throws IOException
    {
        this.ss=new  ServerSocket(port);
        this.inis=inis;        
    }
        public LisSock(int port,int timeout) throws IOException
    {
        ss=new  ServerSocket(port);
    }
    
    public void toloop() throws IOException{
           while (true){
                    clients=ss.accept();
                    ct = new ClientTr(clients,inis);
                    ct.start();
                            }

    
    }
    
   public static void  main(String args[])
   {
        try {
            LisSock ls=new LisSock(11229,10000);
            ls.toloop();
        } catch (IOException ex) {
            Logger.getLogger(LisSock.class.getName()).log(Level.SEVERE, null, ex);
        }
   }


}
