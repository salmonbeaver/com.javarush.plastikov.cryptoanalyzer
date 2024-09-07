import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) throws IOException {
        // Логика для выбора режима работы, вызов соответствующих методов
        Scanner console = new Scanner(System.in);
        String choice;

        System.out.println("<Welcome to Caesar cypher>");
        System.out.println("--------------------------");

        do {
            System.out.println("What do you want to do?");
            System.out.println(" 1. Зашифровать");
            System.out.println(" 2. Расшифровать");
            System.out.println(" 3. Выйти");
            System.out.print("> ");

            choice = console.nextLine();
            System.out.println();

            switch (choice) {
                case "1" -> {
                    doCypher(console.nextLine());
                    System.out.println("Готово");
                    System.out.println("Твой зашифрованный файл находится по адрессу: " + pathOut);
                }
                case "2" -> System.out.println("вы ввели 2");
                case "3" -> System.out.println("Пока");
                default -> System.out.println(" Юрий, не хитри");
            }

        } while (!choice.equals("3"));
    }

    public static void doCypher(String stringPath) {
        System.out.println("Что будем шифровать?");
        System.out.println(" Введи путь к файлу: ");
        Path pathIn = Path.of(stringPath);
//        Path pathOut = pathIn.resolve(); тут будет создаваться путь к зашифрованному файлу, который находится в папке и исходным

        Cypher cypher = new Cypher();

        try (Reader reader = new FileReader(pathIn.toFile());
             Writer writer = new FileWriter(pathOut.toFile());
             BufferedReader buffer = new BufferedReader(reader)) {

            while (buffer.ready()) {
                String string = buffer.readLine();
                String cypherString = cypher.encrypt(string, 1);
                writer.write(cypherString + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Юра, ты мне всё сломал :(");
        }



    }
}
