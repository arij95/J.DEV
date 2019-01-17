/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Agence;
import Entites.Utilisateur;
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
public class UtilisateurServices {
        Connection connexion;

    public UtilisateurServices(Connection connexion) {
        this.connexion = connexion;
    }

    public UtilisateurServices() {
        connexion =Singleton.getInstance().getConnection();
    }
     public void ajouterUtilisateur(Utilisateur a) throws SQLException
             
  {
      String mdpav=a.getMdpu();
      String hashed=BCrypt.hashpw(mdpav, BCrypt.gensalt(13));
      
     String req= "INSERT INTO `user`( `nom`, `prenom`, `email`,`email_canonical`, `password`, `Sexe_user`, `Adresse_user`, `Telephone_user`, `username`, `enabled`, `rolejava`, `roles`) VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?)";
     PreparedStatement PS=connexion.prepareStatement(req);
     PS.setString(1,a.getNomu());
     PS.setString(2,a.getPrenomu());
     PS.setString(3,a.getEmailu());
     PS.setString(4,a.getEmailu());
     PS.setString(5,hashed);
     PS.setString(6,a.getSexeu());
     PS.setString(7,a.getAdresseu());
     
     PS.setInt(8,a.getTelephoneu());
     PS.setString(9,a.getNomu());
     PS.setInt(10,1);
     PS.setInt(11,0);
     
     PS.setString(12,"a:0:{}");
     
    

     PS.executeUpdate();
     }
      public List<Utilisateur> getALLUtilisateurs() throws SQLException
  { List<Utilisateur> utilisateurs= new ArrayList<>();
      String req="SELECT * FROM `user` where rolejava=0";
      Statement stm=connexion.createStatement();
      ResultSet rst= stm.executeQuery(req);
      while(rst.next())
      {
          Utilisateur a=new Utilisateur(rst.getString("nomu"),rst.getString("prenomu"),rst.getString("emailu"),rst.getString("mdpu"),rst.getString("sexe"),rst.getString("adresseu"),rst.getInt("telephoneu"));
          utilisateurs.add(a);
      }
      return utilisateurs;}
       public void modifierUtilisateur(Utilisateur a,int id) throws SQLException
  {
     String req= "UPDATE `user` SET `nom`=?,`prenom`=?,`email`=?,`password`=?,`Sexe_user`=?,`Adresse_user`=?,`Telephone_user`=? WHERE `id`=?";
     PreparedStatement PS=connexion.prepareStatement(req);
    PS.setString(1,a.getNomu());
     PS.setString(2,a.getPrenomu());
     PS.setString(3,a.getEmailu());
     PS.setString(4,a.getMdpu());
     PS.setString(5,a.getSexeu());
     PS.setString(6,a.getAdresseu());
     PS.setInt(7,a.getTelephoneu());
     
     PS.setInt(8,id);
     
     PS.executeUpdate();
     }
       
       
          public void supprimerUtilisateur(int id) throws SQLException
  {
     String req= "DELETE FROM `user` WHERE `id`=? and `roles`='a:0:{}'";
      PreparedStatement PS=connexion.prepareStatement(req);
     PS.setInt(1,id);
     PS.executeUpdate();
     }
    public boolean exist(String Email) throws SQLException {
         boolean b = false ;
     String requete = "select  `email` from user where `email`=?";

         PreparedStatement pst;
        
             pst = connexion.prepareStatement(requete);
              pst.setString(1,Email);
            ResultSet resultat = pst.executeQuery();
            while ((resultat.next())&& (b==false)) {
                 String emailutilisateur=resultat.getString(1);
                  if (emailutilisateur.equalsIgnoreCase(Email))
                      
                  {
                      b=true ;
                      
                  }
                 
              }
        return b;
         }
    
