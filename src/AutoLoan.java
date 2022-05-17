package src;


import static src.Menus.currentCustomer;

public class AutoLoan implements Credits {
public void testMethod(){}
    @Override
    public void kredi() {
        System.out.println("Auto Loan prerequisite is;\nYour monthly income must be " +
                "5000.0 TRY or higher. Our auto loan packages are as follows: \n" +
                "1- 15.000 TRY Auto Loan with monthly 1.8% interest rate\n" +
                "2- 20.000 TRY Auto Loan with monthly 1.5% interest rate\n" +
                "3- 25.000 TRY Auto Loan with monthly 1.2% interest rate");
        if (currentCustomer.getMonthlyInCome() < 5000) {
            System.out.println("You are not suitable for auto loan credit since " +
                    "you dont have enough monthly income for the auto loan prerequisite. Your " +
                    "monthly income is: " + currentCustomer.getMonthlyInCome() + " TRY");
        } else {
            System.out.println("Please select one of our auto loan packages: ");
            switch (TryCatch.intGirisi()) {
                case 1:
                    currentCustomer.setBalaceTRY(currentCustomer.getBalaceTRY() + 15000);
                    System.out.println("Success! 15.000 TRY Auto Loan Credit has been transferred to your account. Your curren account balance is " + currentCustomer.getBalaceTRY());
                    currentCustomer.setPreviousDebit(currentCustomer.getPreviousDebit() + 15000 * 1.8);// ödenmemiş kredi borçlarına eklendi
                    break;
                case 2:
                    currentCustomer.setBalaceTRY(currentCustomer.getBalaceTRY() + 20000);
                    System.out.println("Success! 20.000 TRY Auto Loan Credit has been transferred to your account. Your curren account balance is " + currentCustomer.getBalaceTRY());
                    currentCustomer.setPreviousDebit(currentCustomer.getPreviousDebit() + 35000 * 1.5);// ödenmemiş kredi borçlarına eklendi
                    break;
                case 3:
                    currentCustomer.setBalaceTRY(currentCustomer.getBalaceTRY() + 25000);
                    System.out.println("Success! 25.000 TRY Auto Loan Credit has been transferred to your account. Your curren account balance is " + currentCustomer.getBalaceTRY());
                    currentCustomer.setPreviousDebit(currentCustomer.getPreviousDebit() + 50000 * 1.2);// ödenmemiş kredi borçlarına eklendi
                    break;
                default:
                    System.out.println("Invalid Auto Loan package. Please try again:");
                    kredi();
            }
        }
    }

   
}
