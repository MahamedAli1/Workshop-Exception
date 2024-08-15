package se.lexicon.exceptions.workshop.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader_Writer {

    public static List<String> getMaleFirstNames(){
        BufferedReader reader = null;
        List <String> names = null;
        try {
            reader = Files.newBufferedReader(Paths.get("firstname_males.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("An error occurred while reading male first names: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Failed to close the BufferedReader: " + e.getMessage());
                }
            }
        }
        return names;
    }

    public static List<String> getFemaleFirstNames(){
        List<String> names = null;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("firstname_female.txt"))) {
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("An error occurred while reading female first names: " + e.getMessage());
        }
        return names;
    }

    public static List<String> getLastNames() throws IOException {
        List<String> names = null;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("lastnames.txt"))) {
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        }
        return names;
    }

    public static void saveLastNames(List<String> names) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("lastnames.txt"))) {
            for (String name : names) {
                writer.write(name);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving last names to file: " + e.getMessage());
        }
    }

    public static void saveFemaleNames(List<String> names) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_female.txt"))) {
            for (String name : names) {
                writer.write(name);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving female names to file: " + e.getMessage());
        }
    }

    public static void saveMaleNames(List<String> names) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_males.txt"))) {
            for(String toWrite : names) {
                writer.append(toWrite+",");
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving male names to file: " + e.getMessage());
        }
    }
}
