package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DnsTest
{
    DnsItem dnsitem;
    static Dns dns;
    List<DnsItem> liste = new ArrayList<DnsItem>();
    String ip1;
    String ip2;
    String mac1;
    String mac2;

    @BeforeEach
    public void overwriteFile() {
        dns = new Dns();
        try {
            Path runtimeFile = Path.of(dns.nomFichier);
            // Efface le contenu du fichier sans le supprimer
            Files.write(runtimeFile, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
            dns.bddContenu = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erreur lors du vidage du fichier : " + e.getMessage());
        }
        dnsitem = null;
        ip1 = "193.51.25.90";
        mac1 = "www.uvsq.fr";
        ip2 = "193.51.31.154";
        mac2 = "poste.uvsq.fr";
    }

    @Test
    public void addItemTest()
    {
        AdresseIP ip = new AdresseIP(ip1);
        NomMachine nomMachine = new NomMachine(mac1);
        assertEquals(dns.addItem(ip, nomMachine),"Ajout réussi.");
    }

    @Test
    public void getItemIPTest()
    {
        AdresseIP ip = new AdresseIP(ip1);
        NomMachine nomMachine = new NomMachine(mac1);
        dns.addItem(ip, nomMachine);
        dnsitem = dns.getItem(ip);
        assertEquals(dnsitem.getNomMac().toString(), mac1);
    }

    @Test
    public void getItemsDomaineTest()
    {
        AdresseIP ip = new AdresseIP(ip1);
        NomMachine nomMachine = new NomMachine(mac1);
        dns.addItem(ip, nomMachine);
        AdresseIP anotherip = new AdresseIP(ip2);
        NomMachine nomMachine2 = new NomMachine(mac2);
        dns.addItem(anotherip, nomMachine2);
        String domaine = "ls uvsq.fr";
        liste = dns.getItems(domaine);
        StringBuilder listeResultat = new StringBuilder();
        for(DnsItem i : liste){
            listeResultat.append(i.toString()+"\n");

        }
        String resultat = listeResultat.toString();
        assertEquals(resultat, "193.51.25.90 www.uvsq.fr\n" +
                "193.51.31.154 poste.uvsq.fr\n");
    }

    @Test
    public void getItemNomMacTest()
    {
        AdresseIP ip = new AdresseIP(ip1);
        NomMachine nomMachine = new NomMachine(mac1);
        dns.addItem(ip, nomMachine);
        dnsitem = dns.getItem(nomMachine);
        assertEquals(dnsitem.getAdrIP().toString(), ip1);
    }

}
