package pl.put.poznan.entroop3r.logic;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class MessageAnalizer {
    private Map<Character, Integer> countedCharacters;
    private int messageLength;

    public MessageAnalizer(Map<Character, Integer> countedCharacters, int messageLength) throws IOException {
        this.countedCharacters = countedCharacters;
        this.messageLength = messageLength;
    }

    public double calculateEntropy(int logBase) {
        double entropy = 0;
        for(char currentCharacter : countedCharacters.keySet()) {
            entropy += computeCharacterPropability(currentCharacter)*calculateInformationAmount(currentCharacter, logBase);
        }
        return entropy;
    }

    public double calculateInformationAmount(char character, int logBase) {
        return Math.log10(1/computeCharacterPropability(character))/Math.log10(logBase);
    }

    public double computeCharacterPropability(char character) {
        double result = 0;
        if(this.countedCharacters.containsKey(character)) {
            result = this.countedCharacters.get(character)/(messageLength * 1.0);
        }
        return result;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public int getNumberOfOccurrences(char character) {
        int result = 0;
        if(countedCharacters.containsKey(character)) {
            result = countedCharacters.get(character);
        }
        return result;
    }

    public Set<Character> getSymbols() {
        return countedCharacters.keySet();
    }
}
