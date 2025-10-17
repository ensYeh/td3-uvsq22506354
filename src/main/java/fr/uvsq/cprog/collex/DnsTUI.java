package fr.uvsq.cprog.collex;

import java.util.regex.Pattern;

public class DnsTUI {

    public DnsTUI(){

    }

    public static Commande nextCommande(String texteUtilisateur){
        String hostnameRegex = "^(?!\\d+\\.\\d+\\.\\d+\\.\\d+$)([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        String ipv4Regex = "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$";
        String domainRegex = "^ls(\\s+-a)?\\s+[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$";
        String regexAdd = "^add\\s+((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\s+[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$";
        Pattern hostPattern = Pattern.compile(hostnameRegex);
        Pattern ipPattern = Pattern.compile(ipv4Regex);
        Pattern domaineRegex = Pattern.compile(domainRegex);
        Pattern regexAjout = Pattern.compile(regexAdd);
        if(texteUtilisateur.equals("quit")){
            return new QuitterApp();
        }
        else if (ipPattern.matcher(texteUtilisateur).matches()) {
            System.out.println(texteUtilisateur + " → IPv4 address");
            return new RechercherNom(texteUtilisateur);
        } else if (hostPattern.matcher(texteUtilisateur).matches()) {
            System.out.println(texteUtilisateur + " → Valid hostname");
            return new RechercherIP(texteUtilisateur);
        } else if(domaineRegex.matcher(texteUtilisateur).matches()) {
            System.out.println(texteUtilisateur + " → ls [-a] domain");
            return new RechercherMachinesDomaine(texteUtilisateur);
        }
        else if(regexAjout.matcher(texteUtilisateur).matches()) {
            System.out.println(texteUtilisateur + " → add adr.es.se.ip nom.qualifié.machine");
            return new AjouterItemBD(texteUtilisateur);
        }
        else {
            return null;
        }
    }

    public static void affiche(String result){
        System.out.println(result);
    }
}
