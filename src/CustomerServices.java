package src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static src.Menus.customersMap;
import static src.Menus.loginId;


public class CustomerServices {
    static int numSummary = 1001;


    public static Map<Integer, SummaryVeriables> processSummary = new HashMap<>();

    public static void addCustomer() {

    }

    public static void deleteCustomer() {

    }

    public static void swapAccount() {

    }

    public static void updateCustomer() {

    }

    public static void transactionSummary(String processName, String customerId) {// log alma işlemi yapan method, yapılan işlemin açıklaması ve müşteri id girilecek.
        SummaryVeriables summary = new SummaryVeriables();
        summary.setDate();
        summary.setTime();
        summary.setCustomerId(customerId);
        summary.setProcessName(processName);

        processSummary.put(numSummary, summary);
        numSummary++;

    }

    public static void defaultCustomer() {
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


    public static void exit() {
        String date = "Time";
        String action = "Action";
        String amount = "Amount";
        String process = "Process";
        String tarih = "Date - Time";
        String islem = "P.No";
        LocalDate date2 = LocalDate.now();
        LocalTime time2 = LocalTime.now();
        System.out.print("=====================================================================");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("k:mm");
        String date3 = date2.format(dateFormat);
        String time3 = time2.format(timeFormat);

        System.out.println("\nCOSMOS BANK \nTRANSECTION SUMMARY______________________");
        System.out.printf("%40s", date3);
        System.out.println();
        System.out.printf("\n%-7s %-35s %-14s", islem,  tarih,  process);
        System.out.println("\n------------------------------------------------------------");
        transactionSummaryPrint(processSummary);
        System.out.println("\n----------------------------------------");
        System.out.println("HAVE A GOOD DAY - WE WISH YOU AGAIN");
        System.out.println("=====================================================================");




    }

    public static void transactionSummaryPrint(Map<Integer, SummaryVeriables> processSummary) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("k:mm:ss");
        Set<Map.Entry<Integer,SummaryVeriables>> sumary = processSummary.entrySet();
        for (Map.Entry<Integer,SummaryVeriables> w :sumary ) {
            System.out.printf("%-5s %-10s %-22s %-10s",w.getKey(),w.getValue().date.format(dtf), w.getValue().time.format(dtf2), w.getValue().processName );
            System.out.println();
        }


    }
}
