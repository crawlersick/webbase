/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SshClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author sick
 */
public class process_socket  extends Thread{
    private Socket ss;
    private OutputStream os;
    private InputStream is;
    private byte b[];
    private String str1;
    private int n;
    private int cport;
    private String chost;
    private String cip;
    private JTextArea jta;
    private File ofl;
    private File webitems;
    private FileInputStream fis;
    public process_socket(Socket ss,JTextArea jta){
        this.ss=ss;
        this.jta=jta;
    }
    @Override
    public void run()
    {
        try {
            cip=ss.getInetAddress().getHostAddress();
            chost=ss.getInetAddress().getHostName();
            cport=ss.getPort();
            is=ss.getInputStream();
            
            System.out.println(cip+" | "+chost+" | "+cport+" |");
            
            b=new byte[1024];
            str1="";
            StringBuffer sb = new StringBuffer();
            while((n=is.read(b))!=-1)
            {
            sb=sb.append(new String(b,0,n,"UTF-8"));
            str1=sb.toString();
            if(b[n-1]==10&&b[n-2]==13&&b[n-3]==10&&b[n-4]==13) {
                //System.out.println(b[n-2]+" "+b[n-3]+" "+b[n-4]);
                    break;
                                                                }
            }
            

            System.out.println(str1);
            jta.append(ss.toString());
            jta.append("\r\n");
            jta.append(str1);
            jta.append("\r\n");
            is.close();
            ss.close();
            
        } catch (IOException ex) {
            Logger.getLogger(process_socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
