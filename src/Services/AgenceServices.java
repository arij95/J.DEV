/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Agence;
import Utiles.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author challakh
 */
public class AgenceServices {
    Connection connexion;

    public AgenceServices(Connection connexion) {
        this.connexion = connexion;
    }

    public AgenceServices() {
        connexion =Singleton.getInstance().getConnection();
    }
     public void ajouterAgence(Agence a) throws SQLException
  {
       String mdpav=a.getMdp();
      String hashed=BCrypt.hashpw(mdpav, BCrypt.gensalt(13));
   String req= "INSERT INTO `user`( `nom`, `email`,`email_canonical`, `password`, `Adresse_user`, `Telephone_user`, `Fax_agence`, `username`, `enabled`, `rolejava`, `roles`) VALUES ( ?,?,?,?,?,?,?,?,?,?,?)";
     PreparedStatement PS=connexion.prepareStatement(req);
     PS.setString(1,a.getNom());
     PS.setString(2,a.getEmail());
     PS.setString(3,a.getEmail());
     PS.setString(4,hashed);
     PS.setString(5,a.getAdresse());
     PS.setInt(6,a.getTelephone());
     PS.setInt(7,a.getFax());
     PS.setString(8,a.getNom());
     PS.setInt(9,0);
     PS.setInt(10,1);
     
     PS.setString(11,"a:1:{i:0;s:10:\"ROLE_AGENT\";}");
    

     PS.executeUpdate();
     }
      public List<Agence> getALLAgences() throws SQLException
  { List<Agence> agences= new ArrayList<>();
      String req="SELECT * FROM `user`";
      Statement stm=connexion.createStatement();
      ResultSet rst= stm.executeQuery(req);
      while(rst.next())
      {
          Agence a=new Agence(rst.getString("nom"),rst.getString("email"),rst.getString("password"),rst.getString("Adresse_user "),rst.getInt("Telephone_user")
                  ,rst.getInt("Fax_agence"));
          agences.add(a);
      }
      return agences;}
       public void modifierAgence(Agence a,int id) throws SQLException
  {
     String req= "UPDATE `user` SET `nom`=?,`email`=?,`password`=?,`Adresse_user`=?,`Telephone_user`=?,`Fax_agence`=? WHERE `id`=?";
     PreparedStatement PS=connexion.prepareStatement(req);
    PS.setString(1,a.getNom());
     PS.setString(2,a.getEmail());
     PS.setString(3,a.getMdp());
     PS.setString(4,a.getAdresse());
     PS.setInt(5,a.getTelephone());
     PS.setInt(6,a.getFax());
     
     PS.setInt(7,id);
     
     PS.executeUpdate();
     }
       
       
          public void supprimerAgence(int id) throws SQLException
  {
     String req= "DELETE FROM `user` WHERE `id`=? and `roles`='a:1:{i:0;s:10:\"ROLE_AGENT\";}'";
      PreparedStatement PS=connexion.prepareStatement(req);
     PS.setInt(1,id);
     PS.executeUpdate();
     }
      public boolean exist(String Email) throws SQLException {
         boolean b = false ;
     String req = "select  `email` from user where `email`=?";
    

         PreparedStatement pst;
        
             pst = connexion.prepareStatement(req);
              pst.setString(1,Email);
            ResultSet resultat = pst.executeQuery();
            while ((resultat.next())&& (b==false)) {
                 String emailagence=resultat.getString(1);
                  if (emailagence.equalsIgnoreCase(Email))
                      
                  {
                      b=true ;
                      
                  }
                 
              }
        return b;
         }
        public boolean MotDePassseCorrect (String Email,String modp) throws SQLException
        {boolean b=false;
         String req = "select  `password` from user where `email`=?";   
         PreparedStatement pst;
             pst = connexion.prepareStatement(req);
                pst.setString(1,Email);
            ResultSet resultat = pst.executeQuery();
              while ((resultat.next())&& (b==false)) {
                 String mdpagence=resultat.getString("password");
                  if (mdpagence.equalsIgnoreCase(modp))
                      
                  {
                      b=true ;
                      
                  }
                 
              }
        return b;
        }
        public void MettreAUn(String email) throws SQLException
        {
            String req = "UPDATE `user` SET `connecter_user`=1 WHERE `email`=?";   
        PreparedStatement PS=connexion.prepareStatement(req);
     PS.setString(1,email);
     PS.executeUpdate();
        }
         public void MettreAZero() throws SQLException
        {
            String req = "UPDATE `user` SET `connecter_user`=0";   
        PreparedStatement PS=connexion.prepareStatement(req);
  
     PS.executeUpdate();
        }
         public Agence AgenceConnecte() throws SQLException
         {
             
              String req="SELECT * FROM `user` WHERE `connecter_user`=?";
              PreparedStatement stm=connexion.prepareStatement(req);
              stm.setInt(1,1);
      ResultSet rst= stm.executeQuery();
       Agence a=new Agence();
      while(rst.next())
      {
     a.setId(rst.getInt(1));
     a.setNom(rst.getString(13));
     a.setEmail(rst.getString(4));
     a.setMdp(rst.getString(8));
     a.setAdresse(rst.getString(16));
     a.setTelephone(rst.getInt(17));
     a.setFax(rst.getInt(20));
     a.setConnecter_agence(rst.getInt(18));
     a.setRole(rst.getInt(19));
          
           
      }
         
    return a;
         }
         public boolean TrouveAgence(String email) throws SQLException
         {
             boolean b=false;
             String req = "select  `email` from user where `roles`='a:1:{i:0;s:10:\"ROLE_AGENT\";}'";   
         PreparedStatement stm;
             stm = connexion.prepareStatement(req);        
              ResultSet rst= stm.executeQuery();
              while(rst.next()&&(b==false))
      {
    String email_agence=rst.getString("email");
    if (email.equalsIgnoreCase(email_agence))
            b=true;
    
          
           
      }
              return b;
         }
       
    
           
      
         
  
         
         public boolean AgenceVerifie(String email) throws SQLException
         {
              boolean b=false;
             String req = "select  `enabled` from agence where `email`=? ";   
         PreparedStatement stm;
             stm = connexion.prepareStatement(req); 
             stm.setString(1,email);
              ResultSet rst= stm.executeQuery();
              while(rst.next()&&(b==false))
      {
    int verif=rst.getInt("enabled");
    if (verif==0)
            b=true;
         }
              return b;
}
          public boolean AgenceBloque(String email) throws SQLException
         {
              boolean b=false;
             String req = "select  `enabled` from user where `email`=? ";   
         PreparedStatement stm;
             stm = connexion.prepareStatement(req); 
             stm.setString(1,email);
              ResultSet rst= stm.executeQuery();
              while(rst.next()&&(b==false))
      {
    int bloq=rst.getInt("enabled");
    if (bloq==1)
            b=true;
         }
              return b;
}
          public void desactivercompte(int id) throws SQLException
          {
          
              String sqlupdate = "DELETE FROM `user` WHERE `id` ="+id ;
        PreparedStatement preupdate = connexion.prepareStatement(sqlupdate);

        
        
        preupdate.executeUpdate();
          }
}