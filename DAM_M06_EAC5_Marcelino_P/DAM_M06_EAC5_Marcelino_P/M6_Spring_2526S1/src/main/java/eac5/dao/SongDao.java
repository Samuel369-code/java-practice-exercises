/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.dao;

import eac5.model.Song;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import eac5.repository.SongRepository;

@Component
public class SongDao {

    @Autowired
    private SongRepository songRepository;

    public List<Song> getAll() {
        return songRepository.findAll();
    }

    public List<Song> getMinDuration(int duration) {
        return songRepository.findMinDuration(duration);
    }

}
