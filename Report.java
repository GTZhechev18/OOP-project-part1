import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.text.*;
import java.util.*;
/**
 * Класът Report извежда справки за въведени данни.
 *
 *
 * @author Георги Жечев
 * @version 1.0
 * @since 2025-05-05
 */
public class Report {

    /**
     * Проверява в коя зала се състои събитието.
     *
     * @param event връща събитието което се случва в тази зала
     * @param date датата на събитие
     * @return Връща залата на събитието,в противен случай връща -1 ако залата не съществува или не е зададена.
     */

   public Integer FindHall(String event, String date) throws IOException {

         //намираме залата на събитие на определена дата
         FileHandler fileHandler = new FileHandler();
       //  fileHandler.openForRead(Constants.Events_FILE);
        FileReader fileReader = new FileReader(Constants.Halls_FILE);
        BufferedReader reader = new BufferedReader(fileReader);
         String line;
         while ((line = reader.readLine()) != null) {
             String[] parts = line.split(" ");
             String d = parts[0];
             String e= parts[1];
             int h = Integer.parseInt(parts[2]);
            if (d.equals(date) && e.equals(event)) {fileHandler.close(); 
                                                    return h;
                                                    }
         }
         fileHandler.close(); 
         return -1; // ако зала не е намерена
    }

    /**
     * Проверява в коя зала се състои събитието.
     *
     * @param hall номер на зала
     * @return Връща наредена двойка от броя редова и места в зала,в противен случай връща null.
     */

