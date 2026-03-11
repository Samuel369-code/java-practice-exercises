/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Song;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author joan
 */
public class SongManager {

    private final EntityManager em;

    public SongManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Obté totes les cançons
     * @return la llista de cançons
     */
    List<Song> getAll() {
        //TODO
        return em.createQuery("SELECT s FROM Song s", Song.class)
                .getResultList();
    }

    /**
     * Obté les cançons amb una durada mínima
     * @param seconds el temps en segons
     * @return les cançons amb una durada igual o superior
     */
    List<Song> getMinDuration(int seconds) {
        //Todo
        return em.createQuery(
        "SELECT s FROM Song s WHERE (s.minutes * 60 + s.seconds) >= :seconds",
        Song.class)
        .setParameter("seconds", seconds)
        .getResultList();
    }

}
