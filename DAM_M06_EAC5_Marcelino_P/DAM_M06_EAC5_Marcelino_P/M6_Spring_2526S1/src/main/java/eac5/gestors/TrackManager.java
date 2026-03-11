/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.gestors;

import eac5.dao.TrackDao;
import eac5.model.Track;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackManager {

    private final TrackDao trackDao;

    public void insert(Track track) throws ManagerException {
        trackDao.inserir(track);
    }

    public void delete(int id) throws ManagerException {
        trackDao.delete(id);
    }

    public void deleteAll() {
        trackDao.deleteAll();
    }

    public List<Track> getAll() {
        return trackDao.getAll();
    }

    public List<Track> getTracksByExplicit(boolean explicit) {
        return trackDao.getTracksByExplicit(explicit);
    }

    public void addDownloads(int trackID, int downloads) {
        trackDao.addDownloads(trackID, downloads);
    }

    public void setPopularity(int minLikes, int maxLikes, double popularity) throws ManagerException {
        trackDao.setPopularity(minLikes, maxLikes, popularity);
    }
}
