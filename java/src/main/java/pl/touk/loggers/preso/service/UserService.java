package pl.touk.loggers.preso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.touk.loggers.preso.domain.User;
import pl.touk.loggers.preso.repository.UserRepository;
import pl.touk.loggers.preso.rest.BillingDto;
import pl.touk.loggers.preso.rest.UserWithBilling;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserRepository userRepository;
    private BillingService billingService;

    UserService(UserRepository userRepository, BillingService billingService) {
        this.userRepository = userRepository;
        this.billingService = billingService;
    }
/*
    public void updateUserData(long userId, String data) {

        Optional<User> optUser = userRepository.findById(userId);

        if (optUser.isPresent()) {
            User user = optUser.get();

//            BillingData billingDataObj = new BillingData();
//            billingDataObj.setData(data);
////            user.getData().add(billingDataObj);
        }
    }
*/
    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    public UserWithBilling getUserWithBilling(String phoneNo) {

        logger.debug("Getting billing for [{}]", phoneNo);

        User user = getUserByPhoneNo(phoneNo);

        List<BillingDto> billing = billingService.getBilling(user.getPhoneNo());

        UserWithBilling userWithBiling = UserWithBilling.from(user, billing);

        return userWithBiling;
    }

    public User getUserByPhoneNo(String phoneNo) {
        return userRepository.findByPhoneNo(phoneNo).orElseThrow(() ->
                new RuntimeException(String.format("User with phoneNo [%s] not found", phoneNo)));
    }
}
