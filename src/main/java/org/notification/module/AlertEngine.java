package org.notification.module;

import org.notification.module.entities.LogBean;
import org.notification.module.entities.UserForLog;
import org.notification.module.entities.User;
import org.notification.module.enums.CommunicationMode;
import org.notification.module.repository.UserForLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.notification.module.service.LogBeanService;
import org.notification.module.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/")
public class AlertEngine {

    @Autowired
    UserService userService;

    @Autowired
    LogBeanService logBeanService;

    @Autowired
    UserForLogRepository userForLogRepository;

    @Autowired
    InitializeUsers initializeUsers;

    @RequestMapping(value = "/")
    public void startAlertMechanism() {
        initializeUsers.initializeUsers();

        LogEvaluator logEvaluator = new LogEvaluator();
        logEvaluator.logMapUpdater();

        List<User> users = userService.getAllUsersWithSubscription();
        List<LogBean> logBeans = logBeanService.getAllLogBeans();
        List<UserForLog> userForLogList = userForLogRepository.findAll();

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (User user : users) {
            List<UserForLog> userAndLogMapping = userForLogList.stream()
                    .filter(userForLog -> userForLog.getUserId() == user.getId())
                    .collect(Collectors.toList());
            for (UserForLog userForLog : userAndLogMapping) {

                LogBean logBeanForUser = logBeanService.getLogBeanById(userForLog.getLogId()).get();

                List<CommunicationMode> communicationModes = logBeanForUser.getCommunicationModes();

                for (CommunicationMode communicationMode : communicationModes) {
                    LogEvaluator logEvaluatorForUser = new LogEvaluator(communicationMode, user, logBeanForUser);
                    executorService.submit(logEvaluatorForUser);
                }

            }


        }
    }
}
