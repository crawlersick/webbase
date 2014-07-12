/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 

 
 */
package SshClient;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import networktool.DNSQ;
import sun.misc.BASE64Decoder;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author sick
 */
public class AppspotSocket {
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
    ByteBuffer bbuf = ByteBuffer.allocate(2000000);
    public AppspotSocket(String appid) throws Exception
    {
    this.appid=appid;
    this.webhost=appid+".appspot.com";
    this.hostport=443;
    
    sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        DNSQ dq=new DNSQ();
     
    String googlelist[]={
    //   "www.google.com.hk","www.google.com.tw","www.google.com.sg","www.google.co.jp","www.google.sg","www.google.cat","www.google.jp"
     //   ,"google.io","google.com.my","google.com.pr","google.sk","google.st",
      //  "google.de","google.cz","google.ee","google.gf","google.gp","google.hn"
            
        "www.google.sg","www.google.cat","www.google.co.jp","google.st",  "google.io","google.com.my","www.google.com.tw"
    };
    
    /*
     * 
173.194.127.18
74.125.128.94
173.194.127.55
74.125.128.94
173.194.127.255
173.194.127.31
173.194.127.215
74.125.128.94
173.194.127.243
173.194.127.55
74.125.128.94
     * 
     */
    
    int effecnt=0;
    String effelist[]=new String[googlelist.length];
    String dnsserverips[]={"2001:4860:4860::8888","114.114.114.114","114.114.115.115","8.8.8.8","8.8.4.4"};
    int ipsidx=0;
    while(effecnt==0){
    for(int i=0;i<googlelist.length;i++)
    {
        try{
       //String tempresult=dq.Getip("114.114.114.114", googlelist[i]);
        // String tempresult=dq.Getip("2001:4860:4860::8888", googlelist[i]);
       //     String tempresult=dq.Getip("8.8.8.8", googlelist[i]);
            
        
        //String tempresult=dq.Getip("156.154.70.1", googlelist[i]);         
         String tempresult=dq.Getip(dnsserverips[(ipsidx % (dnsserverips.length-1))], googlelist[i]);         
                 
         String tempresultlist[] =tempresult.split("\\|");
        effelist[effecnt]=tempresultlist[tempresultlist.length-1];
        effecnt++;
        }catch(Exception e)
        {
        System.out.println(e);
        }
    }
    ipsidx++;
                         }
    
    
    
    System.out.println("------------");
    for(int i=0;i<effecnt;i++)
    {
    System.out.println(effelist[i]);
    }
    System.out.println("------------");
    /*
    String tempresult=dq.Getip("114.114.114.114", "www.google.com.cat");
    ipadrs= tempresult.split("\\|");
    if (ipadrs==null )
    {
        Errormsg= "Error: not DNS IP found!";
    }
    
          */
    
    
 
//System.out.println(effelist[3]);
//System.out.println(webhost);
   //sock = (SSLSocket) sslsocketfactory.createSocket();
   // sock = (SSLSocket) sslsocketfactory.createSocket(ipadrs[0],hostport);
  SocketAddress socketAddress ;//= new InetSocketAddress(effelist[3],hostport);
  int rip=0;
  boolean loopflag = true;
  int loopcnt=0;
  while(loopflag){
  
  try{
    rip=(int) (Math.random()*effecnt);  
    socketAddress = new InetSocketAddress(effelist[rip],hostport);
  sock = (SSLSocket) sslsocketfactory.createSocket();
  sock.connect(socketAddress,8000);

  loopflag=false;
  }catch (SocketTimeoutException se){
  loopcnt++;
  System.out.println("" + rip+" Time out Retry connect : "+ loopcnt);
 
  }
  }
  
 System.out.println("connected!!"+rip +" :: "+effelist[rip]);
    sock.setSoTimeout(100000);
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
    int n = 1;
    
    boolean firstflag=true;
    
    

    
    byte x=0;
    while(x!=-1)
    {
    //n = ist.read();
        x = (byte) ist.read();
/*    
    if(firstflag)
    {
        if(n==-1)
        {
            n = ist.read(b);
            firstflag=false;
        }
    }
 */   
//
//    ist.wait(100);
//    bbuf.put(b);
    //break;
    
    //System.out.print(new String(b));
   // System.out.println("read bytes: "+bbuf.position());
    bbuf.put(x);
   
    
    }
  //  System.out.println("read bytes: "+bbuf.position());
    
    
    
    bbuf.flip();
    byte bb[]=new byte[bbuf.limit()];
    //bbuf.position(0);
    bbuf.get(bb,0,bb.length);
    
    return new String(bb);
    //return "END";
    }
    
