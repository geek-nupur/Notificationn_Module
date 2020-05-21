package org.notification.module.service;

import org.notification.module.entities.User;
import org.notification.module.enums.CommunicationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    /*
    @Autowired
    EmailAlertService emailALertService;

    @Autowired
    SMSAlertService smsAlertService;

    @Autowired
    PhoneALertService phoneALertService;

     */

    public void callAlertService(User user, CommunicationMode communicationMode) {

        EmailAlertService emailAlertService = new EmailAlertService();

        if (communicationMode.equals(CommunicationMode.EMAIL)) {
            emailAlertService.sendAlert(user.getEmail());
        } else if (communicationMode.equals(CommunicationMode.SMS)) {
            //to do
            //smsAlertService.sendAlert(user.getMobileNo());
        } else {
            // to do
            //phoneAlertService.sendAlert(user.getMobileNo());
        }


    }

}
