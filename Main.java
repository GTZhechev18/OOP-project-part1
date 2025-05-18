import java.text.ParseException;
import java.util.Scanner; // To get input from keyboard
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Класът Main класът за главна страница
 *
 *
 * @author Георги Жечев
 * @version 1.0
 * @since 2025-05-05
 */

public class Main {
    public static void main(String[] args) {
        menuLoop();
    }

    private static final int EXIT = 99;

    /**
     * Повторение на менюто до използване командата за край на цикъл.
     *
     * @return след изпълняване на цикъла извежда край на програмата
     */

    private static void menuLoop() {
        int action = getMenuChoice();
        while (action != EXIT) {
            switch (action) {
                case 1: try {
                    displayHallsMenu();
                }
                catch (IOException е) {
                    System.out.println("Грешка");}
                    break;
                case 2: try {
                    displayEventsMenu();
                }
                catch (IOException е) {
                    System.out.println("Грешка");}
                    break;
                case 3: try {
                    displayReservationsMenu();
                }
                catch (IOException е) {
                    System.out.println("Грешка");}
                    break;
                case 4: displayPurchaseMenu(); break;
                case 5: displayReportMenu(); break;
                }
            action = getMenuChoice();
        }
        System.out.println("Край на програмата!");
    }


    private static int getMenuChoice() {
        int choice = EXIT;
    System.out.println("Моля изберете една от следните възможности");
    System.out.println("1. Зали");
    System.out.println("2. Представления");
    System.out.println("3. Резервации");
    System.out.println("4. Покупки");
    System.out.println("5. Справки");
    System.out.println("6. Изход");
    System.out.print("Вашият избор:");
        Scanner myObj = new Scanner(System.in);
        choice = myObj.nextInt();
        if (choice==6) choice=EXIT;
        return choice;
    }

    private static void displayHallsMenu() throws IOException {
        int ch=0;
        do {
            System.out.println("Моля изберете една от следните възможности за зали");
            System.out.println("1. Отваряне");
            System.out.println("2. Затваряне");
            System.out.println("3. Запазване като");
            System.out.println("4. Помощ");
            System.out.println("5. Изход");
            System.out.print("Вашият избор:");
            Scanner myObj = new Scanner(System.in);
            ch = myObj.nextInt();
            FileHandler fileHandler = new FileHandler();

            switch (ch){
                case 1: {
                    try {
                        fileHandler.openForRead(Constants.Halls_FILE);
                        String content = fileHandler.read();
                        System.out.println("Съдържание на файла:\n" + content);
                      //  break;
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                       // break;
                    }
                    break;
                }
                case 2:  fileHandler.close();
                    System.out.println("Файлът е затворен!");
                         break;
                case 3: myObj = new Scanner(System.in);
                        System.out.print("Въведете ново име на файла:");
                        String newName = myObj.nextLine();
                        fileHandler.FileSaveAs(Constants.Halls_FILE,newName);
                        break;
                case 4: ShowHelp (); break;
                default: System.out.println("Грешен избор");
            }  //while (ch!=5);

            //if (ch==5) choice=EXIT;
        }while (ch!=5);
    }

    /**
     * Помощно меню.
     *
     * @return няма
     */

    private static void ShowHelp()
    {
        System.out.println("Опция 1. Отваря файл и извежда съдържанието му на екрана");
        System.out.println("Опция 2. Затваря файл");
        System.out.println("Опция 3. Прави копие с ново име");
        System.out.println("Опция 4. Помощ");
        System.out.println("Изход към главно ниво");
    }
    /**
     * DisplayEvents меню.
     *
     * @return няма
     */
    private static void displayEventsMenu() throws IOException{
        int ch=0;
        do {
            System.out.println("Моля изберете една от следните възможности за представления");
            System.out.println("1. Отваряне");
            System.out.println("2. Затваряне");
            System.out.println("3. Запазване като");
            System.out.println("4. Добавяне на ново събитие");
            System.out.println("5. Помощ");
            System.out.println("6. Изход");
            Scanner myObj = new Scanner(System.in);
            ch = myObj.nextInt();
            FileHandler fileHandler = new FileHandler();

            switch (ch) {
                case 1:
                    try {
                        fileHandler.openForRead(Constants.Events_FILE);
                        String content = fileHandler.read();
                        System.out.println("Съдържание на файла:\n" + content);
                        //  break;
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                        // break;
                    }
                    break;
                case 2:
                    try {
                        fileHandler.close();
                        System.out.println("Файлът е затворен!");
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                        // break;
                    }
                    break;
                case 3:
                    try {
                        myObj = new Scanner(System.in);
                        System.out.print("Въведете ново име на файла:");
                        String newName = myObj.nextLine();
                        fileHandler.FileSaveAs(Constants.Halls_FILE, newName);
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                        // break;
                    }
                    break;
                case 4:
                    try {
                        Event e = new Event();
                        myObj = new Scanner(System.in);
                        System.out.print("Въведете ново име на събитие:");
                        String newEvent = myObj.nextLine();
                        System.out.print("Въведете дата на новото събитие:");
                        String newDate = myObj.nextLine();
                        System.out.print("Въведете зала на новото събитие:");
                        Integer newHall = myObj.nextInt();
                        if (e.SearchEvent(newDate,newHall)) System.out.println("На тази дата в тази зала вече има събитие!");
                           else {
                            e.AddEvent(newDate, newHall, newEvent);
                            System.out.println("Събитието е добавено!");
                        }
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                         }
                    break;
                case 5:
                    ShowHelp();
                    break;
                default:
                    System.out.println("Грешен избор");
            }
        }while (ch!=7);
    }

