import java.io.*;

public class Purchase{

    public void PurchaseTicket (String name, String date, Integer row, Integer seat) throws IOException{
        FileHandler fileHandler = new FileHandler();
        fileHandler.openForAppend(Constants.Payments_FILE);
        //FileWriter fileWriter = new FileWriter(Constants.Bookings_FILE);
        //BufferedWriter writer = new BufferedWriter(fileWriter);
     //   UniqueCodeGenerator uc=new UniqueCodeGenerator();
        String code=UniqueCodeGenerator.generateCode(name, date, row, seat);
        String line= name+" "+date+" "+row.toString()+" "+seat.toString()+" "+code+"\n";
        fileHandler.append(Constants.Payments_FILE,line);
        fileHandler.close();
    }

    public int CheckCode (String code){
        String[] res= UniqueCodeGenerator.extractOriginalData(code);
        int seat=Integer.parseInt(res[3]);
        if (seat>0 && seat<200)  //валидно място
            return seat;
        return -1;
    }
}