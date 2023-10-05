// Дз:
// Дописать игру быки-коровы
// - на русском и английском алфавите
// - сделать логирование действий и по запросу пользователя посмотреть всю историю игры
// - доработать интерфейс взаимодействия с пользователем
// - * реализовать перезапуск игры

import java.util.Random;
import java.util.Scanner;

public class BullsAndCowsGame {
    private static final int NUMBER_LENGTH = 4;

    public static void main(String[] args) {
        BullsAndCowsGame game = new BullsAndCowsGame();
        game.startGame();
    }

    private void startGame() {
        int[] secretNumber = generateSecretNumber();

        System.out.println("Добро пожаловать в игру Быки и Коровы!");
        System.out.println("Правила игры:");
        System.out.println("Компьютер загадывает 4-значное число, в котором цифры не повторяются.");
        System.out.println("Ваша задача - угадать это число.");
        System.out.println("Если угадана только цифра, то это bull.");
        System.out.println("Если угадана цифра и ее позиция, то это cow."); 
        System.out.println("Удачи!");

        int attempts = 0;
        while (true) {
            attempts++;
            System.out.println("Попытка #" + attempts + ":");
            int[] guess = readGuess();
            int bulls = countBulls(secretNumber, guess);
            int cows = countCows(secretNumber, guess);

            System.out.println("Быки: " + bulls);
            System.out.println("Коровы: " + cows);

            if (bulls == NUMBER_LENGTH) {
                System.out.println("Поздравляем! Вы угадали число с " + attempts + " попыток.");
                break;
            }
        }
    }

    private int[] generateSecretNumber() {
        int[] secretNumber = new int[NUMBER_LENGTH];
        Random rand = new Random();

        for (int i = 0; i < NUMBER_LENGTH; i++) {
            int digit;
            do {
                digit = rand.nextInt(10);
            } while (containsDigit(secretNumber, digit));

            secretNumber[i] = digit;
        }

        return secretNumber;
    }

    private boolean containsDigit(int[] array, int digit) {
        for (int value : array) {
            if (value == digit) {
                return true;
            }
        }
        return false;
    }

    private int[] readGuess() {
        Scanner scanner = new Scanner(System.in);
        int[] guess = new int[NUMBER_LENGTH];

        System.out.println("Введите " + NUMBER_LENGTH + "-значное число:");
        String input = scanner.nextLine();

        while (input.length() != NUMBER_LENGTH) {
            System.out.println("Некорректный ввод. Введите " + NUMBER_LENGTH + "-значное число:");
            input = scanner.nextLine();
        }

        for (int i = 0; i < NUMBER_LENGTH; i++) {
            guess[i] = Character.getNumericValue(input.charAt(i));
        }

        return guess;
    }

    private int countBulls(int[] secretNumber, int[] guess) {
        int bulls = 0;

        for (int i = 0; i < NUMBER_LENGTH; i++) {
            if (secretNumber[i] == guess[i]) {
                bulls++;
            }
        }

        return bulls;
    }

    private int countCows(int[] secretNumber, int[] guess) {
        int cows = 0;

        for (int i = 0; i < NUMBER_LENGTH; i++) {
            for (int j = 0; j < NUMBER_LENGTH; j++) {
                if (secretNumber[i] == guess[j] && i != j) {
                    cows++;
                }
            }
        }

        return cows;
    }
}