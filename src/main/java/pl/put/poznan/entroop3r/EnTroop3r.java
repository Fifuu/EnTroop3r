package pl.put.poznan.entroop3r;

import pl.put.poznan.entroop3r.logic.FileCharactersCounter;
import pl.put.poznan.entroop3r.logic.MessageAnalizer;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

public class EnTroop3r {
    static DecimalFormat numberFormatter = new DecimalFormat("#.####");

    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            System.out.println("ERROR: There's missing path to text file.");
            return;
        }
        FileCharactersCounter fcc = new FileCharactersCounter();
        String path = args[0];
        Map<Character, Integer> countedSymbols = fcc.count(path);
        MessageAnalizer messageAnalizer = new MessageAnalizer(countedSymbols, fcc.getMessageLength());
        print(countedSymbols, messageAnalizer);
    }

    public static void print(Map<Character, Integer> result, MessageAnalizer messageAnalizer) {
        numberFormatter.setRoundingMode(RoundingMode.CEILING);
        for(char currentSymbol : result.keySet()) {
            System.out.println("<" + currentSymbol + ">" + ":\n\tAmout: " + messageAnalizer.getNumberOfOccurrences(currentSymbol) + "/" + messageAnalizer.getMessageLength()
                    + "\n\tPropability: " + numberFormatter.format(messageAnalizer.computeCharacterPropability(currentSymbol))
                    + "\n\tInformation Amout: " + numberFormatter.format(messageAnalizer.calculateInformationAmount(currentSymbol, 2)));
        }
        System.out.println("Entropy: " + numberFormatter.format(messageAnalizer.calculateEntropy(2)));
    }
}
