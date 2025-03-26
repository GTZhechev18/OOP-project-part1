import java.io.*;

public class Booking{

    public void Book (Integer row, Integer seat, String date, String name, String note) throws IOException
    {
        FileHandler fileHandler = new FileHandler();
          fileHandler.openForAppend(Constants.Bookings_FILE);
        //FileWriter fileWriter = new FileWriter(Constants.Bookings_FILE);
        //BufferedWriter writer = new BufferedWriter(fileWriter);
        String line= name+" "+date+" "+row.toString()+" "+seat.toString()+" "+note;
        fileHandler.append(Constants.Bookings_FILE,line);
        fileHandler.close();
    }

}