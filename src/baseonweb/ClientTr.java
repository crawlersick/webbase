/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baseonweb;

import baseonweb.projbeans.Init_Str;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sick
 */
public class ClientTr  extends Thread{
    private Socket ss;
    private OutputStream os;
    private InputStream is;
    private byte b[];
    private String str1;
    private int n;
    private int cport;
    private String chost;
    private String cip;
    private Init_Str inis;
    private File ofl;
    private File webitems;
    private FileInputStream fis;
    public ClientTr(Socket ss,Init_Str inis){this.ss=ss;this.inis=inis;
    webitems=new File("web_items");
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
            
            b=new byte[10240];
            str1="";
            
            while((n=is.read(b))!=-1)
            {
            str1=str1+new String(b,0,n,"UTF-8");
            if(b[n-1]==10&&b[n-2]==13&&b[n-3]==10&&b[n-4]==13) {
                //System.out.println(b[n-2]+" "+b[n-3]+" "+b[n-4]);
                    break;
                                                                                                                          }
            }
            

            System.out.println(str1);
            String strs[];
            strs = PatternTool.gets(str1, PatternTool.getHttpGetItem());
            System.out.println("--------------------------------------------------------------------------------------------");
            if (strs==null){System.out.println("read in string match failed!!!");}
            else
            {
                for(int i=1;i<strs.length;i++)
                            {
                            System.out.println(strs[i]);
                            }
                if((strs[1].toUpperCase()).equals("GET")&&strs.length>2)
                                    {
                                        System.out.println("Start to Process Get request on "+chost);
                                        if (strs[2]!=null)
                                        {
                                        System.out.println("get request :  "+strs[2]);
                                        System.out.println("get request mapping :  "+inis.getHm().get(strs[2]));
                                                                              
                                        String  header="HTTP/1.1 200 "+"\n\n";
                                        os=ss.getOutputStream();
                                        os.write(header.getBytes());
                                        
                                        ofl=new File("web_items/"+(String)inis.getHm().get(strs[2]));
                                        if(ofl.exists())
                                                {
                                                fis=new FileInputStream(ofl);
                                                int nn=fis.read(b);
                                                os.write(b,0,nn);
                                                
                                                
                                                }
                                        else
                                                {
                                                os.write(("<html><body>link</body></html>").getBytes());
                                                
                                                }
                                        os.close();
                                        }
                                        else
                                        {
                                        System.out.println("null get content!");
                                        }
                                    }
                else 
                {
                
                System.out.println("======================"+strs.length);
                }
            }
            is.close();
            ss.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ClientTr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
