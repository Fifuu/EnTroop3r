package pl.put.poznan.entroop3r.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileCharactersCounter {
    private String message;
    private int messageLength;
    Map<Character, Integer> countedSymbols;

    public FileCharactersCounter() {
        this.message = "";
        this.countedSymbols = new HashMap<>(94);
    }

    public Map<Character, Integer> count(String filePath) throws IOException {
        this.message = new String(Files.readAllBytes(Paths.get(filePath)));
        return countCharacters(message);
    }

    private Map<Character, Integer> countCharacters(String message) {
        countedSymbols = new HashMap<>(94);
        int counter = 0;
        for(char currentCharacter : message.toCharArray()) {
            if((int)currentCharacter < 32) {
                continue;
            }
            messageLength++;
            if(countedSymbols.containsKey(currentCharacter)) {
                counter = countedSymbols.get(currentCharacter);
            }
            countedSymbols.put(currentCharacter, ++counter);
            counter = 0;
        }
        return countedSymbols;
    }

    public int getMessageLength() {
        return this.message.length();
    }

    public Map<Character, Integer> getCountingResults() {
        return this.countedSymbols;
    }
}
