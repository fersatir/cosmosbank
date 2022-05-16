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



    public static void defaultCustomer() { // default customer oluşturmak için oluşturuldu silinecek
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
