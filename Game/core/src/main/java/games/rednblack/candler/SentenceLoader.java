package games.rednblack.candler;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SentenceLoader {
    static ArrayList<String> loadSentencesFromCSV() {
        ArrayList<String> sentences = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("assets/sentences.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String sentence = StringUtils.trim(line).toLowerCase();
                sentences.add(sentence);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sentences;
    }
}
