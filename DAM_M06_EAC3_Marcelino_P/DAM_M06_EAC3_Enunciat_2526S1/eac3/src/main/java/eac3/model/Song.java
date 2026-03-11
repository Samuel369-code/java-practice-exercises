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
@DiscriminatorValue("Song")  
public class Song extends Track {

    @Column(name = "isrc", length = 14)
    private String isrc;

    @Column(name = "compositor")
    private String composer;

    @Column(name = "lletra")
    private String lyricist;

    @Column(name = "productor")
    private String producer;

    public Song() {
    }

    public Song(int id, String title, int minutes, int seconds, boolean explicit, int plays, double rating, 
                int download, int likes, double popularity, List<String> tags,
                String isrc, String composer, String lyricist, String producer) {
        super(id, title, minutes, seconds, explicit, plays, rating, download, likes, popularity, tags);
        this.isrc = isrc;
        this.composer = composer;
        this.lyricist = lyricist;
        this.producer = producer;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getLyricist() {
        return lyricist;
    }

    public void setLyricist(String lyricist) {
        this.lyricist = lyricist;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.isrc);
        hash = 67 * hash + Objects.hashCode(this.composer);
        hash = 67 * hash + Objects.hashCode(this.lyricist);
        hash = 67 * hash + Objects.hashCode(this.producer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        final Song other = (Song) obj;
        if (!Objects.equals(this.isrc, other.isrc)) return false;
        if (!Objects.equals(this.composer, other.composer)) return false;
        if (!Objects.equals(this.lyricist, other.lyricist)) return false;
        return Objects.equals(this.producer, other.producer);
    }
}
