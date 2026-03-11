/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.dao;

import eac5.gestors.ManagerException;
import eac5.model.Showcase;
import eac5.model.Track;
import java.util.List;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import eac5.repository.ShowcaseRepository;
import eac5.repository.TrackRepository;

@Repository
@RequiredArgsConstructor
public class TrackDao {

    private final TrackRepository trackRepository;
    private final ShowcaseRepository showcaseRepository;

    public void inserir(Track track) throws ManagerException {
        if (!trackRepository.existsById(track.getId())) {
            trackRepository.save(track);
        } else {
            throw new ManagerException("El producte ja existeix");
        }
    }

    public void delete(int id) throws ManagerException {
        Track track = trackRepository.findById(id).orElse(null);
        if (track != null) {
            for (Showcase showcase : List.copyOf(track.getShowcases())) {
                showcase.getTracks().remove(track);
                showcaseRepository.save(showcase);
            }
            trackRepository.delete(track);
        } else {
            throw new ManagerException("La pista no existeix");
        }
    }

    public List<Track> getAll() {
        return trackRepository.findAll();
    }

    public void deleteAll() {
        trackRepository.deleteAll();
    }

    public List<Track> getTracksByExplicit(boolean explicit) {
        return trackRepository.findByExplicit(explicit);
    }

    public void addDownloads(int trackID, int downloads) {
        trackRepository.addDownloads(trackID, downloads);
    }

    public void setPopularity(int minLikes, int maxLikes, double popularity) {
        trackRepository.setPopularity(minLikes, maxLikes, popularity);
    }

}
