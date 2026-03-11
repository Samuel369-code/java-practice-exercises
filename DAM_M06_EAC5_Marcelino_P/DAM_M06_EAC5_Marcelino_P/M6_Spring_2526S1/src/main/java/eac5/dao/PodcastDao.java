/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.dao;

import eac5.model.Podcast;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import eac5.repository.PodcastRepository;

@Repository
@RequiredArgsConstructor
public class PodcastDao {

    private final PodcastRepository podcastRepository;

    public List<Podcast> getByShowTitle(String showTitle) {
        return podcastRepository.findByShowTitle(showTitle);
    }
}
