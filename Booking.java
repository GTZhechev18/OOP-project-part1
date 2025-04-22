import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Booking{

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

    public boolean SearchBooking(String event,String date, Integer row, Integer seat, String note) throws IOException
    {
        String line;
        BufferedReader reader;
        FileHandler fileHandler = new FileHandler();
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