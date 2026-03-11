/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.gestors;

import eac5.dao.SongDao;
import eac5.model.Song;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongManager {

    private final SongDao songDao;

    public List<Song> getAll() {
        return songDao.getAll();
    }

    public List<Song> getMinDuration(int seconds) {
        return songDao.getMinDuration(seconds);
    }
}
