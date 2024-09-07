
import java.util.Arrays;

public class Cypher {

    public String encrypt(String text, int shift) {
        // Логика шифрования
        char[] textAsChars = text.toCharArray();
        char[] targetChars = new char[textAsChars.length];
        int j = 0;

        for (char c : textAsChars) {

            if(Character.isLetter(c)) {
                int i = Arrays.binarySearch(Alphabet.RU_LOWERCASE, c);

                if (i >= 0) {
                    int shiftIndex = (i + shift) % Alphabet.RU_LOWERCASE.length;
                    targetChars[j++] = Alphabet.RU_LOWERCASE[shiftIndex];
                } else {
                    i = Arrays.binarySearch(Alphabet.RU_UPPERCASE, c);
                    int shiftIndex = (i + shift) % Alphabet.RU_UPPERCASE.length;
                    targetChars[j++] = Alphabet.RU_UPPERCASE[shiftIndex];
                }
            } else {
                targetChars[j++] = c;
            }
        }
        return String.copyValueOf(targetChars);
    }

    public String decrypt(String encryptedText, int shift) {
        // Логика расшифровки
        return null;
    }
}
