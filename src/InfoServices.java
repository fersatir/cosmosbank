package src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class InfoServices {
    static int numSummary = 1; //fatih log kaydı alırken işlem sırasını 1001'den başlatmak için kullanıldı.
    public static Map<Integer, InfoVariables> processSummary = new HashMap<>();//fatih log kayıtlarının tutulduğu map

    static Map<Integer, ExtractOfAccount> extAcc = new HashMap<>(); // Yasin --> Hesap Ekstresi Map
    static int num = 1;


    public static void extractAccount(String action, double amount, String id) { // Yasin --> Hesap Ekstresi metodu her para giriş çıkış işlemlerinde kullanılmalı.

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
    transactionSummary("Exit Success","1001");
        String date = "Time";
        String action = "Action";
        String amount = "Amount";

        String process = "Process";      //fatih tarafından oluşturuldu.
        String tarih = "Date - Time";   //fatih tarafından oluşturuldu.
        String islem = "P.No";          //fatih tarafından oluşturuldu.

        LocalDate date2 = LocalDate.now();
        LocalTime time2 = LocalTime.now();

        // Start --> Hesap Ekstresi Print Başlık Kısmı <-- // by Yasin
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String date3 = date2.format(dateFormat);

        System.out.println("\nCOSMOS BANK \nEXTRACT OF ACCOUNT______________________");
        System.out.printf("%40s", date3);
        System.out.println();

        System.out.printf("\n%-10s %-22s %-10s", date, action, amount);
        System.out.println("\n----------------------------------------");

        extList(extAcc);
        System.out.println("\n\n\n\n\n----------------------------------------");
        System.out.println("HAVE A GOOD DAY - WE WISH YOU AGAIN");
        // End --> Hesap Ekstresi Print Başlık Kısmı <-- // by Yasin


        System.out.println("\nCOSMOS BANK \nTRANSECTION SUMMARY______________________"); // bu kısım ve aşağıdaki kod bloğu fatih tarafından yapıldı. exit ile birlikte log kayıtlarını göstermek için
        System.out.printf("%40s", date3);
        System.out.println();
        System.out.printf("\n%-7s %-35s %-14s", islem,  tarih,  process);
        System.out.println("\n------------------------------------------------------------");
        transactionSummaryPrint(processSummary);
        System.out.println("\n----------------------------------------");
        System.out.println("HAVE A GOOD DAY - WE WISH YOU AGAIN");
        System.out.println("=====================================================================");

    }
    // Start --> Hesap Ekstresi Print içerik kısmı <-- // by Yasin
    public static void extList(Map<Integer, ExtractOfAccount> extAcc) {
        Set<Map.Entry<Integer, ExtractOfAccount>> abc = extAcc.entrySet();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("k:m:s");

        for (Map.Entry<Integer, ExtractOfAccount> w : abc) {
            System.out.printf("%-10s %-22s %-10s", w.getValue().getTime().format(timeFormat), w.getValue().getAction(), w.getValue().getAmount());
            System.out.println();
        }
    }
    // End --> Hesap Ekstresi Print içerik kısmı <-- // by Yasin

    public static void transactionSummary(String processName, String customerId) {// log alma işlemi yapan method, yapılan işlemin açıklaması ve müşteri id girilecek.
        InfoVariables summary = new InfoVariables();
        summary.setDate();
        summary.setTime();
        summary.setCustomerId(customerId);
        summary.setProcessName(processName);

        processSummary.put(numSummary, summary);
        numSummary++;

    }

    public static void transactionSummaryPrint(Map<Integer, InfoVariables> processSummary) {// log kayıtlarını yazdırma methodu
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("k:mm:ss");
        Set<Map.Entry<Integer, InfoVariables>> sumary = processSummary.entrySet();
        for (Map.Entry<Integer, InfoVariables> w :sumary ) {
            System.out.printf("%-5s %-10s %-22s %-10s",w.getKey(),w.getValue().date.format(dtf), w.getValue().time.format(dtf2), w.getValue().processName );
            System.out.println();
        }

    }

}
