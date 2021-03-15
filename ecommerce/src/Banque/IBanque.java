package Banque;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBanque extends Remote
{
    boolean demandeDePayement(String nomUtilisateur, String motDePasse, double montant) throws RemoteException;

}
