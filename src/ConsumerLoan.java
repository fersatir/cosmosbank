package src;


import static src.Menus.currentCustomer;

public class ConsumerLoan implements Credits {
    @Override
    public void kredi() {
        System.out.println("Consumer Loan prerequisite is;\nYour monthly income must be " +
                "2000.0 TRY or higher. Our Consumer Loan packages are as follows: \n" +
                "1- 7.000 TRY Consumer Loan with monthly 1.1% interest rate\n" +
                "2- 10.000 TRY Consumer Loan with monthly 0.9% interest rate\n" +
                "3- 12.000 TRY Consumer Loan with monthly 0.7% interest rate");
        if (currentCustomer.getMonthlyInCome() < 3000) {
         
            System.out.println("You are not suitable for Consumer Loan credit since " +
                    "you dont have enough monthly income for the Consumer Loan prerequisite. Your " +
                    "monthly income is: " + currentCustomer.getMonthlyInCome() + " TRY");
        } else {
            System.out.println("Please select one of our Consumer Loan packages: ");
            switch (TryCatch.intGirisi()) {
                case 1:
                    currentCustomer.setBalaceTRY(currentCustomer.getBalaceTRY() + 7000);
                    System.out.println("Success! 7.000 TRY Consumer Loan Credit has been transferred to your account. Your curren account balance is " + currentCustomer.getBalaceTRY());
                    currentCustomer.setPreviousDebit(currentCustomer.getPreviousDebit() + 7000 * 1.1);// ödenmemiş kredi borçlarına eklendi
                    break;
                case 2:
                    currentCustomer.setBalaceTRY(currentCustomer.getBalaceTRY() + 10000);
                    System.out.println("Success! 10.000 TRY Consumer Loan Credit has been transferred to your account. Your curren account balance is " + currentCustomer.getBalaceTRY());
                    currentCustomer.setPreviousDebit(currentCustomer.getPreviousDebit() + 10000 * 0.9);// ödenmemiş kredi borçlarına eklendi
                    break;
                case 3:
                    currentCustomer.setBalaceTRY(currentCustomer.getBalaceTRY() + 12000);
                    System.out.println("Success! 12.000 TRY Consumer Loan Credit has been transferred to your account. Your curren account balance is " + currentCustomer.getBalaceTRY());
                    currentCustomer.setPreviousDebit(currentCustomer.getPreviousDebit() + 12000 * 0.7);// ödenmemiş kredi borçlarına eklendi
                    break;
                default:
                    System.out.println("Invalid Consumer Loan package. Please try again:");
                    kredi();
            }

        }
    }
}
