package fr.uvsq.cprog.collex;

public class NomMachine {
    private String nom;
    private String qualifie;
    private String machine;

    public NomMachine(String nom, String qualifie, String machine){
        this.nom = nom;
        this.qualifie = qualifie;
        this.machine = machine;
    }

    public NomMachine(String fullName) {
        if (!fullName.matches("^[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$")) {
            throw new IllegalArgumentException("Invalid machine name: " + fullName);
        }

        int firstDot = fullName.indexOf('.');
        this.nom = fullName.substring(0, firstDot);
        this.qualifie = fullName.substring(firstDot + 1);
        this.machine = fullName;
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
