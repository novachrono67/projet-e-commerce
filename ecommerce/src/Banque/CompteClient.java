package Banque;

public class CompteClient
{
    private String nomUtilisateur;
    private String motDePasse;
    private double solde;

    public CompteClient(String nomUtilisateur, String motDePasse, double solde)
    {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.solde = solde;
    }

    public String getNomUtilisateur()
    {
        return nomUtilisateur;
    }

    public String getMotDePasse()
    {
        return motDePasse;
    }

    public double getSolde()
    {
        return solde;
    }

    public boolean debiterSolde(double montant)
    {
        if(montant <= solde)
        {
            solde -= montant;
            System.out.println(nomUtilisateur + " paiement accepté");
            System.out.println(nomUtilisateur + " solde actuel: " + solde);
            return true;
        }

        System.out.println("Solde insuffisant");
        return false;
    }

    public void crediterSolde(double montant)
    {
        solde += montant;
    }


}
