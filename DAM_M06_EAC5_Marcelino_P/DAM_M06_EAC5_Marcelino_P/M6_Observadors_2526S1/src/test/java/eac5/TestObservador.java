package eac5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaris per a la classe Song i els observadors:
 * VerifyPopularity i RecordPopularity.
 *
 * Author: professor
 */
public class TestObservador {

    private Song song;
    private VerifyPopularity verifier;
    private RecordPopularity recorder;

    @BeforeEach
    void setUp() {
        song = new Song("Smells Like Teen Spirit", "Nirvana");
        verifier = new VerifyPopularity();
        recorder = new RecordPopularity();

        song.addVetoableChangeListener(verifier);
        song.addPropertyChangeListener(recorder);
    }

    @Test
    void testValidPopularityUpdatesAreRecorded() throws PropertyVetoException {
        song.setPopularity(30);
        song.setPopularity(60);
        song.setPopularity(90);

        ArrayList<Integer> history = recorder.getPopularityHistory(song);

        assertEquals(3, history.size());
        assertEquals(30, history.get(0));
        assertEquals(60, history.get(1));
        assertEquals(90, history.get(2));
    }

    @Test
    void testInvalidPopularityThrowsException() {
        assertThrows(PropertyVetoException.class, () -> song.setPopularity(150));
        assertThrows(PropertyVetoException.class, () -> song.setPopularity(-10));
    }

    @Test
    void testInvalidUpdateDoesNotChangeValue() throws PropertyVetoException {
        song.setPopularity(50);

        assertThrows(PropertyVetoException.class, () -> song.setPopularity(101));
        assertEquals(50, song.getPopularity(), "La poularitat no es modifica si hi ha veto");
    }

    @Test
    void testMultipleSongsHaveIndependentHistories() throws PropertyVetoException {
        Song song2 = new Song("Elefants", "Oques grasses");
        song2.addVetoableChangeListener(verifier);
        song2.addPropertyChangeListener(recorder);

        song.setPopularity(40);
        song2.setPopularity(70);

        ArrayList<Integer> h1 = recorder.getPopularityHistory(song);
        ArrayList<Integer> h2 = recorder.getPopularityHistory(song2);

        assertEquals(1, h1.size());
        assertEquals(1, h2.size());
        assertEquals(40, h1.get(0));
        assertEquals(70, h2.get(0));
    }

}
