package Entites;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VoyageOrganise {
    private int id;
    private int prix_voyage;
    private int nb_places;
    private int id_agence;
    private Date date_depart;
    private Date date_retour;
    private String origine;
    private String pays_destination;
    private String ville_destination;
    private String hotel;
    
    private String nom_agence;
    private String image;
     private ImageView imgview;

    public VoyageOrganise(int prix_voyage, int nb_places, int id_agence, Date date_depart, Date date_retour, String origine, String pays_destination, String ville_destination, String hotel, String nom_agence, String image, ImageView imgview) {
        this.prix_voyage = prix_voyage;
        this.nb_places = nb_places;
        this.id_agence = id_agence;
        this.date_depart = date_depart;
        this.date_retour = date_retour;
        this.origine = origine;
        this.pays_destination = pays_destination;
        this.ville_destination = ville_destination;
        this.hotel = hotel;
        this.nom_agence = nom_agence;
        this.image = image;
        this.imgview = imgview;
    }
    
    public VoyageOrganise(int prix_voyage,  Date date_depart, Date date_retour, String origine, String pays_destination, String ville_destination, int nb_places,String hotel,  int id_agence,String nom_agence) {
        this.prix_voyage = prix_voyage;
        this.nb_places = nb_places;
        this.id_agence = id_agence;
        this.date_depart = date_depart;
        this.date_retour = date_retour;
        this.origine = origine;
        this.pays_destination = pays_destination;
        this.ville_destination = ville_destination;
        this.hotel = hotel;
        this.nom_agence = nom_agence;
       

    }
  public ImageView getImgview() {
        return imgview;
    }

    public void setImgview(ImageView imgview) {
        this.imgview = imgview;
        imgview.setFitHeight(150);
imgview.setFitWidth(250);
imgview.setPreserveRatio(false);
    }

    public VoyageOrganise(int id, int prix_voyage, int nb_places, int id_agence, Date date_depart, Date date_retour, String origine, String pays_destination, String ville_destination, String hotel, String nom_agence, String image, ImageView imgview) {
        this.id = id;
        this.prix_voyage = prix_voyage;
        this.nb_places = nb_places;
        this.id_agence = id_agence;
        this.date_depart = date_depart;
        this.date_retour = date_retour;
        this.origine = origine;
        this.pays_destination = pays_destination;
        this.ville_destination = ville_destination;
        this.hotel = hotel;
        this.nom_agence = nom_agence;
        this.image = image;
        this.imgview = imgview;
    }
    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public VoyageOrganise(int id, int prix_voyage, int nb_places, int id_agence, Date date_depart, Date date_retour, String origine, String pays_destination, String ville_destination, String hotel, String nom_agence, String image) {
        this.id = id;
        this.prix_voyage = prix_voyage;
        this.nb_places = nb_places;
        this.id_agence = id_agence;
        this.date_depart = date_depart;
        this.date_retour = date_retour;
        this.origine = origine;
        this.pays_destination = pays_destination;
        this.ville_destination = ville_destination;
        this.hotel = hotel;
        this.nom_agence = nom_agence;
        this.image = image;
    }

    public VoyageOrganise() {
    }
    public VoyageOrganise( String origine) {
        
        this.origine = origine;
    }

    public VoyageOrganise(int id, int prix_voyage, Date date_depart,
            Date date_retour,String origine,String pays_destination,String ville_destination,int nb_places,String hotel,
            int id_agence,String nom_agence) {
        this.id = id;
        this.prix_voyage = prix_voyage;
        this.date_depart = date_depart;
        this.date_retour = date_retour;
        this.origine = origine;
        this.pays_destination = pays_destination;
        this.ville_destination = ville_destination;
        this.nb_places = nb_places;
        this.hotel = hotel;
        this.id_agence = id_agence;
        this.nom_agence = nom_agence;
    }

    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix_voyage() {
        return prix_voyage;
    }

    public void setPrix_voyage(int prix_voyage) {
        this.prix_voyage = prix_voyage;
    }

    public Date getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getPays_destination() {
        return pays_destination;
    }

    public void setPays_destination(String pays_destination) {
        this.pays_destination = pays_destination;
    }

    public String getVille_destination() {
        return ville_destination;
        
    }

    public void setVille_destination(String ville_destination) {
        this.ville_destination = ville_destination;
    }

    public int getnb_places() {
        return nb_places;
    }

    public void setnb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public int getId_agence() {
        return id_agence;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public String getNom_agence() {
        return nom_agence;
    }

    public void setNom_agence(String nom_agence) {
        this.nom_agence = nom_agence;
    }
   /*  public static List<VoyageOrganise> generateImageViews(List<VoyageOrganise> voyageOrganise) {
        List<VoyageOrganise> liste = new ArrayList<VoyageOrganise>();

        for (VoyageOrganise voyageOrganisee : voyageOrganise) {
            File f = new File("C:\\wamp64\\www\\Holidaysnow01\\web\\resources\\"+voyageOrganisee.getImage());
            voyageOrganisee.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(voyageOrganisee);
        }
        return liste;
    }
   
   public static ArrayList<VoyageOrganise> generateImageViews(ArrayList<VoyageOrganise> voyageOrganise) {
        ArrayList<VoyageOrganise> liste = new ArrayList<VoyageOrganise>();

        for (VoyageOrganise voyageOrganisee : voyageOrganise) {
            File f = new File("C:\\wamp64\\www\\Holidaysnow01\\web\\resources\\"+voyageOrganisee.getImage());
            voyageOrganisee.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(voyageOrganisee);
        }
        return liste;
    }
     public static VoyageOrganise generateImageViews(VoyageOrganise hebergement) {
        
         VoyageOrganise h;
        
            File f = new File("C:\\wamp64\\www\\Holidaysnow01\\web\\resources\\" + hebergement.getImage());
            hebergement.setImgview(new ImageView(new Image(f.toURI().toString())));
            h=hebergement;
        
        return h;
    }*/
     public static List<VoyageOrganise> generateImageViews(List<VoyageOrganise> hebergements) {
        List<VoyageOrganise> liste = new ArrayList<VoyageOrganise>();

        for (VoyageOrganise hebergement : hebergements) {
            File f = new File("C:\\wamp64\\www\\Holidaysnow01\\web\\resources\\"+hebergement.getImage());
            hebergement.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(hebergement);
        }
        return liste;
    }
   
   public static ArrayList<VoyageOrganise> generateImageViews(ArrayList<VoyageOrganise> hebergements) {
        ArrayList<VoyageOrganise> liste = new ArrayList<VoyageOrganise>();

        for (VoyageOrganise hebergement : hebergements) {
            File f = new File("C:\\wamp64\\www\\Holidaysnow01\\web\\resources\\"+hebergement.getImage());
            hebergement.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(hebergement);
        }
        return liste;
    }
     public static VoyageOrganise generateImageViews(VoyageOrganise hebergement) {
        
         VoyageOrganise h;
        
            File f = new File("C:\\wamp64\\www\\Holidaysnow01\\web\\resources\\" + hebergement.getImage());
            hebergement.setImgview(new ImageView(new Image(f.toURI().toString())));
            h=hebergement;
        
        return h;
    }

        @Override
    public String toString() {
        return ("Id:"+id+"\nPrix Voyage:"+prix_voyage+"Date Depart"+date_depart+"Date Retour"+date_retour+"Origine:"+origine+"Pays destination"
                +pays_destination+"Ville Destination"+ville_destination+"Nombre de places:"+nb_places+"Hotel:"+hotel
                +"ID Agence:"+id_agence+"Nom Agence:"+nom_agence+", imgview=" + imgview );
    }
   
    
}