    /**
     * displayReservationsMenu меню.
     *
     * @return няма
     */

    private static void displayReservationsMenu()throws IOException {
        int ch=0;
        do {
            System.out.println("Моля изберете една от следните възможности");
            System.out.println("1. Отваряне");
            System.out.println("2. Затваряне");
            System.out.println("3. Запазване като");
            System.out.println("4. Добавяне на резервация");
            System.out.println("5. Изтриване на резервация");
            System.out.println("6. Помощ");
            System.out.println("7. Изход");
            Scanner myObj = new Scanner(System.in);
            ch = myObj.nextInt();
            FileHandler fileHandler = new FileHandler();

            switch (ch) {
                case 1:
                    try {
                        fileHandler.openForRead(Constants.Bookings_FILE);
                        String content = fileHandler.read();
                        System.out.println("Съдържание на файла:\n" + content);
                        //  break;
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                        // break;
                    }
                    break;
                case 2:
                    try {
                        fileHandler.close();
                        System.out.println("Файлът е затворен!");
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                        // break;
                    }
                    break;
                 case 3:
                    try {
                        myObj = new Scanner(System.in);
                        System.out.print("Въведете ново име на файла:");
                        String newName = myObj.nextLine();
                        fileHandler.FileSaveAs(Constants.Bookings_FILE, newName);
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                        // break;
                    }
                    break;
                case 4:
                    try {
                        Booking booking = new Booking();
                        myObj = new Scanner(System.in);
                        System.out.print("Въведете име на събитие за резервация:");
                        String e = myObj.nextLine();
                        System.out.print("Въведете дата на резервацията:");
                        String d = myObj.nextLine();
                        System.out.print("Въведете ред на новото събитие:");
                        Integer row = myObj.nextInt();
                        System.out.print("Въведете място на новото събитие:");
                        Integer seat = myObj.nextInt();
                        System.out.println("Въведете бележка за резервацията:");
                        String note = myObj.nextLine();
                        if (booking.SearchBooking(e,d, row, seat, note))
                            System.out.println("Тази резервация вече е записана!");
                        else {
                            booking.AddBook(row, seat, d, e, note);
                            System.out.println("Резервацията е добавена!");
                        }
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                    }
                    break;
                case 5:
                    try {
                        Booking booking = new Booking();
                        myObj = new Scanner(System.in);
                        System.out.print("Въведете име на събитие за резервация:");
                        String e = myObj.nextLine();
                        System.out.print("Въведете дата на резервацията:");
                        String d = myObj.nextLine();
                        System.out.print("Въведете ред на новото събитие:");
                        Integer row = myObj.nextInt();
                        System.out.print("Въведете място на новото събитие:");
                        Integer seat = myObj.nextInt();
                        System.out.println("Въведете бележка за резервацията:");
                        String note = myObj.nextLine();
                        //      if (booking.SearchBooking(e,d, row, seat, note))
                        //          System.out.println("Тази резервация вече е записана!");
                        //     else {
                        booking.UnBook(e,row, seat, d);
                        System.out.println("Резервацията е изтрита!");
                        //    }
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                    }
                    break;

                case 6: ShowHelp();
                    break;
                default:
                    System.out.println("Грешен избор");
            }
        }
            while (ch != 7) ;
        }

    /**
     * displayPurchaseMenu меню.
     *
     * @return няма
     */

