/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.model;

import java.util.List;
import java.util.Objects;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Audiobook extends Track {

    private String author;
    private String narrator;
    private String publisher;
    private int totalChapters;

    public Audiobook(int id, String title, int minutes, int seconds, boolean explicit, int plays, double rating, int download, int likes, double popularity, List<String> tags, String author, String narrator, String publisher, int totalChapters) {
        super(id, title, minutes, seconds, explicit, plays, rating, download, likes, popularity, tags);
        this.author = author;
        this.narrator = narrator;
        this.publisher = publisher;
        this.totalChapters = totalChapters;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.author);
        hash = 23 * hash + Objects.hashCode(this.narrator);
        hash = 23 * hash + Objects.hashCode(this.publisher);
        hash = 23 * hash + this.totalChapters;
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

        final Audiobook other = (Audiobook) obj;
        if (this.totalChapters != other.totalChapters) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.narrator, other.narrator)) {
            return false;
        }
        return Objects.equals(this.publisher, other.publisher);
    }
}
