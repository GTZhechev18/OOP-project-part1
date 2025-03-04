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
        sayGoodbye();
    }

    private static int getMenuChoice() {
        int choice = EXIT;

        // prompt user for menu choice
    ...

        return choice;
    }

    private static void dispatch(int action) {
        switch (action) {
            case 1: displayList(); break;
            case 2: ...
        }
    }

    private static void displayList() {
    System.out.println("Моля изберете една от следните възможности");
    System.out.println("1. Зали");
    System.out.println("2. Представления");
    System.out.println("3. Резервации");
    System.out.println("4. Покупки");
    System.out.println("5. Справки");
    System.out.println("6. Изход");
    }

    private static void displayHallsMenu() {
        System.out.println("Моля изберете една от следните възможности за зали");
        System.out.println("1. Отваряне");
        System.out.println("2. Затваряне");
        System.out.println("3. Запазване");
        System.out.println("4. Запазване като");
        System.out.println("5. Помощ");
        System.out.println("6. Изход");
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
