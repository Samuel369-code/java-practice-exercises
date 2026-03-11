/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac4.gestors;

import eac4.model.Song;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;

/**
 * Classe que gestiona la persistència dels objectes de la classe model.Song
 *
 * @author joan
 */
public class SongManager {

    //arrel del document 
    //public static final String ARREL = "doc(\"songs/songs.xml\")/collection(\"songs\")/";
    public static final String ARREL = "doc(\"songs/songs.xml\")/";

    private final XQConnection conn;

    public SongManager(XQConnection conn) {
        this.conn = conn;
    }

    /**
     * Elimina totes les cançons de la base de dades
     *
     * @throws ManagerException
     */
    public void deleteAll() throws ManagerException {
        try {
            String consulta = "delete node " + ARREL + "songs/song";
            XQExpression exp = conn.createExpression();
            exp.executeQuery(consulta);
        } catch (XQException ex) {
            Logger.getLogger(SongManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            throw new ManagerException("Error en esborrar les cançons", ex);
        }
    }

    /**
     * Dóna d'alta una cançó a la base de dades
     *
     * @param song la cançó a donar d'alta
     * @throws ManagerException en cas d'error, com per exemple clau duplicada
     */
    public void insert(Song song) throws ManagerException {
        try {
            String isrc = song.getIsrc();

            //Comprobar si ya existe
            String consultaExiste = 
                "count(" + ARREL + "songs/song[isrc = \"" + isrc + "\"])";

            XQExpression exp = conn.createExpression();
            int count = Integer.parseInt(exp.executeQuery(consultaExiste)
                    .getSequenceAsString(null));

            if (count > 0) {
                throw new ManagerException("Ja existeix una cançó amb aquest ISRC");
            }

            //Obtener XML del objeto
            String xml = Utilitats.formaObjecteXML(song);

            //Insertar
            String consultaInsert =
                "insert node " + xml + " into " + ARREL + "songs";

            exp.executeQuery(consultaInsert);

        } catch (XQException ex) {
            throw new ManagerException("Error en inserir la cançó", ex);
        }
    }


    /**
     * Elimina una cançó de la base de dades
     *
     * @param isrc l'identificador de la cançó
     * @throws eac4.gestors.ManagerException en cas d'error, com per exemple que
     * no existeixi
     */
    public void delete(String isrc) throws ManagerException {
        try {
            //Comprobar si existe
            String consultaExiste =
                "count(" + ARREL + "songs/song[isrc = \"" + isrc + "\"])";

            XQExpression exp = conn.createExpression();
            int count = Integer.parseInt(exp.executeQuery(consultaExiste)
                    .getSequenceAsString(null));

            if (count == 0) {
                throw new ManagerException("No existeix la cançó");
            }

            //Eliminar
            String consultaDelete =
                "delete node " + ARREL + "songs/song[isrc = \"" + isrc + "\"]";

            exp.executeQuery(consultaDelete);

        } catch (XQException ex) {
            throw new ManagerException("Error en eliminar la cançó", ex);
        }
    }


    /**
     * Obté una cançó de la base de dades
     *
     * @param isrc el isrc de la canço
     * @return la cançó
     * @throws eac4.gestors.ManagerException en cas d'error, com per exemple que
     * no existeixi
     */
    public Song getSong(String isrc) throws ManagerException {
        try {
            String consulta = 
                ARREL + "songs/song[isrc = \"" + isrc + "\"]";

            XQExpression exp = conn.createExpression();
            var rs = exp.executeQuery(consulta);

            if (!rs.next()) {
                throw new ManagerException("La cançó no existeix");
            }

            String xml = rs.getItemAsString(null);

            return Utilitats.obteObjecte(xml);

        } catch (XQException ex) {
            throw new ManagerException("Error en obtenir la cançó", ex);
        }
    }



    /**
     * Obtenir les cançons amb més etiquetes que un número determinat
     *
     * @param numTags número d'etiquetes
     * @return la llista de cançons amb més etiquetes
     */
    List<Song> getSongsMoreTagsThan(int numTags) throws ManagerException {
        try {
            String consulta =
                "for $s in " + ARREL + "songs/song "
              + "where count($s/tags/tag) > " + numTags + " "
              + "return $s";

            XQExpression exp = conn.createExpression();
            var rs = exp.executeQuery(consulta);

            List<Song> result = new java.util.ArrayList<>();

            while (rs.next()) {
                String xml = rs.getItemAsString(null);
                Song s = Utilitats.obteObjecte(xml);
                result.add(s);
            }

            return result;

        } catch (XQException ex) {
            throw new ManagerException("Error en obtenir les cançons", ex);
        }
    }


}
