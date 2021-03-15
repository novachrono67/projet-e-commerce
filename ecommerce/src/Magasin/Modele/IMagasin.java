package Magasin.Modele;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMagasin extends Remote
{
        ArrayList<Article> getListeDesArticles() throws RemoteException;
        boolean payer(String nomUtilisateur, String motDePasse, ArrayList<Article> articles) throws RemoteException;
}
