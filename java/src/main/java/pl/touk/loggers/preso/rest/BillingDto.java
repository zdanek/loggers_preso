package pl.touk.loggers.preso.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
