import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Event{

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

    //добавяне на събитие 
    void AddEvent(String date, Integer hall, String event)throws IOException
    {
        FileHandler fileHandler = new FileHandler();
        String line=date+" "+hall.toString()+" "+event;
        fileHandler.append(Constants.Events_FILE, line);
    }

}