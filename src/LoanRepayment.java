package src;


import java.time.LocalDateTime;

import static src.Menus.currentCustomer;
import static src.OperationsTRY.creditsTransaction;


public class LoanRepayment implements Credits {
    @Override
    public void kredi() {
        boolean hasDebit = currentCustomer.isDebit();

        if(!hasDebit){
            System.out.println("You dont have any previous unpaid loans on your account. Redirecting to credit transactions...");
            TryCatch.threadSleep(1000);
            creditsTransaction();
        }else {
            System.out.println("Press 1 to pay the amount you decide\nPress 2 to create automatic monthly payment order ");//ayın her 1.inde çekicek hesaptan
            int selection =TryCatch.intGirisi();
            if(selection == 1){
                System.out.println("Please enter the amount you want to pay");
                int amountPayable = TryCatch.intGirisi();
                if(amountPayable<=0){
                    System.out.println("You must enter an amount greater than zero. Redirecting you to Loan Repayment");
                    kredi();
                } else {
                    if(currentCustomer.getBalaceTRY()<amountPayable){
                        System.out.println("You have insufficient balance on your TRY account. Redirecting you to Loan Repayment");
                        kredi();
                    } else {
                        currentCustomer.setPreviousDebit(currentCustomer.getPreviousDebit()-amountPayable);
                        //TRY balance  - amountPayable
                        System.out.println("Payment successful. Your current value of previous unpaid credits is: "+ currentCustomer.getPreviousDebit()+" TRY");}
                }

            } else if ( selection == 2){// her ayın 1'inde TRYbalance hesabında bakiye varsa previousDebit'ten düşecek 1/4

                LocalDateTime tarihSaat= LocalDateTime.now();
                System.out.println("Your previous debit amount will be divived into 4 installments.");

                while(currentCustomer.getPreviousDebit()>0){ //borcu sıfırdan büyükse
                    if(currentCustomer.getBalaceTRY()> currentCustomer.getPreviousDebit()/4 ) {//hesabında taksidin 1/4'ünü ödeyecek kadar para varsa alsın
                        if (tarihSaat.getDayOfMonth() == 1) {
                            currentCustomer.setPreviousDebit(currentCustomer.getPreviousDebit() - (currentCustomer.getPreviousDebit() / 4));//double olduğu için *0.75 yapmadım
                        }
                    } else {
                        System.out.println("You have insufficient funds in your TRY account to pay your monthly installment");
                    }
                }



            } else {
                System.out.println("Invalid selection. Redirecting you to Loan Repayment...");
                TryCatch.threadSleep(1000);
                kredi();
            }
        }
    }
}
