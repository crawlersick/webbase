/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networktool;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sick
 */
 public    class  DNSQ {
    /*
    public  DNSQ(String TargetDomainName,String Targetip){
    this.TargetDomainName=TargetDomainName;
    this.Targetip=Targetip;
    }
    */
     public DNSQ(){
     
     }
     
    public String Getip( String DNSs,String TgDN) throws Exception{
            s=new Socket(DNSs,53);
           // fdata="\u0000\u0002\u0001\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000\u0000"+TgDN+"\u0000\u0000\u0001\u0000\u0001";
            
            
            fdata="\u0000\u0002\u0001\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0000\u0000"+
                    "\u0004\u0070\u006F\u0070\u0064\u0002\u0069\u0078\u0006\u006E\u0065\u0074\u0063\u006F\u006D\u0003\u0063\u006F\u006D"+
                    "\u0000\u0000\u0001\u0000\u0001"
                    //+
                    //"\u00C7\u0000\u007E"
                    ;
            
            String ccdata="\u0070\u006F\u0070\u0064  \u0069\u0078  \u006E\u0065\u0074\u0063\u006F\u006D  \u0063\u006F\u006D";
            
            String newfind=
                    //"00 02 01 00 00 01 00 00 00 00 00 00 04 70 6F 70 64 02 69 78 06 6E 65 74 63 6F 6D 03 63 6F 6D 00 00 01 00 01";
             "00 1f 53 d3 01 00 00 01 00 00 00 00 00 00 03 77 77 77 05 62 61 69 64 75 03 63 6f 6d 00 00 01 00 01";
            newfind=newfind.replaceAll(" ", "");
            System.out.println(newfind);
            b1=HexCodec.hexToBytes(newfind.toCharArray());
            
            
            //b1=fdata.getBytes("UTF-8");
 // b1=new byte[]{0x00, 0x1f,(byte)0xbb};
                            
            for(byte xx:b1){
                
                    System.out.print(Integer.toHexString(xx)+" ");
            }
            System.out.println();
            
            
            
            osm=s.getOutputStream();
            ism=s.getInputStream();
            osm.write(b1,0,b1.length);
            //osm.flush();
            System.out.println("***************************************");
            while ((x=ism.read())!=-1)
            {
            System.out.print(Integer.toHexString(x));
            }
            System.out.println("\n***************************************");
            
    return null;
    }
    
    public static void main(String arg[]){
         try {
             
             DNSQ dq=new DNSQ();
           // dq.Getip("202.96.128.166", "baidu.com");
               dq.Getip("8.8.8.8", "baidu.com");
             
         } catch (Exception ex) {
             Logger.getLogger(DNSQ.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
    private int x;
    private byte[] b1;
    private byte[] b2;
    private String fdata;
    private String DNSserver;
    private String TargetDomainName;
    private String Targetip;
    private Socket s;
    private InputStream ism;
    private OutputStream osm;
}


 class HexCodec {
  private static final char[] kDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
      'b', 'c', 'd', 'e', 'f' };

  public static byte[] hexToBytes(char[] hex) {
    int length = hex.length / 2;
    byte[] raw = new byte[length];
    for (int i = 0; i < length; i++) {
      int high = Character.digit(hex[i * 2], 16);
      int low = Character.digit(hex[i * 2 + 1], 16);
      int value = (high << 4) | low;
      if (value > 127)
        value -= 256;
      raw[i] = (byte) value;
    }
    return raw;
  }

  public static byte[] hexToBytes(String hex) {
    return hexToBytes(hex.toCharArray());
  }
}