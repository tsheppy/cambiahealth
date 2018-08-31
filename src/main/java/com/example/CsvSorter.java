package com.example;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvSorter {
    private static final String ENV_INPUT_FILE = "CSV_INPUT_FILE";
    private static final String ENV_OUTPUT_FILE = "CSV_OUTPUT_FILE";

    public static void main(String[] args) {
        Path inputPath = null;
        Path outputPath = null;
        try {
            inputPath = Paths.get(System.getenv(ENV_INPUT_FILE));
        } catch (Exception e) {
            System.out.println(ENV_INPUT_FILE + " must specify a valid input file path");
            System.exit(1);
        }
        try {
            outputPath = Paths.get(System.getenv(ENV_OUTPUT_FILE));
        } catch (Exception e) {
            System.out.println(ENV_OUTPUT_FILE + " must specify a valid output file path");
            System.exit(1);
        }

        try {
            List<String> sortedEntries = sortCsvEntries(Files.readAllLines(inputPath, Charset.defaultCharset()));
            writeEntriesToFile(sortedEntries, outputPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static List<String> sortCsvEntries(List<String> lines) {
        List<String> sortedList = new ArrayList<>();
        for (String line : lines) {
            String[] entries = line.split(",");
            Arrays.sort(entries);
            sortedList.add(String.join(",", entries));
        }
        return sortedList;
    }

    public static void writeEntriesToFile(List<String> csvLines, Path path) throws IOException {
        Files.write(path, csvLines, StandardOpenOption.CREATE);
    }
}
