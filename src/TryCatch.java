package src;

import java.util.InputMismatchException;
import java.util.Scanner;


public class TryCatch {
    static int giris;
    static double doubleGiris;
    static String kelime;
    static Scanner scanner = new Scanner(System.in);

    public static double doubleGirisi() {
        try {
            doubleGiris = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("enter digit");
            scanner.nextDouble();
            doubleGirisi();
        }
        return doubleGiris;
    }

        public static int intGirisi() {
            try {
                giris = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("sayi gir");
                scanner.next();
                intGirisi();
            }
            return giris;
        }

        public static String stringGirisi() {
            try {
                kelime = scanner.nextLine().toLowerCase();
                String kontrol = kelime.replaceAll("\\D", "");
                String kontrol2 = kelime.replaceAll("\\w", "");
                if (kontrol.length() != 0 || kontrol2.length() != 0) {
                    throw new InputMismatchException("harf disinda karakter yok");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                stringGirisi();
            }

            return kelime;
        }

    public static void threadSleep(int a){
        try {
            Thread.sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }







    }

