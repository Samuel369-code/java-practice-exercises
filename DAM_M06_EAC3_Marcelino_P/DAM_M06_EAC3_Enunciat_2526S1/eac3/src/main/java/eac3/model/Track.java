/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author joan
 */
//TODO Afegir les anotacions JPA
@Entity
@Table(name = "track")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "track_type") // opcional, pero recomendable
public abstract class Track implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "Titol", length = 100)
    private String title;

    @Column(name = "minuts")
    private int minutes;

    @Column(name = "segons")
    private int seconds;

    @Column(name = "es_explicit")
    private boolean explicit;

    @Column(name = "reproduccions")
    private int plays;

    @Column(name = "valoracio")
    private double rating;

    @Column(name = "descarreguas")
    private int download;

    @Column(name = "likes")
    private int likes;

    @Column(name = "popularitat")
    private double popularity;

    @ElementCollection
    @Column(name = "tag")
    private List<String> tags;

    @ManyToMany
    @JoinTable(
        name = "track_showcase", 
        joinColumns = @JoinColumn(name = "track_id"),       // FK hacia Track
        inverseJoinColumns = @JoinColumn(name = "showcase_id") // FK hacia Showcase
    )
    private List<Showcase> showcases = new ArrayList<>();

    public Track() {
    }

    public Track(int id, String title, int minutes, int seconds, boolean explicit, int plays, double rating, int download, int likes, double popularity, List<String> tags) {
        this.id = id;
        this.title = title;
        this.minutes = minutes;
        this.seconds = seconds;
        this.explicit = explicit;
        this.plays = plays;
        this.rating = rating;
        this.download = download;
        this.likes = likes;
        this.popularity = popularity;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Showcase> getShowcases() {
        return showcases;
    }

    public void setShowcases(List<Showcase> showcases) {
        this.showcases = showcases;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.title);
        hash = 97 * hash + this.minutes;
        hash = 97 * hash + this.seconds;
        hash = 97 * hash + (this.explicit ? 1 : 0);
        hash = 97 * hash + this.plays;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.rating) ^ (Double.doubleToLongBits(this.rating) >>> 32));
        hash = 97 * hash + this.download;
        hash = 97 * hash + this.likes;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.popularity) ^ (Double.doubleToLongBits(this.popularity) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.tags);
        hash = 97 * hash + Objects.hashCode(this.showcases);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Track other = (Track) obj;
        if (this.minutes != other.minutes) {
            return false;
        }
        if (this.seconds != other.seconds) {
            return false;
        }
        if (this.explicit != other.explicit) {
            return false;
        }
        if (this.plays != other.plays) {
            return false;
        }
        if (Double.doubleToLongBits(this.rating) != Double.doubleToLongBits(other.rating)) {
            return false;
        }
        if (this.download != other.download) {
            return false;
        }
        if (this.likes != other.likes) {
            return false;
        }
        if (Double.doubleToLongBits(this.popularity) != Double.doubleToLongBits(other.popularity)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }

        List<Integer> idsShowcasesThis = this.showcases.stream().map(p -> p.getId()).toList();
        List<Integer> idsShowcasesOther = other.showcases.stream().map(p -> p.getId()).toList();
        boolean result = idsShowcasesThis.size() == idsShowcasesOther.size() && idsShowcasesThis.containsAll(idsShowcasesOther);
        return result;

    }

}
