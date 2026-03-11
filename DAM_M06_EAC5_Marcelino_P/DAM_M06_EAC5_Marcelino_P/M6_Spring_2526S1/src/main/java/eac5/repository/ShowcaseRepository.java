/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eac5.repository;

import eac5.model.Showcase;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowcaseRepository extends JpaRepository<Showcase, Integer> {

    @Query(value = "SELECT s FROM Showcase s WHERE s.published = :published")
    public List<Showcase> findByPublished(@Param("published") boolean published);

}

