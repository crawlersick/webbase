/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networktool;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
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
     
     @SuppressWarnings("empty-statement")
    public String Getip( String DNSs,String TgDN) throws Exception{
         int datasize;   
         datasize=adddataend(0,b0,datahead);
         
            strarr=TgDN.split("\\.");
            for(String tempstr:strarr)
            {
                x=tempstr.length();
                datasize++;
                b0[datasize-1]=(byte)x;
               datasize=adddataend(datasize,b0,tempstr.getBytes("ASCII"));
               
            }
          datasize=adddataend(datasize,b0,dataend);
          byte[] tempbyby=ByteBuffer.allocate(4).putInt(datasize-2).array();
          b0[0]=tempbyby[2];
          b0[1]=tempbyby[3];
          
          
            s=new Socket(DNSs,53);
  /*        
            String newfind= "00 1f 00 00 01 00 00 01 00 00 00 00 00 00 03 77 77 77 05 62 61 69 64 75 03 63 6f 6d 00 00 01 00 01";
            newfind=newfind.replaceAll(" ", "");
            System.out.println(newfind);
            b1=HexCodec.hexToBytes(newfind.toCharArray());
      */      
            
            //b1=fdata.getBytes("UTF-8");
 // b1=new byte[]{0x00, 0x1f,(byte)0xbb};
            b1=b0;
            for(int i=0;i<datasize;i++){
                
                    System.out.print(Integer.toHexString(b1[i])+" ");
            }
            System.out.println();
            
            
            
            osm=s.getOutputStream();
            ism=s.getInputStream();
            osm.write(b1,0,datasize);
            //osm.flush();
            System.out.println("***************************************");
            
            int n=0;
            while ((x=ism.read())!=-1)
            {
            System.out.print(Integer.toHexString(x)+"~"+x+"|");
            b3[n]=x;
            n++;
            }
            System.out.println("\n***************************************");
            
            for(int i=0;i<n;i++)
            {
              //  System.out.print(b3[i]+"  ^");
                if(b3[i]==192
                        &&
                   b3[i+2]==0
                        &&
                   b3[i+3]==1
                        &&
                   b3[i+4]==0
                        &&
                   b3[i+5]==1
                        ){
                    
                                System.out.println(b3[i+12]+"."+b3[i+13]+"."+b3[i+14]+"."+b3[i+15]);
                                //System.out.println(b3[i+13]);
                                //System.out.println(b3[i+14]);
                                //System.out.println(b3[i+15]);
                              //  break;
                            }
            }
            
            
            
    return null;
    }
    
    public static void main(String arg[]){
         try {
             
             DNSQ dq=new DNSQ();
            //dq.Getip("202.96.128.166", "baidu.com");
              dq.Getip("8.8.8.8", "t66y.com");
              //dq.Getip("8.8.8.8", "plus.google.com");
             //String aaa="asd";
             //System.out.println(aaa.getBytes("ASCII").length);
             
         } catch (Exception ex) {
             Logger.getLogger(DNSQ.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    

    private int adddataend(int headnum,byte[]tb,byte[]tb_end)
    {   
            if(tb.length-headnum<tb_end.length)
            {
                System.out.println("teststaesktjjjflajdasfkll");
                return 0;
            }
            int i;
            for(i=headnum;i<headnum+tb_end.length;i++)
            {
  //              System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+i);
                tb[i]=tb_end[i-headnum];
            }
            return i;
    }
    
    
    private byte[] b0=new byte[1024];
    private byte[] datahead={0x00,0x1f,0x00,0x00,0x01,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00};
    private byte[]dataend={0x00,0x00,0x01,0x00,0x01};
    private String[] strarr;
    private int x;
    private byte[] b1;
    private byte[] b2;
    private int[] b3=new int[1024];
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