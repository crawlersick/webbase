/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SshClient;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import networktool.DNSQ;

/**
 *
 * @author sick
 */
public class AppspotSocketSimple {
    String webhost;
    String appid;
    private int hostport;
    SSLSocketFactory sslsocketfactory;
    private SSLSocket sock;
    String headerstr; 
    String ipadrs[];
    private String Errormsg;
    private OutputStream ost;
    private InputStream ist;
    private byte b[]=new byte[1024];
    public AppspotSocketSimple(String appid) throws Exception
    {
    this.appid=appid;
    this.webhost=appid+".appspot.com";
    this.hostport=443;
    
    sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        DNSQ dq=new DNSQ();
        
    String tempresult=dq.Getip("208.67.222.222", webhost);
     // String tempresult=dq.Getip("2001:4860:4860::8888", webhost);
    ipadrs= tempresult.split("\\|");
    if (ipadrs==null )
    {
        Errormsg= "Error: not DNS IP found!";
    }
    
System.out.println(ipadrs[0]);
System.out.println(ipadrs[ipadrs.length-1]);
//System.out.println(webhost);
   //sock = (SSLSocket) sslsocketfactory.createSocket();
   // sock = (SSLSocket) sslsocketfactory.createSocket(ipadrs[0],hostport);
  SocketAddress socketAddress = new InetSocketAddress(ipadrs[ipadrs.length-1],hostport);
  
  boolean loopflag = true;
  int loopcnt=0;
  while(loopflag){
  
  try{
      sock = (SSLSocket) sslsocketfactory.createSocket();
  sock.connect(socketAddress,1800);
  loopflag=false;
  }catch (SocketTimeoutException se){
  loopcnt++;
  System.out.println("Time out Retry connect : "+ loopcnt);
 
  }
  }
  
 System.out.println("connected!!");
    sock.setSoTimeout(8000);
 //System.out.println("2222");
    ost=sock.getOutputStream();
    ist=sock.getInputStream();
    
    setheader();
    
    }
    
    public String GteErrormsg(){return Errormsg;}
    
    public void setheader()
    {
    headerstr="GET /"+" HTTP/1.1"+"\n"      
            +"Host: "+webhost+"\n"
            +"Connection: close"+"\n\n";
    }
    
    public void setheader(String URLparameter)
    {
    headerstr="GET /"+URLparameter+" HTTP/1.1"+"\n"      
            +"Host: "+webhost+"\n"
            +"Connection: close"+"\n\n";
    }
    
    public String URLConmunicate(String URLparameter) throws Exception
    {
    if (URLparameter==null)
    {setheader();}
    else
    {setheader(URLparameter);}
    
    ost.write(headerstr.getBytes());
    ost.flush();
    ist.read(b);
    
    return new String(b);
    }
    
    public void closeappsocket() throws IOException{
        ist.close();   
        ost.close();
       sock.close();
    
    }
    
    public static void main(String arg[])
    {
        try {
            AppspotSocketSimple appsock= new AppspotSocketSimple("aiworkserver");
            String restr=appsock.URLConmunicate("kukuana?qtype=123&qvalue=我");
            System.out.println(restr);
            appsock.closeappsocket();
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }
    
    
}
