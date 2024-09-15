
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Cryptographer {

    private static String encryptLine(String text, int shift) {
        char[] textAsChars = text.toCharArray();
        char[] targetChars = new char[textAsChars.length];
        int j = 0;

        for (char c : textAsChars) {
            int i = Arrays.binarySearch(Alphabet.JR, c);

            if (i >= 0) {
                int shiftIndex = (i + shift) % Alphabet.JR.length;
                targetChars[j++] = Alphabet.JR[shiftIndex];
            } else {
                targetChars[j++] = c;
            }
        }
        return String.copyValueOf(targetChars);

        // Шифрование для двух регистров без учета символов
//        for (char c : textAsChars) {
//            if (Character.isLetter(c)) {
//                int i = Arrays.binarySearch(Alphabet.RU_LOWERCASE, c);
//
//                if (i >= 0) {
//                    int shiftIndex = (i + shift) % Alphabet.RU_LOWERCASE.length;
//                    targetChars[j++] = Alphabet.RU_LOWERCASE[shiftIndex];
//                } else {
//                    i = Arrays.binarySearch(Alphabet.RU_UPPERCASE, c);
//                    int shiftIndex = (i + shift) % Alphabet.RU_UPPERCASE.length;
//                    targetChars[j++] = Alphabet.RU_UPPERCASE[shiftIndex];
//                }
//            } else {
//                targetChars[j++] = c;
//            }
//        }
    }

    private static String decryptLine(String encryptedText, int shift) {
        char[] textAsChars = encryptedText.toCharArray();
        char[] targetChars = new char[textAsChars.length];
        int j = 0;

        for (char c : textAsChars) {
            int i = Arrays.binarySearch(Alphabet.JR, c);

            if (i >= 0) {
                int shiftIndex = (i + Alphabet.JR.length - shift) % Alphabet.JR.length;
                targetChars[j++] = Alphabet.JR[shiftIndex];
            } else {
                targetChars[j++] = c;
            }
        }
        return String.copyValueOf(targetChars);
    }

    public static void doEncrypt(String stringPath, int shift) throws IOException {
        Path pathIn = Path.of(stringPath);
        pathIn.getParent();
        pathIn.resolve("EncryptedText.txt");
        Path pathOut = pathIn;
        int k = 1;

        while (Files.exists(pathOut)) {
            try {
                pathOut = pathOut.getParent().resolve("EncryptedText" + k + ".txt");
            } catch (NullPointerException e) {
                throw e;
            }
            k++;
        }
        Files.createFile(pathOut);

        try (Reader reader = new FileReader(pathIn.toFile());
             Writer writer = new FileWriter(pathOut.toFile());
             BufferedReader buffer = new BufferedReader(reader)) {

            while (buffer.ready()) {
                String string = buffer.readLine();
                String cypherString = encryptLine(string, shift);
                writer.write(cypherString + "\n");
            }
            writer.flush();

            Thread.sleep(500);
            System.out.println("Готово");
            Thread.sleep(500);
            System.out.println("Зашифрованный файл здесь: " + pathOut + "\n");
            Thread.sleep(500);

        } catch (IOException e) {
            System.out.println("___что-то с вводом/выводом на шифре___\n");
        } catch (InterruptedException e) {
            System.out.println("___какой-то интераптед на шифре___");
        }
    }

    public static void doDecrypt(String stringPath, int shift) throws IOException {
        Path pathIn = Path.of(stringPath);
        Path pathOut = pathIn.getParent().resolve("OriginalText.txt");
        int k = 1;

        while (Files.exists(pathOut)) {
            pathOut = pathOut.getParent().resolve("OriginalText" + k + ".txt");
            k++;
        }
        Files.createFile(pathOut);

        try (Reader reader = new FileReader(pathIn.toFile());
             Writer writer = new FileWriter(pathOut.toFile());
             BufferedReader buffer = new BufferedReader(reader)) {

            while (buffer.ready()) {
                String string = buffer.readLine();
                String cypherString = decryptLine(string, shift);
                writer.write(cypherString + "\n");
            }
            writer.flush();

            Thread.sleep(500);
            System.out.println("\nГотово");
            Thread.sleep(500);
            System.out.println("Расшифрованный файл здесь: " + pathOut + "\n");
            Thread.sleep(500);

        } catch (IOException e) {
            System.out.println("___некорректный ввод___\n");
        } catch (InterruptedException e) {
            System.out.println("___какой-то интераптед на ДЕшифре___");
        }
    }
}
