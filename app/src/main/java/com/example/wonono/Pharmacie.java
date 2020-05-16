package com.example.wonono;

import android.database.sqlite.SQLiteOpenHelper;

public class Pharmacie  {
    String id ;
    String nom;
    String telephone ;
    String email;
    String detail ;
    String ville;


    public Pharmacie(String id, String nom, String telephone, String email, String detail , String ville) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.detail = detail;
        this.ville =ville;



    }


    //Getter

    public String getId() {
        return id;
    }
    public String getVille() {
        return ville;
    }
    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getDetail() {
        return detail;
    }

    //Setter

    public void setId(String id) {
        this.id = id;
    }
    public void setVille(String id) {
        this.ville= ville;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }



}
