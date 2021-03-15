package Magasin.Modele;

import Magasin.Service.ServiceBanque;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Magasin extends UnicastRemoteObject implements IMagasin
{
    private String nom;
    private ArrayList<Article> listeDesArticles;

    public Magasin(String nom) throws RemoteException
    {
        super();
        this.nom = nom;
    }

    public Magasin(String nom, ArrayList<Article> listeDesArticles) throws RemoteException
    {
        super();
        this.nom = nom;
        this.listeDesArticles = listeDesArticles;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public ArrayList<Article> getListeDesArticles()
    {
        return listeDesArticles;
    }

    public void setListeDesArticles(ArrayList<Article> listeDesArticles)
    {
        this.listeDesArticles = listeDesArticles;
    }

    public void ajouterArticle(Article article)
    {
        this.listeDesArticles.add(article);
    }

    public boolean payer(String nomUtilisateur, String motDePasse, ArrayList<Article> articles)
    {
        double montant = 0;
        for(Article a : articles)
        {
            montant += a.getPrix();
        }
        System.out.println(nomUtilisateur + " montant a payer: " + montant);
        boolean statutPaiement = ServiceBanque.payer(nomUtilisateur, motDePasse, montant);
        System.out.println("Paiment accepté? : " + statutPaiement);
        return statutPaiement;
    }
}
