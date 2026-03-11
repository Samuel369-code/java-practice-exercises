package eac5;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Observador que enregistra les actualitzacions de popularitat de les cançons,
 * mantenint un històric per a cada cançó
 *
 * Un objecte d'aquesta classe es comparteix entre múltiples cançons per a
 * mantenir un registre centralitzat.
 *
 * @author professor
 */
public class RecordPopularity implements PropertyChangeListener {

    // Map que té per clau la cançó, i per valor l'històric de popularitat
    private final Map<Song, ArrayList<Integer>> popularityHistory = new LinkedHashMap<>();

    /**
     * Obté l'històric de popularitat d'una cançó
     *
     * @param song la cançó de la qual es demana l'històric
     * @return una llista amb els valors de popularitat enregistrats
     */
    public ArrayList<Integer> getPopularityHistory(Song song) {
        return popularityHistory.getOrDefault(song, new ArrayList<>());
    }

    /**
     * Obté el mapa d'històrics de popularitat
     *
     * @return map de tots els històrics de popularitat
     */
    public Map<Song, ArrayList<Integer>> getAllPopularityHistories() {
        return popularityHistory;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Comprovem que la font sigui una instància de Song
        Object source = evt.getSource();
        if (source instanceof Song) {
            Song song = (Song) source; // Cast tradicional
            int newPopularity = (int) evt.getNewValue();

            // Afegim la cançó al mapa si encara no hi és
            popularityHistory.putIfAbsent(song, new ArrayList<>());

            // Afegim el nou valor a l'històric
            popularityHistory.get(song).add(newPopularity);
        }
    }
}
