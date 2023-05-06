import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Resort {

    public static String findByName(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/resorts.txt"))) {
            return reader.lines()
                    .filter(line -> line.startsWith(name + ": "))
                    .map(line -> {
                        String[] parts = line.split(": ");
                        return String.format("name: '%s' price: $%s;", parts[0], parts[1]);
                    })
                    .collect(Collectors.joining());
        } catch (IOException e) {
            return "Error reading file";
        }
    }

    public String findByPrice(String price) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/resorts.txt"))) {
            return reader.lines()
                    .filter(line -> line.endsWith(": " + price))
                    .map(line -> {
                        String[] parts = line.split(": ");
                        return String.format("name: '%s' price: $%s;", parts[0], parts[1]);
                    })
                    .collect(Collectors.joining());
        } catch (IOException e) {
            return "Error reading file";
        }
    }

    public String findAll(String st) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/resorts.txt"))) {
            return reader.lines()
                    .map(line -> {
                        String[] parts = line.split(": ");
                        return String.format("name: '%s' price: $%s;", parts[0], parts[1]);
                    })
                    .collect(Collectors.joining());
        } catch (IOException e) {
            return "Error reading file";
        }
    }
}
