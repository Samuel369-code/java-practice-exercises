/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eac5.repository;

import eac5.model.Track;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TrackRepository extends JpaRepository<Track, Integer> {

    List<Track> findByExplicit(@Param("explicit") boolean explicit);

    @Modifying
    @Transactional
    @Query("UPDATE Track t SET t.download = t.download + :download WHERE t.id = :trackId")
    void addDownloads(@Param("trackId") int trackID, @Param("download") int download);

    @Modifying
    @Transactional
    @Query("UPDATE Track t SET t.popularity = :popularity WHERE t.likes >= :minLikes AND t.likes <= :maxLikes")
    void setPopularity(@Param("minLikes") int minLikes, @Param("maxLikes") int maxLikes, @Param("popularity") double popularity);

}
