/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.gestors;

import eac5.dao.AudiobookDao;
import eac5.model.Audiobook;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AudiobookManager {

    private final AudiobookDao audiobookDao;

    public List<Audiobook> getByAuthor(String author) {
        return audiobookDao.getByAuthor(author);
    }
}