   /*  public boolean MotDePassseCorrect (String Email,String modp) throws SQLException
        {boolean b=false;
         String req = "select  `password` from user where `email`=?";   
         PreparedStatement pst;
             pst = connexion.prepareStatement(req);
                pst.setString(1,Email);
              //  Utilisateur a= new Utilisateur();
               //  String s=a.getMdpu();
              
            ResultSet resultat = pst.executeQuery();
              while ((resultat.next())&& (b==false)) {
                 String mdputilisateur=resultat.getString("password");
                 //int positionRemplacee = 2;
           //  char nouveauChar = 'a';
               //   s = s.substring(0, positionRemplacee) + nouveauChar + s.substring(positionRemplacee+1);
              
                  if (BCrypt.checkpw( modp , mdputilisateur))
                      
                  {
                      b=true ;
                      
                  }
                 
              }
        return b;
        }*/
     public boolean MotDePassseCorrect (String Email,String modp) throws SQLException
        {boolean b=false;
         String req = "select  `password` from user where `email`=?";   
         PreparedStatement pst;
             pst = connexion.prepareStatement(req);
                pst.setString(1,Email);
            ResultSet resultat = pst.executeQuery();
              while ((resultat.next())&& (b==false)) {
                 String s=resultat.getString("password");
                 int positionRemplacee = 2;
                 char nouveauChar = 'a';
                  s = s.substring(0, positionRemplacee) + nouveauChar + s.substring(positionRemplacee+1);
                // BCrypt.checkpw(modp,mdputilisateur)
                  if ( BCrypt.checkpw(modp,s))
                      
                  {
                      b=true ;
                      
                  }
                 
              }
        return b;
        }
      public void MettreAUnu(String email) throws SQLException
        {
            String req = "UPDATE `user` SET `Connecter_user`=1 WHERE `email`=?";   
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
          public Utilisateur UserConnecte() throws SQLException
         {
           String req="  SELECT * FROM `user` WHERE `Connecter_user`=?";
             
              PreparedStatement stm=connexion.prepareStatement(req);
              stm.setInt(1,1);
      ResultSet rst= stm.executeQuery();
       Utilisateur a=new Utilisateur();
      while(rst.next())
      {
     a.setIdu(rst.getInt(1));
     a.setNomu(rst.getString(2));
     a.setPrenomu(rst.getString(3));
     a.setEmailu(rst.getString(4));
     a.setMdpu(rst.getString(5));
     a.setSexeu(rst.getString(6));
     a.setConnecteru(rst.getInt(7));
     a.setAdresseu(rst.getString(8));
     a.setTelephoneu(rst.getInt(9));
          a.setRole(rst.getInt(10));
           
      }
      return a;
         }
          
      public boolean TrouveUtilisateur(String email) throws SQLException
         {
             boolean b=false;
             String req = "select  `email` from user where `roles`='a:0:{}'";   
         PreparedStatement stm;
             stm = connexion.prepareStatement(req);        
              ResultSet rst= stm.executeQuery();
              while(rst.next()&&(b==false))
      {
    String email_user=rst.getString("email");
    if (email.equalsIgnoreCase(email_user))
            b=true;
    
          
           
      }
              return b;
         }
    public int TrouveRole(String email) throws SQLException
         {
             String req="SELECT `rolejava` FROM `user` WHERE `email`=?";
              PreparedStatement stm=connexion.prepareStatement(req);
              stm.setString(1,email);
      ResultSet rst= stm.executeQuery();
       int role=-1;
      while(rst.next())
      {
          role=rst.getInt("rolejava");
    
           
      }
         
    return role;
         }
      
    
     public Utilisateur retournerUtilisateur() throws SQLException
    {
        ArrayList a = new ArrayList();
        String req = "SELECT id,nom,prenom,email,password,Sexe_user,connecter_user,Adresse_user,Telephone_user,rolejava"
                + " FROM user WHERE connecter_user=1";
        PreparedStatement pre = connexion.prepareStatement(req);
        ResultSet rs = pre.executeQuery();
        if(rs != null)
        {
            while (rs.next()) {
                int id_user = rs.getInt("id");
                String nom_user = rs.getString("nom");
                String prenom_user = rs.getString("prenom");
                String email_user = rs.getString("email");
                String mdp_user = rs.getString("password");
                String sexe_user= rs.getString("Sexe_user");
                int connecter_user = rs.getInt("connecter_user");
                String adresse_user = rs.getString("Adresse_user");
                int telephone_user = rs.getInt("Telephone_user");
                int role_user = rs.getInt("rolejava");
                
                
                Utilisateur u = new Utilisateur(id_user, nom_user, prenom_user,email_user, mdp_user, sexe_user, connecter_user, adresse_user,
                                                telephone_user,role_user);
               //a.add(u);
                return u;
                
            }
        }
            return null;
        
    }
        public boolean UserBloque(String email) throws SQLException
         {
              boolean b=false;
             String req = "select  `enabled` from Utilisateur where `Email_user`=? ";   
         PreparedStatement stm;
             stm = connexion.prepareStatement(req); 
             stm.setString(1,email);
              ResultSet rst= stm.executeQuery();
              while(rst.next()&&(b==false))
      {
    int bloq=rst.getInt("enabled");
    if (bloq==0)
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
