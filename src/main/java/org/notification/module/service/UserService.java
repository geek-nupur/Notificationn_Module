package org.notification.module.service;

import org.notification.module.entities.User;
import org.notification.module.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> getAllUsersWithSubscription() {
        return userRepository.findByIsAlertSubscriptionTaken(true);
    }

}