    private static void displayPurchaseMenu() {
        int ch = 0;
        do {
            System.out.println("Моля изберете една от следните възможности за представления");
            System.out.println("1. Отваряне");
            System.out.println("2. Затваряне");
            System.out.println("3. Запазване като");
            System.out.println("4. Добавяне на нова покупка");
            System.out.println("5. Проверка на код");
            System.out.println("6. Помощ");
            System.out.println("7. Изход");
            System.out.println("Вашият избор:");
            Scanner myObj = new Scanner(System.in);
            ch = myObj.nextInt();
            FileHandler fileHandler = new FileHandler();

            switch (ch){
                case 1:
                    try {
                        fileHandler.openForRead(Constants.Payments_FILE);
                        String content = fileHandler.read();
                        System.out.println("Съдържание на файла:\n" + content);
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                    }
                    break;
                case 2:
                    try {
                     fileHandler.close();
                      System.out.println("Файлът е затворен!");
                     } catch (IOException е) {
                    //e.printStackTrace();
                    System.out.println("Грешка");
                }
                break;
                case 3:
                    try {
                        myObj = new Scanner(System.in);
                        System.out.print("Въведете ново име на файла:");
                        String newName = myObj.nextLine();
                        fileHandler.FileSaveAs(Constants.Payments_FILE, newName);
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                    }
                    break;
                case 4:
                    try {
                        Purchase p = new Purchase();
                        myObj = new Scanner(System.in);
                        System.out.print("Въведете име на събитие:");
                        String newEvent = myObj.nextLine();
                        System.out.print("Въведете дата на събитие:");
                        String newDate = myObj.nextLine();
                        System.out.print("Въведете ред на събитие:");
                        Integer newRow = myObj.nextInt();
                        System.out.print("Въведете място на събитие:");
                        Integer newSeat = myObj.nextInt();

                        p.PurchaseTicket(newEvent, newDate, newRow, newSeat);
                        System.out.println("Билетът е закупен!");
                    } catch (IOException е) {

                        //e.printStackTrace();
                        System.out.println("Грешка");
                    }
                    break;
                case 5:
                    Purchase p=new Purchase();
                    myObj = new Scanner(System.in);
                    System.out.print("Въведете код за проверка:");
                    String newCode = myObj.nextLine();
                    if(p.CheckCode(newCode)>0) System.out.println("Кодът е валиден");
                    else System.out.println("Кодът е невалиден");

            }

        }
        while(ch!=7);
    }

    /**
     * displayReportMenu меню.
     *
     * @return няма
     */

    private static void displayReportMenu() {
        int ch = 0;
        do {
            System.out.println("Моля изберете една от следните възможности за справки");
            System.out.println("1. Търсене на зала");
            System.out.println("2. Справка за свободни места");
            System.out.println("3. Справка за резервирани, но незакупени билети");
            System.out.println("4. Справка за закупени билети в даден период");
            System.out.println("5. Помощ");
            System.out.println("6. Изход");
            System.out.println("Вашият избор:");
            Scanner myObj = new Scanner(System.in);
            ch = myObj.nextInt();

            switch (ch) {
                case 1:
                    try{
                    Report r=new Report();
                    myObj = new Scanner(System.in);
                    System.out.print("Въведете име на събитие:");
                    String event = myObj.nextLine();
                    System.out.print("Въведете дата на събитие:");
                    String date = myObj.nextLine();
                    Integer hall=r.FindHall(event, date);
                    if (hall>-1) System.out.println("Събитието ще се проведе в зала "+hall);
                        else System.out.println("Не е намерено такова събитие на тази дата!");
                    break;
                    } catch (IOException е) {

                        //e.printStackTrace();
                        System.out.println("Грешка");
                    }
                case 2:
                    try{
                        Report r=new Report();
                        myObj = new Scanner(System.in);
                        System.out.print("Въведете име на събитие:");
                        String event = myObj.nextLine();
                        System.out.print("Въведете дата на събитие:");
                        String date = myObj.nextLine();
                        r.ReportFreeSeats(event,date);
                        break;
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                    }
                case 3:
                    try{
                        Report b=new Report();
                        myObj = new Scanner(System.in);
                        System.out.print("Въведете име на събитие:");
                        String event = myObj.nextLine();
                        System.out.print("Въведете дата на събитие (или празно, ако не желаете):");
                        String date = myObj.nextLine();
                        if (date.equals("") ) b.ReportReservedUnpayedBookings(event);
                        else if (event.equals("")) b.ReportReservedUnpayedBookings(date);
                        else b.ReportReservedUnpayedBookings(event,date);

                        break;
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка");
                    }
                    break;
                case 4:
                    try{
                        Report b=new Report();
                        myObj = new Scanner(System.in);
                        System.out.print("Въведете начална дата:");
                        String date1 = myObj.nextLine();
                        System.out.print("Въведете крайна дата:");
                        String date2 = myObj.nextLine();
                        System.out.print("Въведете зала или 0 ако не желаете:");
                        Integer hall = myObj.nextInt();
                        if (hall==0) b.ReportPurchasedTickets(date1, date2);
                            else b.ReportPurchasedTickets(date1, date2, hall);

                        break;
                    } catch (IOException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка във файловете");
                    }catch (ParseException е) {
                        //e.printStackTrace();
                        System.out.println("Грешка в данните");
                    }
                    break;
                case 5: ShowHelp();
                    break;
                default:
                    System.out.println("Грешен избор");
            }

        } while (ch != 6) ;

    }
}
