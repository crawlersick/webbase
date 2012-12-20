/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baseonweb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author sick
 */
public class PatternTool {
    private final static Pattern HttpGetItem=
                                       //Pattern.compile("^GET\\s+([^\\s]+)\\s+[^\\s]+\\s+HOST:\\s+([^\\s]+)\\s+USER-AGENT:([^\\s]+).+",0b1110);
            Pattern.compile("^([^\\s]+)\\s+([^\\s]+)\\s+([^\\s]+)\\s+\\n.*",13);
                            

    public static Pattern getHttpGetItem() {
        return HttpGetItem;
    }
    

    public static void  A_hd(String header)
    {
      
    }
    
    
     public static Pattern getp(String strp,int flag){
         return Pattern.compile(strp, 13);   
     }
    public static String[] gets(String s, Pattern p)
    {
        Matcher m;
        String ss[];
            ss=null;
            m=p.matcher(s);
            while(m.find())
            {
                int n=m.groupCount();
                ss=new String[n+1];
                for(int i=0;i<=n;i++)
                {
                ss[i]=m.group(i);
                }
                break;
            }
            return ss;
    }
     
     /* test */
     public static void main(String arg[]){
     System.out.println(Pattern. CASE_INSENSITIVE);
     System.out.println(Pattern.MULTILINE);
     System.out.println(Pattern.DOTALL);
     System.out.println(Pattern.UNICODE_CASE);
     }
}
