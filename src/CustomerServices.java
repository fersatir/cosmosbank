package src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static src.InfoServices.transactionSummary;
import static src.Menus.customersMap;
import static src.Menus.loginId;


public class CustomerServices {


    public static void addCustomer() {

    }

    public static void deleteCustomer() {

    }

    public static void swapAccount() {

    }

    public static void updateCustomer() {

    }



    public static void checkBalance() {
        System.out.println("TL hesap bakiyesi"+customersMap.get(loginId).getBalaceTRY());

        transactionSummary("TRY Balance Inquiry", loginId);


    }

    public static void withDraw() {

        transactionSummary("Withdraw Money", loginId);


    }


}
