package src;



import java.util.Map;
import java.util.Scanner;

import static src.InfoServices.extractAccount;
import static src.InfoServices.transactionSummary;
import static src.Menus.*;

public class OperationsUSD implements Operations {

    Scanner scan = new Scanner(System.in);

    @Override
    public void balanceInquiry() {
        transactionSummary("USD Balance Inquiry", loginId);
        String customerCardNumber = currentCustomer.getCardNumber();

        String secretCardNumber = customerCardNumber.substring(0, customerCardNumber.length() - 4).replaceAll("\\d", "*");

        System.out.println("There is a balance of " + currentCustomer.getBalanceUSD() + " $" + " in your USD account number "
                + secretCardNumber + customerCardNumber.
                substring(customerCardNumber.length() - 4) + "");

        TryCatch.threadSleep(2000);
        //mainMenu();
    }

    @Override
    public void withdrawal() {
        transactionSummary("Sell USD", loginId);


        System.out.print("Please Enter Sell USD Quantity : ");
        double enterUsd = TryCatch.doubleGirisi();// Satmak istediği usd miktari kullanıcıdan isteniyor.
        double quantityTry = enterUsd * USD;// static final olan USD güncel kur bilgisi ile satmak istenilen USD çarpma yapılıyor ve kaç TL olduğu hesap ediliyor.


        if (customersMap.get(loginId).getBalanceUSD() >= enterUsd) { // usd hesabındaki miktar satmak için girilen miktardan büyük mü kontrol yapılıyor.
            customersMap.get(loginId).setBalanceUSD(customersMap.get(loginId).getBalanceUSD() - enterUsd);//usd hesabına yeni miktar set ediliyor.
            customersMap.get(loginId).setBalaceTRY(customersMap.get(loginId).getBalaceTRY()+quantityTry);
            System.out.println("Sell: "+ enterUsd+ " $");
            System.out.println("There is a balance of " + currentCustomer.getBalanceUSD() + "$");

            extractAccount("Sell USD",enterUsd,loginId);
        }else {
            System.out.println("Your balance is not enough \npress 1 to re-enter \npress 2 to return to the main menu");
            if (TryCatch.intGirisi() == 1) {
                withdrawal();
            } else {
                selectForeignCurrency();
            }
        }
    }

    @Override
    public void deposit() {
        transactionSummary("Buy USD", loginId);

        System.out.print("Please Enter Buy USD Quantity : ");
        double enterUsd = TryCatch.doubleGirisi();// almak istediği usd miktari kullanıcıdan isteniyor.
        double quantityTry = enterUsd * USD;// static final olan USD güncel kur bilgisi ile almak istenilen USD çarpma yapılıyor kaç TL ihtiyaç var hesaplama yapılıyor

        if (customersMap.get(loginId).getBalaceTRY() >= quantityTry) { // try hesabındaki miktar usd alabilmek için yeterli ise kontrolü yapılıyor
            customersMap.get(loginId).setBalaceTRY(customersMap.get(loginId).getBalaceTRY() - quantityTry);//usd alabilmek için yeterli olan try hesabına yeni bakiye set ediliyor.
            customersMap.get(loginId).setBalanceUSD(customersMap.get(loginId).getBalanceUSD()+enterUsd);// usd hesabına para eklendi.
            System.out.println("Buy: "+ enterUsd+ " $");
            System.out.println("There is a balance of " + currentCustomer.getBalanceUSD() + "$");

            extractAccount("Buy USD",enterUsd,"1001");
        }else {
            System.out.println("Your balance is not enough \npress 1 to re-enter \npress 2 to return to the main menu");
            if (TryCatch.intGirisi() == 1) {
                deposit();

            } else {
                selectForeignCurrency();
            }
        }



    }

    @Override
    public void moneyTransfer() {
        transactionSummary("USD Transfer", loginId);

        String accountSelection = "";
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

                if (sendAmount >= 0 && currentCustomer.getBalanceUSD() >= sendAmount) {
                    currentCustomer.setBalanceUSD(currentCustomer.getBalanceUSD() - sendAmount - 3);
                    transferCustomer.setBalanceUSD(transferCustomer.getBalanceUSD() + sendAmount);
                    System.out.println("transaction completed successfully");
                    System.out.println("your new balance : " + currentCustomer.getBalanceUSD());
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
            if (sendAmount >= 0 || currentCustomer.getBalaceTRY() >= sendAmount) {
                currentCustomer.setBalaceTRY(currentCustomer.getBalaceTRY() - sendAmount - 5);
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
