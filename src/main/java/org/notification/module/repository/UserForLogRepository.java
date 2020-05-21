package org.notification.module.repository;

import org.notification.module.entities.UserForLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserForLogRepository extends JpaRepository<UserForLog, Integer> {
}
