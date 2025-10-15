package fr.uvsq.cprog.collex;

import java.util.ArrayList;
import java.util.List;

public class RechercherMachinesDomaine implements Commande{
    String texteCommande;

    public RechercherMachinesDomaine(String texteCommande){
        this.texteCommande = texteCommande;
        execute();
    }

    @Override
    public void execute(){
        Dns dns = new Dns();
        List<DnsItem> liste = dns.getItems(texteCommande);
        StringBuilder resultat = new StringBuilder();
        for (DnsItem item : liste) {
            resultat.append(item.toString()).append(System.lineSeparator());
        }
        String res = resultat.toString();
        DnsTUI.affiche(res);
    }
}
