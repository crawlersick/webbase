package baseonweb.projbeans;

import java.util.HashMap;

public class Init_Str{
 private int port;
 private String adminid;
 private String adminpw;
 private HashMap hm;

    public HashMap getHm() {
        return hm;
    }

    public void setHm(HashMap hm) {
        this.hm = hm;
    } 

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getAdminpw() {
        return adminpw;
    }

    public void setAdminpw(String adminpw) {
        this.adminpw = adminpw;
    }
 
 
   public int getPort(){return port;}
   public void setPort(int i){this.port=i;}
    

}
 
