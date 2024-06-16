package games.rednblack.candler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;

public class SentenceLoader {
    public static FileReader getFileReader(String internalPath) throws IOException {
        File tempFile = File.createTempFile("temp", ".tmp");
        tempFile.deleteOnExit();
        FileHandle fileHandle = Gdx.files.internal(internalPath);
        try (InputStream inputStream = fileHandle.read();
             FileOutputStream out = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        return new FileReader(tempFile);
    }
    static ArrayList<String> loadSentencesFromCSV() {
        ArrayList<String> sentences = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(getFileReader("sentences.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String sentence = StringUtils.trim(line);
                sentences.add(sentence);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sentences;
    }
}
