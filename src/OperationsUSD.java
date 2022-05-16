package src;

import static Projects.P14_BankaProjesi.CustomerServices.transactionSummary;
import static Projects.P14_BankaProjesi.Menus.*;

public class OperationsUSD implements Operations {
    @Override
    public void balanceInquiry() {
        transactionSummary("USD Balance Inquiry", "1001");
        System.out.println("USD($) ACCOUNT BALANCE: ======> " + customersMap.get(loginId).getBalanceUSD() + " $, " );

    }

    @Override
    public void withdrawal() {
        transactionSummary("Sell USD", "1001");

        System.out.print("Please Enter Sell USD Quantity : ");
        double enterUsd = TryCatch.doubleGirisi();// Satmak istediği usd miktari kullanıcıdan isteniyor.
        double quantityTry = enterUsd * USD;// static final olan USD güncel kur bilgisi ile satmak istenilen USD çarpma yapılıyor ve kaç TL olduğu hesap ediliyor.


        if (customersMap.get(loginId).getBalanceUSD() >= enterUsd) { // usd hesabındaki miktar satmak için girilen miktardan büyük mü kontrol yapılıyor.
            customersMap.get(loginId).setBalanceUSD(customersMap.get(loginId).getBalanceUSD() - enterUsd);//usd hesabına yeni miktar set ediliyor.
            customersMap.get(loginId).setBalaceTRY(customersMap.get(loginId).getBalaceTRY()+quantityTry);
            System.out.println("Tebrikler "+enterUsd+" $ ile, "+ quantityTry+ " TL aldınız.");
        }else {
            System.out.println(" Bakiyeniz yeterli değil tekrar denemek için 1'e\nMenu'ye dönmek için 2'ye basınız.  ");
            if (TryCatch.intGirisi() == 1) {
                withdrawal();
            } else {
                selectForeignCurrency();
            }
        }
    }

    @Override
    public void deposit() {
        transactionSummary("Buy USD", "1001");

        System.out.print("Please Enter Buy USD Quantity : ");
        double enterUsd = TryCatch.doubleGirisi();// almak istediği usd miktari kullanıcıdan isteniyor.
        double quantityTry = enterUsd * USD;// static final olan USD güncel kur bilgisi ile almak istenilen USD çarpma yapılıyor kaç TL ihtiyaç var hesaplama yapılıyor

        if (customersMap.get(loginId).getBalaceTRY() >= quantityTry) { // try hesabındaki miktar usd alabilmek için yeterli ise kontrolü yapılıyor
            customersMap.get(loginId).setBalaceTRY(customersMap.get(loginId).getBalaceTRY() - quantityTry);//usd alabilmek için yeterli olan try hesabına yeni bakiye set ediliyor.
            customersMap.get(loginId).setBalanceUSD(enterUsd);// usd hesabına para eklendi.
            System.out.println("Tebrikler "+quantityTry+" TL ile, "+ enterUsd+ " $ aldınız.");
        }else {
            System.out.print(enterUsd + " $ almak için gerekli TL miktarı: " + quantityTry);
            System.out.println(" Bakiyeniz yeterli değil tekrar denemek için 1'e\nMenu'ye dönmek için 2'ye basınız.  ");
            if (TryCatch.intGirisi() == 1) {
                deposit();
            } else {
                selectForeignCurrency();
            }
        }



    }

    @Override
    public void moneyTransfer() {
        transactionSummary("USD Transfer", "1001");
        System.out.print("Please Enter Transfer USD Quantity : ");
        double enterUsd = TryCatch.doubleGirisi();// transfer etmek istenilen miktar giriliyor.
        System.out.print("Please Enter Iban : ");
        int iban = TryCatch.intGirisi();

        if (customersMap.get(loginId).getBalanceUSD() >= enterUsd) { // usd hesabındaki miktar transfer için girilen miktardan büyük mü kontrol yapılıyor.
            customersMap.get(loginId).setBalanceUSD(customersMap.get(loginId).getBalanceUSD() - enterUsd);//usd hesabına yeni miktar set ediliyor.
            System.out.println("Tebrikler "+iban+" nolu hesaba, "+ enterUsd+ " $ transfer ettiniz.");
        }else {
            System.out.println(" Bakiyeniz yeterli değil tekrar denemek için 1'e\nMenu'ye dönmek için 2'ye basınız.  ");
            if (TryCatch.intGirisi() == 1) {
                moneyTransfer();
            } else {
                selectForeignCurrency();
            }
        }

    }


}
