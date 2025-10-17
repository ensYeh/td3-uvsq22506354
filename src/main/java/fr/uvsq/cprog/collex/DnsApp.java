package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsApp {

    public static void run(String arg){
        DnsTUI.nextCommande(arg);
    }

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir vos commandes (tapez 'quit' pour quitter) :");

        while (true) {
            System.out.print("> "); // invite
            String str = sc.nextLine().trim();

            if (str.equalsIgnoreCase("quit")) {
                // Appelle ta classe QuitterApplication pour quitter
                QuitterApp a = new QuitterApp();
                break; // quitte la boucle au cas où
            }

            try {
                run(str); // ta méthode pour exécuter la commande
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        }

        sc.close();
    }
}

