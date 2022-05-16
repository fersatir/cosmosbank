package src;

import java.time.LocalDate;
import java.time.LocalTime;

public class InfoVariables {

    String customerId;
    LocalDate date;
    LocalTime time;
    String processName;

    public InfoVariables() {
    }

    public InfoVariables(String customerId, LocalDate date, LocalTime time, String processName) {
        this.customerId = customerId;
        this.date = date;
        this.time = time;
        this.processName = processName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate() {

        this.date = LocalDate.now();
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime() {
        this.time = LocalTime.now();
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
