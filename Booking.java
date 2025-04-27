import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

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

    public void ReportReservedUnpayedBookings(String date, String name)throws IOException
    {
        String line, line1;
        BufferedReader readerRes, readerPurch;
        FileReader fileReader1 = new FileReader(Constants.Bookings_FILE);
        readerRes = new BufferedReader(fileReader1);
        FileReader fileReader2 = new FileReader(Constants.Payments_FILE);
        readerPurch = new BufferedReader(fileReader2);
        while ((line = readerRes.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts[0].equals(name) && parts[1].equals(date) )
            {
                Boolean found=false;
                while ((line1 = readerPurch.readLine()) != null) {
                    String[] parts1 = line.split(" ");
                    if (parts[0].equals(parts1[0]) && parts[1].equals(parts1[1]) && parts[2].equals(parts1[2])
                        && parts[3].equals(parts1[3])) found=true;
                }
                if (!found) System.out.println(line);
            }
        }
        readerRes.close();
        readerPurch.close();
    }

    public void ReportReservedUnpayedBookings(String param)throws IOException
    {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
        boolean validDate;
        sdfrmt.setLenient(false);
        try {
            Date javaDate = sdfrmt.parse(param);
            validDate=true;
        }
        catch(ParseException e)
        {
          //  System.out.println(strDate+" is Invalid Date format");
            validDate=false;
        }

        String line, line1;
        BufferedReader readerRes, readerPurch;
        FileReader fileReader1 = new FileReader(Constants.Bookings_FILE);
        readerRes = new BufferedReader(fileReader1);
        FileReader fileReader2 = new FileReader(Constants.Payments_FILE);
        readerPurch = new BufferedReader(fileReader2);
        while ((line = readerRes.readLine()) != null) {
            String[] parts = line.split(" ");
            if (validDate) {
                if (parts[1].equals(param)) {
                    Boolean found = false;
                    while ((line1 = readerPurch.readLine()) != null) {
                        String[] parts1 = line.split(" ");
                        if (parts[0].equals(parts1[0]) && parts[1].equals(parts1[1]) && parts[2].equals(parts1[2])
                                && parts[3].equals(parts1[3])) found = true;
                    }
                    if (!found) System.out.println(line);
                }
            }
            else if (parts[0].equals(param) )
            {
                Boolean found=false;
                while ((line1 = readerPurch.readLine()) != null) {
                    String[] parts1 = line.split(" ");
                    if (parts[0].equals(parts1[0]) && parts[1].equals(parts1[1]) && parts[2].equals(parts1[2])
                            && parts[3].equals(parts1[3])) found=true;
                }
                if (!found) System.out.println(line);
            }
        }
        readerRes.close();
        readerPurch.close();
    }


}