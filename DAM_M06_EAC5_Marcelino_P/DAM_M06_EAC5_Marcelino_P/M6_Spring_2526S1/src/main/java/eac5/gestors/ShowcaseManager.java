/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.gestors;

import eac5.dao.ShowcaseDao;
import eac5.dao.TrackDao;
import eac5.model.Showcase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowcaseManager {

    private final ShowcaseDao showcaseDao;
    private final TrackDao trackDao;

    public void deleteAll() {
        showcaseDao.deleteAll();
    }

    public void insert(Showcase showcase) throws ManagerException {
        showcaseDao.insert(showcase);
    }

    public void delete(int id) throws ManagerException {
        showcaseDao.delete(id);
    }

    public List<Showcase> getByPublished(boolean published) {
        return showcaseDao.getByPublished(published);
    }

    public void removeTrack(int idShowcase, int idTrack) throws ManagerException {
        showcaseDao.removeTrack(idShowcase, idTrack);
    }

    public void addTrack(int idShowcase, int idTrack) throws ManagerException {
        showcaseDao.addTrack(idShowcase, idTrack);
    }

    public List<Showcase> getAll() {
        return showcaseDao.getAll();
    }

}
