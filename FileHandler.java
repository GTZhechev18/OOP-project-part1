import java.io.*;

/**
 * Класът FileHandler съдържа методи за работа с файлове.
 *
 *
 * @author Георги Жечев
 * @version 1.0
 * @since 2025-05-05
 */

public class FileHandler {

    private BufferedReader reader;
    private BufferedWriter writer;
    private FileWriter fileWriter;
    private FileReader fileReader;

    /**
     * Отваряне на файл за четене.
     *
     * @param fileName име на файл
     * @return няма
     */

    public void openForRead(String fileName) throws IOException {
        fileReader = new FileReader(fileName);
        reader = new BufferedReader(fileReader);
    }

    /**
     * Отваряне на файл за писане.
     *
     * @param fileName име на файл
     * @return няма
     */

    public void openForWrite(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName);
        writer = new BufferedWriter(fileWriter);
    }

    /**
     * Отваряне на файл за добавяне на текст.
     *
     * @param fileName име на файл
     * @return няма
     */

    public void openForAppend(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName, true); // true задава добавяне
        writer = new BufferedWriter(fileWriter);
    }

    /**
     * Запис в отворен за писане файл.
     *
     * @param text съдържание на файл
     * @return няма
     */

    public void write(String text) throws IOException {
        if (writer != null) {
            writer.write(text);
        } else {
            System.out.println("Файлът не е отворен за писане.");
        }
    }

    /**
     * Запис в нов файл (презаписва текущото съдържание).
     *
     * @param fileName име на файл
     * @param text съдържание на файл
     * @return няма
     */

    public void writeToNewFile(String fileName, String text) throws IOException {
        openForWrite(fileName);
        write(text);
        close();
    }

    /**
     * Прочитане на съдържанието на файл.
     *
     * @return няма
     */

    //
    public String read() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Добавяне на текст в края на съществуващ файл.
     *
     * @param fileName име на файла
     * @param text съдържание на файла
     * @return няма
     */

    public void append(String fileName, String text) throws IOException {
        openForAppend(fileName);
        write(text);
        close();
    }

    /**
     * Затваряне на отворените файлови потоци.
     *
     * @return няма
     */

    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
        if (writer != null) {
            writer.close();
        }
    }

    /**
     * Записване на файл под друго име.
     *
     * @param OldFileName предишното име на файла
     * @param NewFileName новото име на файла
     * @return няма
     */

    public void FileSaveAs(String OldFileName, String NewFileName) throws IOException{
        openForRead(OldFileName);
        openForWrite(NewFileName);
        //StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line+"\n");
        }
        reader.close();
        writer.close();
    }

    /**
     * Записване на файл под друго име.
     *
     * @param OldName предишното име на файла
     * @param NewName новото име на файла
     * @return няма
     */

    public void FileRename(String OldName, String NewName) {
        File file = new File(OldName);
        File rename = new File(NewName);
        boolean flag = file.renameTo(rename);

 /*     if (flag == true) {
            System.out.println("File Successfully Rename");
        }
        // if renameTo() return false then else block is
        // executed
        else {
            System.out.println("Operation Failed");
        }*/
    }

    /**
     * Записване на файл под друго име.
     *
     * @param FileName име на файла
     * @return няма
     */

    //изтриване на файл
    public void DeleteFile (String FileName) throws IOException
    {
        File myObj = new File(FileName);
        // myObj.delete();
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }


/*
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
    }*/
}
