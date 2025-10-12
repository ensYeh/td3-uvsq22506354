package fr.uvsq.cprog.collex;

public class NomMachine {
    private String nom;
    private String qualifie;
    private String machine;

    public NomMachine(){

    }

    public NomMachine(String nom, String qualifie, String machine){
        this.nom = nom;
        this.qualifie = qualifie;
        this.machine = machine;
    }

    public String getNom(){
        return nom;
    }

    public String getQualifie(){
        return qualifie;
    }

    public String getMachine(){
        return machine;
    }

    @Override
    public String toString(){
        return nom+"."+qualifie+"."+machine;
    }
}
