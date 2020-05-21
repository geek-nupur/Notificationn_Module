package org.notification.module.repository;

import org.notification.module.entities.LogBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LogBeanRepository extends JpaRepository<LogBean, Integer> {
}
