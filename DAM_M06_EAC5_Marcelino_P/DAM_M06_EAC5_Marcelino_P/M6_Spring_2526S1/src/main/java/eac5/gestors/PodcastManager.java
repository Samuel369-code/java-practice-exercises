/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.gestors;

import eac5.dao.PodcastDao;
import eac5.model.Podcast;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PodcastManager {

    private final PodcastDao podcastDao;

    public List<Podcast> getByShowTitle(String showTitle) {
        return podcastDao.getByShowTitle(showTitle);
    }
}
