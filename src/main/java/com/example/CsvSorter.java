package com.example;

import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvSorter {
    public static void main(String[] args) {
        Path inputPath;
        Path outputPath;
        if (args.length < 1) {
            System.out.println("An input file path is required");
            System.exit(1);
        }
        inputPath = Paths.get(args[1]);
        if (args.length < 2) {
            System.out.println("An output file path is required");
            System.exit(1);
        }
        outputPath = Paths.get(args[1]);

        try {
            List<String> sortedEntries = sortCsvEntries(Files.readAllLines(inputPath, Charset.defaultCharset()));
            writeEntriesToFile(sortedEntries, outputPath);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static List<String> sortCsvEntries(List<String> lines) {
        List<String> sortedList = new ArrayList<>();
        try {
            for (String line : lines) {
                String[] entries = line.split(",");
                Arrays.sort(entries);
                sortedList.add(String.join(",", entries));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortedList;
    }
    public static void writeEntriesToFile(List<String> csvLines, Path path) {
        try {
            Files.write(path, csvLines, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
