/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author joan
 */
//TODO Afegir les anotacions JPA
@Entity
@DiscriminatorValue("Podcast")  
public class Podcast extends Track {

    @Column(name = "Lletra")
    private String host;

    @Column(name = "nom_del_show")
    private String showTitle;

    @Column(name = "episodi")
    private int episodeNumber;

    public Podcast() {
    }

    public Podcast(int id, String title, int minutes, int seconds, boolean explicit, int plays, double rating, 
                   int download, int likes, double popularity, List<String> tags,
                   String host, String showTitle, int episodeNumber) {
        super(id, title, minutes, seconds, explicit, plays, rating, download, likes, popularity, tags);
        this.host = host;
        this.showTitle = showTitle;
        this.episodeNumber = episodeNumber;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.host);
        hash = 83 * hash + Objects.hashCode(this.showTitle);
        hash = 83 * hash + this.episodeNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        final Podcast other = (Podcast) obj;
        if (this.episodeNumber != other.episodeNumber) return false;
        if (!Objects.equals(this.host, other.host)) return false;
        return Objects.equals(this.showTitle, other.showTitle);
    }
}

