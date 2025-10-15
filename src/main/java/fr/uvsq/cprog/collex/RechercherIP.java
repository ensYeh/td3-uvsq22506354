package fr.uvsq.cprog.collex;

public class RechercherIP implements Commande{
    String texteCommande;

    public RechercherIP(String texteCommande){
        this.texteCommande = texteCommande;
        execute();
    }

    @Override
    public void execute() {
        Dns dns = new Dns();
        NomMachine nm = new NomMachine(texteCommande);
        DnsItem item = dns.getItem(nm);
        DnsTUI.affiche(item.getAdrIP().toString());
    }
}
