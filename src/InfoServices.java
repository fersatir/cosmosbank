package src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class InfoServices {


    static Map<Integer, ExtractOfAccount> extAcc = new HashMap<>(); // Yasin --> Hesap Ekstre
    static int num = 1;

    public static void extractAccount(String action, double amount, String id) {

        ExtractOfAccount obj = new ExtractOfAccount();
        obj.setDate(LocalDate.now());
        obj.setTime(String.valueOf(LocalTime.now()));
        obj.setAction(action);
        obj.setAmount(amount);
        obj.setIdNumber(id);
        extAcc.put(num, obj);
        num++;

    }

    public static void exit() {

        String date = "Time";
        String action = "Action";
        String amount = "Amount";


        LocalDate date2 = LocalDate.now();
        LocalTime time2 = LocalTime.now();


        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("k:mm");
        String date3 = date2.format(dateFormat);
        String time3 = time2.format(timeFormat);


        System.out.println("\nCOSMOS BANK \nEXTRACT OF ACCOUNT______________________");
        System.out.printf("%40s", date3);
        System.out.println();

        System.out.printf("\n%-10s %-22s %-10s", date, action, amount);
        System.out.println("\n----------------------------------------");

        extList(extAcc);
        System.out.println("\n\n\n\n\n----------------------------------------");
        System.out.println("HAVE A GOOD DAY - WE WISH YOU AGAIN");

    }

    public static void extList(Map<Integer, ExtractOfAccount> extAcc) {
        Set<Map.Entry<Integer, ExtractOfAccount>> abc = extAcc.entrySet();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("k:m:s");
        for (Map.Entry<Integer, ExtractOfAccount> w : abc) {
            System.out.printf("%-10s %-22s %-10s", w.getValue().getTime().format(timeFormat), w.getValue().getAction(), w.getValue().getAmount());
            System.out.println();
        }
    }

}
