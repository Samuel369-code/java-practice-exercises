/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.gestors;

import ex2.model.Album;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Samuel
 */
public class GestorAlbum {

    /**
     * Retorna un objecte de classe Album a partir d'un fitxer XML
     *
     * @param nomFitxer el nom del fitxer
     * @return l'objecte de classe Album
     * @throws GestorAlbumException si no s'ha pogut llegir el fitxer
     */
    public Album llegirFitxerXML(String nomFitxer) throws GestorAlbumException {
        //TODO Implementar el mètode
        try {
            JAXBContext context = JAXBContext.newInstance(Album.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Album album = (Album) unmarshaller.unmarshal(new java.io.File(nomFitxer));
            return album;
        } catch (Exception e) {
            throw new GestorAlbumException("Error llegint fitxer XML: " + nomFitxer, e);
        }
    }

    /**
     * Crea un fitxer XML a partir d'un objecte de la classe Album
     *
     * @param nomFitxer nom del fitxer que es crearà
     * @param album objecte de la classe Album
     * @throws GestorAlbumException si no s'ha pogut gravar el fitxer
     */
    public void gravarFitxerXML(String nomFitxer, Album album) throws GestorAlbumException {
        //TODO Implementar el mètode
        try {
            JAXBContext context = JAXBContext.newInstance(Album.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(album, new java.io.File(nomFitxer));
        } catch (Exception e) {
            throw new GestorAlbumException("Error gravant fitxer XML: " + nomFitxer, e);
        }
    }

    /**
     * Retorna un objecte de classe Album a partir d'un fitxer JSON
     *
     * @param nomFitxer el nom del fitxer
     * @return l'objecte de classe Album
     * @throws GestorAlbumException si no s'ha pogut llegir el fitxer
     */
    public Album llegirFitxerJSON(String nomFitxer) throws GestorAlbumException {
        //TODO Implementar el mètode
        try (FileReader reader = new FileReader(nomFitxer)) {
            Gson gson = new Gson();
            Album album = gson.fromJson(reader, Album.class);
            return album;
        } catch (Exception e) {
            throw new GestorAlbumException("Error llegint fitxer JSON: " + nomFitxer, e);
        }
    }

    /**
     * Crea un fitxer JSON a partir d'un objecte de la classe Album
     *
     * @param nomFitxer nom del fitxer que es crearà
     * @param album objecte de la classe Album
     * @throws GestorAlbumException si no s'ha pogut escriure el fitxer
     */
    public void gravarFitxerJSON(String nomFitxer, Album album) throws GestorAlbumException {
        //TODO Implementar el mètode
        try (FileWriter writer = new FileWriter(nomFitxer)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(album, writer);
        } catch (Exception e) {
            throw new GestorAlbumException("Error gravant fitxer JSON: " + nomFitxer, e);
        }
    }
}
