package fr.uvsq.cprog.collex;

public class QuitterApp implements Commande{

    public QuitterApp(){
        execute();
    }

    @Override
    public void execute(){
        System.out.println("Au revoir !");
        System.exit(0);
    }
}
