/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package eac3.gestors;

import eac3.model.Song;
import eac3.model.Showcase;
import eac3.model.Podcast;
import eac3.model.Audiobook;
import eac3.model.Track;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author joan
 */
@TestMethodOrder(OrderAnnotation.class)
public class TestGestors {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa2526s1");

    private static List<Showcase> showcases;
    private static List<Track> tracks;

    public TestGestors() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    private static void initBD() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.createQuery("DELETE FROM Showcase").executeUpdate();

        em.createQuery("DELETE FROM Track").executeUpdate();

        em.getTransaction().commit();

        em.close();

    }

    private static void createShowcases() {

        // Instanciem els showcases
        showcases = new ArrayList<>();

        showcases.add(new Showcase(101, "Top Hits", "Most popular tracks", "user1", LocalDate.now(), true));
        showcases.add(new Showcase(102, "Chill & Relax", "Relaxing songs and podcasts", "user2", LocalDate.now(), true));
        showcases.add(new Showcase(103, "Workout Pump", "Energetic music for gym", "user3", LocalDate.now(), true));
        showcases.add(new Showcase(104, "Study Time", "Focus and concentrate", "user4", LocalDate.now(), true));
        showcases.add(new Showcase(105, "Story Time", "Audiobooks and podcasts", "user5", LocalDate.now(), true));
        showcases.add(new Showcase(106, "Party Mix", "Party songs and hits", "user6", LocalDate.now(), true));
        showcases.add(new Showcase(107, "Classic Collection", "Famous songs and audiobooks", "user7", LocalDate.now(), true));
        showcases.add(new Showcase(108, "Mixed Bag", "Eclectic showcase", "user8", LocalDate.now(), true));

    }

    private static void createTracks() {
        // Instanciem tracks
        tracks = new ArrayList<>();

        // Songs (15)
        tracks.add(new Song(1, "Anti-Hero", 3, 21, false, 500000, 4.8, 200000, 300000, 95.0,
                Arrays.asList("pop"), "USRC17607839", "Taylor Swift", "Taylor Swift", "Jack Antonoff"));
        tracks.add(new Song(2, "Shivers", 3, 27, false, 400000, 4.7, 150000, 250000, 90.0,
                Arrays.asList("pop"), "USRC17607840", "Ed Sheeran", "Ed Sheeran", "Fred Again.."
        ));
        tracks.add(new Song(3, "Easy On Me", 4, 15, false, 300000, 4.9, 100000, 200000, 92.0,
                Arrays.asList("pop", "ballad"), "USRC17607841", "Adele", "Adele", "Greg Kurstin"
        ));
        tracks.add(new Song(4, "Happier Than Ever", 4, 58, true, 250000, 4.6, 90000, 150000, 85.0,
                Arrays.asList("pop", "alternative"), "USRC17607842", "Billie Eilish", "Billie Eilish", "Finneas"
        ));
        tracks.add(new Song(5, "Levitating", 3, 23, false, 350000, 4.7, 120000, 180000, 88.0,
                Arrays.asList("pop", "dance"), "USRC17607843", "Dua Lipa", "Dua Lipa", "Ian Kirkpatrick"
        ));
        tracks.add(new Song(6, "Leave The Door Open", 4, 2, false, 280000, 4.8, 95000, 140000, 87.0,
                Arrays.asList("r&b", "soul"), "USRC17607844", "Bruno Mars", "Bruno Mars", "Anderson .Paak"
        ));
        tracks.add(new Song(7, "Enemy", 3, 55, false, 300000, 4.5, 100000, 160000, 82.0,
                Arrays.asList("rock", "pop"), "USRC17607845", "Imagine Dragons", "Dan Reynolds", "Dan Reynolds"
        ));
        tracks.add(new Song(8, "My Universe", 3, 45, false, 270000, 4.6, 90000, 150000, 80.0,
                Arrays.asList("pop", "rock"), "USRC17607846", "Coldplay", "Chris Martin", "Max Martin"
        ));
        tracks.add(new Song(9, "Save Your Tears", 3, 35, false, 400000, 4.7, 150000, 200000, 89.0,
                Arrays.asList("pop", "r&b"), "USRC17607847", "The Weeknd", "Abel Tesfaye", "Max Martin"
        ));
        tracks.add(new Song(10, "Positions", 2, 52, false, 350000, 4.6, 120000, 180000, 85.0,
                Arrays.asList("pop"), "USRC17607848", "Ariana Grande", "Ariana Grande", "Max Martin"
        ));
        tracks.add(new Song(11, "Señorita", 3, 11, false, 300000, 4.5, 100000, 150000, 83.0,
                Arrays.asList("pop", "latin"), "USRC17607849", "Shawn Mendes", "Shawn Mendes", "Scott Harris"
        ));
        tracks.add(new Song(12, "Watermelon Sugar", 2, 54, false, 320000, 4.6, 110000, 160000, 86.0,
                Arrays.asList("pop", "rock"), "USRC17607850", "Harry Styles", "Harry Styles", "Kid Harpoon"
        ));
        tracks.add(new Song(13, "Good As Hell", 2, 39, false, 280000, 4.5, 90000, 140000, 82.0,
                Arrays.asList("pop", "r&b"), "USRC17607851", "Lizzo", "Lizzo", "Ricky Reed"
        ));
        tracks.add(new Song(14, "Say So", 3, 58, false, 300000, 4.6, 100000, 150000, 84.0,
                Arrays.asList("pop", "dance"), "USRC17607852", "Doja Cat", "Doja Cat", "Dr. Luke"
        ));
        tracks.add(new Song(15, "Circles", 3, 35, false, 350000, 4.7, 120000, 180000, 87.0,
                Arrays.asList("pop", "rap"), "USRC17607853", "Post Malone", "Post Malone", "Louis Bell"
        ));

        // Podcasts (10)
        tracks.add(new Podcast(16, "Elon Musk Interview", 120, 0, true, 200000, 4.9, 5000, 100000, 95.0,
                Arrays.asList("technology", "interview"), "Joe Rogan", "The Joe Rogan Experience", 215
        ));
        tracks.add(new Podcast(17, "The Alibi", 60, 0, false, 150000, 4.8, 3000, 70000, 90.0,
                Arrays.asList("true crime"), "Sarah Koenig", "Serial", 1
        ));
        tracks.add(new Podcast(18, "The Eiffel Tower", 30, 0, false, 120000, 4.7, 2000, 50000, 88.0,
                Arrays.asList("design", "architecture"), "Roman Mars", "99% Invisible", 410
        ));
        tracks.add(new Podcast(19, "Airbnb Story", 45, 0, false, 110000, 4.6, 2000, 48000, 85.0,
                Arrays.asList("business", "entrepreneur"), "Guy Raz", "How I Built This", 350
        ));
        tracks.add(new Podcast(20, "Episode 710", 60, 0, false, 130000, 4.7, 2500, 60000, 87.0,
                Arrays.asList("storytelling"), "Ira Glass", "This American Life", 710
        ));
        tracks.add(new Podcast(21, "Episode 120", 40, 0, false, 90000, 4.5, 1500, 40000, 80.0,
                Arrays.asList("society", "culture"), "Anna Sale", "Death, Sex & Money", 120
        ));
        tracks.add(new Podcast(22, "How Volcanoes Work", 35, 0, false, 95000, 4.6, 1800, 42000, 82.0,
                Arrays.asList("education", "science"), "Josh Clark", "Stuff You Should Know", 500
        ));
        tracks.add(new Podcast(23, "Episode 300", 70, 0, false, 85000, 4.4, 1600, 38000, 78.0,
                Arrays.asList("interview", "celebrity"), "Dax Shepard", "Armchair Expert", 300
        ));
        tracks.add(new Podcast(24, "Episode 200", 50, 0, false, 90000, 4.5, 1700, 40000, 80.0,
                Arrays.asList("comedy", "interview"), "Conan O'Brien", "Conan O'Brien Needs a Friend", 200
        ));
        tracks.add(new Podcast(25, "Episode 420", 55, 0, false, 95000, 4.6, 1800, 45000, 82.0,
                Arrays.asList("sports", "discussion"), "Bill Simmons", "The Bill Simmons Podcast", 420
        ));

        // Audiobooks (5)
        tracks.add(new Audiobook(26, "1984", 600, 0, false, 50000, 4.9, 10000, 30000, 95.0,
                Arrays.asList("dystopia", "classic"), "George Orwell", "Simon Prebble", "Penguin", 20
        ));
        tracks.add(new Audiobook(27, "Harry Potter and the Sorcerer's Stone", 480, 0, false, 80000, 4.8, 20000, 50000, 97.0,
                Arrays.asList("fantasy", "magic"), "J.K. Rowling", "Jim Dale", "Bloomsbury", 17
        ));
        tracks.add(new Audiobook(28, "The Hobbit", 600, 0, false, 70000, 4.9, 15000, 40000, 96.0,
                Arrays.asList("fantasy", "adventure"), "J.R.R. Tolkien", "Rob Inglis", "HarperCollins", 22
        ));
        tracks.add(new Audiobook(29, "Becoming", 720, 0, false, 60000, 4.8, 12000, 35000, 94.0,
                Arrays.asList("memoir", "inspiration"), "Michelle Obama", "Michelle Obama", "Crown", 25
        ));
        tracks.add(new Audiobook(30, "Sapiens", 540, 0, false, 55000, 4.9, 11000, 33000, 95.0,
                Arrays.asList("history", "science"), "Yuval Noah Harari", "Derek Perkins", "Harper", 18
        ));

    }

    private static void addTracksShowcase() {
        // Assignem aleatòriament 6 pistes (no repetides) a cada showcase 
        Random rand = new Random();

        for (Showcase pl : showcases) {
            Set<Track> selected = new LinkedHashSet();
            while (selected.size() < 6) {
                Track t = tracks.get(rand.nextInt(tracks.size()));
                selected.add(t);
                if (!t.getShowcases().contains(pl)) t.getShowcases().add(pl);
            }
            pl.setTracks(new ArrayList(selected));
        }
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws ManagerException {
        initBD();

        createShowcases();
        createTracks();
        addTracksShowcase();

        EntityManager em = emf.createEntityManager();
        ShowcaseManager showcaseManager = new ShowcaseManager(em);
        TrackManager trackManager = new TrackManager(em);

        for (Showcase pl : showcases)
            try {
            showcaseManager.insert(pl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Track tr : tracks)
            try {
            trackManager.insert(tr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void testAddShowcase() throws ManagerException {
        EntityManager em = emf.createEntityManager();
        ShowcaseManager showcaseManager = new ShowcaseManager(em);

        for (Showcase showcase : showcases) {
            assertThrows(ManagerException.class, () -> {
                showcaseManager.insert(showcase);
            });
        }

        em.close();
    }

    @Test
    @Order(2)
    public void testAddTrack() throws ManagerException {
        EntityManager em = emf.createEntityManager();
        TrackManager trackManager = new TrackManager(em);

        Song song = new Song(31, "drivers license", 4, 2, false, 380000, 4.9, 140000, 220000, 93.0,
                Arrays.asList("pop", "ballad"), "USRC17607854", "Olivia Rodrigo", "Olivia Rodrigo", "Dan Nigro"
        );

        Podcast podcast = new Podcast(32, "Our Unlikely Friendship", 60, 0, false, 100000, 4.8, 2500, 45000, 89.0,
                Arrays.asList("society", "conversation"), "Barack Obama & Bruce Springsteen", "Renegades: Born in the USA", 8
        );

        Audiobook audiobook = new Audiobook(33, "It", 1320, 0, false, 45000, 4.7, 9000, 28000, 91.0,
                Arrays.asList("horror", "fiction"), "Stephen King", "Steven Weber", "Scribner", 23
        );

        trackManager.insert(podcast);
        trackManager.insert(audiobook);
        trackManager.insert(song);

        assertThrows(ManagerException.class, () -> {
            trackManager.insert(podcast);
        });
        assertThrows(ManagerException.class, () -> {
            trackManager.insert(audiobook);
        });

        assertThrows(ManagerException.class, () -> {
            trackManager.insert(song);
        });

        em.close();
    }

    @Test
    @Order(3)
    public void testGetPlayists() {
        EntityManager em = emf.createEntityManager();
        ShowcaseManager showcaseManager = new ShowcaseManager(em);

        List<Showcase> result = showcaseManager.getAll();
        em.close();

        List<Showcase> expResult = showcases;
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @Order(4)
    public void testGetShowcasesByPublished(boolean published) {
        EntityManager em = emf.createEntityManager();
        ShowcaseManager showcaseManager = new ShowcaseManager(em);

        List<Showcase> result = showcaseManager.getByPublished(published);
        em.close();

        List<Showcase> expResult = showcases.stream().filter(c -> c.isPublished() == published).toList();
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 5})
    @Order(5)
    void testDeleteShowcase(int index) throws ManagerException {
        EntityManager em = emf.createEntityManager();
        ShowcaseManager showcaseManager = new ShowcaseManager(em);

        Showcase showcase = showcases.get(index);
        showcases.remove(showcase);
        for (Track p : showcase.getTracks()) {
            p.getShowcases().remove(showcase);
        }

        showcaseManager.delete(showcase.getId());

        List<Showcase> result = showcaseManager.getAll();
        List<Showcase> expResult = showcases;

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);
        assertThrows(ManagerException.class, () -> {
            showcaseManager.delete(showcase.getId());
        });
        em.close();
    }

    @ParameterizedTest
    @ValueSource(ints = {29, 20, 18, 10, 9, 5})
    @Order(6)
    void testDeleteTrack(int index) throws ManagerException {
        EntityManager em = emf.createEntityManager();
        TrackManager trackManager = new TrackManager(em);
        ShowcaseManager showcaseManager = new ShowcaseManager(em);

        Track track = tracks.get(index);
        tracks.remove(track);
        for (Showcase c : track.getShowcases()) {
            c.getTracks().remove(track);
        }

        trackManager.delete(track.getId());

        List<Showcase> result = showcaseManager.getAll();
        List<Showcase> expResult = showcases;

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);
        assertThrows(ManagerException.class, () -> {
            trackManager.delete(track.getId());
        });
        em.close();
    }

    @Test
    @Order(7)
    void testGetTracks() {
        EntityManager em = emf.createEntityManager();
        TrackManager trackManager = new TrackManager(em);

        List<Track> result = trackManager.getAll();
        List<Track> expResult = tracks;

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);

        em.close();
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @Order(8)
    void testGetTracksByExplicit(boolean explicit) throws ManagerException {
        EntityManager em = emf.createEntityManager();
        TrackManager trackManager = new TrackManager(em);

        List<Track> result = trackManager.getTracksByExplicit(explicit);
        List<Track> expResult = tracks.stream().filter(p -> p.isExplicit() == explicit).toList();

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);

        em.close();
    }

    @Test
    @Order(9)
    void testGetSongs() {
        EntityManager em = emf.createEntityManager();
        SongManager songManager = new SongManager(em);

        List<Song> expResult = tracks.stream()
                .filter(b -> b instanceof Song)
                .map(b -> (Song) b)
                .toList();
        List<Song> result = songManager.getAll();

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);
        em.close();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 100, 160, 200, 230, 260})
    @Order(10)
    void testGetSongsMinDuration(int seconds) {
        EntityManager em = emf.createEntityManager();
        SongManager songManager = new SongManager(em);

        List<Song> expResult = tracks.stream()
                .filter(b -> b instanceof Song)
                .map(b -> (Song) b)
                .filter(b -> b.getMinutes() * 60 + b.getSeconds() >= seconds)
                .toList();
        List<Song> result = songManager.getMinDuration(seconds);

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);
        em.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"The Joe Rogan Experience", "Stuff You Should Know", "The Bill Simmons Podcast", "---"})
    @Order(11)
    void testGetPodcastByShowTitle(String showTitle) {
        EntityManager em = emf.createEntityManager();
        PodcastManager podcastManager = new PodcastManager(em);

        List<Podcast> expResult = tracks.stream()
                .filter(p -> p instanceof Podcast)
                .map(p -> (Podcast) p)
                .filter(p -> p.getShowTitle().equals(showTitle))
                .toList();
        List<Podcast> result = podcastManager.getByShowTitle(showTitle);

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);
        em.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"George Orwell", "J.R.R. Tolkien", "Yuval Noah Harari", "---"})
    @Order(12)
    void testGetAudioBookByAuthor(String author) {
        EntityManager em = emf.createEntityManager();
        AudiobookManager audiobookManager = new AudiobookManager(em);

        List<Audiobook> expResult = tracks.stream()
                .filter(m -> m instanceof Audiobook)
                .map(p -> (Audiobook) p)
                .filter(p -> p.getAuthor().equals(author))
                .toList();
        List<Audiobook> result = audiobookManager.getByAuthor(author);

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);
        em.close();
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "1, 25",
        "3, 1",
        "5, -1",
        "-1, 5"
    })
    @Order(13)
    void testRemoveTrack(int indexShowcase, int indexTrack) throws ManagerException {
        EntityManager em = emf.createEntityManager();
        ShowcaseManager showcaseManager = new ShowcaseManager(em);

        Showcase[] showcase = new Showcase[1];
        try {
            showcase[0] = showcases.get(indexShowcase);
        } catch (IndexOutOfBoundsException e) {
            showcase[0] = new Showcase();
            showcase[0].setId(-1);
        }

        Track[] track = new Track[1];
        try {
            track[0] = tracks.get(indexTrack);
        } catch (IndexOutOfBoundsException e) {
            track[0] = new Song();
            track[0].setId(-1);
        }

        if (showcase[0].getTracks().remove(track[0]) && track[0].getShowcases().remove(showcase[0])) {
            showcaseManager.removeTrack(showcase[0].getId(), track[0].getId());

            List<Showcase> result = showcaseManager.getAll();
            List<Showcase> expResult = showcases;

            assertEquals(expResult.size(), result.size());
            assertEquals(expResult.containsAll(result), true);

        } else {
            assertThrows(ManagerException.class, () -> {
                showcaseManager.removeTrack(showcase[0].getId(), track[0].getId());

            });
        }

    }

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "1, 25",
        "3, 1",
        "5, -1",
        "-1, 5"
    })
    @Order(14)
    void testAddTrackShowcase(int indexShowcase, int indexTrack) throws ManagerException {
        EntityManager em = emf.createEntityManager();
        ShowcaseManager showcaseManager = new ShowcaseManager(em);

        Showcase[] showcase = new Showcase[1];
        try {
            showcase[0] = showcases.get(indexShowcase);
        } catch (IndexOutOfBoundsException e) {
            showcase[0] = new Showcase();
            showcase[0].setId(-1);
        }

        Track[] track = new Track[1];
        try {
            track[0] = tracks.get(indexTrack);
        } catch (IndexOutOfBoundsException e) {
            track[0] = new Song();
            track[0].setId(-1);
        }

        if (showcases.contains(showcase[0]) && tracks.contains(track[0])
                && !showcase[0].getTracks().contains(track[0]) && !track[0].getShowcases().contains(showcase[0])) {
            showcase[0].getTracks().add(track[0]);
            track[0].getShowcases().add(showcase[0]);

            showcaseManager.addTrack(showcase[0].getId(), track[0].getId());

            List<Showcase> result = showcaseManager.getAll();
            List<Showcase> expResult = showcases;

            assertEquals(expResult.size(), result.size());
            assertEquals(expResult.containsAll(result), true);

        } else {
            assertThrows(ManagerException.class, () -> {
                showcaseManager.addTrack(showcase[0].getId(), track[0].getId());

            });
        }

    }

    @ParameterizedTest
    @CsvSource(
            {
                "1, 2",
                "8, 3",
                "15, 4",
                "22, 5"
            })
    @Order(15)
    void testAddDownloads(int indexTrack, int downloads) {
        EntityManager em = emf.createEntityManager();
        TrackManager trackManager = new TrackManager(em);

        Track track = tracks.get(indexTrack);
        track.setDownload(track.getDownload() + downloads);
        trackManager.addDownloads(track.getId(), downloads);

        List<Track> result = trackManager.getAll();
        List<Track> expResult = tracks;

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);

        em.close();

    }

    @ParameterizedTest
    @CsvSource(
            {
                "200000, 300000, 90",
                "100000, 199999, 80",
                "50000, 99999, 70",
                "20000, 49999, 60"
            })
    @Order(16)
    void testSetPopularity(double popularity, int minLikes, int MaxLikes) throws ManagerException {
        EntityManager em = emf.createEntityManager();
        TrackManager trackManager = new TrackManager(em);

        trackManager.setPopularity(popularity, minLikes, MaxLikes);
        tracks.stream().filter(t -> t.getLikes() >= minLikes && t.getLikes() <= MaxLikes)
                .forEach(t -> t.setPopularity(popularity));

        List<Track> result = trackManager.getAll();
        List<Track> expResult = tracks;

        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.containsAll(result), true);

        em.close();
    }

}
