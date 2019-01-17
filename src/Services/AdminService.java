/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Activite;
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

/**
 *
 * @author challakh
 */
public class AdminService {
    Connection connexion;

    public AdminService() {
                connexion =Singleton.getInstance().getConnection();

    }

    public AdminService(Connection connexion) {
        this.connexion = connexion;
    }
 public List<Agence> getALLAgencesnNonVerifie() throws SQLException
  { List<Agence> agences= new ArrayList<>();
      String req="SELECT * FROM `user` where `enabled`=? and `roles`='a:1:{i:0;s:10:\"ROLE_AGENT\";}'";
      PreparedStatement stm=connexion.prepareStatement(req);
              stm.setInt(1,0);
      ResultSet rst= stm.executeQuery();
      
      while(rst.next())
      {Agence a=new Agence();
           a.setId(rst.getInt(1));
     a.setNom(rst.getString(2));
     a.setEmail(rst.getString(4));
     
          agences.add(a);
      }
      return agences;}
     public List<Agence> getALLAgencesnNonBloque() throws SQLException
  { List<Agence> agences= new ArrayList<>();
      String req="SELECT * FROM `user` where `enabled`=? and `roles`='a:1:{i:0;s:10:\"ROLE_AGENT\";}'";
      PreparedStatement stm=connexion.prepareStatement(req);
              stm.setInt(1,1);
      ResultSet rst= stm.executeQuery();
      
      while(rst.next())
      {Agence a=new Agence();
           a.setId(rst.getInt(1));
     a.setNom(rst.getString(2));
     a.setEmail(rst.getString(4));
     
          agences.add(a);
      }
      return agences;}
      public List<Agence> getALLAgencesnBloque() throws SQLException
  { List<Agence> agences= new ArrayList<>();
      String req="SELECT * FROM `user` where `enabled`=? and `roles`='a:1:{i:0;s:10:\"ROLE_AGENT\";}'";
      PreparedStatement stm=connexion.prepareStatement(req);
              stm.setInt(1,0);
      ResultSet rst= stm.executeQuery();
      
      while(rst.next())
      {Agence a=new Agence();
           a.setId(rst.getInt(1));
     a.setNom(rst.getString(2));
     a.setEmail(rst.getString(4));
     
          agences.add(a);
      }
      return agences;}
    public void VerifierCompteAgence(int id) throws SQLException
    {
        String req= "UPDATE `user` SET`enabled`=? WHERE  `id`=?";
     PreparedStatement PS=connexion.prepareStatement(req);
     PS.setInt(1,1);
     PS.setInt(2,id);
     
     PS.executeUpdate();
    }
      public void BloquerCompteAgence(int id) throws SQLException
    {
        String req= "UPDATE `user` SET `enabled`=? WHERE  `id`=?";
     PreparedStatement PS=connexion.prepareStatement(req);
     PS.setInt(1,0);
     PS.setInt(2,id);
     
     PS.executeUpdate();
    }
       public void DebloquerCompteAgence(int id) throws SQLException
    {
        String req= "UPDATE `user` SET `enabled`=? WHERE  `id`=?";
     PreparedStatement PS=connexion.prepareStatement(req);
     PS.setInt(1,1);
     PS.setInt(2,id);
     
     PS.executeUpdate();
    }
        public List<Utilisateur> getALLUtilisateursbloque() throws SQLException
  { List<Utilisateur> utilisateurs= new ArrayList<>();
      String req="SELECT * FROM `user` where `enabled`=?  and `roles`='a:0:{}'";
     PreparedStatement stm=connexion.prepareStatement(req);
              stm.setInt(1,0);
      ResultSet rst= stm.executeQuery();
      
      while(rst.next())
      {
          Utilisateur a=new Utilisateur();
         a.setIdu(rst.getInt(1));
     a.setNomu(rst.getString(2));
     
     a.setEmailu(rst.getString(4));
     
          utilisateurs.add(a);
      }
      return utilisateurs;}
         public List<Utilisateur> getALLUtilisateursNonbloque() throws SQLException
  { List<Utilisateur> utilisateurs= new ArrayList<>();
      String req="SELECT * FROM `user` where `enabled`=? and `roles`='a:0:{}'";
     PreparedStatement stm=connexion.prepareStatement(req);
              stm.setInt(1,1);
      ResultSet rst= stm.executeQuery();
      
      while(rst.next())
      { Utilisateur a=new Utilisateur();
         a.setIdu(rst.getInt(1));
     a.setNomu(rst.getString(2));
   
     a.setEmailu(rst.getString(4));
     
          utilisateurs.add(a);
      }
      return utilisateurs;}
       public void DebloquerCompteUser(int id) throws SQLException
    {
        String req= "UPDATE `user` SET `enabled`=? WHERE  `id`=? and `roles`='a:0:{}'";
     PreparedStatement PS=connexion.prepareStatement(req);
     PS.setInt(1,1);
     PS.setInt(2,id);
     
     PS.executeUpdate();
    }
        public void BloquerCompteUser(int id) throws SQLException
    {
        String req= "UPDATE `user` SET `enabled`=? WHERE  `id`=? and `roles`='a:0:{}'";
     PreparedStatement PS=connexion.prepareStatement(req);
     PS.setInt(1,0);
     PS.setInt(2,id);
     
     PS.executeUpdate();
    }
        public List<Activite> afficherActiviteNonValide() throws SQLException {
       List<Activite> maListe = new ArrayList();
        
            
            
            String requette = "SELECT * from activite1 where `valider`=?";
             PreparedStatement stm=connexion.prepareStatement(requette);
              stm.setInt(1,0);
      ResultSet rs= stm.executeQuery();
            while (rs.next()) {
                Activite U=new Activite();
                U.setId(rs.getInt(1));
                U.setNom(rs.getString(2));
                U.setType(rs.getString(3));
                U.setAdresse(rs.getString(4));
                U.setPays(rs.getString(5));
                U.setRegion(rs.getString(6));
                U.setDescription(rs.getString(7));
                U.setPrix(rs.getFloat(8));
                 U.setPlacedisponible(rs.getInt(9));
                U.setId_agence(rs.getInt(11));
               
                
                maListe.add(U);
            }
        

        return maListe;
        
    }
         public void VerifierActivite(int id) throws SQLException
    {
        String req= "UPDATE `activite1` SET`valider`=? WHERE  `Id_activite`=?";
     PreparedStatement PS=connexion.prepareStatement(req);
     PS.setInt(1,1);
     PS.setInt(2,id);
     
     PS.executeUpdate();
    }
}
