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
        // Vérifie que c'est au format a.b.c (au moins 3 parties)
        if (!fullName.matches("^[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$")) {
            throw new IllegalArgumentException("Invalid machine name: " + fullName);
        }

        String[] parts = fullName.split("\\."); // sépare par '.'
        if (parts.length < 3) {
            throw new IllegalArgumentException("Nom de machine doit avoir au moins 3 parties");
        }

        this.nom = parts[0];
        this.qualifie = parts[1];
        this.machine = parts[2];
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
