package pl.touk.loggers.preso.rest;

import java.time.LocalDateTime;

public class BillingDto {

    private LocalDateTime time;

    private String connectionParty;

    private int connectionLength;

    @Override
    public String toString() {
        return "Billing{" +
                "time=" + time +
                ", connectionParty='" + connectionParty + '\'' +
                ", connectionLength=" + connectionLength +
                '}';
    }
}
