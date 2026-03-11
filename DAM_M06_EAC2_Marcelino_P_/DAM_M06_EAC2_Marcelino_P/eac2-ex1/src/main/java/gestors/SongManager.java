/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestors;

import java.sql.Array;
import model.Song;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joan
 */
public class SongManager {

    private final Connection con;

    public SongManager(Connection con) {
        this.con = con;
    }

    /**
     * Esborra tot el contingut de la taula song
     * @throws gestors.ManagerException
     */
    public void deleteAll() throws ManagerException {
        String sql = "DELETE FROM song";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ManagerException("Error eliminant totes les cançons", e);
        }
    }

    /**
     * Afegeix una cançó
     * @param song
     * @throws gestors.ManagerException
     */
    public void addSong(Song song) throws ManagerException {
        String sql = """
            INSERT INTO song (isrc, title, duration, credits, stats, popularity, explicit, tags)
            VALUES (?, ?, ROW(?, ?)::duration_type, ROW(?, ?, ?)::credits_type, 
                    ROW(?, ?, ?, ?)::stats_type, ?, ?, ?)
            """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, song.getIsrc());
            ps.setString(2, song.getTitle());
            ps.setInt(3, song.getMinutes());
            ps.setInt(4, song.getSeconds());
            ps.setString(5, song.getComposer());
            ps.setString(6, song.getLyricist());
            ps.setString(7, song.getProducer());
            ps.setInt(8, song.getPlays());
            ps.setDouble(9, song.getRating());
            ps.setInt(10, song.getDownloads());
            ps.setInt(11, song.getLikes());
            ps.setDouble(12, song.getPopularity());
            ps.setBoolean(13, song.isExplicit());

            Array tagsArray = con.createArrayOf("varchar", song.getTags().toArray());
            ps.setArray(14, tagsArray);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ManagerException("Error afegint la cançó amb isrc=" + song.getIsrc(), e);
        }
    }

    /**
     * Obté una cançó pel seu isrc
     * @param isrc
     * @return 
     * @throws gestors.ManagerException
     */
    public Song getSong(String isrc) throws ManagerException {
        String sql = "SELECT * FROM song WHERE isrc = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, isrc);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapSong(rs);
                } else {
                    // lanzar excepción en vez de devolver null
                    throw new ManagerException("La cançó amb isrc=" + isrc + " no existeix");
                }
            }
        } catch (SQLException e) {
            throw new ManagerException("Error obtenint la cançó amb isrc=" + isrc, e);
        }
    }


    /**
     * Esborra una cançó pel seu isrc
     * @param isrc
     * @throws gestors.ManagerException
     */
    public void deleteSong(String isrc) throws ManagerException {
        String sql = "DELETE FROM song WHERE isrc = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, isrc);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new ManagerException("No s'ha pogut eliminar la cançó amb isrc=" + isrc + " perquè no existeix");
            }
        } catch (SQLException e) {
            throw new ManagerException("Error eliminant la cançó amb isrc=" + isrc, e);
        }
    }

    /**
     * Obté totes les cançons que tenen una etiqueta concreta
     * @param tag
     * @return 
     * @throws gestors.ManagerException 
     */
    public List<Song> getSongsByTag(String tag) throws ManagerException {
        String sql = "SELECT * FROM song WHERE ? = ANY(tags)";
        List<Song> songs = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tag);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    songs.add(mapSong(rs));
                }
            }
        } catch (SQLException e) {
            throw new ManagerException("Error obtenint cançons amb tag=" + tag, e);
        }
        return songs;
    }

        /**
         * Converteix un ResultSet en un objecte Song
         */
        private Song mapSong(ResultSet rs) throws SQLException {
        String isrc = rs.getString("isrc");
        String title = rs.getString("title");

        // duration_type (minutes, seconds)
        String[] durParts = rs.getString("duration")
                              .replace("(", "")
                              .replace(")", "")
                              .split(",");
        int minutes = Integer.parseInt(durParts[0].trim());
        int seconds = Integer.parseInt(durParts[1].trim());

        // credits_type (composer, lyricist, producer)
        String credits = rs.getString("credits")
                           .replace("(", "")
                           .replace(")", "");
        // dividir de forma más robusta (por comas no entre comillas)
        String[] credParts = credits.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String composer = credParts[0].replace("\"", "").trim();
        String lyricist = credParts[1].replace("\"", "").trim();
        String producer = credParts[2].replace("\"", "").trim();

        // stats_type (plays, rating, downloads, likes)
        String stats = rs.getString("stats")
                         .replace("(", "")
                         .replace(")", "");
        String[] statsParts = stats.split(",");
        int plays = Integer.parseInt(statsParts[0].trim());
        double rating = Double.parseDouble(statsParts[1].trim());
        int downloads = Integer.parseInt(statsParts[2].trim());
        int likes = Integer.parseInt(statsParts[3].trim());

        double popularity = rs.getDouble("popularity");
        boolean explicit = rs.getBoolean("explicit");

        Array tagsArray = rs.getArray("tags");
        List<String> tags = new ArrayList<>();
        if (tagsArray != null) {
            String[] tagValues = (String[]) tagsArray.getArray();
            for (String t : tagValues) {
                tags.add(t.trim());
            }
        }

        return new Song(isrc, title, minutes, seconds, composer, lyricist, producer,
                plays, rating, downloads, likes, popularity, explicit, tags);
    }

}