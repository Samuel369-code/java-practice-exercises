/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

/**
 *
 * @author joan
 */
public class Song {

    private String title;
    private String author;
    private int popularity;

    private final String propertyName = "Popularity";

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final VetoableChangeSupport vcs = new VetoableChangeSupport(this);

    public Song(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Getters i setters de title i author
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    /**
     * Actualitza la popularitat d'una cançó i notifica els observadors.
     * Primer notifica els observadors VetoableChangeListener, i si no vetoquen,
     * aplica el nou valor i notifica als PropertyChangeListener.
     *
     * @param popularity el nou valor de popularitat
     * @throws PropertyVetoException si el valor no és vàlid segons els observadors
     */
    public int getPopularity() { return popularity; }

    public void setPopularity(int popularity) throws PropertyVetoException {
        int oldValue = this.popularity;

        // Notificar als observadors veto
        vcs.fireVetoableChange(propertyName, oldValue, popularity);

        // Actualitzar el valor
        this.popularity = popularity;

        // Notificar als observadors de canvi
        pcs.firePropertyChange(propertyName, oldValue, popularity);
    }

    // Mètodes per afegir/eliminar observadors
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vcs.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vcs.removeVetoableChangeListener(listener);
    }
}