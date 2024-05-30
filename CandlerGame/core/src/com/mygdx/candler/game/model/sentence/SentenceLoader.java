package com.mygdx.candler.game.model.sentence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SentenceLoader {
    public static Collection<String> loadSentencesFromCSV(FileReader fileReader) {
        Collection<String> sentences = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                sentences.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sentences;
    }
}
