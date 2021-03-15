package Client.Service;

import Client.Modele.Corbeille;
import Magasin.Modele.Article;
import Magasin.Modele.IMagasin;
import Magasin.Service.ServeurMagasin;

import java.rmi.Naming;
import java.util.ArrayList;

public class ServiceMagasin
{
    public static int PORT_MAGASIN = 1248;
    public static String ENDPOINT_MAGASIN = "Magasin";

    public static ArrayList<Article> getListeDesArticles()
    {
        ArrayList<Article> listeDesArticles = new ArrayList<>();
        try
        {
            IMagasin obj = (IMagasin) Naming.lookup("rmi://localhost:" + ServeurMagasin.PORT_MAGASIN + "/" + ServeurMagasin.ENDPOINT_MAGASIN);
            listeDesArticles = obj.getListeDesArticles();
        }
        catch (Exception e)
        {
            System.out.println("Client.Client exception: " + e);
        }
        return listeDesArticles;
    }

    public static boolean payer()
    {
        Corbeille corbeille = new Corbeille();
        boolean statutPaiement = false;
        try
        {
            IMagasin obj = (IMagasin) Naming.lookup("rmi://localhost:" + ServeurMagasin.PORT_MAGASIN + "/" + ServeurMagasin.ENDPOINT_MAGASIN);
            statutPaiement = obj.payer(corbeille.getNomUtilisateur(), corbeille.getMotDePasse(), corbeille.getListeDesArticles());
        }
        catch (Exception e)
        {
            System.out.println("Client.Client exception: " + e);
        }
        System.out.println("Paiement accepté? : " + statutPaiement);
        return statutPaiement;
    }
}