    public void closeappsocket() throws IOException{
        ist.close();   
        ost.close();
       sock.close();
    
    }
    
    public void resultAnalyst(String restr,int delaynum,int speednum,String targetoutputfolder) throws IOException
    {
        
                   String []tempgetudplist=restr.split("sickjohnsisick1122356l112355iaaaoss");
            
       //     System.out.println(tempgetudplist[1]);
            
            String []tempstrs=tempgetudplist[0].split("\\r\\n");
            

 
            
            
            for(int i=10;i<tempstrs.length;i++)
            {

                String []tempstrsxxxx=tempstrs[i].split(",");
              //vpn539246233|182.216.181.220|508611|35|41230804|Korea Republic of|KR|13|  
              //#HostName|IP|Score|Ping|Speed|CountryLong|CountryShort|NumVpnSessions|Uptime|TotalUsers|TotalTraffic|LogType|Operator|Message|OpenVPN_ConfigData_Base64|\
               
               if(tempstrsxxxx.length>14)
               {
                   BASE64Decoder decoder = new BASE64Decoder();
                  
                   byte[] decodedBytes = decoder.decodeBuffer(tempstrsxxxx[14]);
                   tempstrsxxxx[14]=new String(decodedBytes,"UTF-8");
                   tempstrsxxxx[14]=tempstrsxxxx[14].replaceAll("#.+?\r\n", "");
                   
                   if ( //tempstrsxxxx[14].indexOf("proto udp")!=-1 &&  
                           Integer.valueOf(tempstrsxxxx[7])>0
                           &&  Integer.valueOf(tempstrsxxxx[3])<delaynum
                           &&  Integer.valueOf(tempstrsxxxx[4])>speednum
                      )
                   {   
                   
                     //  tempgetudplist[1].split(",");
                       List<String> udplist = Arrays.asList(tempgetudplist[1].split(","));
                       
                       int tempudpportnum=udplist.indexOf(tempstrsxxxx[0]);
                       
                       if(tempudpportnum!=-1)
                       {
                          
                           tempstrsxxxx[14]=tempstrsxxxx[14].replace("proto tcp", "proto udp");
                           tempstrsxxxx[14]=tempstrsxxxx[14].replaceFirst("remote [0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+ [0-9]+", 
                                   "remote "+tempstrsxxxx[1]+" "+udplist.get(tempudpportnum+1));
                           
                           
                        int performrank=Integer.valueOf(tempstrsxxxx[2])/10000;

                        File tempfile=new File(targetoutputfolder+tempstrsxxxx[1]+"_"+tempstrsxxxx[6]+"_udp_"+"Rank"+performrank+".ovpn");
                        FileOutputStream osss =new FileOutputStream(tempfile);
                        osss.write(tempstrsxxxx[14].getBytes("UTF-8"));
                        osss.close(); 
                        
                        System.out.println(tempfile.getAbsoluteFile());
                        tempfile=null;   
                          /* 
                        System.out.println(tempstrsxxxx[0]+"|"+tempstrsxxxx[1]+"|"+tempstrsxxxx[2]+
                       "|"+tempstrsxxxx[3]+"|"+tempstrsxxxx[4]+"|"+tempstrsxxxx[5]+"|"+tempstrsxxxx[6]
                       +"|"+tempstrsxxxx[7]+"|"+tempstrsxxxx[8]+"|"+tempstrsxxxx[9]+"|"+tempstrsxxxx[10]+"|"
                       +tempstrsxxxx[11]+"|"+tempstrsxxxx[12]+"|"+tempstrsxxxx[13]+"|"
                                +udplist.get(tempudpportnum+1)+"|"+tempstrsxxxx[14]
                           );
                        */
                        
                       }
                           //+tempstrsxxxx[14]+"|");
               }
                   
               }
               
 
            }
    
    }
    
    
    public static void main(String arg[])
    {
        try {
            //if(arg!=null && arg=)
            
            
            //AppspotSocket appsock= new AppspotSocket("aiworkserver");
            AppspotSocket appsock= new AppspotSocket("sorryformynet");
            //String restr=appsock.URLConmunicate("kukuana?qtype=123&qvalue=我");
            String restr=appsock.URLConmunicate("urlfopenvpn?qtype=http://www.vpngate.net/api/iphone/");
            int delaynum=90;
            int speednum=3000000;
            String targetoutputfolder="/tmp/";
            appsock.resultAnalyst(restr,delaynum,speednum,targetoutputfolder);
            appsock.closeappsocket();
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }
    
    
}
