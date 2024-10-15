package PasswordGenerator;


/**
 * Генератор паролей, соответствующих требованиям задания
 * @author Никита Железко
 */

import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {


    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+<>?";
    private static final String ALL_CHARACTERS = UPPER_CASE + LOWER_CASE + DIGITS + SPECIAL_CHARACTERS;


    private static final SecureRandom random = new SecureRandom();

    /**
     * Метод для генерации случайного пароля.
     * @param length длина пароля
     * @return сгенерированный пароль
     */
    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder(length);


        password.append(UPPER_CASE.charAt(random.nextInt(UPPER_CASE.length())));
        password.append(LOWER_CASE.charAt(random.nextInt(LOWER_CASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));


        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }


        return shufflePassword(password.toString());
    }

    /**
     * Метод для перемешивания символов в пароле для большей случайности.
     * @param password исходный пароль
     * @return перемешанный пароль
     */
    private static String shufflePassword(String password) {
        char[] characters = password.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Введите длину пароля (от 8 до 12 символов): ");
        int length = scanner.nextInt();


        if (length < 8 || length > 12) {
            System.out.println("Ошибка: длина пароля должна быть от 8 до 12 символов.");
        } else {

            String password = generatePassword(length);
            System.out.println("Сгенерированный пароль: " + password);
        }

        scanner.close();
    }
}
