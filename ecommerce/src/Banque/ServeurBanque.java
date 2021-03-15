package Banque;

import Magasin.Modele.ArticlesNike;
import Magasin.Modele.Magasin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServeurBanque
{
    public static int PORT_BANQUE = 1249;
    public static String ENDPOINT_BANQUE = "Banque";

    public static void main (String[] args)
    {
        try
        {
            Banque obj = new Banque();
            LocateRegistry.createRegistry(PORT_BANQUE);
            Naming.rebind("rmi://localhost:" + PORT_BANQUE + "/" + ENDPOINT_BANQUE, obj);
            System.out.println ("Serveur Banque Prêt");
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
