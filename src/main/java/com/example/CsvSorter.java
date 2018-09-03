package com.example;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Basic CSV parser in Java made for use from within Docker container.
 *
 * @author Tucker Sheppy
 *
 */
public final class CsvSorter {

    /**
     * Location where input and output files will be stored.
     */
    private static final String FILE_DIRECTORY = "/work/";

    /**
     *  Name of input CSV file.
     */
    private static final Path INPUT_FILE_PATH =
            Paths.get(FILE_DIRECTORY + "input.csv");

    /**
     * Name of output CSV file.
     */
    private static final Path OUTPUT_FILE_PATH =
            Paths.get(FILE_DIRECTORY + "output.csv");

    /**
     * Internal empty constructor.
     */
    private CsvSorter() { }

    /**
     * Main entry point of execution.
     * @param args command line arguments
     */
    public static void main(final String[] args) {

        if (!Files.exists(INPUT_FILE_PATH)) {
            System.out.println(INPUT_FILE_PATH + " must be present");
            System.exit(1);
        }

        try {
            List<String> sortedEntries = sortCsvEntries(Files.readAllLines(
                    INPUT_FILE_PATH, Charset.defaultCharset()));
            Files.write(OUTPUT_FILE_PATH, sortedEntries,
                    StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     *
     * @param lines List of comma separated strings to sort.
     * @return List of sorted comma separated strings.
     */
    public static List<String> sortCsvEntries(final List<String> lines) {
        List<String> sortedList = new ArrayList<>();
        for (String line : lines) {
            String[] entries = line.split(",");
            Arrays.sort(entries, Collections.reverseOrder());
            sortedList.add(String.join(",", entries));
        }
        return sortedList;
    }
}
