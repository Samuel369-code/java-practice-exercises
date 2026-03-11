/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Podcast;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author joan
 */
public class PodcastManager {

    private final EntityManager em;

    public PodcastManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Consulta els podcasts pel showTitle
     * @param showTitle el títol del show
     * @return la llista de podcasts
     */
    List<Podcast> getByShowTitle(String showTitle) {
        return em.createQuery("SELECT p FROM Podcast p WHERE p.showTitle = :showTitle", Podcast.class)
                .setParameter("showTitle", showTitle)
                .getResultList();
    }

}
