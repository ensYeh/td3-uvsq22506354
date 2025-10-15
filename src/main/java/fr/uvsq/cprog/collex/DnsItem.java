package fr.uvsq.cprog.collex;

public class DnsItem {
    private AdresseIP adrIP;
    private NomMachine nomMac;

    public DnsItem(){

    }

    public DnsItem(AdresseIP adrIP, NomMachine nomMac){
        this.adrIP = adrIP;
        this.nomMac = nomMac;
    }

    public AdresseIP getAdrIP() {
        return adrIP;
    }

    public NomMachine getNomMac(){
        return nomMac;
    }

    @Override
    public String toString(){
        return adrIP.toString()+" "+nomMac.toString();
    }
}
