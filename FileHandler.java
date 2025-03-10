import java.io.*;

public class FileHandler {

    private BufferedReader reader;
    private BufferedWriter writer;
    private FileWriter fileWriter;
    private FileReader fileReader;

    // Отваряне на файл за четене
    public void openForRead(String fileName) throws IOException {
        fileReader = new FileReader(fileName);
        reader = new BufferedReader(fileReader);
    }

    // Отваряне на файл за писане
    public void openForWrite(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName);
        writer = new BufferedWriter(fileWriter);
    }

    // Отваряне на файл за добавяне на текст
    public void openForAppend(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName, true); // true задава добавяне
        writer = new BufferedWriter(fileWriter);
    }

    // Запис в отворен за писане файл
    public void write(String text) throws IOException {
        if (writer != null) {
            writer.write(text);
        } else {
            System.out.println("Файлът не е отворен за писане.");
        }
    }

    // Запис в нов файл (презаписва текущото съдържание)
    public void writeToNewFile(String fileName, String text) throws IOException {
        openForWrite(fileName);
        write(text);
        close();
    }

    // Прочитане на съдържанието на файл
    public String read() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

    // Добавяне на текст в края на съществуващ файл
    public void append(String fileName, String text) throws IOException {
        openForAppend(fileName);
        write(text);
        close();
    }

    // Затваряне на отворените файлови потоци
    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
        if (writer != null) {
            writer.close();
        }
    }
    
}
