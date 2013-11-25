/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SshClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 *
 * @author sick
 */
public class SwingWorker_lis_s extends SwingWorker<String, Socket>{
    JTextArea jta;
    private ServerSocket ss;
    private Socket ct;
    public SwingWorker_lis_s(JTextArea jta,int port) throws IOException
    {
        this.jta=jta;
        ss=new  ServerSocket(port);
    }
    @Override
    protected String doInBackground() throws Exception {
           
           while (true){
                    ct=ss.accept();
                    publish(ct);
                            }
        
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }
   // @Override
    protected void process(List<Socket> chunks)  {

        for (Socket cs : chunks){
               // jta.append(cs.toString()+"  start");
                process_socket psck=new process_socket(cs,jta);
                psck.start();
                                }
        
    
    }
    
}
