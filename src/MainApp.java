import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        String choice;

        System.out.println("<Welcome to Caesar cypher>");
        System.out.println("--------------------------");

        do {
            Scanner console = new Scanner(System.in);

            printMenu();
            choice = console.nextLine();
            System.out.println();

            switch (choice) {
                case "1" -> {
                    try {
                        System.out.println("Что будем зашифровывать?");

                        System.out.print(" Путь к файлу: ");
                        String path = console.nextLine();

                        while (Files.notExists(Path.of(path))) {
                            System.out.println("Неверный путь, либо такого файла не существует, попробуй еще раз...");
                            System.out.print(" Путь к файлу: ");
                            path = console.nextLine();
                        }

                        System.out.print(" Шаг сдвига: ");
                        int key;

                        while (!console.hasNextInt()) {
                            console.next();
                            System.out.println("Шаг должен быть только целым числом...");
                            System.out.print(" Шаг сдвига: ");
                        }
                        key = console.nextInt();

                        Cryptographer.doEncrypt(path, key);
                    } catch (IOException IOE) {
                        System.out.println("что-то не то");
                    }
                }

                case "2" -> {
                    try {
                        System.out.println("Что будем расшифровывать?");

                        System.out.print(" Путь к файлу: ");
                        String path = console.nextLine();

                        while (Files.notExists(Path.of(path))) {
                            System.out.println("Неверный путь, либо такого файла не существует, попробуй еще раз...");
                            System.out.print(" Путь к файлу: ");
                            path = console.nextLine();
                        }

                        System.out.print(" Ключ: ");

                        while (!console.hasNextInt()) {
                            console.next();
                            System.out.println("Ключ должен быть целым числом...");
                            System.out.print(" Ключ: ");
                        }
                        int key = console.nextInt();

                        Cryptographer.doDecrypt(path, key);
                    } catch (IOException IOE) {
                        System.out.println("что-то не то\n");
                    }
                }

                case "3" -> System.out.println("Пока");
                default -> {
                    System.out.println("___Такого пункта нет___");
                    System.out.println("---Повторите попытку---\n");
                }

            }

        } while (!choice.equals("3"));
    }

    public static void printMenu() {
        System.out.println("Что будем делать?");
        System.out.println("  1. Шифровать");
        System.out.println("  2. Расшифровывать");
        System.out.println("  3. Выход");
        System.out.print("> ");
    }
}
