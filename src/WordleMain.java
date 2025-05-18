import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordleMain {
    public static void main(String[] args) {
        // Store all words from the list in allWords
        ArrayList<String> allWords = new ArrayList<String>();
        try {
            File myObj = new File("/Users/Admin/IdeaProjects/Wordle/src/wordle.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                allWords.add(data.trim()); // Remove extra spaces
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return;
        }

        allWords.add("Hello");
        allWords.add("Bigma");
        allWords.add("Bigch");
        allWords.add("Shite");
        allWords.add("Fucek");
        // Select a random word to be the answer
        Random rand = new Random();
        String answer = allWords.get(rand.nextInt(allWords.size()));
        String[] answerLetters = answer.split("");

        // Initialize WordleObject with the target word
        wordleObject wordle = new wordleObject(answerLetters);

        System.out.println("Welcome to Wordle! Guess the 5-letter word.");
        System.out.println("B=Letter is not in the word");
        System.out.println("Y=Letter is in the word but not the correct place");
        System.out.println("G=Letter is in the word and in the correct place");
        Scanner input = new Scanner(System.in);
        int maxAttempts = 6; // Number of allowed attempts
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.print("Enter your 5-letter guess: ");
            String guess = input.nextLine().toLowerCase().trim();

            if (guess.length() != 5) {
                System.out.println("Invalid guess! Please enter a 5-letter word.");
                continue;
            }

            if (!allWords.contains(guess)) {
                System.out.println("Invalid guess! The word is not in the word list.");
                continue;
            }

            attempts++;
            String feedback = wordle.checkGuess(guess);
            System.out.println("Feedback: " + feedback);
            wordle.displayRemainingAlphabet();

            if (feedback.equals("GGGGG")) {
                System.out.println("Congratulations! You guessed the word in " + attempts + " attempts.");
                return;
            }

            if (attempts == maxAttempts) {
                System.out.println("Game over! The correct word was: " + answer);
            }
        }
    }
}