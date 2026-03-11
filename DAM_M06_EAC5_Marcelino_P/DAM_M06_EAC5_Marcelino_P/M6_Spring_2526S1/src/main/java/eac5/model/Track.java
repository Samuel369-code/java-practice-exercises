/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
public abstract class Track implements Serializable {

    @Id
    private int id;
    private String title;
    private int minutes;
    private int seconds;
    private boolean explicit;
    private int plays;
    private double rating;
    private int download;
    private int likes;
    private double popularity;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @ManyToMany(mappedBy = "tracks", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Showcase> showcases = new ArrayList<>();

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
        this.tags = tags == null ? new ArrayList<>() : tags;
    }

    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Track other = (Track) obj;
    return this.id == other.id; // solo ID
}

@Override
public int hashCode() {
    return Objects.hash(this.id);
}
}

