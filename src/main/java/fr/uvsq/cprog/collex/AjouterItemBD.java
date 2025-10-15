package fr.uvsq.cprog.collex;

public class AjouterItemBD implements Commande {
    String texteCommande;

    public AjouterItemBD(String texteCommande){
        this.texteCommande = texteCommande;
        execute();
    }
    @Override
    public void execute(){
        Dns dns = new Dns();
        String[] parts = texteCommande.split("\\s+");
        if (parts.length != 3 || !parts[0].equals("add")) {
            throw new IllegalArgumentException("Invalid command format");
        }
        AdresseIP adrIP = new AdresseIP(parts[1]);
        NomMachine nomMac = new NomMachine(parts[2]);
        String message = dns.addItem(adrIP, nomMac);
        DnsTUI.affiche(message);
    }
}
