/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package eac4.gestors;

import eac4.model.Song;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import net.xqj.basex.BaseXXQDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author joan
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestGestors {

    private static XQDataSource xQDataSource;
    private static List<Song> songs;

    private static void creaLlista() {
        songs = new ArrayList<>();
        int i = 0;

        songs.add(new Song("USUM71703861", "Shape of You", 233, 9.8, 1500000, false));
        songs.get(i++).setTags(Arrays.asList("pop", "dance", "romantic"));

        songs.add(new Song("GBUM71604612", "Castle on the Hill", 261, 9.2, 1100000, false));
        songs.get(i++).setTags(Arrays.asList("pop", "nostalgic", "energetic"));

        songs.add(new Song("USAT21701234", "Believer", 204, 9.6, 1800000, false));
        songs.get(i++).setTags(Arrays.asList("rock", "motivational", "anthem"));

        songs.add(new Song("USRC11800567", "Shallow", 215, 9.7, 2200000, false));
        songs.get(i++).setTags(Arrays.asList("pop", "duet", "emotional"));

        songs.add(new Song("USUM71901234", "Bad Guy", 194, 9.3, 1300000, true));
        songs.get(i++).setTags(Arrays.asList("alternative", "electropop", "dark"));

        songs.add(new Song("USSM12012345", "Blinding Lights", 200, 9.9, 2500000, false));
        songs.get(i++).setTags(Arrays.asList("synthwave", "pop", "80s"));

        songs.add(new Song("GBUM71603456", "Perfect", 263, 9.5, 2100000, false));
        songs.get(i++).setTags(Arrays.asList("romantic", "ballad", "acoustic"));

        songs.add(new Song("USRC11904567", "Memories", 189, 8.9, 1000000, false));
        songs.get(i++).setTags(Arrays.asList("pop", "melancholic"));

        songs.add(new Song("USRC11907890", "Someone You Loved", 182, 9.4, 1900000, false));
        songs.get(i++).setTags(Arrays.asList("pop", "sad", "piano"));

        songs.add(new Song("USUM72004567", "Watermelon Sugar", 174, 9.0, 1600000, false));
        songs.get(i++).setTags(Arrays.asList("pop", "summer", "feelgood"));

        songs.add(new Song("USUM72102345", "Levitating", 203, 9.1, 1700000, false));
        songs.get(i++).setTags(Arrays.asList("dance", "pop", "disco"));

        songs.add(new Song("USAT22103456", "Thunder", 187, 8.7, 1400000, false));
        songs.get(i++).setTags(Arrays.asList("rock", "upbeat", "anthem"));

        songs.add(new Song("USRC12007891", "Circles", 215, 9.0, 1500000, false));
        songs.get(i++).setTags(Arrays.asList("pop", "chill", "emotional"));

        songs.add(new Song("USSM12106789", "Save Your Tears", 215, 9.4, 1800000, false));
        songs.get(i++).setTags(Arrays.asList("pop", "retro", "melancholic"));

        songs.add(new Song("USUM72205678", "As It Was", 167, 9.8, 2400000, false));
        songs.get(i++).setTags(Arrays.asList("pop", "synthpop", "reflective"));

        songs.add(new Song("USRC12104567", "Heat Waves", 238, 9.2, 2000000, false));
        songs.get(i++).setTags(Arrays.asList("indie", "pop", "nostalgic"));

        songs.add(new Song("USRC12109876", "Peaches", 198, 8.8, 1300000, false));
        songs.get(i++).setTags(Arrays.asList("rnb", "pop", "feelgood"));

        songs.add(new Song("USUM72301234", "Stay", 141, 9.1, 1900000, true));
        songs.get(i++).setTags(Arrays.asList("pop", "energetic", "youth"));

        songs.add(new Song("USRC12203456", "Enemy", 175, 9.3, 1800000, false));
        songs.get(i++).setTags(Arrays.asList("rock", "series", "intense"));

        songs.add(new Song("USUM72405678", "Flowers", 201, 9.6, 2500000, false));
        songs.get(i++).setTags(Arrays.asList("pop", "empowerment", "breakup"));

    }

    public TestGestors() {
    }

    @BeforeAll
    public static void setUpClass() throws XQException, ManagerException {
        xQDataSource = new BaseXXQDataSource();
        xQDataSource.setProperty("serverName", "localhost");
        xQDataSource.setProperty("port", "1984");

        XQConnection conn = xQDataSource.getConnection("admin", "admin");
        SongManager songManager = new SongManager(conn);
        songManager.deleteAll();
        conn.close();

        creaLlista();
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void testInsert() throws Exception {
        System.out.println("inserir");

        XQConnection conn = xQDataSource.getConnection("admin", "admin");
        SongManager instance = new SongManager(conn);

        for (Song song : songs) {
            instance.insert(song);
        }

        for (Song song : songs) {
            assertThrows(ManagerException.class, () -> {
                instance.insert(song);
            });
        }

        conn.close();

    }

    @Test
    @Order(2)
    public void testGetSong() throws Exception {
        System.out.println("getSong");
        XQConnection conn = xQDataSource.getConnection("admin", "admin");
        SongManager instance = new SongManager(conn);

        for (Song song : songs) {
            Song song2 = instance.getSong(song.getIsrc());
            assertEquals(song, instance.getSong(song.getIsrc()));
        }

        conn.close();
    }


     @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @Order(4)
    public void testGetSongsMoreTagsThan(int numTags) throws Exception {
        System.out.println("testGetSongsMoreTagsThan");

        XQConnection conn = xQDataSource.getConnection("admin", "admin");
        SongManager instance = new SongManager(conn);

        List<Song> expResult = songs.stream()
                .filter((song) -> {
                    return song.getTags().size() > numTags;
                })
                .collect(Collectors.toList());

        List<Song> result = instance.getSongsMoreTagsThan(numTags);

        assertEquals(expResult, result);

        conn.close();

    }

    
    @Test
    @Order(5)
    public void testDelete() throws Exception {
        System.out.println("eliminar");

        XQConnection conn = xQDataSource.getConnection("admin", "admin");
        SongManager instance = new SongManager(conn);

        for (Song song : songs) {
            instance.delete(song.getIsrc());
            assertThrows(ManagerException.class,
                    () -> {
                        instance.delete(song.getIsrc());
                    });
        }

        conn.close();
    }

   
}
