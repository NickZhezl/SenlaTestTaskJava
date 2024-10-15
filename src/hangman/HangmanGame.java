/**
 * Класс HangmanGame реализует логику игры "Виселица".
 * В игре игрок пытается угадать слово, вводя буквы по одной.
 * Игрок теряет жизни за каждую неправильную букву.
 * Если слово отгадано полностью или игрок теряет все жизни, игра заканчивается.
 *
 * @author Никита Железко
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
    /**
     * Конструктор HangmanGame инициализирует игру.
     * Слово для угадывания выбирается случайным образом из списка.
     * Игроку дается 6 жизней.
     */
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
    /**
     * Метод play() запускает процесс игры.
     * Пользователю предлагается вводить буквы до тех пор, пока он не угадает слово
     * или не потеряет все жизни. После каждой попытки выводится текущее состояние
     * слова и количество оставшихся жизней.
     */

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

    /**
     * Отоброжает графически виселицу
     */
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
