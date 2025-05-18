import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * Класът Резервации съдържа методи за добавяне, изтриване, търсене,и проверяване на резервации.
 *
 * @author Георги Жечев
 * @version 1.0
 * @since 2025-05-04
 */

public class Booking{

    /**
     * Добавя резервация.
     *
     * @param row съдържа резервираният ред
     * @param seat съдържа мястото на залата
     * @param date съдържа датата
     * @param name съдържа името на събитието
     * @param note съдържа бележка
     * @return няма
     */

    public void AddBook (Integer row, Integer seat, String date, String name, String note) throws IOException
    {
        FileHandler fileHandler = new FileHandler();
          fileHandler.openForAppend(Constants.Bookings_FILE);
        //FileWriter fileWriter = new FileWriter(Constants.Bookings_FILE);
        //BufferedWriter writer = new BufferedWriter(fileWriter);
        String line= name+" "+date+" "+row.toString()+" "+seat.toString()+" "+note+"\n";
        fileHandler.append(Constants.Bookings_FILE,line);
        fileHandler.close();
    }

    /**
     * Изтрива резервация.
     *
     * @param row съдържа резервираният ред
     * @param seat съдържа мястото на залата
     * @param date съдържа датата
     * @param name съдържа името на събитието
     * @return няма
     */

    public void UnBook (String name, Integer row, Integer seat, String date)throws IOException
    {
        FileHandler fileHandler = new FileHandler();
      //  fileHandler.openForRead(Constants.Bookings_FILE);
        FileReader fileReader = new FileReader(Constants.Bookings_FILE);
       BufferedReader reader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter("Temp.txt");
        BufferedWriter writer = new BufferedWriter(fileWriter);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String e = parts[0];
            String d=parts[1];
            int r = Integer.parseInt(parts[2]);
            int s = Integer.parseInt(parts[3]);
            if (d.equals(date) && e.equals(name) && r==row && s==seat) continue;
            else writer.write(line+"\n");
        }
        reader.close();
        writer.close();
        fileHandler.DeleteFile(Constants.Bookings_FILE);
        fileHandler.FileRename("Temp.txt", Constants.Bookings_FILE);

    }

    /**
     * Намиране на резервация.
     *
     * @param event съдържа (името) събитието
     * @param row съдържа резервираният ред
     * @param seat съдържа мястото на залата
     * @param date съдържа датата
     * @param note съдържа името на събитието
     * @return true-ако резервацията я има и false-ако резервацията не съществува
     */

    //da se raboti pri otvarqne
    public boolean SearchBooking(String event,String date, Integer row, Integer seat, String note) throws IOException
    {
        String line;
        BufferedReader reader;
        //FileHandler fileHandler = new FileHandler();
        //   fileHandler.openForRead(Constants.Halls_FILE);
        FileReader fileReader = new FileReader(Constants.Bookings_FILE);
        reader = new BufferedReader(fileReader);
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String e = parts[0];
            String d=parts[1];
            int r = Integer.parseInt(parts[2]);
            int s = Integer.parseInt(parts[3]);
            if (d.equals(date) && e.equals(event) && r==row && s==seat) return true;

        }
        return false;
    }



}