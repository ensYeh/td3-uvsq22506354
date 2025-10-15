package fr.uvsq.cprog.collex;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Dns {
    private String nomFichier;
    private List<String> bddContenu;

    public Dns(){
        //Charger la base de données depuis le fichier "BDD du DNS"
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            this.nomFichier = props.getProperty("dns.database.filename");
            System.out.println("Le fichier de base de données du DNS chargé : " + nomFichier);
            this.bddContenu = Files.readAllLines(Path.of(this.nomFichier));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier de propriétés : " + e.getMessage());
        }
    }

    public DnsItem getItem(AdresseIP adrIP){
        //Retourner une instance de DnsItem à partir d'une adresse IP
        NomMachine nomMac = null;
        for(String item : bddContenu){
           if(item.contains(adrIP.toString())){
               String[] parts = item.split(" ");
               String[] nomMachine = parts[0].split("\\.");
               nomMac = new NomMachine(nomMachine[0], nomMachine[1], nomMachine[2]);
               break;
           }
        }
        return new DnsItem(adrIP, nomMac);
    }

    public DnsItem getItem(NomMachine nomMac){
        //Retourner une instance de DnsItem à partir d'un nom de machine
        AdresseIP adrIP = null;
        for(String item : bddContenu){
            if(item.contains(nomMac.toString())){
                String[] parts = item.split(" ");
                String[] adresse = parts[1].split("\\.");
                adrIP = new AdresseIP(Integer.parseInt(adresse[0]), Integer.parseInt(adresse[1]), Integer.parseInt(adresse[2]), Integer.parseInt(adresse[3]));
                break;
            }
        }
        return new DnsItem(adrIP, nomMac);
    }

    public List<DnsItem> getItems(String nomDomaine){
        //Retourner une liste d'instances de DnsItem à partir d'un nom de domaine
        List<DnsItem> listeIPDomaine = new ArrayList<DnsItem>();
        for(String item : bddContenu){
            if(item.contains(nomDomaine+" ")){
                String[] parts = item.split(" ");
                String[] machine = parts[0].split("\\.");
                String[] adresse = parts[1].split("\\.");
                NomMachine nomMac = new NomMachine(machine[0], machine[1], machine[2]);
                AdresseIP adrIP = new AdresseIP(Integer.parseInt(adresse[0]), Integer.parseInt(adresse[1]), Integer.parseInt(adresse[2]), Integer.parseInt(adresse[3]));
                listeIPDomaine.add(new DnsItem(adrIP, nomMac));
            }
        }
        return listeIPDomaine;
    }

    public String addItem(AdresseIP adrIP, NomMachine nomMac) {
        try {
            for (String line : bddContenu) {
                if (line.contains(adrIP.toString())) {
                    return "ERREUR : L'adresse IP existe déjà !";
                }
                else if (line.contains(nomMac.toString())) {
                    return "ERREUR : Le nom de machine existe déjà !";
                }
            }

            List<String> dnsItem = new ArrayList<>();
            dnsItem.add(adrIP.toString() + " " + nomMac.toString()); // IP first, machine second
            Files.write(Path.of(this.nomFichier), dnsItem, StandardOpenOption.APPEND);

            return "Ajout réussi.";

        } catch (IOException e) {
            e.printStackTrace();
            return "ERREUR : Impossible d'écrire dans le fichier : " + e.getMessage();
        }
    }

}
