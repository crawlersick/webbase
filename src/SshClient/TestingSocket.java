/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SshClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import networktool.DNSQ;

/**
 *
 * @author sick
 */
public class TestingSocket {
   private int port;
   private String host;
   private SSLSocket sock;
   private OutputStream ost;
   private InputStream ist;
   private byte b[]=new byte[1024];
   public TestingSocket(String host,int port) throws UnknownHostException, IOException
   {
       this.host=host;
       this.port=port;
                   SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
             sock = (SSLSocket) sslsocketfactory.createSocket(host,port);
       
      // sock=new Socket(host,port);       
       ost=sock.getOutputStream();
       ist=sock.getInputStream();
   }
   public void Writetosock(String wstr) throws IOException
   {
       ost.write(wstr.getBytes());
       ost.flush();
       
       ist.read(b);
       ost.close();
       sock.close();
   }
   
   public byte[] getre(){
   return b;
   }
   
   public static void main(String arg[])
   {
       String urlll="";
      // String urll="api.weibo.com";
        String urll="aiworkserver.appspot.com";
       String hhh="GET /"+" HTTP/1.1"+"\n"
+"Host: "+urll+"\n\n";

                      

//                       +"User-Agent: Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.2.18) Gecko/20110628 Ubuntu/10.04 (lucid) Firefox/3.6.18"+"\n"
// +"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"+"\n"
//Accept-Language: en-us,en;q=0.5"+"\n"
//+"Accept-Encoding: gzip,deflate"+"\n"
//+"Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7"+"\n"
//+"Keep-Alive: 115"+"\n"
//+"Connection: keep-alive"+"\n\n";
 
       
       
       
        try {
            
                         DNSQ dq=new DNSQ();

                   //   dq.Getip("8.8.8.8", "www.youtube.com");
                  // dq.Getip("8.8.8.8", "t66y.com");
                      String ipadr= dq.Getip("8.8.4.4", urll);
            System.out.println(ipadr);
            String ipadrs[]=ipadr.split("\\|");
            TestingSocket tests= new TestingSocket(ipadrs[0],443);
            tests.Writetosock(hhh);
            System.out.println(new String(tests.getre()));
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
   
   }
   
}
