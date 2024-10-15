/**
 Автор : Железко Никита Сергеевич
 запуск через точку входа в классе main.
 */
package hangman;

import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    private static final String[] WORDS = {"java", "programming", "hangman", "computer", "science"};
    private String wordToGuess;
    private char[] guessedWord;
    private int lives;
    private boolean[] guessedLetters;

    public HangmanGame() {
        Random random = new Random();
        this.wordToGuess = WORDS[random.nextInt(WORDS.length)];
        this.lives = 6;
        this.guessedWord = new char[wordToGuess.length()];
        this.guessedLetters = new boolean[26];

        for (int i = 0; i < wordToGuess.length(); i++) {
            guessedWord[i] = '_';
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (lives > 0 && !isWordGuessed()) {
            System.out.println("Загаданное слово: " + new String(guessedWord));
            System.out.println("У вас осталось " + lives + " жизней.");
            System.out.print("Введите букву: ");
            char guess = scanner.nextLine().toLowerCase().charAt(0);

            if (guess < 'a' || guess > 'z') {
                System.out.println("Пожалуйста, введите букву!");
                continue;
            }

            if (guessedLetters[guess - 'a']) {
                System.out.println("Вы уже вводили эту букву!");
                continue;
            }

            guessedLetters[guess - 'a'] = true;

            if (wordToGuess.contains(String.valueOf(guess))) {
                updateGuessedWord(guess);
                System.out.println("Правильно!");
            } else {
                lives--;
                System.out.println("Неправильно!");
            }

            drawHangman();
        }

        if (lives > 0) {
            System.out.println("Поздравляем! Вы угадали слово: " + wordToGuess);
        } else {
            System.out.println("Вы проиграли! Загаданное слово было: " + wordToGuess);
        }

        scanner.close();
    }

    private boolean isWordGuessed() {
        return wordToGuess.equals(new String(guessedWord));
    }

    private void updateGuessedWord(char guess) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                guessedWord[i] = guess;
            }
        }
    }

    private void drawHangman() {
        System.out.println("Человечек на виселице:");
        switch (lives) {
            case 6 -> System.out.println("  +---+\n      |\n      |\n      |\n     ===");
            case 5 -> System.out.println("  +---+\n  O   |\n      |\n      |\n     ===");
            case 4 -> System.out.println("  +---+\n  O   |\n  |   |\n      |\n     ===");
            case 3 -> System.out.println("  +---+\n  O   |\n /|   |\n      |\n     ===");
            case 2 -> System.out.println("  +---+\n  O   |\n /|\\  |\n      |\n     ===");
            case 1 -> System.out.println("  +---+\n  O   |\n /|\\  |\n /    |\n     ===");
            case 0 -> System.out.println("  +---+\n  O   |\n /|\\  |\n / \\  |\n     ===");
        }
    }
}
