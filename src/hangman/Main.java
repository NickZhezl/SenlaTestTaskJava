/**
 * Точка main для игры "Виселица".
 * Создает объект HangmanGame и запускает игру.
 *
 * @author Никита Железко
 */
package hangman;

public class Main {
    public static void main(String[] args) {
        HangmanGame game = new HangmanGame();
        game.play();
    }
}
