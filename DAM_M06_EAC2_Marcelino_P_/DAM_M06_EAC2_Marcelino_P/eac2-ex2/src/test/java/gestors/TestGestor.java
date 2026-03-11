/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gestors;

import java.util.ArrayList;
import java.util.Arrays;
import model.Song;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author joan
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestGestor {

    static private EntityManager em;
    static private SongManager gestor;
    static private List<Song> songs;

    public TestGestor() {
    }

    private static void createSongs() {
        songs = new ArrayList<>();
            songs.add(new Song("US-ABC-25-0001", "Ordinary", 3, 7, "Alex Warren", "Alex Warren", "Alex Warren", 50000000, 4.5, 2500000, 200000, 9.0, false, Arrays.asList("pop", "indie", "viral")));
        songs.add(new Song("US-ABC-25-0002", "Love Me Not", 3, 33, "Ravyn Lenae", "Ravyn Lenae", "Ravyn Lenae", 45000000, 4.7, 200000, 180000, 8.8, false, Arrays.asList("R&B", "soul", "TikTok")));
        songs.add(new Song("US-ABC-25-0003", "Golden", 3, 15, "HUNTR/X, EJAE", "Audrey Nuna", "KPop Demon Hunters Cast", 60000000, 4.6, 400000, 220000, 9.2, true, Arrays.asList("K-pop", "pop", "soundtrack")));
        songs.add(new Song("US-ABC-25-0004", "Just Keep Watching", 2, 23, "Tate McRae", "Tate McRae", "Tate McRae", 55000000, 4.4, 300000, 210000, 8.9, false, Arrays.asList("pop", "F1", "pel·lícula")));
        songs.add(new Song("US-ABC-25-0005", "Mystical Magical", 2, 46, "Benson Boone", "Benson Boone", "Benson Boone", 40000000, 4.3, 248000, 200000, 8.5, false, Arrays.asList("pop", "indie", "emocional")));
        songs.add(new Song("US-ABC-25-0006", "The Subway", 4, 12, "Chappell Roan", "Chappell Roan", "Chappell Roan", 35000000, 4.2, 210000, 190000, 8.0, true, Arrays.asList("pop", "alternatiu", "explícita")));
        songs.add(new Song("US-ABC-25-0007", "Sapphire", 2, 59, "Ed Sheeran", "Ed Sheeran", "Ed Sheeran", 70000000, 4.8, 34000, 250000, 9.5, false, Arrays.asList("pop", "romàntica", "ballable")));
        songs.add(new Song("US-ABC-25-0008", "Manchild", 3, 10, "Sabrina Carpenter", "Sabrina Carpenter", "Sabrina Carpenter", 65000000, 4.6, 235000, 230000, 9.3, false, Arrays.asList("pop", "indie", "emocional")));
        songs.add(new Song("US-ABC-25-0009", "Back to Friends", 3, 19, "sombr", "sombr", "sombr", 50000000, 4.4, 234000, 210000, 9.0, false, Arrays.asList("indie", "pop", "nostàlgic")));
        songs.add(new Song("US-ABC-25-0010", "Sorry I'm Here For Someone Else", 3, 0, "Benson Boone", "Benson Boone", "Benson Boone", 45000000, 4.5, 450000, 220000, 8.8, false, Arrays.asList("pop", "indie", "relacions")));
        songs.add(new Song("US-ABC-25-0011", "DAISIES", 2, 45, "Justin Bieber", "Justin Bieber", "Justin Bieber", 80000000, 4.7, 260000, 240000, 9.4, false, Arrays.asList("pop", "estiu", "optimista")));
        songs.add(new Song("US-ABC-25-0012", "What I Want", 3, 25, "Morgan Wallen, Tate McRae", "Morgan Wallen, Tate McRae", "Morgan Wallen, Tate McRae", 75000000, 4.6, 234000, 230000, 9.2, false, Arrays.asList("country", "pop", "col·laboració")));
        songs.add(new Song("US-ABC-25-0013", "Flash", 3, 10, "2Hollis", "2Hollis", "2Hollis", 30000000, 4.3, 210000, 200000, 8.5, false, Arrays.asList("pop", "electrònica", "ballable")));
        songs.add(new Song("US-ABC-25-0014", "Triple Lavada", 3, 0, "Esau Ortiz", "Esau Ortiz", "Esau Ortiz", 25000000, 4.2, 200000, 190000, 8.0, false, Arrays.asList("reggaeton", "llatí", "festiu")));
        songs.add(new Song("US-ABC-25-0015", "Bad State of Mind", 3, 30, "Treaty Oak Revival", "Treaty Oak Revival", "Treaty Oak Revival", 20000000, 4.1, 181900, 180000, 7.5, false, Arrays.asList("rock", "alternatiu", "introspectiu")));
        songs.add(new Song("US-ABC-25-0016", "Shot in the Dark", 3, 20, "Kingfishr", "Kingfishr", "Kingfishr", 15000000, 4.0, 170500, 170000, 7.0, false, Arrays.asList("folk", "acústic", "irlandès")));
        songs.add(new Song("US-ABC-25-0017", "Killeagh", 3, 10, "Kingfishr", "Kingfishr", "Kingfishr", 10000000, 4.2, 181000, 180000, 7.2, false, Arrays.asList("folk", "irlandès", "viral")));
        songs.add(new Song("US-ABC-25-0018", "Die With A Smile", 3, 40, "Lady Gaga, Bruno Mars", "Lady Gaga, Bruno Mars", "Lady Gaga, Bruno Mars", 90000000, 4.9, 270000, 260000, 9.8, true, Arrays.asList("pop", "R&B", "col·laboració")));
        songs.add(new Song("US-ABC-25-0019", "A Bar Song (Tipsy)", 3, 0, "Shaboozey", "Shaboozey", "Shaboozey", 85000000, 4.8, 260000, 250000, 9.6, false, Arrays.asList("pop", "festiu", "ballable")));
        songs.add(new Song("US-ABC-25-0020", "Undressed", 3, 15, "sombr", "sombr", "sombr", 80000000, 4.7, 241500, 240000, 9.5, true, Arrays.asList("indie", "pop", "explícita")));
        songs.add(new Song("US-ABC-25-0021", "Pocket Knife", 3, 30, "Walker Hayes", "Walker Hayes", "Walker Hayes", 70000000, 4.6, 240000, 230000, 9.2, false, Arrays.asList("country", "personal", "emotiu")));
        songs.add(new Song("US-ABC-25-0022", "With You", 3, 20, "Davido, Victoria Monét", "Davido, Victoria Monét", "Davido, Victoria Monét", 65000000, 4.5, 230000, 220000, 9.1, false, Arrays.asList("afrobeats", "R&B", "romàntica")));
        songs.add(new Song("US-ABC-25-0023", "Lo Que Le Pasó a Hawaii", 3, 10, "Bad Bunny", "Bad Bunny", "Bad Bunny", 60000000, 4.4, 220000, 210000, 9.0, true, Arrays.asList("reggaeton", "llatí", "nostàlgic")));
        songs.add(new Song("US-ABC-25-0024", "Si Antes Te Hubiera Conocido", 3, 0, "Karol G", "Karol G", "Karol G", 55000000, 4.3, 210000, 200000, 8.8, false, Arrays.asList("reggaeton", "romàntic", "ballable")));
        songs.add(new Song("US-ABC-25-0025", "Together", 3, 50, "David Guetta, Hypaton, Bonnie Tyler", "David Guetta, Hypaton, Bonnie Tyler", "David Guetta, Hypaton, Bonnie Tyler", 50000000, 4.2, 191000, 190000, 8.5, true, Arrays.asList("dance", "remix", "clàssic")));
        songs.add(new Song("US-ABC-25-0026", "Strategy", 3, 30, "TWICE", "TWICE", "TWICE", 45000000, 4.1, 185000, 180000, 8.0, false, Arrays.asList("K-pop", "pop", "energètica")));
        songs.add(new Song("US-ABC-25-0027", "Flash", 3, 10, "2Hollis", "2Hollis", "2Hollis", 40000000, 4.0, 170200, 170000, 7.5, false, Arrays.asList("pop", "electrònica", "ballable")));
        songs.add(new Song("US-ABC-25-0028", "Total Eclipse of the Heart", 4, 0, "David Guetta, Hypaton", "David Guetta, Hypaton", "David Guetta, Hypaton", 35000000, 4.1, 167000, 160000, 7.8, false, Arrays.asList("dance", "remix", "clàssic")));
    }

    @BeforeAll
    public static void setUpClass() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("eac2PU");
        em = emf.createEntityManager();
        gestor = new SongManager(em);
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Song").executeUpdate();
        em.getTransaction().commit();

        createSongs();
    }

    @AfterAll
    public static void tearDownClass() {
        em.close();
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void testAddSong() throws ManagerException {
        for (Song song : songs) {
            gestor.addSong(song);
        }
    }

    @Test
    @Order(2)
    public void testGetSong() throws ManagerException {
        for (Song song : songs) {
            assertEquals(song, gestor.getSong(song.getIsrc()));
        }

        assertThrows(ManagerException.class, () -> gestor.getSong("---"));
    }

    @Test
    @Order(3)
    public void testUpdateSong() throws ManagerException {
        Song song = new Song("US-ABC-25-0010", "Sorry I'm Here For Someone Else", 3, 0, "Benson Boone", "Benson Boone", "Benson Boone", 
                45000011, 4.8, 260000, 220015, 90.0, false, Arrays.asList("pop", "indie", "relacions"));
        gestor.updateSong(song);
        assertEquals(song, gestor.getSong("US-ABC-25-0010"));
        song.setIsrc("---");
        assertThrows(ManagerException.class, () -> gestor.updateSong(song));
    }

    @Test
    @Order(4)
    public void testDeleteSong() throws ManagerException {
        for (Song song : songs) {
            gestor.deleteSong(song.getIsrc());
            assertThrows(ManagerException.class, () -> gestor.deleteSong(song.getIsrc()));
        }
    }

}
