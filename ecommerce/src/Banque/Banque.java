package Banque;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Banque extends UnicastRemoteObject implements IBanque
{
    private ArrayList<CompteClient> listeCompteClient;

    public Banque() throws RemoteException
    {
        super();
        this.listeCompteClient = new ArrayList<>();
        listeCompteClient.add(new CompteClient("Omar Sy", "passe1", 300));
        listeCompteClient.add(new CompteClient("Jean Lassalle", "passe2", 400));
        listeCompteClient.add(new CompteClient("Marie Curie", "passe3", 700));
    }

    public boolean demandeDePayement(String nomUtilisateur, String motDePasse, double montant)
    {
        for(CompteClient compte : listeCompteClient)
        {
            if(compte.getNomUtilisateur().equals(nomUtilisateur) && compte.getMotDePasse().equals(motDePasse))
            {
                return compte.debiterSolde(montant);
            }
        }
        System.out.println("Nom d'utilisateur ou mot de passe incorrect");
        return false;
    }
}
