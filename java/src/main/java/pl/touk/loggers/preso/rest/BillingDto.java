package pl.touk.loggers.preso.rest;

public class BillingDto {

    private int time;

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
