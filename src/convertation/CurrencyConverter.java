/**
 * Программа для конвертации валют из долларов США (USD) в Евро (EUR),
 * Российские рубли (RUB), Фунты стерлингов (GBP) и Японские иены (JPY).
 * Курсы валют заданы в программе как константы.
 * Программа запрашивает сумму в USD у пользователя и выводит эквивалентные суммы в других валютах.
 * @author Никита Железко
 */
package convertation;

import java.util.Scanner;

public class CurrencyConverter {

//константы брал усреднённые по банкам РБ
    private static final double USD_TO_EUR = 0.85;
    private static final double USD_TO_RUB = 96.50;
    private static final double USD_TO_GBP = 0.76;
    private static final double USD_TO_JPY = 149.55;


    public static void convert(double amount) {
        System.out.println("Конвертация суммы: $" + amount);
        System.out.printf("В EUR: %.2f%n", amount * USD_TO_EUR);
        System.out.printf("В RUB: %.2f%n", amount * USD_TO_RUB);
        System.out.printf("В GBP: %.2f%n", amount * USD_TO_GBP);
        System.out.printf("В JPY: %.2f%n", amount * USD_TO_JPY);
    }

    /**
     * Основной метод (точка входа в программу)
     * Запрашивает сумму в долларах и конвертирует в другие валюты
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Введите сумму в USD: ");
        double amountInUSD = scanner.nextDouble();


        convert(amountInUSD);

        scanner.close();
    }
}
