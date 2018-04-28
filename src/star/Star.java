/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package star;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import javax.crypto.*;
import java.sql.*;  


/**
 *
 * @author mpant
 */
public class Star {
public String od;
public String do_;
public String prevHash;
public int index;
public String hash;

    



     public Star(String od,String do_, String prevHash)throws NoSuchAlgorithmException {
         hash = od+do_+prevHash;
         
      byte[] input = hash.getBytes();
      MessageDigest SHA1 = MessageDigest.getInstance("SHA1");
      SHA1.update(input);
      byte[] digest = SHA1.digest();
      StringBuffer hexDigest = new StringBuffer();
      for(int i=0;i<digest.length;i++)
          hexDigest.append(Integer.toString((digest[i]&0xff)+0x100,16).substring(1));
      String hash_to_string = hexDigest.toString();
      System.out.println(hash_to_string);
      

      
      
      try
    {
      // create a mysql database connection
      String myDriver = "org.gjt.mm.mysql.Driver";
      String myUrl = "jdbc:mysql://localhost:3306/star";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
    
      String output = "";
      
      String output_id="";
      
           
      
     
            Statement stmt = null;
            ResultSet rs = null;
      String SQL = "select hash from blockchain where id=(SELECT MAX(id))";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    output =  (String) rs.getObject(1);
                }
                System.out.println(output);
      
      // create a sql date object so we can use it in our INSERT statement   

      // the mysql insert statement
      String query = " insert into blockchain (hash, prevHash, od, do)"
        + " values (?, ?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setString (1, hash_to_string);
      preparedStmt.setString (2, output);
      preparedStmt.setString (3, od);
      preparedStmt.setString (4, do_);


      preparedStmt.execute();
      
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
  
      
      
       System.out.println("Poslao: "+od+" , Primio: "+do_+" , Predhodni hash: "+prevHash+" , Sadasnji hash: "+hash );       
   }
     
     
     
 
     
     
     
      public String calculateRoyality() {
    return null;
  
     

          
      }
      
      
      
      
      
      
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        
       
        
        
        
        
        
        
       Star transaction = new Star("asdhsada11","12ab","0");
      

       
       
  
    }

 
    
}
