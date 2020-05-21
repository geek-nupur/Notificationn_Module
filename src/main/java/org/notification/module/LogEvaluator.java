package org.notification.module;

import org.notification.module.entities.LogBean;
import org.notification.module.entities.User;
import org.notification.module.enums.CommunicationMode;
import org.notification.module.enums.LogType;
import org.notification.module.service.AlertService;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;

public class
LogEvaluator implements Runnable {

    static ConcurrentHashMap<LogType, Integer> logMap = new ConcurrentHashMap<>();

    private CommunicationMode communicationMode;
    private User user;
    private LogBean logBean;


    LogEvaluator() {
    }

    LogEvaluator(CommunicationMode communicationMode, User user, LogBean logBean) {
        this.communicationMode = communicationMode;
        this.user = user;
        this.logBean = logBean;
    }

    /*
    this function has to keep running from the time the application starts till it stops
    so we need to create a separate running thread for this function
     */
    public void logMapUpdater() {

        logMap.put(LogType.Warning, 0);
        logMap.put(LogType.Critical, 0);
        logMap.put(LogType.Blocker, 0);
        logMap.put(LogType.Info, 0);

        File logFile = new File("logfile");

        int logCount = 0;

//        FileInputStream
//        FileChannel.MapMode
//        AsynchronousFileChannel


        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader("/Users/nupursinghal/Documents/UserLogALerts/src/main/java/logfile"));
//            Scanner scanner = new Scanner(logFile);
            while (true) {
                String fileLogLine = reader.readLine();
                if (fileLogLine == null) {
//                    Thread.sleep(2000);
                    break;
                }

                String first18CharactersOfLine = fileLogLine.substring(0, 18);
                boolean isTimeStamp = isTimeStamp(first18CharactersOfLine);
                if (isTimeStamp) {
                    switch (fileLogLine.charAt(20)) {
                        case 'W':
                            logCount = logMap.get(LogType.Warning);
                            logMap.put(LogType.Warning, logCount + 1);

                        case 'I':
                            logCount = logMap.get(LogType.Info);
                            logMap.put(LogType.Info, logCount + 1);
                        case 'C':
                            logCount = logMap.get(LogType.Critical);
                            logMap.put(LogType.Critical, logCount + 1);
                        case 'B':
                            logCount = logMap.get(LogType.Blocker);
                            logMap.put(LogType.Blocker, logCount + 1);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            // put a logger here
            System.out.println(e);
        } catch (IOException e) {

            System.out.println(e);
        }

    }

    private boolean isTimeStamp(String first18CharactersOfLine) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            simpleDateFormat.parse(first18CharactersOfLine);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /*
    this is the method which should run for all the user's communication modes that have subscribed to the logs
    alerts
    ex :if we have 2 users
    user1 nd user tww and user 1 has 1 communication mode : sms
     user2 has 2 communication modes : sms and email
     then we need 3 threads : 1 thread for user 1 (for its sms aler service)
     and 2 threads for user2 , 1 thread for each alert service(1 thead for sms service and 1 for email service)
     */
    public void evaluateLogs(CommunicationMode communicationMode, User user, LogBean logBean) {
        int lastLogCount = 0;
        int currentLogCount = 0;
        while (true) {
            try {
                Thread.sleep(logBean.getDuration());
                currentLogCount = logMap.get(logBean.getLogType());
                if ((currentLogCount - lastLogCount) > logBean.getFrequency()) {
                    // send alert
                    AlertService alertService = new AlertService();
                    alertService.callAlertService(user, communicationMode);

                    lastLogCount = currentLogCount;
                }
                Thread.sleep(logBean.getWaitTime());
                lastLogCount = logMap.get(logBean.getLogType());
            } catch (InterruptedException e) {
                // log the error
            }

        }

    }


    @Override
    public void run() {
        evaluateLogs(communicationMode, user, logBean);
    }
}
