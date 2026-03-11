/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Showcase;
import eac3.model.Track;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author joan
 */
public class TrackManager {

    private final EntityManager em;

    TrackManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Insereix una pista a la BD
     *
     * @param track la pista
     * @throws ManagerException si la pista ja existeix
     */
    void insert(Track track) throws ManagerException {
        if (em.find(track.getClass(), track.getId()) != null) {
            throw new ManagerException("La pista " + track.getId() + " ja existeix");
        } else {
            em.getTransaction().begin();
            em.merge(track);
            em.getTransaction().commit();
        }
    }

    /**
     * Elimina una pista de la BD
     *
     * @param id l'id de la pista
     * @throws ManagerException si el track no existeix
     */
    void delete(int id) throws ManagerException {
        Track track = em.find(Track.class, id);

        if (track == null) {
            throw new ManagerException("No existeix cap track amb id " + id);
        }

        try {
            em.getTransaction().begin();

            for (Showcase showcase : track.getShowcases()) {
                showcase.getTracks().remove(track);
            }
            track.getShowcases().clear();

            em.remove(track);

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ManagerException("Error eliminant el track: " + e.getMessage());
        }
}


    /**
     * Obté totes les pistes de la BD
     *
     * @return la llista de pistes
     */
    List<Track> getAll() {
        //TODO
        return em.createQuery("SELECT t FROM Track t ", Track.class)
                .getResultList();
                
    }

    /**
     * Obté les pistes am contingut explícit
     *
     * @param explicit cert si es volen obtenir les pistes amb contingut
     * explícit, fals si es volen optenir les que no
     * @return la llista de pistes
     */
    List<Track> getTracksByExplicit(boolean explicit) {
        //TODO
        return em.createQuery("SELECT t FROM Track t where t.explicit = :explicit", Track.class)
                .setParameter("explicit", explicit)
                .getResultList();
    }

    /**
     * Afegir un número de descarregues a una pista
     *
     * @param trackID l'id de la pista
     */
    void addDownloads(int trackID, int downloads){
        //TODO
        Track track = em.find(Track.class, trackID);
        
        if (track == null) {
                    
        }
        
        track.setDownload(track.getDownload()+ downloads);
        
        em.merge(track);
    }

    /**
     * Estableix una popularitat determinada a totes les pistes dins d'un
     * interval de likes
     *
     * @param popularity la popularitat a establir
     * @param minLikes el mínim número de likes de l'interval
     * @param maxLikes el màxim número de likes de l'interval
     */
    void setPopularity(double popularity, int minLikes, int maxLikes) {
        em.getTransaction().begin(); // 🔹 Abrir transacción

        em.createQuery(
            "UPDATE Track t SET t.popularity = :popularity WHERE t.likes BETWEEN :minLikes AND :maxLikes")
            .setParameter("popularity", popularity)
            .setParameter("minLikes", minLikes)
            .setParameter("maxLikes", maxLikes)
            .executeUpdate();

        em.getTransaction().commit();
    }


}
