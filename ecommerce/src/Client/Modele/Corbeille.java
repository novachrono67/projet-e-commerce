package Client.Modele;

import Magasin.Modele.Article;
import java.util.ArrayList;

public class Corbeille
{
    private static String nomUtilisateur;
    private static String motDePasse;
    private static ArrayList<Article> listeDesArticles;

    public Corbeille()
    {

    }

    public Corbeille(String nomUtilisateur, String motDePasse)
    {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        listeDesArticles = new ArrayList<>();
    }

    public String getNomUtilisateur()
    {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur)
    {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse()
    {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse)
    {
        this.motDePasse = motDePasse;
    }

    public static ArrayList<Article> getListeDesArticles()
    {
        return listeDesArticles;
    }

    public static void ajouterArticle(Article article)
    {
        listeDesArticles.add(article);
    }

    public static void retirerArticle(Article article)
    {
        listeDesArticles.remove(article);
    }

    public static void viderCorbeille()
    {
        nomUtilisateur = "";
        motDePasse = "";
        listeDesArticles = new ArrayList<>();
    }

    public static double getTotal()
    {
        double total = 0;
        for(Article a : listeDesArticles)
        {
            total += a.getPrix();
        }
        return total;
    }
}
