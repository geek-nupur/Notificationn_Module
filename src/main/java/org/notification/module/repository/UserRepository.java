package org.notification.module.repository;

import org.notification.module.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByIsAlertSubscriptionTaken(boolean isAlertSubscriptionTaken);
}
