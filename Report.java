import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Report {

    Integer FindHall(String event, String date) throws IOException {

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
    
   // Integer CountSeats(String AllLines)
   static int countFreq(String pat, String txt)
   {
       int M = pat.length();
       int N = txt.length();
       int res = 0;

       /* A loop to slide pat[] one by one */
       for (int i = 0; i <= N - M; i++) {
           /* For current index i, check for
       pattern match */
           int j;
           for (j = 0; j < M; j++) {
               if (txt.charAt(i + j) != pat.charAt(j)) {
                   break;
               }
           }

           // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
           if (j == M) {
               res++;
               j = 0;
           }
       }
       return res;
   }

    void ReportFreeSeats(String event, String date) throws IOException{

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
}