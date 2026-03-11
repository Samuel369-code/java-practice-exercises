/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Samuel
 */
@XmlRootElement(name="album")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","titol","artista","any","genere","segell","durada","cancons"})
public class Album {

    private String id;
    private String titol;
    private String artista;
    private int any;
    private String genere;
    private String segell;
    private String durada;

    @XmlElementWrapper(name="cancons")
    @XmlElement(name="canco")
    private List<Canco> cancons = new ArrayList<>();

    public Album() {}

    public Album(String id, String titol, String artista, int any, String genere, String segell, String durada) {
        this.id = id;
        this.titol = titol;
        this.artista = artista;
        this.any = any;
        this.genere = genere;
        this.segell = segell;
        this.durada = durada;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitol() { return titol; }
    public void setTitol(String titol) { this.titol = titol; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public int getAny() { return any; }
    public void setAny(int any) { this.any = any; }
    public String getGenere() { return genere; }
    public void setGenere(String genere) { this.genere = genere; }
    public String getSegell() { return segell; }
    public void setSegell(String segell) { this.segell = segell; }
    public String getDurada() { return durada; }
    public void setDurada(String durada) { this.durada = durada; }
    public List<Canco> getCancons() { return cancons; }
    public void setCancons(List<Canco> cancons) { this.cancons = cancons; }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.titol);
        hash = 61 * hash + Objects.hashCode(this.artista);
        hash = 61 * hash + this.any;
        hash = 61 * hash + Objects.hashCode(this.genere);
        hash = 61 * hash + Objects.hashCode(this.segell);
        hash = 61 * hash + Objects.hashCode(this.durada);
        hash = 61 * hash + Objects.hashCode(this.cancons);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Album other = (Album) obj;
        if (this.any != other.any) return false;
        if (!Objects.equals(this.titol, other.titol)) return false;
        if (!Objects.equals(this.artista, other.artista)) return false;
        if (!Objects.equals(this.genere, other.genere)) return false;
        if (!Objects.equals(this.segell, other.segell)) return false;
        if (!Objects.equals(this.durada, other.durada)) return false;
        return Objects.equals(this.cancons, other.cancons);
    }

    /**
     * Obté les cançons de durada igual o superior a una determinada
     *
     * @param strDurada cadena que representa la durada hh:mm:ss
     * @return la llista de cançons
     */
    public List<Canco> getCanconsDuradaMin(String strDurada) {
        List<Canco> llista = new ArrayList<>();
        // Convertir hh:mm:ss a segons
        String[] parts = strDurada.split(":");
        int minSegons = Integer.parseInt(parts[0])*3600 +
                        Integer.parseInt(parts[1])*60 +
                        Integer.parseInt(parts[2]);
        
        for (Canco c : cancons) {
            String[] cParts = c.getDurada().split(":");
            int cSegons = Integer.parseInt(cParts[0])*3600 +
                          Integer.parseInt(cParts[1])*60 +
                          Integer.parseInt(cParts[2]);
            if (cSegons >= minSegons) {
                llista.add(c);
            }
        }
        return llista;
    }
}
