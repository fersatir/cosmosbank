package src;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExtractOfAccount {

    private String idNumber;
    private LocalDate date;
    private LocalTime time;
    private String action;
    private double amount;


    public ExtractOfAccount() {
    }

    public ExtractOfAccount(String action, double amount) {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.action = action;
        this.amount = amount;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate dt) {
        this.date = LocalDate.now();
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(String tm) {
        this.time =LocalTime.now();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ExtractOfAccount{" +
                "dt=" + date +
                ", tm=" + time +
                ", action='" + action + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
