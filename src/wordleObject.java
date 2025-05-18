import java.util.*;

public class wordleObject {
    private String[] letters;  // Target word split into letters
    private ArrayList<String> alphabet;  // Remaining letters in the alphabet

    public wordleObject(String[] letters) {
        this.letters = letters;
        alphabet = new ArrayList<>(Arrays.asList(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        ));
    }

    public String checkGuess(String guess) {
        String[] guessLetters = guess.split("");
        String[] result = {"B", "B", "B", "B", "B"};
        boolean[] matched = new boolean[5];

        // Check for correct letters in correct positions
        for (int i = 0; i < 5; i++) {
            if (guessLetters[i].equals(letters[i])) {
                result[i] = "G";
                matched[i] = true;
            }
        }

        // Check for correct letters in wrong positions
        for (int i = 0; i < 5; i++) {
            if (result[i].equals("G")) continue;
            for (int j = 0; j < 5; j++) {
                if (!matched[j] && guessLetters[i].equals(letters[j])) {
                    result[i] = "Y";
                    matched[j] = true;
                    break;
                }
            }
        }

        // Update the alphabet
        for (String letter : guessLetters) {
            if (alphabet.contains(letter)) {
                alphabet.remove(letter);
            }
        }

        return String.join("", result);
    }

    public void displayRemainingAlphabet() {
        System.out.println("Remaining letters: " + String.join(" ", alphabet));
    }
}
