/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.dao;

import eac5.gestors.ManagerException;
import eac5.model.Showcase;
import eac5.model.Track;
import java.util.List;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import eac5.repository.ShowcaseRepository;
import eac5.repository.TrackRepository;

@Repository
@RequiredArgsConstructor
public class ShowcaseDao {

    private final ShowcaseRepository showcaseRepository;
    private final TrackRepository trackRepository;

    public void deleteAll() {
        showcaseRepository.deleteAll();
    }

    public void insert(Showcase showcase) throws ManagerException {
        if (!showcaseRepository.existsById(showcase.getId())) {
            showcaseRepository.save(showcase);
        } else {
            throw new ManagerException("La carta ja existeix");
        }
    }

    public void delete(int id) throws ManagerException {
        Showcase showcase = showcaseRepository.findById(id).orElse(null);
        if (showcase != null) {
            // Eliminar la relación bidireccional
            for (Track t : List.copyOf(showcase.getTracks())) {
                t.getShowcases().remove(showcase);
                trackRepository.save(t);
            }
            showcaseRepository.delete(showcase);
        } else {
            throw new ManagerException("La showcase no existeix");
        }
    }

    public List<Showcase> getByPublished(boolean published) {
        return showcaseRepository.findByPublished(published);
    }

    @Transactional
    public void addTrack(int idShowcase, int idTrack) throws ManagerException {
        Showcase showcase = showcaseRepository.findById(idShowcase).orElse(null);
        Track track = trackRepository.findById(idTrack).orElse(null);

        if (showcase == null) throw new ManagerException("La showcase amb id " + idShowcase + " no existeix");
        if (track == null) throw new ManagerException("La pista amb id " + idTrack + " no existeix");
        if (showcase.getTracks().contains(track))
            throw new ManagerException("La pista amb id " + idTrack + " ja és a la showcase " + idShowcase);

        // Añadimos a ambas listas
        showcase.getTracks().add(track);
        if (!track.getShowcases().contains(showcase)) {
            track.getShowcases().add(showcase);
        }

        // Guardamos para mantener la relación
        showcaseRepository.save(showcase);
        trackRepository.save(track);
    }

    public List<Showcase> getAll() {
        return showcaseRepository.findAll();
    }

    public void removeTrack(int idShowcase, int idTrack) throws ManagerException {
        Showcase showcase = showcaseRepository.findById(idShowcase).orElse(null);
        Track track = trackRepository.findById(idTrack).orElse(null);

        if (showcase == null) throw new ManagerException("La carta amb codi " + idShowcase + " no existeix");
        if (track == null) throw new ManagerException("El producte amb codi " + idTrack + " no existeix");
        if (!showcase.getTracks().remove(track))
            throw new ManagerException("El producte amb codi " + idTrack + " no és a la carta " + idShowcase);

        track.getShowcases().remove(showcase);

        showcaseRepository.save(showcase);
        trackRepository.save(track);
    }
}
