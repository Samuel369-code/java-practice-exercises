/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ex1;

import ex2.gestors.GestorAlbum;
import ex2.gestors.GestorAlbumException;
import ex2.model.Album;
import java.io.File;
import java.io.FileFilter;
import java.util.Locale;

/**
 *
 * @author Samuel
 */
public class Eac1 {

    public static void main(String[] args) {
        // --- Exercici 1: gestió de fitxers ---
        if (args.length >= 4 && args.length <= 5) {
            String rutaCarpeta = args[0];
            String operacio = args[1].toUpperCase(Locale.ROOT);
            String tipusFiltro = args[2].toUpperCase(Locale.ROOT);
            String parametre = args[3];
            boolean incloureOcults = args.length == 5 && args[4].equalsIgnoreCase("H");

            File carpeta = new File(rutaCarpeta);
            if (!carpeta.isDirectory() || !carpeta.canWrite()) {
                System.err.println("Error: la ruta no és una carpeta vàlida o no es tenen permisos d'escriptura.");
                System.exit(2);
            }
            if (!operacio.equals("E") && !operacio.equals("L")) {
                System.err.println("Error: segon paràmetre ha de ser 'E' o 'L'.");
                System.exit(2);
            }
            if (!tipusFiltro.equals("N") && !tipusFiltro.equals("M")) {
                System.err.println("Error: tercer paràmetre ha de ser 'N' o 'M'.");
                System.exit(2);
            }

            final long tamanyMinFinal;
            if (tipusFiltro.equals("M")) {
                try {
                    tamanyMinFinal = Long.parseLong(parametre);
                } catch (NumberFormatException e) {
                    System.err.println("Error: quart paràmetre ha de ser un enter per a la mida.");
                    System.exit(2);
                    return; // assegura inicialització
                }
            } else {
                tamanyMinFinal = 0; // no s'utilitza si tipusFiltro és "N"
            }

            // FileFilter segons criteris
            FileFilter filtre = file -> {
                if (!incloureOcults && file.isHidden()) return false;
                if (tipusFiltro.equals("N")) {
                    return file.getName().contains(parametre);
                } else { // "M"
                    return file.isFile() && file.length() >= tamanyMinFinal;
                }
            };

            File[] items = carpeta.listFiles(filtre);
            if (items == null) items = new File[0];

            long totalBytes = 0;
            int count = 0;
            for (File f : items) {
                if (operacio.equals("E") && f.isFile()) {
                    totalBytes += f.length();
                    if (f.delete()) count++;
                } else if (operacio.equals("L")) {
                    String flags = "";
                    if (f.isHidden()) flags += "H";
                    if (f.isDirectory()) flags += (flags.isEmpty() ? "D" : ",D");
                    System.out.printf("%s%s %dB%n", f.getName(), flags.isEmpty() ? "" : " (" + flags + ")", f.length());
                    totalBytes += f.length();
                    count++;
                }
            }
            if (operacio.equals("E")) {
                System.out.printf("%d fitxers esborrats, %d Bytes%n", count, totalBytes);
            } else {
                System.out.printf("%d fitxers/directoris llistats, %d Bytes%n", count, totalBytes);
            }
        } else {
            System.err.println("Error: nombre de paràmetres incorrecte.");
            System.exit(2);
        }

        // --- Exercici 2: gestió d'àlbums ---
        try {
            String xmlFile = "album.xml";
            String jsonFile = "album.json";

            GestorAlbum gestor = new GestorAlbum();

            Album albumXML = gestor.llegirFitxerXML(xmlFile);
            System.out.println("Àlbum llegit des de XML: " + albumXML.getTitol());
            gestor.gravarFitxerXML("album2.xml", albumXML);
            System.out.println("Àlbum gravat a album2.xml");

            Album albumJSON = gestor.llegirFitxerJSON(jsonFile);
            System.out.println("Àlbum llegit des de JSON: " + albumJSON.getTitol());
            gestor.gravarFitxerJSON("album2.json", albumJSON);
            System.out.println("Àlbum gravat a album2.json");

            String duradaMin = "00:05:00";
            System.out.println("Cançons amb durada >= " + duradaMin + ":");
            albumXML.getCanconsDuradaMin(duradaMin).forEach(c ->
                System.out.println(c.getTitol() + " - " + c.getDurada())
            );

        } catch (GestorAlbumException e) {
            System.err.println("Error en gestionar l'àlbum: " + e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            System.err.println("Mètode no implementat: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}