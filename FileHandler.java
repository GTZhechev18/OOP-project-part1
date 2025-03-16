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

    // търсене дали на дата date в зала hall вече има събитие
    Boolean SearchEvent(String date, Integer hall)
    {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String d = parts[0];
            String event=parts[1];
            int h = Integer.parseInt(parts[2]);
            if (d.equals(date) && hall==h) return true;
        
        }
        return false;
    }

    void AddEvent(String date, Integer hall, String event)
    {
        String line=date+" "+hall.toString()+" "+event; 
        append(Constants.Events_FILE, line);
    }

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        
        try {
            // Запис в нов файл
            fileHandler.writeToNewFile("example.txt", "Това е примерен текст.\n");

            // Добавяне на нов текст в съществуващ файл
            fileHandler.append("example.txt", "Това е добавен текст.\n");

            // Отваряне на съществуващ файл за четене
            fileHandler.openForRead("example.txt");
            String content = fileHandler.read();
            System.out.println("Съдържание на файла:\n" + content);

            // Затваряне на файла след работа
            fileHandler.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
