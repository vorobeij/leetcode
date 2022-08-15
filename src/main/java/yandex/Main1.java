package yandex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main1 {

    public static void main(String[] args) throws IOException {

        List<String> input = input();

        for (String line : input) {

        }
    }

    private static List<String> input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        return List.of(line.split(" "));
    }
}
