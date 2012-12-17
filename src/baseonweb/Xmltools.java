/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baseonweb;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 *
 * @author sick
 */
public class Xmltools {
private FileOutputStream fos;
private FileInputStream fis;
private XMLEncoder e ;
private XMLDecoder d ;   
private Object tempob;
    public boolean writeconfig(Object obj,File f) throws FileNotFoundException, IOException
    {
        //System.out.println("lllllllllllllllllll");
        fos = new  FileOutputStream(f);
        e = new XMLEncoder(fos );
        e.writeObject(obj);
        e.close();
        fos.close();
        return true;
    }
    
    
    public Object readconfig(File f) throws FileNotFoundException, IOException{
        fis = new FileInputStream(f);
        d= new XMLDecoder(fis);
        tempob=d.readObject();
        d.close();
        fis.close();
        return tempob;
    }
    
            
}

