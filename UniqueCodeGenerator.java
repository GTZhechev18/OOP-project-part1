import java.util.Base64;

/**
 * Класът UniqueCodeGenerator съдържа методи за генерирането на уникален сложен код, който съдържа информация за билет.
 *
 * @author Георги Жечев
 * @version 1.0
 * @since 2025-05-04
 */

public class UniqueCodeGenerator {

    /**
     * генерира код.
     *
     * @param str1 съдържа низ със събитие
     * @param str2 съдържа низ с дата
     * @param num1 съдържа число с реда
     * @param num2 съдържа число с място
     * @return низ който съдържа генерирания код
     */

    public static String generateCode(String str1, String str2, int num1, int num2) {
        // Съединява всички стойности в един низ
        String combined = str1 + ":" + str2 + ":" + num1 + ":" + num2;
        // Кодиране в Base64 за уникалност
        return Base64.getEncoder().encodeToString(combined.getBytes());
    }

    /**
     * генерира код.
     *
     * @param code съдържа кода
     * @return масив с елементи, от който е генериран кода
     */

    public static String[] extractOriginalData(String code) {
        // Декодиране от Base64 обратно в оригиналния низ
        String decoded = new String(Base64.getDecoder().decode(code));
        // Разделяне на низовете обратно на оригиналните стойности
        return decoded.split(":");
    }
}
