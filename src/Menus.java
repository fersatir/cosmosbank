package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static src.InfoServices.exit;
import static src.InfoServices.transactionSummary;


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


    static String loginId;//fatih login olan müşterinin id nurasını heryerden ulaşabilmek için oluşturuldu.
    static OperationsUSD opusd = new OperationsUSD();
    static OperationsTRY optry = new OperationsTRY();
   // static Customers currentCustomer; // hangi müşteri işlem yapıyor onu takip etmek için variable

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
        System.out.println(customersMap);


        if (TryCatch.intGirisi() == 1) {
            login();
        } else {
            register();
        }
    }
    static void defaultCustomers() {
        Customers obj = new Customers();
        Customers cstmr1 = new Customers("Elif", "Uzun", "ABc123_z", 34.000, 10000, false, "53721245678", 12000, "elifuzun21@gmail.com", "Ankara");
        Customers cstmr2 = new Customers("Melih", "Öztürk", "ABc123_y", 5.000, 0, false, "50568686868", 7000, "melih2002@hotmail.com", "İstanbul");
        Customers cstmr3 = new Customers("Beyza", "Bilir", "ABc123_v", 65.000, 1500, true, "53156712344", 35000, "bilir27byz@gmail.com", "");
        Customers cstmr4 = new Customers("Mert", "Candan", "ABc123_u", 120.000, 15000, false, "53321229435", 27000, "", "Muğla");
        Customers cstmr5 = new Customers("Deniz", "Sipahi", "ABc123_k", 350.000, 50000, true, "54167889090", 45000, "smilest26@hotmail.com", "Sakarya");
        Customers cstmr6 = new Customers("Ceyda", "Erdinç", "ABc123_m", 75.000, 0, true, "54278999830", 20000, "erdinc_56c@gmail.com", "Samsun");
        Customers cstmr7 = new Customers("Ferit", "Aydoğan", "ABc123_l", 15.750, 0, false, "53233465607", 11000, "aydogan_fert8@gmail.com", "Tokat");
        Customers cstmr8 = new Customers("Ayşe", "Özdil", "ABc123_p", 3.500, 0, false, "54167868733", 85000, "semure45öz@hotmail.com", ",Bolu");
        customersMap.put(cstmr1.getIdNumber(), cstmr1);
        customersMap.put(cstmr2.getIdNumber(), cstmr2);
        customersMap.put(cstmr3.getIdNumber(), cstmr3);
        customersMap.put(cstmr4.getIdNumber(), cstmr4);
        customersMap.put(cstmr5.getIdNumber(), cstmr5);
        customersMap.put(cstmr6.getIdNumber(), cstmr6);
        customersMap.put(cstmr7.getIdNumber(), cstmr7);
        customersMap.put(cstmr8.getIdNumber(), cstmr8);
        // System.out.println(customersMap);

    }
    private static void register() {

        Customers customer1 = new Customers();

        System.out.println("Register screen. Please enter customer's; ");
        System.out.print("Name: ");
        customer1.setName(scanner.nextLine());
        System.out.print("Surname: ");
        customer1.setSurname(scanner.nextLine());
        //System.out.print("ID Number: ");
        //customer1.setIdNumber(scanner.nextLine());
        System.out.print("Password: ");// 1 büyük,1küçük harf,1 rakam kontrolü eklenecek
        customer1.setPassword(scanner.nextLine());
        System.out.print("Previously used credit? Y/N ");//pojoClass'taki setDebit methodunu sadece Y/N girecek şekilde değiştirdim
        customer1.setDebit(scanner.nextLine());
        System.out.print("Phone Number: ");
        customer1.setTelephone(scanner.nextLine());
        System.out.print("Your Monthly Income: ");
        customer1.setMonthlyInCome(TryCatch.intGirisi());

        currentCustomer = customer1; // we now know that this particular customer is using our app


        customersMap.put(customer1.getIdNumber(), customer1);
        System.out.println("New customer has been registered to our system. You are being redirected to the main menu...");
        transactionSummary("Customer Registered","1001");
        TryCatch.threadSleep(1000);
        System.out.println(customersMap);
        mainMenu();


    }

    private static void login() {

        System.out.println("Please enter your ID number: ");
        loginId = scanner.nextLine();

        if (customersMap.containsKey(loginId)) {

            System.out.println("Please enter your Password: ");
            String loginPassword = scanner.nextLine();
            currentCustomer=customersMap.get(loginId);
            if(currentCustomer.getPassword().equals(loginPassword)) {

                System.out.println("Welcome to Cosmos Bank Mr/Mrs " + customersMap.get(loginId).getName() + "! redirecting to the main menu...");
                TryCatch.threadSleep(1000);
                mainMenu();
            }else wrongLogin();

        } else wrongLogin();

    }

    private static void wrongLogin() {
        System.out.println("No such customer exists in our system by the ID you provided.\nPress 1 to register new customer,\n" +
                "Press 2 to try to login again,\nPress 0 to exit login screen: ");

        while (true) { // kullanıcı 1,2 veya 0'dan başka giriş yapmaması için while döngüsüne aldım
            int loginSelect = TryCatch.intGirisi();
            if (loginSelect == 1) {
                register();
                break;
            } else if (loginSelect == 2) {
                login();
                break;
            } else if (loginSelect == 0) {
                registerMenu();
                break;
            } else {
                System.out.println("Invalid selection, please pres 1, 2 or 0 to proceed: ");

            }
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
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯  " +  B);

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


    private static void updateCustomer() {


        System.out.println(G+"<<SELECT THE FIELD TO EDIT>>\n 1) PHONE NUMBER\n 2) E-MAIL\n 3) ADDRESS\n 4) PASSWORD\n ");
        //selection = scanner.nextInt();
        switch( scanner.nextInt()) {
            case 1:
                System.out.println(B+"CURRENT PHONE NUMBER= " + currentCustomer.getTelephone());
                scanner.nextLine();
                System.out.println(B+"NEW PHONE NUMBER");
                currentCustomer.setTelephone(scanner.nextLine());
                System.out.println(R+"PHONE NUMBER IS CHANGED");
                System.out.println(G+"***UPDATED PROFILE***");
                profil();
                while (true) {
                    System.out.println(G+"DO YOU WANT TO CONTINUE? 1) YES 2) MAIN MENU");
                    selection = scanner.nextInt();
                    if (selection == 1) {
                        updateCustomer();
                    } else if (selection == 2) {
                        mainMenu();
                    } else System.out.println((R+"INVALID SELECTION!!"));
                }

            case 2:
                System.out.println(B+"CURRENT EMAIL= " + currentCustomer.getEmail());
                scanner.nextLine();
                System.out.println(B+"NEW E-MAIL");
                currentCustomer.setEmail(scanner.nextLine());
                System.out.println(R+"EMAIL IS CHANGED");
                System.out.println(G+"UPDATED PROFILE ");
                profil();
                while (true) {
                    System.out.println(B+"DO YOU WANT TO CONTINUE? 1) YES 2) MAIN MENU");
                    selection=scanner.nextInt();
                    if (selection == 1) {
                        updateCustomer();
                    } else if (selection == 2) {
                        mainMenu();
                    } else System.out.println((R+"INVALID SELECTION!!"));

                }
            case 3:
                System.out.println(B+"ADDRESS= " + currentCustomer.getAddress());

                System.out.println(B+"NEW ADDRESS");
                scanner.nextLine();
                currentCustomer.setAddress(scanner.nextLine());
                System.out.println(R+"ADDRESS IS CHANGED");
                System.out.println(G+"UPDATED PROFILE");
                profil();
                while (true) {
                 //   scanner.nextLine();
                    System.out.println(B+"DO YOU WANT TO CONTINUE? 1) YES 2) MAIN MENU");
                    selection=scanner.nextInt();
                    if (selection == 1) {
                        updateCustomer();
                    } else if (selection == 2) {
                        mainMenu();
                    } else System.out.println(R+"INVALID SELECTION!!");
                }
            case 4:
                passwordUpdate();

        }


    }

    private static void passwordUpdate() {
        int count1=0;
        scanner.nextLine();
        System.out.println(B+"PLEASE ENTER YOUR OLD PASSWORD");
        if (scanner.nextLine().equals(currentCustomer.getPassword())) {
            passwordControl();
        } else {
            //scanner.nextLine();
            System.out.println(R+"WRONG PASSWORD");
            if (count1 == 3) {
                System.out.println("Şifre giriş hakkınız dolmuştur. Ana Menüye yönlendiriliyorsunuz");
                TryCatch.threadSleep(1000);
                mainMenu();
            } else {
                count1++;
                passwordUpdate();

            }

        }

    }

    private static void passwordControl() {
        System.out.println("PLEASE ENTER NEW PASSWORD");
        String password = scanner.nextLine();
        if (password.length() >= 8) {
            Pattern letter = Pattern.compile("[a-z]");
            Pattern letteR = Pattern.compile("[A-Z]");
            Pattern digit = Pattern.compile("[0-9]");
            //    Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            //Pattern eight = Pattern.compile (".{8}");
            Pattern special = Pattern.compile("[*_.!?-]");

            Matcher hasLetteR = letteR.matcher(password);
            Matcher hasLetter = letter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);

            boolean b = (hasLetteR.find() && hasLetter.find() && hasDigit.find() && hasSpecial.find());

            if (b) {
                System.out.println("<<Your password has been successfully created>>");
                System.out.println("DO YOU WANT TO CONTINUE? 1) YES 2) MAIN MENU");
                selection = scanner.nextInt();
                if (selection == 1) {
                    updateCustomer();
                } else if (selection == 2) {
                    mainMenu();
                } else {
                    System.out.println("WRONG ENTRY");
                }
            } else {
                System.out.println("<<Password must be at least 8 characters long, contain uppercase, lowercase, numbers and special characters!!>>");
                passwordControl();
            }
        }else {
            System.out.println("<<Password must be at least 8 characters long>>");
            passwordControl();
        }

    }


    static void profil() {
        System.out.println("ID\t  NAME\t\t\tSURNAME\t\t  TELEPHONE\t\t  MONTLY INCOME\t\tE-MAIL\t\t\t\t\t  ADDRESS \n=======================================================================================================");

        System.out.printf("%-6S%-14S%-14S%-16S%-18d%-25s %-25S\n", currentCustomer.getIdNumber(), currentCustomer.getName(), currentCustomer.getSurname(), currentCustomer.getTelephone(), currentCustomer.getMonthlyInCome(), currentCustomer.getEmail(), currentCustomer.getAddress());
    }}

//class
