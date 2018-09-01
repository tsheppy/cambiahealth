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

public class CsvSorter {
    private static final String FILE_DIRECTORY = "/work/";
    private static final Path inputFilePath = Paths.get(FILE_DIRECTORY + "input.csv");
    private static final Path outputFilePath = Paths.get(FILE_DIRECTORY + "output.csv");

    public static void main(String[] args) {

        if (!Files.exists(inputFilePath)){
            System.out.println(inputFilePath + " must be present");
            System.exit(1);
        }

        try {
            List<String> sortedEntries = sortCsvEntries(Files.readAllLines(inputFilePath, Charset.defaultCharset()));
            Files.write(outputFilePath, sortedEntries, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static List<String> sortCsvEntries(List<String> lines) {
        List<String> sortedList = new ArrayList<>();
        for (String line : lines) {
            String[] entries = line.split(",");
            Arrays.sort(entries, Collections.reverseOrder());
            sortedList.add(String.join(",", entries));
        }
        return sortedList;
    }
}
