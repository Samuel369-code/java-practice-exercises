package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Song {

    private String isrc; //L'identificador: International Standard Reccording Code
    private String title;
    private int minutes;
    private int seconds;
    private String composer;
    private String lyricist;
    private String producer;
    private int plays;
    private double rating;
    private int downloads;
    private int likes;
    private double popularity;
    private boolean explicit;
    private List<String> tags = new ArrayList<>();

    public Song(String isrc, String title, int minutes, int seconds, String composer, String lyricist, String producer,
            int plays, double rating, int downloads, int likes, double popularity, boolean explicit, List<String> tags) {
        this.isrc = isrc;
        this.title = title;
        this.minutes = minutes;
        this.seconds = seconds;
        this.composer = composer;
        this.lyricist = lyricist;
        this.producer = producer;
        this.plays = plays;
        this.rating = rating;
        this.downloads = downloads;
        this.likes = likes;
        this.popularity = popularity;
        this.explicit = explicit;
        this.tags = tags;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
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

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
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

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        if (this.minutes != other.minutes) {
            return false;
        }
        if (this.seconds != other.seconds) {
            return false;
        }
        if (this.plays != other.plays) {
            return false;
        }
        if (Double.doubleToLongBits(this.rating) != Double.doubleToLongBits(other.rating)) {
            return false;
        }
        if (this.likes != other.likes) {
            return false;
        }
        if (Double.doubleToLongBits(this.popularity) != Double.doubleToLongBits(other.popularity)) {
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
        if (!Objects.equals(this.composer, other.composer)) {
            return false;
        }
        if (!Objects.equals(this.lyricist, other.lyricist)) {
            return false;
        }
        if (!Objects.equals(this.producer, other.producer)) {
            return false;
        }
        return Objects.equals(this.tags, other.tags);
    }

}
