/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Samuel
 */
//Posar les anotacions JAXB
@XmlRootElement(name="canco")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"isrc","titol","durada","popularitat","explicit"})
public class Canco {

    private String isrc;
    private String titol;
    private String durada;
    private int popularitat;
    private boolean explicit;

    public Canco() {
    }

    public Canco(String isrc, String titol, String durada, int popularitat, boolean explicit) {
        this.isrc = isrc;
        this.titol = titol;
        this.durada = durada;
        this.popularitat = popularitat;
        this.explicit = explicit;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getDurada() {
        return durada;
    }

    public void setDurada(String durada) {
        this.durada = durada;
    }

    public int getPopularitat() {
        return popularitat;
    }

    public void setPopularitat(int popularitat) {
        this.popularitat = popularitat;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.isrc);
        hash = 37 * hash + Objects.hashCode(this.titol);
        hash = 37 * hash + Objects.hashCode(this.durada);
        hash = 37 * hash + this.popularitat;
        hash = 37 * hash + (this.explicit ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        final Canco other = (Canco) obj;
        if (this.popularitat != other.popularitat) { return false; }
        if (this.explicit != other.explicit) { return false; }
        if (!Objects.equals(this.isrc, other.isrc)) { return false; }
        if (!Objects.equals(this.titol, other.titol)) { return false; }
        return Objects.equals(this.durada, other.durada);
    }

}
