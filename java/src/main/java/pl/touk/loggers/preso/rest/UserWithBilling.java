package pl.touk.loggers.preso.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.touk.loggers.preso.domain.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserWithBilling {

    private long id;
    private String phoneNo;
    private List<BillingDto> billings;

    UserWithBilling(long id, String phoneNo, List<BillingDto> billings) {
        this.id = id;
        this.phoneNo = phoneNo;
        this.billings = billings;
    }

    public static UserWithBilling from(User user, List<BillingDto> billings) {
        return new UserWithBilling(user.getId(), user.getPhoneNo(), billings);
    }
}
