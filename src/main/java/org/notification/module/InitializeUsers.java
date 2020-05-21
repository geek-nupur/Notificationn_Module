package org.notification.module;

import org.notification.module.entities.LogBean;
import org.notification.module.entities.User;
import org.notification.module.entities.UserForLog;
import org.notification.module.enums.CommunicationMode;
import org.notification.module.enums.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.notification.module.repository.LogBeanRepository;
import org.notification.module.repository.UserForLogRepository;
import org.notification.module.repository.UserRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitializeUsers {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogBeanRepository logBeanRepository;

    @Autowired
    private UserForLogRepository userForLogRepository;

    public void initializeUsers() {

        User user1 = new User();
        user1.setName("nupur");
        user1.setEmail("nupurnitjsr5@gmail.com");
//        user1.setPassword("thisIsMe6#");
        user1.setMobileNo(new BigInteger("7829840398"));
        user1.setAlertSubscriptionTaken(true);
        user1 = userRepository.save(user1);

        List<CommunicationMode> communicationModes = new ArrayList<>();
//        communicationModes.add(CommunicationMode.SMS);
        communicationModes.add(CommunicationMode.EMAIL);


        LogBean logBeanForUser1 = new LogBean();
        logBeanForUser1.setLogType(LogType.Warning);
        logBeanForUser1.setDuration(10);
        logBeanForUser1.setFrequency(30);
        logBeanForUser1.setWaitTime(100);
        logBeanForUser1.setCommunicationModes(communicationModes);
        logBeanForUser1 = logBeanRepository.save(logBeanForUser1);

        UserForLog userForLog = new UserForLog();
        userForLog.setLogType(logBeanForUser1.getLogType());
        userForLog.setLogId(logBeanForUser1.getId());
        userForLog.setUserId(user1.getId());
        userForLogRepository.save(userForLog);

    }

}
