package src;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static src.InfoServices.transactionSummary;
import static src.Menus.*;

public class CustomerServices {
    public static int count1 = 0;

    public static void updateCustomer() {

        System.out.println(G + "<<SELECT THE FIELD TO EDIT>>\n 1) PHONE NUMBER\n 2) E-MAIL\n 3) ADDRESS\n 4) PASSWORD\n 5) MAIN MENU "+B);
        //selection = scanner.nextInt();
        switch (scanner.nextInt()) {
            case 1:
                System.out.println(B + "CURRENT PHONE NUMBER= " + currentCustomer.getTelephone());
                scanner.nextLine();
                System.out.println(B + "NEW PHONE NUMBER");
                currentCustomer.setTelephone(scanner.nextLine());
                System.out.println(R + "PHONE NUMBER IS CHANGED");
                transactionSummary("Phone Number Is Changed", currentCustomer.getIdNumber());
                System.out.println(G + "***UPDATED PROFILE***");
                profil();
                while (true) {
                    customerContinue();
                    selection = scanner.nextInt();
                    if (selection == 1) {
                        updateCustomer();
                    } else if (selection == 2) {
                        mainMenu();
                    } else System.out.println((R + "INVALID SELECTION!!"));
                }

            case 2:
                System.out.println(B + "CURRENT EMAIL= " + currentCustomer.getEmail());
                scanner.nextLine();
                System.out.println(B + "NEW E-MAIL");
                currentCustomer.setEmail(scanner.nextLine());
                System.out.println(R + "EMAIL IS CHANGED");
                transactionSummary("Email Is Changed", currentCustomer.getIdNumber());
                System.out.println(G + "UPDATED PROFILE ");
                profil();
                while (true) {
                    customerContinue();
                    selection = scanner.nextInt();
                    if (selection == 1) {
                        updateCustomer();
                    } else if (selection == 2) {
                        mainMenu();
                    } else System.out.println((R + "INVALID SELECTION!!"));

                }
            case 3:
                System.out.println(B + "ADDRESS= " + currentCustomer.getAddress());

                System.out.println(B + "NEW ADDRESS");
                scanner.nextLine();
                currentCustomer.setAddress(scanner.nextLine());
                System.out.println(R + "ADDRESS IS CHANGED");
                transactionSummary("Address Is Changed", currentCustomer.getIdNumber());
                System.out.println(G + "UPDATED PROFILE");
                profil();
                while (true) {
                    customerContinue();
                    selection = scanner.nextInt();
                    if (selection == 1) {
                        updateCustomer();
                    } else if (selection == 2) {
                        mainMenu();
                    } else System.out.println(R + "INVALID SELECTION!!");
                }
            case 4:
                passwordUpdate();
            case 5:
                mainMenu();
        }
    }

    public static void passwordUpdate() {
        transactionSummary("Password Update", currentCustomer.getIdNumber());
        int count1 = 0;
        scanner.nextLine();
        System.out.println(B + "PLEASE ENTER YOUR OLD PASSWORD");
        if (scanner.nextLine().equals(currentCustomer.getPassword())) {
            passwordControl();
        } else {
            //scanner.nextLine();
            System.out.println(R + "WRONG PASSWORD");
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

    public static void passwordControl() {
        System.out.println("PLEASE ENTER NEW PASSWORD");
        String password = scanner.nextLine();
        if (password.length() >= 8) {
            Pattern letter = Pattern.compile("[a-zA-Z0-9*_.!?-]");//tüm regex işlemlerini tek satırda topladım.

            Matcher hasLetter = letter.matcher(password);

            boolean b = (hasLetter.find());

            if (b) {
                System.out.println("<<Your password has been successfully created>>");
                transactionSummary("Password Is Created", currentCustomer.getIdNumber());
                customerContinue();
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
        } else {
            System.out.println("<<Password must be at least 8 characters long>>");
            passwordControl();
        }

    }

    public static void profil() {
        System.out.println("ID\t  NAME\t\t\tSURNAME\t\t  TELEPHONE\t\t  MONTLY INCOME\t\tE-MAIL\t\t\t\t\t  ADDRESS \n=======================================================================================================");

        System.out.printf("%-6S%-14S%-14S%-16S%-18d%-25s %-25S\n", currentCustomer.getIdNumber(), currentCustomer.getName(), currentCustomer.getSurname(), currentCustomer.getTelephone(), currentCustomer.getMonthlyInCome(), currentCustomer.getEmail(), currentCustomer.getAddress()+B);
    }

    public static void defaultCustomers() {
        Customers obj = new Customers();
        Customers cstmr1 = new Customers("Elif", "Uzun", "ABc123_z", 34000, 10000, false, "53721245678", 12000, "elifuzun21@gmail.com", "Ankara");
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
    }

    public static void register() {

        Customers customer1 = new Customers();

        System.out.println("Register screen. Please enter customer's; ");
        System.out.print("Name: ");
        customer1.setName(scanner.nextLine());
        System.out.print("Surname: ");
        customer1.setSurname(scanner.nextLine());
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
        transactionSummary("Customer Registered", currentCustomer.getIdNumber());
        TryCatch.threadSleep(1000);
        mainMenu();
    }

    public static void login() {

        System.out.println("Please enter your ID number: ");
        String loginId = scanner.nextLine();

        if (customersMap.containsKey(loginId)) {

            System.out.println("Please enter your Password: ");
            String loginPassword = scanner.nextLine();
            currentCustomer = customersMap.get(loginId);
            if (currentCustomer.getPassword().equals(loginPassword)) {
                transactionSummary("Customer Login", currentCustomer.getIdNumber());
                System.out.println("Welcome to Cosmos Bank Mr/Mrs " + customersMap.get(loginId).getName() + "! redirecting to the main menu...");
                TryCatch.threadSleep(1000);
                mainMenu();
            } else wrongLogin();

        } else wrongLogin();

    }

    public static void wrongLogin() {
        transactionSummary("Customer Login Failed",currentCustomer.getIdNumber());
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

    public static void customerContinue(){ // customer işlemlerinde sürekli çağırmak için buraya alındı, kod tekrarını engelleme amacıyla
        System.out.println(B+"DO YOU WANT TO CONTINUE? 1) YES 2) MAIN MENU");
    }
}
