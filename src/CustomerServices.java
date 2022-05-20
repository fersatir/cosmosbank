package src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static src.InfoServices.extractAccount;
import static src.InfoServices.transactionSummary;
import static src.Menus.*;


public class CustomerServices {
   static int count1=0;

    public static void addCustomer() {

    }

    public static void deleteCustomer() {

    }

    public static void swapAccount() {

    }


    public static void updateCustomer() {

        transactionSummary("Customer Information Update",currentCustomer.getIdNumber());
        extractAccount("Customer Information Update",0,currentCustomer.getIdNumber());
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
                transactionSummary("Phone Number Updated",currentCustomer.getIdNumber());
                extractAccount("Phone Number Updated",0,currentCustomer.getIdNumber());
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
                transactionSummary("Email Updated",currentCustomer.getIdNumber());
                extractAccount("Email Updated",0,currentCustomer.getIdNumber());
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
                transactionSummary("Address Updated",currentCustomer.getIdNumber());
                extractAccount("Address Updated",0,currentCustomer.getIdNumber());
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
                scanner.nextLine();
                passwordUpdate();

        }


    }

    private static void passwordUpdate() {
        transactionSummary("Password Update Operations",currentCustomer.getIdNumber());
        extractAccount("Password Update Operations",0,currentCustomer.getIdNumber());
        System.out.println(B+"PLEASE ENTER YOUR OLD PASSWORD");
        if (scanner.nextLine().equals(currentCustomer.getPassword())) {
            passwordControl();
        } else {
            //scanner.nextLine();
            System.out.println(R+"WRONG PASSWORD");
            if (count1 == 2) {
                System.out.println("Şifre giriş hakkınız dolmuştur. Ana Menüye yönlendiriliyorsunuz");
                count1=0;
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
                transactionSummary("Password Updated",currentCustomer.getIdNumber());
                extractAccount("Password Updated",0,currentCustomer.getIdNumber());
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
    }



    public static void defaultCustomer() { //fatih default customer oluşturmak için oluşturuldu silinecek
        Customers customer1 = new Customers("fatih", "erşatır", "1001", "Fe123456", 20000, 0, "n", "12345", 10000);
        Customers customer2 = new Customers("yavuz", "erşatır", "1002", "Fe123456", 20000, 0, "n", "12345", 10000);
        Customers customer3 = new Customers("meltem", "erşatır", "1003", "Fe123456", 20000, 0, "n", "12345", 10000);
        customersMap.put("1001", customer1);
        customersMap.put("1002", customer2);
        customersMap.put("1003", customer3);
    }

    public static void checkBalance() {
        System.out.println("TL hesap bakiyesi"+customersMap.get(loginId).getBalaceTRY());

        transactionSummary("TRY Balance Inquiry", "1001");


    }

    public static void withDraw() {

        transactionSummary("Withdraw Money", "1001");


    }


}
