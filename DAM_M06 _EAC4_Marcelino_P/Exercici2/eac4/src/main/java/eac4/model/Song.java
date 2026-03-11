/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author joan
 */
@XmlRootElement(name = "song")
//@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"isrc", "title", "duration", "popularity", "downloads", "explicit", "tags"})
public class Song {

    private String isrc;
    private String title;
    private int duration;
    private double popularity;
    private int downloads;
    private boolean explicit;
    private final List<String> tags = new ArrayList<>();

    public Song() {

    }

    public Song(String isrc, String title, int duration, double popularity, int downloads, boolean explicit) {
        this.isrc = isrc;
        this.title = title;
        this.duration = duration;
        this.popularity = popularity;
        this.downloads = downloads;
        this.explicit = explicit;
    }

   

    @XmlAttribute
    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @XmlElement
    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @XmlElement
    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }
    
    @XmlElement
    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags.clear();
        this.tags.addAll(tags);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.isrc);
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + this.duration;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.popularity) ^ (Double.doubleToLongBits(this.popularity) >>> 32));
        hash = 53 * hash + this.downloads;
        hash = 53 * hash + (this.explicit ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.tags);
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
        final Song other = (Song) obj;
        if (this.duration != other.duration) {
            return false;
        }
        if (Double.doubleToLongBits(this.popularity) != Double.doubleToLongBits(other.popularity)) {
            return false;
        }
        if (this.downloads != other.downloads) {
            return false;
        }
        if (this.explicit != other.explicit) {
            return false;
        }
        if (!Objects.equals(this.isrc, other.isrc)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return Objects.equals(this.tags, other.tags);
    }

    

   

    

    
    
}
