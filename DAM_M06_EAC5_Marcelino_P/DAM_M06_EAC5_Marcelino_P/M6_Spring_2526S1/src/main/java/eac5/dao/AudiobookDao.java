/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5.dao;

import eac5.model.Audiobook;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import eac5.repository.AudiobookRepository;

@Repository
@RequiredArgsConstructor
public class AudiobookDao {

    private final AudiobookRepository audiobookRepository;

    public List<Audiobook> getByAuthor(String author) {
        return audiobookRepository.findByAuthor(author);
    }
}
