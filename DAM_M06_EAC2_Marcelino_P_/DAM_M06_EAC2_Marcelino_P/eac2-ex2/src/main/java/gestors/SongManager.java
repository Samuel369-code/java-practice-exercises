/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestors;

import model.Song;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author joan
 */
public class SongManager {

    EntityManager em;

    public SongManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Afegiex una cançó a la BD
     *
     * @param song la cançó a afegir a la BD
     * @throws ManagerException en cas que no es pugui afegir
     */
    public void addSong(Song song) throws ManagerException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Comprovar si la cançó ja existeix
            Song existing = em.find(Song.class, song.getIsrc());
            if (existing != null) {
                throw new ManagerException("La cançó ja existeix amb isrc=" + song.getIsrc());
            }
            em.persist(song);
            tx.commit();
        } catch (ManagerException e) {
            if (tx.isActive()) tx.rollback();
            throw new ManagerException("Error afegint la cançó amb isrc=" + song.getIsrc(), e);
        }
    }

    /**
     *
     * @param isrc el isrc d'una cançó
     * @return la cançó
     * @throws ManagerException en cas que no es pugui obtenir
     */
    public Song getSong(String isrc) throws ManagerException {
        try {
            Song song = em.find(Song.class, isrc);
            if (song == null) {
                throw new ManagerException("No existeix cap cançó amb isrc=" + isrc);
            }
            return song;
        } catch (ManagerException e) {
            throw new ManagerException("Error obtenint la cançó amb isrc=" + isrc, e);
        }
    }

    /**
     * Modifica la informació d'una cançó
     *
     * @param song la cançó a modificar
     * @throws ManagerException en cas que no es pugui modificar
     */
    public void updateSong(Song song) throws ManagerException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Song existing = em.find(Song.class, song.getIsrc());
            if (existing == null) {
                throw new ManagerException("No existeix cap cançó amb isrc=" + song.getIsrc());
            }
            em.merge(song);
            tx.commit();
        } catch (ManagerException e) {
            if (tx.isActive()) tx.rollback();
            throw new ManagerException("Error modificant la cançó amb isrc=" + song.getIsrc(), e);
        }
    }

    /**
     * Esborra una cançó
     *
     * @param isrc l'isrc de la cançó
     * @throws ManagerException en cas que no es pugui esborrar
     */
    public void deleteSong(String isrc) throws ManagerException {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Song existing = em.find(Song.class, isrc);
            if (existing == null) {
                throw new ManagerException("No existeix cap cançó amb isrc=" + isrc);
            }
            em.remove(existing);
            tx.commit();
        } catch (ManagerException e) {
            if (tx.isActive()) tx.rollback();
            throw new ManagerException("Error eliminant la cançó amb isrc=" + isrc, e);
        }
    }

}
