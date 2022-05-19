package src;


import java.util.Map;
import java.util.Scanner;

import static src.InfoServices.transactionSummary;
import static src.Menus.currentCustomer;
import static src.Menus.*;


public class OperationsTRY implements Operations{

    Scanner scan = new Scanner(System.in);

    public static void payments(){

    }

    public static void creditsTransaction(){//Sadece TL için kredi, bunu diğer döviz
                                           // cinsleri için de ayırabiliriz.
        System.out.println("========================== CREDIT TRANSACTIONS =======================\r\n"

                + "   ____________________             ____________________    \n"
                + "   | 1-MORTGAGE       |             | 2-AUTO LOAN      |    \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯             ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    \n"
                + "   _____________________            ____________________    \n"
                + "   | 3-CONSUMER LOAN   |            | 4-LOAN REPAYMENT  |   \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯            ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    \n" + B);

        switch(TryCatch.intGirisi()){
            case 1:
                Credits obj0 = new Mortgage();
                obj0.kredi();
                break;
            case 2:
                Credits obj = new AutoLoan();
                 obj.kredi();
                break;
            case 3:
                Credits obj1 = new ConsumerLoan();
                obj1.kredi();
                break;
            case 4:
                Credits obj2 = new LoanRepayment();
                obj2.kredi();
                break;
            default:
                System.out.println("Invalid credit selection please try again");
                TryCatch.threadSleep(1000);
                creditsTransaction();

        }


    }//creditsTransaction();


    public static void creditRatingCheck() {//checks if the customer, who applied for new credit, have previously unpaid loans
        if(currentCustomer.isDebit()){
            System.out.println("You are not suitable to use credit since it seems you currently have unpaid loan: \n" +
                    currentCustomer.getPreviousDebit()+
                    ". If you wish to proceed, you must close your previous loan with your 'CURRENT BALANCE'\n" +
                    "Press 1 to proceed\nPress 0 to exit");
            int selection = TryCatch.intGirisi();
            if(selection==1){

                double currBalanceTRY = currentCustomer.getBalaceTRY();
                double prevDebit = currentCustomer.getPreviousDebit();

                if(currBalanceTRY >= prevDebit){
                    currentCustomer.setBalaceTRY(currBalanceTRY-prevDebit);
                } else {
                    System.out.println("You have insufficient funds to pay your previous credit loan. Redirecting to the main menu...");
                    TryCatch.threadSleep(1000);
                    mainMenu();
                }

            } else {
                mainMenu();
            }
        }
        else {// müşterinin ödenmemiş kredi borcu YOKSA
            creditsTransaction();
        }
    }


    @Override
    public void balanceInquiry() {

        String customerCardNumber = currentCustomer.getCardNumber();

        String secretCardNumber = customerCardNumber.substring(0, customerCardNumber.length() - 4).replaceAll("\\d", "*");

        System.out.println("There is a balance of " + currentCustomer.getBalaceTRY() + " TL" + " in your TL account number "
                + secretCardNumber + customerCardNumber.
                substring(customerCardNumber.length() - 4) + "");

        transactionSummary("TRY Balance Inquiry", currentCustomer.getIdNumber());

        TryCatch.threadSleep(2000);
        mainMenu();

    }

    @Override
    public void withdrawal() {

        System.out.println("Please enter the amount you want to withdraw");
        int amountOfWithdraw = scan.nextInt();
        int selection = 0;
        if (currentCustomer.getBalaceTRY() < amountOfWithdraw || amountOfWithdraw <= 0) {
            System.out.println(" your balance is not enough or invalid choice \npress 1 to re-enter \npress 2 to return to the main menu");
            selection = TryCatch.intGirisi();
            if (selection == 1) {
                withdrawal();
            } else if (selection == 2) {
                mainMenu();
            } else {
                System.out.println("invalid choice");
                mainMenu();
            }
        } else {
            currentCustomer.setBalaceTRY(currentCustomer.getBalaceTRY() - amountOfWithdraw);
            System.out.println("transaction completed successfully");
            System.out.println("your new balance : " + currentCustomer.getBalaceTRY());
        }

        transactionSummary("Withdraw Money", currentCustomer.getIdNumber());
    }

    @Override
    public void deposit() {

        System.out.println("Please enter the amount you want to deposit");
        int amountOfDeposit = TryCatch.intGirisi();
        int selection = 0;
        if (amountOfDeposit <= 0) {
            System.out.println("Invalid choice \npress 1 to re-enter \npress 2 to return to the main menu");
            selection = TryCatch.intGirisi();
            if (selection == 1) {
                deposit();
            } else if (selection == 2) {
                mainMenu();
            } else {
                System.out.println("invalid choice");
                mainMenu();
            }
        } else {
            currentCustomer.setBalaceTRY(currentCustomer.getBalaceTRY() + amountOfDeposit);
            System.out.println("transaction completed successfully");
            System.out.println("your new balance : " + currentCustomer.getBalaceTRY());
            mainMenu();
        }
    }



    @Override
    public void moneyTransfer() {

        String accountSelection = null;
        Customers transferCustomer = null;
        boolean account = false;
        int sendAmount = 0;
        int customerSelection = 0;

        System.out.println(" If the person you want to send money to is a Cosmos Bank customer, \nclick 1 to send money quickly," +
                        "\nor click 2 if another bank customer. \nor click 3 to back to main menu");
        customerSelection = TryCatch.intGirisi();


        if (customerSelection == 1) {
            System.out.println("Please enter the account number you want to send:");
            accountSelection = scan.nextLine();

            for(Map.Entry<String, Customers> entry : customersMap.entrySet()){
                if (entry.getValue().getCardNumber().equals(accountSelection)){
                    transferCustomer = entry.getValue();
                    account = true;
                }
            }

            if (account) {
                System.out.println("Enter the amount you want to send");
                sendAmount = TryCatch.intGirisi();

                if (sendAmount >= 0 && currentCustomer.getBalaceTRY() >= sendAmount) {
                    currentCustomer.setBalaceTRY(currentCustomer.getBalaceTRY() - sendAmount - 1);
                    transferCustomer.setBalaceTRY(transferCustomer.getBalaceTRY() + sendAmount);
                    System.out.println("transaction completed successfully");
                    System.out.println("your new balance : " + currentCustomer.getBalaceTRY());
                    mainMenu();
                } else {
                    System.out.println("Invalid amount. Redirecting Money Transfer main menu");
                    moneyTransfer();
                }
            } else {
                System.out.println("Invalid Account Number. Redirecting Money Transfer main menu");
                moneyTransfer();
            }

        } else if (customerSelection == 2) {
            System.out.println("Please enter the account number you want to send:");
            accountSelection = scan.nextLine();
            System.out.println("Enter the amount you want to send");
            sendAmount = TryCatch.intGirisi();
            if (sendAmount >= 0 && currentCustomer.getBalaceTRY() >= sendAmount) {
                currentCustomer.setBalaceTRY(currentCustomer.getBalaceTRY() - sendAmount - 2);
                System.out.println("transaction completed successfully");
                System.out.println("your new balance : " + currentCustomer.getBalaceTRY());
                mainMenu();
            } else {
                System.out.println("Invalid amount. Redirecting Money Transfer main menu");
                moneyTransfer();
            }

        } else if (customerSelection == 3) {
            mainMenu();
        }
    }

}
