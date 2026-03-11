/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Audiobook;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author joan
 */
public class AudiobookManager {

    private final EntityManager em;

    public AudiobookManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Consulta els audiollibres que tenen un determinat autor
     *
     * @param author l'autor
     * @return la llista d'audiollibres
     */
    List<Audiobook> getByAuthor(String author) {
    return em.createQuery("SELECT a FROM Audiobook a WHERE a.author = :author", Audiobook.class)
             .setParameter("author", author)
             .getResultList();
    }

}
