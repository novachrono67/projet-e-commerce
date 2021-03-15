package Magasin.Service;

import Banque.IBanque;
import Magasin.Modele.Article;
import Magasin.Modele.IMagasin;

import java.rmi.Naming;
import java.util.ArrayList;

public class ServiceBanque
{
    public static int PORT_BANQUE = 1249;
    public static String ENDPOINT_BANQUE = "Banque";

    public static boolean payer(String nomUtilisateur, String motDePasse, double montant)
    {
        boolean confirmation = false;
        try
        {
            IBanque obj = (IBanque) Naming.lookup("rmi://localhost:" + PORT_BANQUE + "/" + ENDPOINT_BANQUE);
            confirmation = obj.demandeDePayement(nomUtilisateur, motDePasse, montant);
        }
        catch (Exception e)
        {
            System.out.println("Client.Client exception: " + e);
        }

        return confirmation;
    }
}
