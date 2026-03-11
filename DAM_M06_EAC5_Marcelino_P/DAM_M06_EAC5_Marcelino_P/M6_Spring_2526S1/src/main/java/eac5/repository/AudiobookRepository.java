/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eac5.repository;

import eac5.model.Audiobook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudiobookRepository extends JpaRepository<Audiobook, Integer> {

    List<Audiobook> findByAuthor(String author);
}