    Pair <Integer, Integer> FindNumSeats(Integer hall)throws IOException{
        //намираме залата и връщаме броя редове и места
        FileHandler fileHandler = new FileHandler();
      //   fileHandler.openForRead(Constants.Halls_FILE);
        FileReader fileReader = new FileReader(Constants.Halls_FILE);
        BufferedReader reader = new BufferedReader(fileReader);
         String line;
         Pair<Integer, Integer> pair;
         while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            int h = Integer.parseInt(parts[0]);
            int rows=Integer.parseInt(parts[1]);
            int seats= Integer.parseInt(parts[2]);
            if (h==hall) {pair= new Pair<>(rows, seats);
                        fileHandler.close(); 
                        return pair;
                         }
             }
            fileHandler.close(); 
            return null; //ако няма такава зала
    }

    /**
     * Връща колко пъти се среща низ в друг низ.
     *
     * @param pat търсеният низ.
     * @param txt основният низ.
     * @return Връща броя срещания.
     */

   static int countFreq(String pat, String txt)
   {
       int M = pat.length();
       int N = txt.length();
       int res = 0;

       for (int i = 0; i <= N - M; i++) {
           /* For current index i, check for pattern match */
           int j;
           for (j = 0; j < M; j++) {
               if (txt.charAt(i + j) != pat.charAt(j)) {
                   break;
               }
           }

           if (j == M) {
               res++;
               j = 0;
           }
       }
       return res;
   }

    /**
     * Извежда справка за свободните места за представление.
     *
     * @param event име на събитие.
     * @param date дата на събития.
     * @return Връща броя свободни места.
     */

  public void ReportFreeSeats(String event, String date) throws IOException{

        StringBuilder sb=new StringBuilder();
        FileHandler fileHandler = new FileHandler();
        fileHandler.openForRead(Constants.Bookings_FILE);
        //четем всички резервации
        String bookings=fileHandler.read();
        fileHandler.close();

        Integer num1=countFreq(event+" "+date, bookings);

        fileHandler.openForRead(Constants.Payments_FILE);
        //четем всички покупки
        String payments=fileHandler.read();
        fileHandler.close();
        Integer num2=countFreq(event+" "+date, payments);

        Integer hall=FindHall(event, date); //намираме залата на събитието
         
        Pair<Integer, Integer> pair=FindNumSeats(hall);
        if (pair==null) { System.out.println("Залата не е намерена");
                           return; }
        Integer result=pair.getKey()*pair.getValue()- num1 - num2;
        System.out.println("Брой свободни места="+ result);

    }

    /**
     * Проверяване на резервацията дали е резервирана и неплатена.
     *
     * @param date съдържа датата
     * @param name съдържа името на събитието
     * @return true-ако резервацията съществува и ако датата е вярна и false-ако събитието не съществува или е грешна датата
     */

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

    /**
     * Проверяване на резервацията дали е резервирана и неплатена.
     *
     * @param param съдържа датата и резервацията на събитието
     * @return true-ако и двата параметъра са налице и false-ако събитието не са налични
     */

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

    /**
     * Намира дадено събитие в ArrayList от наредени двойки <събитие, билети>.
     *
     * @param arr ArrayList от наредени двойки
     * @param ev търсеното събитие
     * @return индекса на търсеното събитие в arr или -1, ако не съществува.
     */

    Integer FindEventNum (ArrayList<Pair<String, Integer>> arr, String ev)
    {
        for (int i=0; i<arr.size(); i++)
            if (ev.equals(arr.get(i).getKey())) return i;
        return -1;
    }

    /**
     * Показва продадените билети за събития.
     *
     * @param arr ArrayList от наредени двойки.
     * @return няма.
     */

    void ShowTicketResults(ArrayList<Pair<String, Integer>> arr)
    {
        if (arr.size()==0) System.out.println("Няма продадени билети в този интервал!");
        else {
            System.out.println("Продадени билети в посочения интервал:");
            for (int i=0; i<arr.size(); i++)
                System.out.println(arr.get(i).getKey()+" "+arr.get(i).getValue());
        }
    }

    /**
     * Извежда справка за продадените билети между две дати.
     *
     * @param StartDate начална дата.
     * @param EndDate крайна дата.
     * @return няма.
     */

    public void ReportPurchasedTickets(String StartDate, String EndDate) throws IOException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.mm.yyyy");
        Date date1 = simpleDateFormat.parse(StartDate);
        Date date2 = simpleDateFormat.parse(EndDate);
        if (date2.before(date1)) { //разменяме двете дати, ако първата е след втората
            Date temp = date1;
            date1 = date2;
            date2 = temp;
        }

        // четем всички събития в дадения интервал и ги въвеждаме в ArrayList във формата (събитие, 0)
        Pair<String, Integer> pair;
        ArrayList<Pair<String, Integer>> events = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.Events_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                Date d = simpleDateFormat.parse(parts[0]);
                if (d.after(date1) && d.before(date2)) {
                    //aко събитието е в дадения интервал, го добавяме в списъка
                    pair = new Pair<>(parts[1], 0);
                    events.add(pair);
                }
            }

            reader.close();
        }

        String line;
        // четем файла с билети и броим билетите за дадено събитие
        FileReader fileReader = new FileReader(Constants.Payments_FILE);
        BufferedReader reader1 = new BufferedReader(fileReader);
        while ((line = reader1.readLine()) != null) {
            String[] parts = line.split(" ");
            String e = parts[0];
            Date currentDate = simpleDateFormat.parse(parts[1]);
            if (date1.before(currentDate) && date2.after(currentDate)) //System.out.println(line);
            {
                Integer ind=FindEventNum(events, e);
                events.get(ind).setValue(events.get(ind).getValue()+1);
            }
        }
        reader1.close();

        ShowTicketResults(events);
    }

    /**
     * Извежда справка за продадените билети между две дати в определена зала.
     *
     * @param StartDate начална дата.
     * @param EndDate крайна дата.
     * @param hall зала.
     * @return няма.
     */

    public void ReportPurchasedTickets(String StartDate, String EndDate, Integer hall) throws IOException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.mm.yyyy");
        Date date1 = simpleDateFormat.parse(StartDate);
        Date date2 = simpleDateFormat.parse(EndDate);
        if (date2.before(date1)) { //разменяме двете дати, ако първата е след втората
            Date temp = date1;
            date1 = date2;
            date2 = temp;
        }

        // четем всички събития в дадения интервал и ги въвеждаме в ArrayList във формата (събитие, 0)
        Pair<String, Integer> pair;
        ArrayList<Pair<String, Integer>> events = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.Events_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                Date d = simpleDateFormat.parse(parts[0]);
                Integer h=Integer.parseInt(parts[2]);
                if (d.after(date1) && d.before(date2) && h==hall) {
                    //aко събитието е в дадения интервал и в съответната зала, го добавяме в списъка
                    pair = new Pair<>(parts[1], 0);
                    events.add(pair);
                }
            }

            reader.close();
        }

        String line;
        // четем файла с билети и броим билетите за дадено събитие
        FileReader fileReader = new FileReader(Constants.Payments_FILE);
        BufferedReader reader1 = new BufferedReader(fileReader);
        while ((line = reader1.readLine()) != null) {
            String[] parts = line.split(" ");
            String e = parts[0];
            Date currentDate = simpleDateFormat.parse(parts[1]);
            Integer h=Integer.parseInt(parts[2]);
            if (date1.before(currentDate) && date2.after(currentDate))
            {
                Integer ind=FindEventNum(events, e);
                events.get(ind).setValue(events.get(ind).getValue()+1);
            }
        }
        reader1.close();

        ShowTicketResults(events);
    }
}