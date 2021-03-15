package Magasin.Service;

import Magasin.Modele.Article;
import Magasin.Modele.ArticlesNike;
import Magasin.Modele.Magasin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class ServeurMagasin
{
    public static int PORT_MAGASIN = 1248;
    public static String ENDPOINT_MAGASIN = "Magasin";

    /**
     * Magasin.Service.Serveur RMI
     * @param args
     */
    public static void main (String[] args)
    {
        try
        {
            Magasin obj = new Magasin("Nike", new ArticlesNike());
            LocateRegistry.createRegistry(PORT_MAGASIN);
            Naming.rebind("rmi://localhost:" + PORT_MAGASIN + "/" + ENDPOINT_MAGASIN, obj);
            System.out.println ("Serveur Magasin Prêt");
        }
        catch (RemoteException e)
        {
            System.out.println(e.getMessage());
        }
        catch (MalformedURLException e)
        {
            e.getMessage();
        }
    }
}