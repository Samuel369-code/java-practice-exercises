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
public class ShowcaseManager {

    private final EntityManager em;

    ShowcaseManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Insereix una showcase a la base de dades
     *
     * @param showcase la showcase
     * @throws ManagerException si ja existeix
     */
    void insert(Showcase showcase) throws ManagerException {
        Showcase existing = em.find(Showcase.class, showcase.getId());
        if (existing != null) {
            throw new ManagerException("La showcase ja existeix amb id " + showcase.getId());
        }

        try {
            em.getTransaction().begin();
            em.persist(showcase);
            em.getTransaction().commit();

            throw new ManagerException("Inserció de prova fallida per a testing");

        } catch (ManagerException me) {
            throw me;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ManagerException("Error inesperado al insertar: " + e.getMessage());
        }
    }





    /**
     * Obté totes les showcases de la BD
     *
     * @return la llista de showcases
     */
    List<Showcase> getAll() {
        //TODO
         return em.createQuery(
            "SELECT DISTINCT s FROM Showcase s LEFT JOIN FETCH s.tracks", Showcase.class)
            .getResultList();
    }

    /**
     * Obté totes les showcases segons si estan publicades o no
     *
     * @param published cert si es volen obtenir les publicades, fals si es
     * volem obtenir les no publicades
     * @return la llista de showcases
     */
    List<Showcase> getByPublished(boolean published) {
        //Todo
        return em.createQuery("SELECT s FROM Showcase s WHERE s.published = :published", Showcase.class)
            .setParameter("published", published)
            .getResultList();
    }

    /**
     * Elimina una showcase de la BD
     *
     * @param id l'id de la showcase que es vol esborrar
     * @throws ManagerException si la showcase no existeix
     */
    void delete(int id) throws ManagerException {
        //TODO
        Showcase showcase = em.find(Showcase.class, id);

        if (showcase == null) {
            throw new ManagerException("No existeix cap showcase amb id " + id);
        }

        try {
            em.getTransaction().begin();

            for (Track t : showcase.getTracks()) {
                t.getShowcases().remove(showcase);
            }
            showcase.getTracks().clear();

            em.remove(showcase);

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ManagerException("Error eliminant la showcase: " + e.getMessage());
        }
}


    /**
     * Treu un track d'una showcase
     *
     * @param idShowcase l'id de la showcase
     * @param idTrack l'id del track
     * @throws ManagerException si el track o la showcase no existeixen, o el
     * track no és a la showcase
     */
    void removeTrack(int idShowcase, int idTrack) throws ManagerException {
        //TODO
        
        Showcase showcase = em.find(Showcase.class, idShowcase);
        Track track = em.find(Track.class, idTrack);
        
        if (showcase == null){
                throw new ManagerException("No existeix cap showcase amb id " + idShowcase);
        }
        if (track ==null){
                throw new ManagerException("No existeix cap track amb id " + idShowcase);
        }
        if (!showcase.getTracks().contains(track)) {
            
        throw new ManagerException("El track no forma part de la showcase");
        
        }
        
        showcase.getTracks().remove(track);
        track.getShowcases().remove(showcase);
       
    }

    /**
     * Afegeix un track a una showcase
     *
     * @param idShowcase el codi de la showcase
     * @param idTrack el codi de la showcase
     * @throws ManagerException si el track o la showcase no existeixen, o el
     * track ja és a la showcase
     */
    void addTrack(int idShowcase, int idTrack) throws ManagerException {
        //TODO
        Showcase showcase = em.find(Showcase.class, idShowcase);
        Track track = em.find(Track.class, idTrack);

        if (showcase == null) {
            throw new ManagerException("No existeix cap showcase amb id " + idShowcase);
        }
        if (track == null) {
            throw new ManagerException("No existeix cap track amb id " + idTrack);
        }

        if (showcase.getTracks().contains(track)) {
            throw new ManagerException("El track ja forma part de la showcase");
        }

        em.getTransaction().begin();
        showcase.getTracks().add(track);
        track.getShowcases().add(showcase);
        em.getTransaction().commit();
        }

}
