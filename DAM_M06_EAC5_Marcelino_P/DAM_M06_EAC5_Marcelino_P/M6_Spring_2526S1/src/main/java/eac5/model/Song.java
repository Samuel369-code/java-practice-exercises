/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.model;

import jakarta.persistence.Entity;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Song extends Track {

    private String isrc;
    private String composer;
    private String lyricist;
    private String producer;

    public Song(int id, String title, int minutes, int seconds, boolean explicit, int plays, double rating,
            int download, int likes, double popularity, List<String> tags,
            String isrc, String composer, String lyricist, String producer) {
        super(id, title, minutes, seconds, explicit, plays, rating, download, likes, popularity, tags);
        this.isrc = isrc;
        this.composer = composer;
        this.lyricist = lyricist;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        final Song other = (Song) obj;
        if (!Objects.equals(this.isrc, other.isrc)) {
            return false;
        }
        if (!Objects.equals(this.composer, other.composer)) {
            return false;
        }
        if (!Objects.equals(this.lyricist, other.lyricist)) {
            return false;
        }
        return Objects.equals(this.producer, other.producer);
    }

}
