/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac5;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * Observador que verifica que la popularitat assignada a una cançó és correcta.
 * Llença una excepció PropertyVetoException si el valor està fora de l'interval 0-100.
 */
public class VerifyPopularity implements VetoableChangeListener {

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if ("Popularity".equals(evt.getPropertyName())) {
            Object newValue = evt.getNewValue();
            if (newValue instanceof Integer) {
                int pop = (Integer) newValue;
                if (pop < 0 || pop > 100) {
                    throw new PropertyVetoException(
                        "Popularitat invàlida: ha d'estar entre 0 i 100", evt);
                }
            }
        }
    }
}
