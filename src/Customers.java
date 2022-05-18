package src;

import java.util.Scanner;

import static src.Menus.B;
import static src.Menus.currentCustomer;

public class Customers {
    Scanner scanner = new Scanner(System.in);


    private String name;
    private String surname;
    private String idNumber ="" + Menus.mapKeyGenerator();
    private String password;
    private double balaceTRY;//hesap açılışında 0 olmalı zaten?
    private double balanceUSD;//hesap açılışında 0 olmalı zaten?
    private boolean debit;//construtr'da değiştirdim
    private String telephone;
    private int monthlyInCome;//constructor'da değiştirdim
    private double previousDebit;
    private String email;//Update işlemleri için ekledim
    private String address;//Update işlemleri için ekledim//previous debiti belli bir miktarın üstüne olan müşteriler kara listeye alınabilir. kara listede olan müşteriler credi başvursuna
                                // direkt ret yerler ve hesaplarındaki tüm para birimleri, o borcu kapatmak için otomatik olarak banka tarafından harcanır(FİKİR)

    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customers() {
    }

    public Customers(String name, String surname, String password, double balaceTRY, double balanceUSD, String debit, String telephone, int monthlyInCome) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.balaceTRY = balaceTRY;
        this.balanceUSD = balanceUSD;
        setDebit(debit);
        this.telephone = telephone;
        this.monthlyInCome=monthlyInCome;

    }
    public Customers(String name, String surname,  String password, double balaceTRY, double balanceUSD, boolean debit, String telephone, int monthlyInCome, String email,String address) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.balaceTRY = balaceTRY;
        this.balanceUSD = balanceUSD;
        this.debit = debit;
        setTelephone(telephone);
        this.monthlyInCome=monthlyInCome;
        this.email=email;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdNumber() {
        return idNumber;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {//müşteri şifrei 1 büyük harf 1 küçük harf ve 1 rakam içermeli, yoksa kabul etmeyen bi setPass()

        boolean lowerCaseControl=false;
        boolean upperCaseControl=false;
        boolean numberControl=false;

        for (int i=0; i<password.length() ; i++) {
            if(Character.isLowerCase(password.charAt(i)) ){
                lowerCaseControl=true;
                break;
            }
        }
        for (int i=0; i<password.length() ; i++) {
            if(Character.isUpperCase(password.charAt(i))){
                upperCaseControl=true;
                break;
            }
        }
        for (int i=0; i<password.length() ; i++) {
            if(Character.isDigit(password.charAt(i))){
                numberControl=true;
                break;
            }

        }

        if(lowerCaseControl && upperCaseControl && numberControl){
            this.password = password;
        } else {
            System.out.println("Invalid password combination. Password must contain\n" +
                    "-1 Uppercase Letter\n" +
                    "-1 Lowercase Letter\n" +
                    "-1 Number in it. Please set new password: ");
            setPassword(scanner.nextLine());
        }

    }

    public double getBalaceTRY() {
        return balaceTRY;
    }

    public void setBalaceTRY(double balaceTRY) {
        this.balaceTRY = balaceTRY;
    }

    public double getBalanceUSD() {
        return balanceUSD;
    }

    public void setBalanceUSD(double balanceUSD) {
        this.balanceUSD = balanceUSD;
    }

    public boolean isDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        if(debit.equalsIgnoreCase("y")){
            this.debit=true;
            System.out.println("The amount of previous credit loan: ");
            setPreviousDebit(TryCatch.intGirisi());//eğer önceden kapanmamış kredi borcu varsa bu borç ne kadar ?

        } else if(debit.equalsIgnoreCase("n")){
            this.debit=false;
        } else {
            System.out.println("Invalid debit info. You can only enter 'Y' for yes, and 'N' for no. Please enter credit info again: ");
            setDebit(scanner.nextLine());
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        while (telephone.length()!=11) {
            System.out.println("Phone number must be 11 digits");
            System.out.println(B + "NEW PHONE NUMBER");
            telephone=scanner.nextLine();
        }            this.telephone = telephone;
        }

    public int getMonthlyInCome() {
        return monthlyInCome;
    }

    public void setMonthlyInCome(int monthlyInCome) {// <0 girişi kontrol edilbilir

        this.monthlyInCome = monthlyInCome;
    }

    public double getPreviousDebit() {
        return previousDebit;
    }

    public void setPreviousDebit(double previousDebit) {
        this.previousDebit = previousDebit;
    }

    @Override
    public String toString() {
        return "Customers " +
                "name= " + name +
                ", surname= " + surname +
                ", idNumber= " + idNumber +
                ", password= " + password +
                ", balaceTRY= " + balaceTRY +
                ", balanceUSD= " + balanceUSD +
                ", debit= " + debit +
                ", telephone= " + telephone + '\'' +
                ", monthlyInCome= " + monthlyInCome +
                ", previousDebit= " + previousDebit;
    }
}
