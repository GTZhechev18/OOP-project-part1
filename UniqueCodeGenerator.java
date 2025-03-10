import java.util.Base64;

public class UniqueCodeGenerator {

    public static String generateCode(String str1, String str2, int num1, int num2) {
        // Съединява всички стойности в един низ
        String combined = str1 + ":" + str2 + ":" + num1 + ":" + num2;
        // Кодиране в Base64 за уникалност
        return Base64.getEncoder().encodeToString(combined.getBytes());
    }

    public static String[] extractOriginalData(String code) {
        // Декодиране от Base64 обратно в оригиналния низ
        String decoded = new String(Base64.getDecoder().decode(code));
        // Разделяне на низовете обратно на оригиналните стойности
        return decoded.split(":");
    }
}
