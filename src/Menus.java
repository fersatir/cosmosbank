package src;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static src.CustomerServices.*;
import static src.InfoServices.exit;

public class Menus extends OperationsTRY {   //Ana menu
    public static final String W = "\u001B[37m";
    public static final String R = "\u001B[31m";
    public static final String G = "\u001B[32m";
    public static final String Y = "\u001B[33m";
    public static final String B = "\u001B[34m";

    static Scanner scanner = new Scanner(System.in);
    static int count;//default Customerı bir kez üretmek için kullanılan değişken
    static int selection;
    static int customerKey = 999;//id number için tanımlanan değişken
    static Customers currentCustomer;
    static int cardNumber = 2744;

    public static int mapKeyGenerator() {//id üretmek için kullanılan method
        return customerKey++;
    }

    public static String cardNumberGenerator() {
        return "4209 0000 0239" + " " + (cardNumber++);
    }

    static OperationsUSD opusd = new OperationsUSD();
    static OperationsTRY optry = new OperationsTRY();

    static Map<String, Customers> customersMap = new TreeMap<>();//customerID string olduğu için, map'in key'lerini String olarak yazdım

    public static void registerMenu() {

        System.out.println("========================== WELCOME COSMOS BANK =======================\r\n"

                + "   ____________________             ____________________    \n"
                + "   | 1-LOGIN          |             | 2-REGISTER        |   \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯   " + B);

        System.out.print("Please make your choice: ");
        if (count == 0) {
            defaultCustomers();
            count++;
        }

        if (TryCatch.intGirisi() == 1) {
            login();
        } else {
            register();
        }
    }

    public static void mainMenu() { // Kullanıcı login/register olduktan sonraki ekran

        System.out.println("========================== CUSTOMER OPERATION =======================\r\n"

                + "   ____________________             ____________________    \n"
                + "   | 1-CHECK BALANCE |               | 2-WITHDRAWAL     |   \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    \n"
                + "   _____________________             ____________________    \n"
                + "   | 3-DEPOSIT|                      | 4-UPDATE COSTUMER |   \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    \n"
                + "   _____________________            _____________________   \n"
                + "   | 5-FOREIGN CURRENCY|            |6- LOAN TRANSACTION|   \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯            ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯   \n"
                + "   _____________________            _____________________   \n"
                + "   | 7-EXIT            |            | 8- MONEY TRANSFER |   \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯            ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯  " + B);

        System.out.print("Please make your selection: ");
        switch (TryCatch.intGirisi()) {
            case 1:
                optry.balanceInquiry();
                mainMenu();
                break;
            case 2:
                optry.withdrawal();
                mainMenu();
                break;
            case 3:
                optry.deposit();
                break;
            case 4:
                updateCustomer();
                break;
            case 5:
                selectForeignCurrency();
                break;
            case 6:
                creditRatingCheck();
                break;
            case 7:
                exit();
                break;
            case 8:
                optry.moneyTransfer();
                break;
        }


    }//main menu

    public static void selectForeignCurrency() {//Döviz işlemleri menu seçim methodu
        System.out.println("========================== SELECT FOREIGN CURRENCY =======================\r\n"

                + "   ____________________             ____________________    \n"
                + "   | 1-CHECK USD BALANCE|           | 2-BUY USD        |   \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯   \n"
                + "   ____________________             ____________________    \n"
                + "   | 3-SELL USD       |             | 4-SEND USD       |     \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    \n"
                + "   ____________________             ____________________    \n"
                + "   | 5-MAIN MENU      |             |  -----------     |   \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯  " + B);

        System.out.print("Please make your choice: ");


        switch (TryCatch.intGirisi()) {
            case 1:
                opusd.balanceInquiry();
                selectForeignCurrency();
                break;
            case 2:
                opusd.deposit();
                selectForeignCurrency();
                break;
            case 3:
                opusd.withdrawal();
                selectForeignCurrency();
                break;
            case 4:
                opusd.moneyTransfer();
                selectForeignCurrency();
                break;
            case 5:
                mainMenu();
                break;
            default:
                System.out.println("Selected Wrong Transaction Please Try Again.!");
                selectForeignCurrency();
                break;
        }

    }


}

//class
