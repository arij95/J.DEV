/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author RANI
 */
public class ReservationVols {
    private int id_reservation;
    private int id_vol;
    private int id_user;
    private int id_agence;
    private int prix;
    private int nbrplace;
    
    public ReservationVols( int id_vol, int id_user, int id_agence, int prix, int nbrplace) {
        
        this.id_vol = id_vol;
        this.id_user = id_user;
        this.id_agence = id_agence;
        this.prix = prix;
        this.nbrplace = nbrplace;
    }
   
    
    public ReservationVols(int id_reservation, int id_vol, int id_user, int id_agence) {
        this.id_reservation = id_reservation;
        this.id_vol = id_vol;
        this.id_user = id_user;
        this.id_agence = id_agence;
    }

    public ReservationVols(int id_vol, int id_user, int id_agence) {
        this.id_vol = id_vol;
        this.id_user = id_user;
        this.id_agence = id_agence;
    }

    public ReservationVols() {
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_agence() {
        return id_agence;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    @Override
    public String toString() {
        return "ReservationVols{" + "id_reservation=" + id_reservation + ", id_vol=" + id_vol + ", id_user=" + id_user + ", id_agence=" + id_agence + '}';
    }

   
}
