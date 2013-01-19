package baseonweb;

import baseonweb.projbeans.Init_Str;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sick
 */
public class Baseonweb {

    public Baseonweb(){}
            
    public static void main(String[] args) {
//        System.out.print("abcs");
        
        Init_Str is=new Init_Str();
        is.setPort(9999);
        is.setAdminpw("11111100000000");
        is.setAdminid("iamsick");
        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("/home", "index.html");
        hm.put("/home2", "index2.html");
        is.setHm(hm);          
        
        Xmltools xml_tool=new Xmltools();
        File xmlfile = new File("cfg.xml");
        try {        
            if(!xmlfile.exists()) {
                xml_tool.writeconfig(is, xmlfile);
                                                }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Baseonweb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Baseonweb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            tempob=xml_tool.readconfig(xmlfile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Baseonweb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Baseonweb.class.getName()).log(Level.SEVERE, null, ex);
        }
     // test    
        Init_Str tempstr;
        tempstr = (Init_Str)tempob;
        System.out.println(tempstr.getAdminid());
        System.out.println(tempstr.getAdminpw());
        System.out.println(tempstr.getPort());
        System.out.println(tempstr.getHm().get("/home2"));
        
        
                   try {
                            LisSock ls=new LisSock(11229,10000,tempstr);
                            ls.toloop();
                        } catch (IOException ex) {
                            Logger.getLogger(LisSock.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
    }
    
    private static Object tempob;
}
