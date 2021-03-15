package Magasin.Modele;

import java.io.Serializable;

public class Article implements Serializable
{
    private String nom;
    private double prix;

    public Article(String nom, double prix)
    {
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public double getPrix()
    {
        return prix;
    }

    public void setPrix(double prix)
    {
        this.prix = prix;
    }
}
