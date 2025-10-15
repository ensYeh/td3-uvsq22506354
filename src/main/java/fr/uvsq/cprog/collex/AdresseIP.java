package fr.uvsq.cprog.collex;

public class AdresseIP {
    private int adr;
    private int es;
    private int se;
    private int ip;

    public AdresseIP(int adr, int es, int se, int ip){
        this.adr = adr;
        this.es = es;
        this.se = se;
        this.ip = ip;
    }

    public AdresseIP(String adresseIP) {
        if (!adresseIP.matches("^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$")) {
            throw new IllegalArgumentException("Invalid IPv4 address: " + adresseIP);
        }
        String[] parts = adresseIP.split("\\.");
        this.adr = Integer.parseInt(parts[0]);
        this.es  = Integer.parseInt(parts[1]);
        this.se  = Integer.parseInt(parts[2]);
        this.ip  = Integer.parseInt(parts[3]);
    }

    public int getAdr() {
        return adr;
    }

    public int getEs() {
        return es;
    }

    public int getSe() {
        return se;
    }

    public int getIp() {
        return ip;
    }

    @Override
    public String toString(){
        return ((Integer)adr)+"."+((Integer)es)+"."+((Integer)se)+"."+((Integer)ip);
    }
}
