package fr.uvsq.cprog.collex;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Dns {
    String nomFichier;
    List<String> bddContenu;

    public Dns() {
        Properties props = new Properties();

        // Ouverture du fichier de configuration "config.properties"
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Impossible de trouver config.properties");
                return;
            }
            // Chargement de la propriété dns.database.filename qui contient le path du fichier de base de données du DNS
            props.load(input);
            String resourceFile = props.getProperty("dns.database.filename");

            Path runtimeFile = Path.of("dns_database_runtime.txt");
            this.nomFichier = runtimeFile.toString();

            if (!Files.exists(runtimeFile)) {
                try (InputStream dnsStream = getClass().getClassLoader().getResourceAsStream(resourceFile)) {
                    if (dnsStream != null) {
                        Files.copy(dnsStream, runtimeFile);
                        System.out.println("Copie initiale du fichier vers dns_database_runtime.txt");
                    } else {
                        // Créer un fichier vide si la ressource n’existe pas
                        Files.createFile(runtimeFile);
                        System.out.println("Création d’un nouveau fichier dns_database_runtime.txt vide");
                    }
                }
            }

            // Lire le contenu du fichier modifiable
            this.bddContenu = Files.readAllLines(runtimeFile);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier DNS :");
            e.printStackTrace();
        }
    }

    public DnsItem getItem(AdresseIP adrIP){
        //Retourner une instance de DnsItem à partir d'une adresse IP
        NomMachine nomMac = null;
        for(String item : bddContenu){
           if(item.contains(adrIP.toString())){
               String[] parts = item.split(" ");
               String[] nomMachine = parts[1].split("\\.");
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
                String[] adresse = parts[0].split("\\.");
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
            String nomD = (nomDomaine.split(" "))[1];
            if(item.contains(nomD)){
                String[] parts = item.split(" ");
                String[] adresse = parts[0].split("\\.");
                String[] machine = parts[1].split("\\.");
                NomMachine nomMac = new NomMachine(machine[0], machine[1], machine[2]);
                AdresseIP adrIP = new AdresseIP(Integer.parseInt(adresse[0]), Integer.parseInt(adresse[1]), Integer.parseInt(adresse[2]), Integer.parseInt(adresse[3]));
                listeIPDomaine.add(new DnsItem(adrIP, nomMac));
            }
        }
        return listeIPDomaine;
    }

    public String addItem(AdresseIP adrIP, NomMachine nomMac) {
        try {
            // Vérification doublons
            for (String line : bddContenu) {
                if (line.contains(adrIP.toString())) {
                    return "ERREUR : L'adresse IP existe déjà !";
                } else if (line.contains(nomMac.toString())) {
                    return "ERREUR : Le nom de machine existe déjà !";
                }
            }

            // Création de la nouvelle ligne
            String entry = adrIP.toString() + " " + nomMac.toString();

            // S’assurer que le fichier existe avant d’écrire
            Path filePath = Path.of(this.nomFichier);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            // Écrire dans le fichier (avec saut de ligne)
            Files.writeString(filePath, entry + System.lineSeparator(), StandardOpenOption.APPEND);

            // Mettre à jour la liste en mémoire
            this.bddContenu.add(entry);

            return "Ajout réussi.";

        } catch (IOException e) {
            e.printStackTrace();
            return "ERREUR : Impossible d'écrire dans le fichier : " + e.getMessage();
        }
    }

}
