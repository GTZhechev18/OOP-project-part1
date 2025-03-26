import java.util.Scanner; // To get input from keyboard
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        menuLoop();
    }

    private static final int EXIT = 99;

    private static void menuLoop() {
        int action = getMenuChoice();
        while (action != EXIT) {
            dispatch(action);
            action = getMenuChoice();
        }
        System.out.println("Край на програмата!");
    }

    private static void dispatch(int action) {
        switch (action) {
            case 1: try {
                displayHallsMenu();
            }
            catch (IOException е) {
                System.out.println("Грешка");}
                break;
            case 2: displayEventsMenu(); break;
            case 3: displayReservationsMenu(); break;
            case 4: displayPurchaseMenu(); break;
            case 5: displayCheckMenu(); break;
            //case 6: return;
        }
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
            System.out.println("3. Запазване");
            System.out.println("4. Запазване като");
            System.out.println("5. Помощ");
            System.out.println("6. Изход");
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
                case 3:
                case 4: myObj = new Scanner(System.in);
                        System.out.print("Въведете ново име на файла:");
                        String newName = myObj.nextLine();
                        fileHandler.FileSaveAs(Constants.Halls_FILE,newName);
                        break;
                case 5: ShowHelp (); break;
                default: System.out.println("Грешен избор");
            }  //while (ch!=6);

            //if (ch==6) choice=EXIT;
        }while (ch!=6);
    }

    private static void ShowHelp()
    {
        System.out.println("Опция 1. Отваря файл и извежда съдържанието му на екрана");
        System.out.println("Опция 2. Затваря файл");
        System.out.println("Опция 4. Прави копие с ново име");
        System.out.println("Опция 5. Помощ");
        System.out.println("Изход към главно ниво");
    }
    private static void displayEventsMenu() {
        System.out.println("Моля изберете една от следните възможности за представления");
        System.out.println("1. Отваряне");
        System.out.println("2. Затваряне");
        System.out.println("3. Запазване");
        System.out.println("4. Запазване като");
        System.out.println("5. Помощ");
        System.out.println("6. Изход");
    }

    private static void displayReservationsMenu() {
        System.out.println("Моля изберете една от следните възможности");
        System.out.println("1. Отваряне");
        System.out.println("2. Затваряне");
        System.out.println("3. Запазване");
        System.out.println("4. Запазване като");
        System.out.println("5. Помощ");
        System.out.println("6. Изход");
    }

    private static void displayPurchaseMenu() {
        System.out.println("Моля изберете една от следните възможности за покупки");
        System.out.println("1. Отваряне");
        System.out.println("2. Затваряне");
        System.out.println("3. Запазване");
        System.out.println("4. Запазване като");
        System.out.println("5. Помощ");
        System.out.println("6. Изход");
    }

    private static void displayCheckMenu() {
        System.out.println("Моля изберете една от следните възможности за справки");
        System.out.println("1. Отваряне");
        System.out.println("2. Затваряне");
        System.out.println("3. Запазване");
        System.out.println("4. Запазване като");
        System.out.println("5. Помощ");
        System.out.println("6. Изход");
    }
}
