package pl.touk.loggers.preso.rest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

public class BillingDto {

    @JsonDeserialize()
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
