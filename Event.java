import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класът събитие включва методи за реализирне на събития, например номера на залата, датата на провеждане и др.
 *
 * @author Георги Жечев
 * @version 1.0
 * @since 2025-05-04
 */

public class Event{

    /**
     * Търси събитие.
     *
     * @param date съдържа датата
     * @param hall съдържа залата
     * @return true-ако събитието го има и false-ако събитието не съществува
     */

    // търсене дали на дата date в зала hall вече има събитие
    Boolean SearchEvent(String date, Integer hall) throws IOException
    {
        String line;
        BufferedReader reader;
        FileHandler fileHandler = new FileHandler();
     //   fileHandler.openForRead(Constants.Halls_FILE);
        FileReader fileReader = new FileReader(Constants.Halls_FILE);
        reader = new BufferedReader(fileReader);
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String d = parts[0];
            String event=parts[1];
            int h = Integer.parseInt(parts[2]);
            if (d.equals(date) && hall==h) return true;
        
        }
        return false;
    }

    /**
     * Добавя ново събитие.
     *
     * @param date съдържа датата
     * @param hall съдържа залата
     * @param event съдържа името на събитието
     * @return няма
     */

    //добавяне на събитие 
    void AddEvent(String date, Integer hall, String event)throws IOException
    {
        FileHandler fileHandler = new FileHandler();
        String line=date+" "+event+" "+hall.toString()+"\n";
        fileHandler.append(Constants.Events_FILE, line);
    }

}